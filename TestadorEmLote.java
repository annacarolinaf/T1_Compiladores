import java.io.*;
import java.util.*;

public class TestadorEmLote {
    public static void main(String[] args) {
        File pastaDeTestes = new File("1.casos_teste_t1/entrada"); // pasta com os arquivos de entrada
        File pastaSaida = new File("1.casos_teste_t1/saida_anna"); // diretório para salvar os arquivos de saída


        // Obtém todos os arquivos .txt na pasta de testes
        File[] arquivos = pastaDeTestes.listFiles((dir, nome) -> nome.endsWith(".txt"));

        if (arquivos == null || arquivos.length == 0) {
            System.out.println("Nenhum arquivo de teste encontrado.");
            return;
        }

        // Processa cada arquivo de teste
        for (File arquivo : arquivos) {
            // Lê o conteúdo do arquivo
            String codigo = lerArquivo(arquivo);

            // Analisa o código
            List<Token> tokens = AnalisadorLexico.analisar(codigo);

            // Cria um arquivo de saída no diretório "saida_anna"
            File arquivoSaida = new File(pastaSaida, arquivo.getName());

            try (BufferedWriter escritor = new BufferedWriter(new FileWriter(arquivoSaida))) {
                // Escreve os tokens no arquivo de saída
                for (Token token : tokens) {
                    escritor.write(token.toString());
                    escritor.newLine();
                }
                System.out.println("Resultados gravados no arquivo: " + arquivoSaida.getAbsolutePath());
            } catch (IOException e) {
                e.printStackTrace(); // Apenas imprime a exceção caso ocorra
            }
        }
    }

    // Método para ler o conteúdo de um arquivo
    private static String lerArquivo(File arquivo) {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader leitor = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            while ((linha = leitor.readLine()) != null) {
                sb.append(linha).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace(); // Apenas imprime a exceção caso ocorra
        }
        return sb.toString();
    }
}
