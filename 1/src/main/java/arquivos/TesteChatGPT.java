//package arquivos;
//
//import org.apache.commons.compress.archivers.zip.*;
//import org.apache.commons.compress.utils.IOUtils;
//import java.io.*;
//import java.nio.file.*;
//import java.nio.charset.StandardCharsets;
//
//public class TesteChatGPT {
//
//    public static void main(String[] args) throws IOException {
//
//        String origem = "C:\\Users\\walyson.ferreira\\Downloads\\teste.zip";  // Caminho do arquivo ZIP
//        String destino = "C:\\Users\\walyson.ferreira\\Downloads";  // Caminho de destino onde os arquivos serão extraídos
//
//        File file = new File(origem);
//        byte[] fileBytes = org.apache.commons.io.FileUtils.readFileToByteArray(file);
//
//        try (ByteArrayInputStream bis = new ByteArrayInputStream(fileBytes);
//             ZipArchiveInputStream zipStream = new ZipArchiveInputStream(bis)) {
//
//            ZipArchiveEntry ze;
//            byte[] buffer = new byte[16384];  // 16KB buffer
//            int readBytes;
//
//            while ((ze = zipStream.getNextZipEntry()) != null) {
//                // Obtenha o nome do arquivo dentro do ZIP
//                String fileName = destino + File.separator + ze.getName();
//                System.out.println("NOVO NOME DO ARQUIVO: " + fileName);
//                // Sanitize the file name to ensure it's valid for the file system
//                fileName = sanitizeFileName(fileName);
//
//                // Se for um diretório, cria o diretório no destino
//                File fileOut = new File(fileName);
//                if (ze.isDirectory()) {
//                    fileOut.mkdirs();  // Cria o diretório, se necessário
//                    continue;
//                }
//
//                // Criação dos diretórios necessários para o arquivo extraído
//                File parentDir = fileOut.getParentFile();
//                if (!parentDir.exists()) {
//                    parentDir.mkdirs();  // Cria os diretórios pai se não existirem
//                }
//
//                // Escreva o arquivo extraído no destino
//                try (FileOutputStream outFile = new FileOutputStream(fileOut)) {
//                    while ((readBytes = zipStream.read(buffer)) != -1) {
//                        outFile.write(buffer, 0, readBytes);
//                    }
//                }
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    // Método para substituir caracteres inválidos em nomes de arquivos
//    private static String sanitizeFileName(String fileName) {
//        // Substitui caracteres inválidos com um caractere seguro, como um underscore.
//        return fileName.replaceAll("[<>:\"/\\|?*]", "_");
//    }
//}

package arquivos;

import org.apache.commons.compress.archivers.zip.*;
import org.apache.commons.compress.utils.IOUtils;
import java.io.*;
import java.nio.file.*;
import java.nio.charset.StandardCharsets;

public class TesteChatGPT 
{

    public static void main(String[] args) throws IOException 
    {

        String origem = "C:\\Users\\walyson.ferreira\\Downloads\\teste.zip";  // Caminho do arquivo ZIP
        String destino = "C:\\Users\\walyson.ferreira\\zipFiles\\";  // Caminho de destino onde os arquivos serão extraídos

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
                // Obtenha o nome do arquivo dentro do ZIP (Usando UTF-8 para garantir que caracteres especiais sejam lidos corretamente)
                String fileName = new String(ze.getName().getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
                System.out.println("Nome do arquivo: " + fileName);
                
                // Sanitize the file name to ensure it's valid for the file system
                fileName = sanitizeFileName(fileName);
                System.out.println("ARQUIVO COM ALTERAÇÃO: " + fileName);
                
                // Se for um diretório, cria o diretório no destino
                File fileOut = new File(destino + File.separator + fileName);
                if (ze.isDirectory()) 
                {
                    fileOut.mkdirs();  // Cria o diretório, se necessário
                    System.out.println("SAÍDA DO ARQUIVO: " + fileOut);
                    continue;
                }

                // Criação dos diretórios necessários para o arquivo extraído
                File parentDir = fileOut.getParentFile();
                if (!parentDir.exists()) 
                {
                    parentDir.mkdirs();  // Cria os diretórios pai se não existirem
                }

                // Escreva o arquivo extraído no destino
                try (FileOutputStream outFile = new FileOutputStream(fileOut)) 
                {
                    while ((readBytes = zipStream.read(buffer)) != -1) 
                    {
                        outFile.write(buffer, 0, readBytes);
                    }
                }
            }
        } catch (IOException e) 
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

