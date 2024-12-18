/*
 * 	@author DrKobun github.com/DrKobun
 */
package codes;

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

//import java.util.ArrayList;
//import java.util.List;
import org.apache.log4j.BasicConfigurator;
//import org.openqa.selenium.By;
//import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.Colors;
//import org.openqa.selenium.interactions.Actions;

// TORNAR EM ARQUIVO EXECUTÁVEL opções: Maven...?
// ESTE ARQUIVO POSSUI CONSOLE EM JANELA SEPARADA!
public class Code 
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
        
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\walyson.ferreira\\Desktop\\Programas-para-instalar\\chromedriver-win64\\chromedriver.exe");
        System.setProperty("webdriver.http.factory", "jdk-http-client");
        WebDriver driver = new ChromeDriver();

        try 
        {
            
            driver.get("https://www.caixa.gov.br/site/paginas/downloads.aspx#categoria_868");

            // Espera até que os links estejam visíveis (pode ser necessário ajustar dependendo da página)
            Thread.sleep(5000);  

            
//            String[] estados = 
//            {
//                "AC", "AL", "AP", 
//                "AM", "BA", "CE", 
//                "DF", "ES", "GO", 
//                "MA", "MT", "MS", 
//                "MG", "PA", "PB", 
//                "PR", "PE", "PI", 
//                "RJ", "RN", "RS", 
//                "RO", "RR", "SC", 
//                "SP", "SE", "TO"
//            };
            
            String[] estados = 
                {
                    "AC"
                };
//	   		 System.out.println("------------------------------");
//	   		 System.out.println("Hora atual: " + dtf.format(now)); 
	   		 
	   		 
            
            // Para cada estado, gera o link de download
            for(String estado : estados) 
            {
            	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
	   	   		LocalDateTime now = LocalDateTime.now();
	   	   		String dataAtual = dtf.format(now);
	   	   		String mesAno = dataAtual.replaceAll("/", "");
	   	   		String anoMes = mesAno.substring(0, 6);
	   	   		
                // modelo de links para navegação e download
            	//https://www.caixa.gov.br/Downloads/sinapi-a-partir-jul-2009-ac/SINAPI_ref_Insumos_Composicoes_AC_202409_Desonerado.zip
            	//https://www.caixa.gov.br/Downloads/sinapi-a-partir-jul-2009-al/SINAPI_ref_Insumos_Composicoes_AL_202409_NaoDesonerado.zip
                
	   	   		
	   	   		
	   	   		//String desoneradoLink = "https://www.caixa.gov.br/Downloads/sinapi-a-partir-jul-2009-" + estado + "/SINAPI_ref_Insumos_Composicoes_" + estado + "_" + anoMes + "_Desonerado.zip";
	   	   		//String naoDesoneradoLink = "https://www.caixa.gov.br/Downloads/sinapi-a-partir-jul-2009-" + estado + "/SINAPI_ref_Insumos_Composicoes_" + estado + "_" + anoMes + "_NaoDesonerado.zip";
                
                // apagar esses e descomentar os de cima
	   	   		String desoneradoLink = "https://www.caixa.gov.br/Downloads/sinapi-a-partir-jul-2009-" + estado + "/SINAPI_ref_Insumos_Composicoes_" + estado + "_202409_Desonerado.zip";
	   	   		String naoDesoneradoLink = "https://www.caixa.gov.br/Downloads/sinapi-a-partir-jul-2009-" + estado + "/SINAPI_ref_Insumos_Composicoes_" + estado + "_202409_NaoDesonerado.zip";
               
	   	   	
                // resgata a URL atual para verificar se a página existe ou não
                
                
                
                while(limitador != 1)
                {
                	System.out.println("------------------------------------------------------------");
                	limitador++;
                }
                
                System.out.println("Data e Hora atual: " + dtf.format(now));
                 
                
                
                //downloadFile(desoneradoLink);
                driver.get(desoneradoLink);
                
                String urlAtual = driver.getCurrentUrl();

                if(urlAtual.contains("PageNotFoundError"))
                {
                	break;
                }
                
                System.out.println("Arquivo baixado(DESONERADO): " + desoneradoLink + "\nDESONERADO " + estado + " OK!");
                Thread.sleep(5000);
                
                System.out.println("LINK DA URL ATUAL: " + urlAtual);
               
                
                
                //downloadFile(naoDesoneradoLink);
                driver.get(naoDesoneradoLink);
                if(urlAtual.contains("PageNotFoundError"))
                {
                	break;
                }
                System.out.println("Arquivo baixado(NÃO DESONERADO): " + naoDesoneradoLink + "\nNÃO DESONERADO " + estado + " OK!");
                Thread.sleep(5000);

                System.out.println("LINK DA URL ATUAL: " + urlAtual);

                //aviso de download 
                System.out.println("Arquivos baixados para o estado: " + estado);
                System.out.println("------------------------------------------------------------");
                
            }
            System.out.println("Programa encerrado!\nNão existem downloads disponíveis para o mês atual...");
            
        }
        catch(Exception e)
        {
        	System.out.println("ERRO: " + e);
        }
        
        // Caminho da pasta onde procurar os arquivos
        String sourceDirectory = "C:\\Users\\walyson.ferreira\\Downloads";
        // Caminho da pasta onde mover os arquivos
        String targetDirectory = "C:\\Users\\walyson.ferreira\\Desktop\\SINAPI";
        // Nome do arquivo a ser procurado
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
        // String zipFilePath, String destDirectory
        try 
        {
			unzip("C:\\Users\\walyson.ferreira\\Desktop\\SINAPI", "C:\\Users\\walyson.ferreira\\Desktop\\SINAPI\\Arquivos descompactados");
		} catch (IOException e) 
        {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    // Método para baixar o arquivo
    public static void downloadFile(String fileUrl) 
    {
        try 
        {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder().uri(URI.create(fileUrl)).build();

            HttpResponse<byte[]> response = client.send(request, HttpResponse.BodyHandlers.ofByteArray());

            // Salva o arquivo na pasta
            String fileName = fileUrl.substring(fileUrl.lastIndexOf("/") + 1);
            Path filePath = Path.of("C:\\Users\\walyson.ferreira\\Desktop\\ARQUIVOS SINAPI", fileName);
            Files.createDirectories(filePath.getParent());
            Files.write(filePath, response.body(), StandardOpenOption.CREATE);

            System.out.println("Arquivo baixado: " + fileName + "\nno caminho: " + filePath);
        } 
        catch (IOException | InterruptedException e) 
        {
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
    
    public static void unzip(String zipFilePath, String destDirectory) throws IOException 
    {
        File destDir = new File(destDirectory);
        if (!destDir.exists()) {
            destDir.mkdir();
        }
        ZipInputStream zipIn = new ZipInputStream(new FileInputStream(zipFilePath));
        ZipEntry entry = zipIn.getNextEntry();
        // iterates over entries in the zip file
        while (entry != null)
        {
            String filePath = destDirectory + File.separator + entry.getName();
            if (!entry.isDirectory()) {
                // if the entry is a file, extracts it
                extractFile(zipIn, filePath);
            } 
            else 
            {
                // if the entry is a directory, make the directory
                File dir = new File(filePath);
                dir.mkdirs();
            }
            zipIn.closeEntry();
            entry = zipIn.getNextEntry();
        }
        zipIn.close();
    }
	
    private static void extractFile(ZipInputStream zipIn, String filePath) throws IOException 
    {
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(filePath));
        byte[] bytesIn = new byte[BUFFER_SIZE];
        int read = 0;
        while ((read = zipIn.read(bytesIn)) != -1) 
        {
            bos.write(bytesIn, 0, read);
        }
        bos.close();
    }
}


