import java.io.*;
import java.nio.file.*;
import java.util.*;

public class ComparadorDeArquivos {
    public static void main(String[] args) {
        File pastaSaida = new File("1.casos_teste_t1/saida_anna"); // Diretório com seus arquivos de saída
        File pastaSaidaOriginal = new File("1.casos_teste_t1/saida"); // Diretório com os arquivos de saída originais
        
        // Obtém todos os arquivos .out da pasta de saída
        File[] arquivosSaida = pastaSaida.listFiles((dir, nome) -> nome.endsWith(".txt"));
        File[] arquivosSaidaOriginal = pastaSaidaOriginal.listFiles((dir, nome) -> nome.endsWith(".txt"));

        // Verifica se o número de arquivos é o mesmo
        if (arquivosSaida.length != arquivosSaidaOriginal.length) {
            System.out.println("O número de arquivos de saída não é o mesmo.");
            return;
        }

        // Para cada arquivo de saída gerado, procura o arquivo correspondente no diretório original
        for (File arquivoSaida : arquivosSaida) {
            File arquivoOriginal = new File(pastaSaidaOriginal, arquivoSaida.getName());

            // Verifica se o arquivo correspondente existe na pasta original
            if (!arquivoOriginal.exists()) {
                System.out.println("Arquivo não encontrado no diretório original: " + arquivoSaida.getName());
                continue;
            }

            // Compara os conteúdos dos dois arquivos
            try {
                String conteudoSaida = lerArquivo(arquivoSaida);
                String conteudoOriginal = lerArquivo(arquivoOriginal);

                if (conteudoSaida.equals(conteudoOriginal)) {
                } else {
                    System.out.println(arquivoSaida.getName() + " - Arquivos são diferentes.");
                }
            } catch (IOException e) {
                System.err.println("Erro ao ler os arquivos: " + e.getMessage());
            }
        }
    }

    // Método para ler o conteúdo de um arquivo
    private static String lerArquivo(File arquivo) throws IOException {
        return new String(Files.readAllBytes(arquivo.toPath()));
    }
}
