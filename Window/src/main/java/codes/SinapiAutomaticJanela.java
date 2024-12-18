/*
 * 	@author DrKobun github.com/DrKobun
 */
package codes;

import org.apache.commons.compress.archivers.zip.*;
import java.io.*;
import java.nio.file.*;
import java.nio.charset.StandardCharsets;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;
import java.nio.file.attribute.BasicFileAttributes;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.apache.commons.compress.archivers.zip.*;
import org.apache.commons.compress.utils.IOUtils;
import java.io.*;
import java.nio.file.*;
import java.nio.charset.StandardCharsets;
import org.apache.log4j.BasicConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import javax.swing.*;
import java.io.*;
import java.awt.*;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;
import java.nio.file.attribute.BasicFileAttributes;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import org.apache.log4j.BasicConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.Colors;

// ESTE PROGRAMA POSSUI CONSOLE EM JANELA SEPARADA!
public class SinapiAutomaticJanela 
{
	private static final int BUFFER_SIZE = 4096;
	static int limitador = 0;
	public static void main(String[] args) 
	{
		
		SwingUtilities.invokeLater(() -> {
            // Criação do JFrame
            JFrame frame = new JFrame("console");
            frame.setSize(500, 400);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            // Criação do JTextArea para exibir o console
            JTextArea textArea = new JTextArea();
            textArea.setEditable(false);
            JScrollPane scrollPane = new JScrollPane(textArea);
            frame.add(scrollPane, BorderLayout.CENTER);
            
            //configuraçõoes para localização e tamanho da janela (janela do console)
            frame.setLocation(960, 0); // aqui é Localização (onde a janela vai ficar) o primeiro argumento é a largura
            frame.setSize(960, 1080);  // aqui é tamanho (qual o espaço que a janela vai ocupar)
            
            // Redirecionar o System.out e System.err para o JTextArea
            PrintStream printStream = new PrintStream(new TextAreaOutputStream(textArea));
            System.setOut(printStream);
            System.setErr(printStream);

            frame.setVisible(true);
        });
    	
		BasicConfigurator.configure();
		
		//TODO método para procurar automaticamente o caminho do ChromeDriver.exe
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\walyson.ferreira\\Desktop\\Programas-para-instalar\\chromedriver-win64\\chromedriver.exe");
        System.setProperty("webdriver.http.factory", "jdk-http-client");
        WebDriver driver = new ChromeDriver();

        try 
        {
            
            driver.get("https://www.caixa.gov.br/site/paginas/downloads.aspx#categoria_868");

            Thread.sleep(5000);  
         
//          String[] estados = 
//          {
//              "AC", "AL", "AP", 
//              "AM", "BA", "CE", 
//              "DF", "ES", "GO", 
//              "MA", "MT", "MS", 
//              "MG", "PA", "PB", 
//              "PR", "PE", "PI", 
//              "RJ", "RN", "RS", 
//              "RO", "RR", "SC", 
//              "SP", "SE", "TO"
//          };
            String[] estados = 
            {
            		"AC","AL","AP"
            };
            
             DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
	   		 LocalDateTime now = LocalDateTime.now();
	   		 System.out.println("Hora atual: " + dtf.format(now));
	   		 
	   		 // conversão de DateTime para String
	   		 String dataAtual = dtf.format(now);
	   		 // remoção das barras para espaços em branco
	   		 String mesAno = dataAtual.replaceAll("/", "");
	   		 // corte exato do mês e ano. Formato: "AAAAMM"
	   		 String anoMes = mesAno.substring(0, 6);
	   		 
            
            // Para cada estado, gera o link de download
            for(String estado : estados) 
            {
                // modelo de links para navegação e download
            	String desoneradoLink = "https://www.caixa.gov.br/Downloads/sinapi-a-partir-jul-2009-" + estado + "/SINAPI_ref_Insumos_Composicoes_" + estado + "_202409_Desonerado.zip";
            	String naoDesoneradoLink = "https://www.caixa.gov.br/Downloads/sinapi-a-partir-jul-2009-" + estado + "/SINAPI_ref_Insumos_Composicoes_" + estado + "_202409_NaoDesonerado.zip";
            	//String desoneradoLink = "https://www.caixa.gov.br/Downloads/sinapi-a-partir-jul-2009-" + estado + "/SINAPI_ref_Insumos_Composicoes_" + estado + "_" + anoMes + "_Desonerado.zip";
            	//String naoDesoneradoLink = "https://www.caixa.gov.br/Downloads/sinapi-a-partir-jul-2009-" + estado + "/SINAPI_ref_Insumos_Composicoes_" + estado + "_" + anoMes + "_NaoDesonerado.zip";
                
                
                // resgara a URL atual para verificar se a página existe ou não
                String urlAtual = driver.getCurrentUrl();
                System.out.println("LINK DA URL ATUAL: " + urlAtual);
                
                if(urlAtual.contains("PageNotFoundError"))
                {
                	break;
                }
                
                //Baixar os dois arquivos para cada estado
                //downloadFile(desoneradoLink);
                //downloadFile(naoDesoneradoLink);
                
                System.out.println("Hora atual: " + dtf.format(now)); 
                driver.get(desoneradoLink);
                Thread.sleep(2000);
                driver.get(naoDesoneradoLink);
                Thread.sleep(2000);

                //aviso de download 
                System.out.println("Arquivos baixados para o estado: " + estado);
            }
	            System.out.println("LOOP DOS ESTADOS TERMINADO!");
	            Thread.sleep(3000);
	        }
	        catch(Exception e)
	        {
	        	System.out.println("ERRO: " + e);
	        }
                
//--------------------------------------------------------------------------------------------------------------------------------                
                                                         
                // TODO MOVER ARQUIVOS BAIXADOS PARA PASTA NO DESKTOP                
                String sourceDirectory = "C:\\Users\\walyson.ferreira\\Downloads\\";
                // TODO procurar automaticamente qual é a pasta do usuário atual
                String targetDirectory = "C:\\Users\\walyson.ferreira\\Desktop\\RelatóriosTeste\\Arquivos ZIP";              
                String fileNameToFind = "SINAPI"; // Substitua pelo nome do arquivo a ser procurado                  
                
                try 
                {
                    moveFiles(sourceDirectory, targetDirectory, fileNameToFind);
                } 
                catch (IOException e) 
                {
                    System.err.println("Ocorreu um erro: \n" + e.getMessage());
                    e.printStackTrace();
                }
//------------------------------------------------------------------------------------------------------------------------------  
                // *EXTRAIR* ARQUIVOS MOVIDOS (procura todos os arquivos .zip da pasta e todo o conteúdo de extração vai para outra pasta
    
    	        String origem = "C:\\Users\\walyson.ferreira\\Desktop\\RelatóriosTeste\\Arquivos ZIP"; // path de onde tem arquivos zipados para extração
    	        String destino = "C:\\Users\\walyson.ferreira\\Desktop\\RelatóriosTeste\\Resto";  // Caminho de destino onde os arquivos serão extraídos

    	        // Listar todos os arquivos no diretório de origem
    	        File folder = new File(origem);
    	        File[] zipFiles = folder.listFiles((dir, name) -> name.toLowerCase().endsWith(".zip"));

    	        // Verificar se há arquivos .zip
    	        if (zipFiles != null && zipFiles.length > 0) 
    	        {
    	            for (File zipFile : zipFiles) 
    	            {
    	                System.out.println("Processando o arquivo: " + zipFile.getName());

    	                // Processar cada arquivo .zip encontrado
    	                byte[] fileBytes = null;
						try 
						{
							fileBytes = org.apache.commons.io.FileUtils.readFileToByteArray(zipFile);
						} 
						catch (IOException e) 
						{
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

    	                try (ByteArrayInputStream bis = new ByteArrayInputStream(fileBytes);
    	                     ZipArchiveInputStream zipStream = new ZipArchiveInputStream(bis)) {

    	                    ZipArchiveEntry ze;
    	                    byte[] buffer = new byte[16384];  // 16KB buffer
    	                    int readBytes;

    	                    while ((ze = zipStream.getNextZipEntry()) != null) {
    	                        // Obtenha o nome do arquivo dentro do ZIP
    	                        String fileName = new String(ze.getName().getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
    	                        System.out.println("Nome do arquivo: " + fileName);

    	                        // Sanitize the file name to ensure it's valid for the file system
    	                        fileName = sanitizeFileName(fileName);
    	                        System.out.println("ARQUIVO COM ALTERAÇÃO: " + fileName);

    	                        // Se for um diretório, cria o diretório no destino
    	                        File fileOut = new File(destino + File.separator + fileName);
    	                        if (ze.isDirectory()) {
    	                            fileOut.mkdirs();  // Cria o diretório, se necessário
    	                            continue;
    	                        }

    	                        // Criação dos diretórios necessários para o arquivo extraído
    	                        File parentDir = fileOut.getParentFile();
    	                        if (!parentDir.exists()) {
    	                            parentDir.mkdirs();  // Cria os diretórios pai se não existirem
    	                        }

    	                        // Escreva o arquivo extraído no destino
    	                        try (FileOutputStream outFile = new FileOutputStream(fileOut)) 
    	                        {
    	                            while ((readBytes = zipStream.read(buffer)) != -1) {
    	                                outFile.write(buffer, 0, readBytes);
    	                            }
    	                        }
    	                    }
    	                } catch (IOException e) 
    	                {
    	                    e.printStackTrace();
    	                }
    	            }
    	        } else 
    	        {
    	            System.out.println("Nenhum arquivo .zip encontrado no diretório especificado.");
    	        }
                
           //---------------------------------------------------------------------------------------------------------
    	        //Mover apenas arquivos selecionados APÓS a extração
    	        String sourceDirector = "C:\\Users\\walyson.ferreira\\Desktop\\RelatóriosTeste"; // Substitua pelo caminho da pasta de origem
                // TODO procurar automaticamente qual é a pasta do usuário atual
                String targetDirector = "C:\\Users\\walyson.ferreira\\Desktop\\RelatóriosTeste\\Arquivos Excel"; // Substitua pelo caminho da pasta de destino
                // Nome do arquivo para ser movido
                String fileNameToFin = ".xlsx"; // Substitua pelo nome do arquivo a ser procurado
                  
                
                // MOVER APENAS ARQUIVOS BAIXADOS
            	 try 
            	 {
	                 moveFiles(sourceDirector, targetDirector, fileNameToFin);

            	 }
            	 catch(IOException e)
            	 {
            		 System.out.println("OCORREU UM ERRO: " + e);
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
	private static String sanitizeFileName(String fileName) 
    {
        // Substitui caracteres inválidos com um caractere seguro, como um underscore.
        return fileName.replaceAll("[<>:\"/\\|?*]", "_");
    }	
}