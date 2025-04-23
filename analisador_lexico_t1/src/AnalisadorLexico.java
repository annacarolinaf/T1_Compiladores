package src;
import java.io.*;
import java.util.*;

public class AnalisadorLexico {

    private static final Set<String> palavrasReservadas = new HashSet<>(Arrays.asList(
            "algoritmo", "declare", "constante", "tipo",
            "literal", "inteiro", "real", "logico", "verdadeiro", "falso",
            "registro", "fim_registro", "procedimento", "fim_procedimento",
            "funcao", "fim_funcao", "var", "leia", "escreva", "se", "entao",
            "senao", "fim_se", "caso", "seja", "fim_caso", "para", "ate",
            "faca", "fim_para", "enquanto", "fim_enquanto", "retorne", "nao",
            "fim_algoritmo", "e", "ou"));

    private static final Set<Character> simbolos = new HashSet<>(Arrays.asList(
            ':', '(', ')', ',', '.', '[', ']', '^', '&'));

    private static final Set<Character> operadores = new HashSet<>(Arrays.asList(
            '+', '-', '*', '/', '%', '=', '<', '>'));

    public static List<Token> analisar(String codigo) {
        List<Token> tokens = new ArrayList<>();
        int i = 0;
        int linha = 1;

        while (i < codigo.length()) {
            char c = codigo.charAt(i);

            if (c == '\n') {
                linha++;
            }

            if (Character.isWhitespace(c)) {
                i++;
                continue;
            }

            // Comentários { ... }
            if (c == '{') {
                int linhaComentario = linha;
                i++;
                boolean fechado = false;

                while (i < codigo.length()) {
                    char atual = codigo.charAt(i);
                    if (atual == '\n') {
                        tokens.add(Token.erro("Linha " + linhaComentario + ": comentario nao fechado"));
                        return tokens;
                    }
                    if (atual == '}') {
                        i++;
                        fechado = true;
                        break;
                    }
                    i++;
                }

                if (!fechado) {
                    tokens.add(Token.erro("Linha " + linhaComentario + ": comentario nao fechado"));
                    return tokens;
                }

                continue;
            }

            

            // Cadeia de caracteres
            if (c == '"') {
                StringBuilder cadeia = new StringBuilder();
                cadeia.append(c);
                i++;

                while (i < codigo.length() && codigo.charAt(i) != '"') {
                    cadeia.append(codigo.charAt(i));
                    i++;
                    
                }

                if (i >= codigo.length() || codigo.charAt(i) == ',' || codigo.charAt(i) == '\n') {
                    tokens.add(Token.erro("Linha " + linha + ": cadeia literal nao fechada"));
                    return tokens;
                }

                cadeia.append('"');
                i++;
                tokens.add(new Token(cadeia.toString(), TipoToken.CADEIA));
                continue;
            }

            // Identificadores e palavras reservadas
            if (Character.isLetter(c) || c == '_') {
                StringBuilder palavra = new StringBuilder();
                while (i < codigo.length() &&
                        (Character.isLetterOrDigit(codigo.charAt(i)) || codigo.charAt(i) == '_')) {
                    palavra.append(codigo.charAt(i));
                    i++;
                }
                String lexema = palavra.toString();
                if (palavrasReservadas.contains(lexema)) {
                    tokens.add(new Token(lexema, TipoToken.PALAVRA_RESERVADA));
                } else {
                    tokens.add(new Token(lexema, TipoToken.IDENT));
                }
                continue;
            }

            // Números inteiros e reais
            if (Character.isDigit(c)) {
                StringBuilder numero = new StringBuilder();
                boolean temPonto = false;

                while (i < codigo.length() &&
                        (Character.isDigit(codigo.charAt(i)) || codigo.charAt(i) == '.')) {

                    if (codigo.charAt(i) == '.') {
                        // Verificar se o ponto é parte de um intervalo ".."
                        if (i + 1 < codigo.length() && codigo.charAt(i + 1) == '.') {
                            // Detecta o intervalo ".."
                            tokens.add(new Token(numero.toString(), TipoToken.NUM_INT)); // Número antes do intervalo
                            tokens.add(new Token("..", TipoToken.OPERADOR)); // Token para o operador de intervalo
                            i += 2; // Avança para o próximo número após ".."
                            continue; // Avança para a próxima iteração sem processar o número após o intervalo
                        } else if (i + 1 < codigo.length() && Character.isDigit(codigo.charAt(i + 1))) {
                            temPonto = true; // O ponto é parte de um número real
                        }
                    }

                    numero.append(codigo.charAt(i));
                    i++;
                }

                if (temPonto) {
                    tokens.add(new Token(numero.toString(), TipoToken.NUM_REAL));
                } else {
                    tokens.add(new Token(numero.toString(), TipoToken.NUM_INT));
                }
                continue;
            }

            // Símbolos
            if (simbolos.contains(c)) {
                tokens.add(new Token(String.valueOf(c), TipoToken.SIMBOLO));
                i++;
                continue;
            }

            // Operadores
            // Verificação de operadores compostos (<=, >=, <>, <-, =>)
            if (i + 1 < codigo.length()) {
                if (codigo.charAt(i) == '<' && codigo.charAt(i + 1) == '-') {
                    tokens.add(new Token("<-", TipoToken.OPERADOR));
                    i += 2; // Avança dois caracteres
                    continue;
                } else if (codigo.charAt(i) == '<' && codigo.charAt(i + 1) == '>') {
                    tokens.add(new Token("<>", TipoToken.OPERADOR));
                    i += 2;
                    continue;
                } else if (codigo.charAt(i) == '=' && codigo.charAt(i + 1) == '>') {
                    tokens.add(new Token("=>", TipoToken.OPERADOR));
                    i += 2;
                    continue;
                } else if (codigo.charAt(i) == '<' && codigo.charAt(i + 1) == '=') {
                    tokens.add(new Token("<=", TipoToken.OPERADOR));
                    i += 2;
                    continue;
                } else if (codigo.charAt(i) == '>' && codigo.charAt(i + 1) == '=') {
                    tokens.add(new Token(">=", TipoToken.OPERADOR));
                    i += 2;
                    continue;
                }
            }

            // Operadores simples (+, -, *, /, %, =, <, >)
            if (operadores.contains(c)) {
                tokens.add(new Token(String.valueOf(c), TipoToken.OPERADOR));
                i++;
                continue;
            }

            // Caractere inválido
            tokens.add(Token.erro("Linha " + linha + ": " + c + " - simbolo nao identificado"));
            return tokens;
        }

        return tokens;
    }
}
