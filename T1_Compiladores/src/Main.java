import java.io.*;
import java.util.List;

/**
 * Classe principal responsável por executar o analisador léxico.
 * 
 * Essa classe lê um arquivo de entrada contendo o código-fonte,
 * executa a análise léxica sobre ele e grava os tokens gerados em um arquivo de saída.
 *
 * Uso esperado:
 *     java -jar analisador.jar <arquivo_entrada> <arquivo_saida>
 */
public class Main {
    public static void main(String[] args) {
        // Verifica se o número de argumentos está correto
        if (args.length != 2) {
            System.out.println("Uso: java -jar analisador.jar <arquivo_entrada> <arquivo_saida>");
            return;
        }

        String caminhoEntrada = args[0]; // Caminho do arquivo de entrada
        String caminhoSaida = args[1]; // Caminho do arquivo de saída

        try {
            // Lê todo o conteúdo do arquivo de entrada
            StringBuilder conteudo = new StringBuilder();
            BufferedReader leitor = new BufferedReader(new FileReader(caminhoEntrada));
            String linha;
            while ((linha = leitor.readLine()) != null) {
                conteudo.append(linha).append("\n"); // Preserva quebras de linha
            }
            leitor.close();

            // Executa a análise léxica no conteúdo lido
            List<Token> tokens = AnalisadorLexico.analisar(conteudo.toString());

            // Escreve os tokens no arquivo de saída
            BufferedWriter escritor = new BufferedWriter(new FileWriter(caminhoSaida));
            for (Token token : tokens) {
                escritor.write(token.toString());
                escritor.newLine(); // Cada token em uma linha
            }
            escritor.close();

        } catch (IOException e) {
            // Trata erros de leitura ou escrita de arquivos
            System.err.println("Erro ao ler ou escrever arquivo: " + e.getMessage());
        }
    }
}
