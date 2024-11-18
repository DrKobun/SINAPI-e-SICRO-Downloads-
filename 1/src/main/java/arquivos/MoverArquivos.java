package arquivos;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.FileVisitResult;

public class MoverArquivos 
{


    public static void main(String[] args) 
    {
        // Caminho da pasta onde procurar os arquivos
        String sourceDirectory = "C:\\Users\\walyson.ferreira\\Desktop\\Relatório Sintético de Materiais"; // Substitua pelo caminho da pasta de origem
        // Caminho da pasta onde mover os arquivos
        String targetDirectory = "C:\\Users\\walyson.ferreira\\Desktop\\Relatório Sintético de Materiais\\pdf"; // Substitua pelo caminho da pasta de destino
        // Nome do arquivo a ser procurado
        String fileNameToFind = ".pdf"; // Substitua pelo nome do arquivo a ser procurado

        try 
        {
            moveFiles(sourceDirectory, targetDirectory, fileNameToFind);
        } 
        catch (IOException e) 
        {
            System.err.println("Ocorreu um erro: \n" + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void moveFiles(String sourceDir, String targetDir, String fileNameToFind) throws IOException 
    {
        Path sourcePath = Paths.get(sourceDir);
        Path targetPath = Paths.get(targetDir);

        // Verifica se a pasta de destino existe, se não, cria
        if(!Files.exists(targetPath)) 
        {
            Files.createDirectories(targetPath);
        }

        // Percorre a árvore de diretórios e move os arquivos encontrados
        Files.walkFileTree(sourcePath, new SimpleFileVisitor<Path>() 
        {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException 
            {
                if (file.getFileName().toString().contains(fileNameToFind))
                {
                    Path targetFilePath = targetPath.resolve(file.getFileName());
                    Files.move(file, targetFilePath, StandardCopyOption.REPLACE_EXISTING);
                    System.out.println("Movido: " + file + " para " + targetFilePath);
                }
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFileFailed(Path file, IOException e) throws IOException 
            {
                System.err.println("Falha ao visitar o arquivo: " + file + " - " + e.getMessage());
                return FileVisitResult.CONTINUE;
            }
        });
    }
}
