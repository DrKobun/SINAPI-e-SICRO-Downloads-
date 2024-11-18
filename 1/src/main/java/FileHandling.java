import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileHandling {

    public static void main(String[] args) 
    {
        
        String diretorioBase = "C:\\Users\\walyson.ferreira\\Desktop\\Relatório Sintético de Equipamentos"; // Substitua pelo caminho desejado

        
        String[][] estados = 
        {
            {"AC", "Acre"},
            {"AL", "Alagoas"},
            {"AP", "Amapá"},
            {"AM", "Amazonas"},
            {"BA", "Bahia"},
            {"CE", "Ceará"},
            {"DF", "Distrito Federal"},
            {"ES", "Espírito Santo"},
            {"GO", "Goiás"},
            {"MA", "Maranhão"},
            {"MT", "Mato Grosso"},
            {"MS", "Mato Grosso do Sul"},
            {"MG", "Minas Gerais"},
            {"PA", "Pará"},
            {"PB", "Paraíba"},
            {"PR", "Paraná"},
            {"PE", "Pernambuco"},
            {"PI", "Piauí"},
            {"RJ", "Rio de Janeiro"},
            {"RN", "Rio Grande do Norte"},
            {"RS", "Rio Grande do Sul"},
            {"RO", "Rondônia"},
            {"RR", "Roraima"},
            {"SC", "Santa Catarina"},
            {"SP", "São Paulo"},
            {"SE", "Sergipe"},
            {"TO", "Tocantins"}
        };

        try 
        {
            // Criar diretórios para cada estado
            for (String[] estado : estados) 
            {
                String sigla = estado[0];
                String nome = estado[1];
                String nomePasta = sigla + " - " + nome;

                // Caminho completo da nova pasta
                Path caminhoPasta = Paths.get(diretorioBase, nomePasta);

                // Criar a pasta
                Files.createDirectories(caminhoPasta);
                System.out.println("Criada pasta: " + caminhoPasta);
            }
        } catch (IOException e) 
        {
            System.err.println("Ocorreu um erro: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
