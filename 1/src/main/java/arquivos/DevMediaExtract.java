package arquivos;
import org.apache.commons.compress.archivers.zip.*;
import org.apache.commons.compress.utils.IOUtils;
import java.io.*;
import java.nio.file.*;
import java.nio.charset.StandardCharsets;

public class DevMediaExtract 
{

    public static void main(String[] args) throws IOException 
    {
        
        String origem = "C:\\Users\\walyson.ferreira\\Downloads\\teste.zip";
        // C:\\Users\\walyson.ferreira\\Downloads\\teste.zip
        //String destino = "../";
        
        String destino = "C:\\Users\\walyson.ferreira\\Downloads\\teste";
        
        File file = new File(origem);
        byte[] fileBytes = org.apache.commons.io.FileUtils.readFileToByteArray(file);

        try (ByteArrayInputStream bis = new ByteArrayInputStream(fileBytes);
             ZipArchiveInputStream zipStream = new ZipArchiveInputStream(bis)) 
        {

            ZipArchiveEntry ze;
            byte[] buffer = new byte[16384];  // 16KB buffer
            int readBytes;

            while ((ze = zipStream.getNextZipEntry()) != null) 
            {
                // Obtenha o nome do arquivo extraído
                String fileName = destino + File.separator + ze.getName();

                // Substituir caracteres inválidos para Windows
                fileName = sanitizeFileName(fileName);

                if (ze.isDirectory()) 
                {
                    Files.createDirectories(Paths.get(fileName));
                    continue;
                }

                // Escreva o arquivo extraído
                try (FileOutputStream outFile = new FileOutputStream(fileName)) 
                {
                    while ((readBytes = zipStream.read(buffer)) != -1) 
                    {
                        outFile.write(buffer, 0, readBytes);
                    }
                }
            }
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
    }

    // Método para substituir caracteres inválidos em nomes de arquivos
    private static String sanitizeFileName(String fileName) 
    {
        // Substitui caracteres inválidos com um caractere seguro, como um underscore.
        return fileName.replaceAll("[<>:\"/\\|?*]", "_");
    }
}
