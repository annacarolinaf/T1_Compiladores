import java.io.*;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Uso: java -jar analisador.jar <arquivo_entrada> <arquivo_saida>");
            return;
        }

        String caminhoEntrada = args[0];
        String caminhoSaida = args[1];

        try {
            StringBuilder conteudo = new StringBuilder();
            BufferedReader leitor = new BufferedReader(new FileReader(caminhoEntrada));
            String linha;
            while ((linha = leitor.readLine()) != null) {
                conteudo.append(linha).append("\n");
            }
            leitor.close();

            List<Token> tokens = AnalisadorLexico.analisar(conteudo.toString());

            BufferedWriter escritor = new BufferedWriter(new FileWriter(caminhoSaida));
            for (Token token : tokens) {
                escritor.write(token.toString());
                escritor.newLine();
            }
            escritor.close();

        } catch (IOException e) {
            System.err.println("Erro ao ler ou escrever arquivo: " + e.getMessage());
        }
    }
}