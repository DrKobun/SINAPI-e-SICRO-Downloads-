package arquivos;
import java.io.*;
import java.util.zip.*;

public class ExtracaoMk 
{
    public static void main(String[] args) 
    {
        String zipFile = "C:\\Users\\walyson.ferreira\\Downloads\\teste"; // Caminho para o arquivo ZIP
        String destDir = "C:\\Users\\walyson.ferreira\\Downloads\\teste"; // Diretório de destino para extração
        
        try (ZipInputStream zis = new ZipInputStream(new FileInputStream(zipFile)))
        {
            ZipEntry entry;
            
            while ((entry = zis.getNextEntry()) != null) 
            {
                System.out.println("Extraindo: " + entry.getName());
                
                // Cria um arquivo de destino para o arquivo dentro do ZIP
                File newFile = new File(destDir, entry.getName());
                
                // Cria diretórios, se necessário
                new File(newFile.getParent()).mkdirs();
                
                try (BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(newFile))) 
                {
                    byte[] buffer = new byte[1024];
                    int len;
                    while ((len = zis.read(buffer)) > 0) 
                    {
                        bos.write(buffer, 0, len);
                    }
                }
                zis.closeEntry();
            }
            System.out.println("Descompactação concluída com sucesso!");
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
    }
}
