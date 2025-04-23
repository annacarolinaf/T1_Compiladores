/**
 * Enumeração que representa os diferentes tipos de tokens que o analisador léxico pode reconhecer.
 */
public enum TipoToken {

 
    // Palavras reservadas da linguagem (ex: "algoritmo", "declare", "fim_algoritmo")
    PALAVRA_RESERVADA,
 
    // Identificadores (ex: nomes de variáveis)
    IDENT,
 
    // Cadeias de caracteres entre aspas
    CADEIA,
 
    // Números inteiros
    NUM_INT,
 
    // Números reais/decimais
    NUM_REAL,
 
    // Operadores
    OPERADOR,
 
    // Símbolos especiais
    SIMBOLO,
 
    // Fim de arquivo (usado para indicar que não há mais caracteres a ler)
    EOF,
 
    // Token inválido ou erro léxico detectado
    ERRO
 

}
