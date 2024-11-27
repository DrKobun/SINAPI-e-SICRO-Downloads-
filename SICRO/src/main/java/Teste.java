////import org.apache.log4j.BasicConfigurator;
////import org.openqa.selenium.Alert;
////import org.openqa.selenium.By;
////import org.openqa.selenium.Dimension;
////import org.openqa.selenium.JavascriptExecutor;
////import org.openqa.selenium.WebDriver;
////import org.openqa.selenium.WebElement;
////import org.openqa.selenium.chrome.ChromeDriver;
////import org.openqa.selenium.interactions.Actions;
////import org.openqa.selenium.support.ui.ExpectedConditions;
////import org.openqa.selenium.support.ui.WebDriverWait;
////
////import java.io.IOException;
////import java.net.URI;
////import java.net.http.HttpClient;
////import java.net.http.HttpRequest;
////import java.net.http.HttpResponse;
////import java.nio.file.Files;
////import java.nio.file.Path;
////import java.nio.file.StandardOpenOption;
////import java.time.Duration;
////import java.util.ArrayList;
////import java.util.List;
////
////public class Teste {
////    public static void main(String[] args) {
////    	BasicConfigurator.configure();
////        
////        System.setProperty("webdriver.chrome.driver", "C:\\Users\\walyson.ferreira\\chromedriver.exe");
////        System.setProperty("webdriver.http.factory", "jdk-http-client");
////        WebDriver driver = new ChromeDriver();
////
////        try 
////        {
////            // Abre a página inicial
////            driver.get("https://www.gov.br/dnit/pt-br/assuntos/planejamento-e-pesquisa/custos-e-pagamentos/custos-e-pagamentos-dnit/sistemas-de-custos/sicro/relatorios-sicro/relatorios-sicro");
////
////            // para clicar no centro da tela (ignorar mensagem popup)
////            Dimension windowSize = driver.manage().window().getSize();
////            int centerX = windowSize.getWidth() / 2;
////            int centerY = windowSize.getHeight() / 2;
////
////            // Clica no centro da tela
////            Actions actions = new Actions(driver);
////            actions.moveByOffset(centerX, centerY).click().perform();
////
////            //String linkHref = driver.findElement(By.xpath("//a[contains(@href, 'norte')]")).getAttribute("href");
////            String linkHref = driver.findElement(By.xpath("//a[contains(@href, 'norte') or contains(@href, 'nordeste') or contains(@href, 'centro-oeste') or contains(@href, 'sudeste') or contains(@href, 'sul')]")).getAttribute("href");
//// 
////            // linkHref para fazer o download diretamente com o HttpClient
////	         downloadFile(linkHref);
////	         
////	         
////	         
////
////
////	        // "//a[text()='Nordeste' or text()='Sul' or text()='Centro-Oeste' or text()='Sudeste' ]"
////            // Pega os links das regiões											// remover regiões que já correram
////	        
////	        //List<WebElement> regionLinks = driver.findElements(By.xpath("//a[contains(text(), 'Norte') or contains(text(), 'Nordeste') or contains(text(), 'Centro-Oeste') or contains(text(), 'Sudeste') or contains(text(), 'Sul')]"));
////            //List<WebElement> regionLinks = driver.findElements(By.xpath("//a[text()='Norte']"));
////	        List<WebElement> regionLinks = driver.findElements(By.xpath("//a[text()='Norte' or text()='Nordeste' or text()='Centro-Oeste' or text()='Sudeste' or text()='Sul']"));
////            
////            for(WebElement regionLink : regionLinks)
////            {
////                regionLink.click(); // Clica no link da região para abrir a página dos meses
////
////                // Pega os hrefs dos meses dentro da região e armazena em uma lista de Strings
////                
////              //List<WebElement> monthElements = driver.findElements(By.xpath("//a[contains(text(), 'Janeiro') or contains(text(), 'Fevereiro') or contains(text(), 'Março') or contains(text(), 'Abril') or contains(text(), 'Maio') or contains(text(), 'Junho') or contains(text(), 'Julho') or contains(text(), 'Agosto') or contains(text(), 'Setembro') or contains(text(), 'Outubro') or contains(text(), 'Novembro') or contains(text(), 'Dezembro')]"));
////                
////              //List<WebElement> monthElements = driver.findElements(By.xpath("//a[contains(translate(text(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'janeiro') or contains(translate(text(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'fevereiro') or contains(translate(text(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'março') or contains(translate(text(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'abril') or contains(translate(text(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'maio') or contains(translate(text(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'junho') or contains(translate(text(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'julho') or contains(translate(text(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'agosto') or contains(translate(text(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'setembro') or contains(translate(text(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'outubro') or contains(translate(text(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'novembro') or contains(translate(text(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'dezembro')]"));
////
////                List<WebElement> monthElements = driver.findElements(By.xpath("//a[(contains(translate(text(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'janeiro') or contains(translate(text(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'fevereiro') or contains(translate(text(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'março') or contains(translate(text(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'abril') or contains(translate(text(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'maio') or contains(translate(text(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'junho') or contains(translate(text(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'julho') or contains(translate(text(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'agosto') or contains(translate(text(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'setembro') or contains(translate(text(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'outubro') or contains(translate(text(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'novembro') or contains(translate(text(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'dezembro')) and contains(@href, 'tocantins')]"));
////
////                List<String> monthLinks = new ArrayList<>();
////                
////                for (WebElement monthElement : monthElements) 
////                {
////                    monthLinks.add(monthElement.getAttribute("href"));
////                }
////
////                // Itera sobre cada link dos meses usando os hrefs
////                for (String monthLink : monthLinks) 
////                {
////                    driver.get(monthLink); // Navega para o link do mês
////
////                    																			 //.zip	//remover ,'2017' // REFAZER CONDIÇÕES PARA DOWNLOAD
////                    List<WebElement> zipLinks = driver.findElements(By.xpath("//a[contains(@href, '.zip')]"));
////
////                    
////                    for (WebElement zipLink : zipLinks) 
////                    {
////                        String fileUrl = zipLink.getAttribute(".zip");
////                        downloadFile(fileUrl); // Função de download
////                    }
////
////                    // Volta para a página anterior (da região)
////                    driver.navigate().back();
////                }
////
////                // Volta para a página inicial das regiões
////                driver.navigate().back();
////            }
////        } 
////        finally 
////        {
////            driver.quit();
////        }
////    }
////
////    // Método para baixar o arquivo
////    public static void downloadFile(String fileUrl) {
////        try {
////            HttpClient client = HttpClient.newHttpClient();
////            HttpRequest request = HttpRequest.newBuilder().uri(URI.create(fileUrl)).build();
////
////            HttpResponse<byte[]> response = client.send(request, HttpResponse.BodyHandlers.ofByteArray());
////
////            // Salva o arquivo na pasta
////            String fileName = fileUrl.substring(fileUrl.lastIndexOf("/") + 1);
////            Path filePath = Path.of("Downloads", fileName);
////            Files.createDirectories(filePath.getParent());
////            Files.write(filePath, response.body(), StandardOpenOption.CREATE);
////
////            System.out.println("Arquivo baixado: " + fileName + "\n no caminho: " + filePath);
////        } catch (IOException | InterruptedException e) {
////            e.printStackTrace();
////        }
////    }
////}
//
//
////import org.apache.log4j.BasicConfigurator;
////import org.openqa.selenium.By;
////import org.openqa.selenium.Dimension;
////import org.openqa.selenium.WebDriver;
////import org.openqa.selenium.WebElement;
////import org.openqa.selenium.chrome.ChromeDriver;
////import org.openqa.selenium.interactions.Actions;
////
////import java.io.IOException;
////import java.net.URI;
////import java.net.http.HttpClient;
////import java.net.http.HttpRequest;
////import java.net.http.HttpResponse;
////import java.nio.file.Files;
////import java.nio.file.Path;
////import java.nio.file.StandardOpenOption;
////import java.util.ArrayList;
////import java.util.List;
////
////public class Teste {
////    public static void main(String[] args) throws InterruptedException {
////        BasicConfigurator.configure();
////        System.setProperty("webdriver.chrome.driver", "C:\\Users\\walyson.ferreira\\chromedriver.exe");
////        System.setProperty("webdriver.http.factory", "jdk-http-client");
////        WebDriver driver = new ChromeDriver();
////
////        try {
////            driver.get("https://www.gov.br/dnit/pt-br/assuntos/planejamento-e-pesquisa/custos-e-pagamentos/custos-e-pagamentos-dnit/sistemas-de-custos/sicro/relatorios-sicro/relatorios-sicro");
////
////            Dimension windowSize = driver.manage().window().getSize();
////            int centerX = windowSize.getWidth() / 2;
////            int centerY = windowSize.getHeight() / 2;
////            Actions actions = new Actions(driver);
////            actions.moveByOffset(centerX, centerY).click().perform();
////
////            List<WebElement> regionLinks = driver.findElements(By.xpath("//a[text()='Nordeste' or text()='Sul' or text()='Centro-Oeste' or text()='Sudeste']"));
////
////            for (WebElement regionLink : regionLinks) 
////            {
////                String regionName = regionLink.getText();
////                regionLink.click();
////
////                List<WebElement> monthElements = driver.findElements(By.xpath("//a[contains(text(), 'Janeiro') or contains(text(), 'Fevereiro') or contains(text(), 'Março') or contains(text(), 'Abril') or contains(text(), 'Maio') or contains(text(), 'Junho') or contains(text(), 'Julho') or contains(text(), 'Agosto') or contains(text(), 'Setembro') or contains(text(), 'Outubro') or contains(text(), 'Novembro') or contains(text(), 'Dezembro')]"));
////                List<String> monthLinks = new ArrayList<>();
////
////                for (WebElement monthElement : monthElements) 
////                {											//href
////                    monthLinks.add(monthElement.getAttribute(".zip"));
////                }
////
////                for (String monthLink : monthLinks) 
////                {
////                    driver.get(monthLink);
////                    // Declaração Throw lançada no método main
////                    Thread.sleep(5000);
////                    String monthName = driver.findElement(By.tagName("h1")).getText();
////
////                    // Cria diretório para a região e o mês, antes de baixar qualquer arquivo
////                    Path directoryPath = Path.of("downloads", regionName, monthName);
////                    Files.createDirectories(directoryPath);
////
////                    List<WebElement> zipLinks = driver.findElements(By.xpath("//a[contains(@href, '.zip')]"));
////                    for (WebElement zipLink : zipLinks) 
////                    {										//href
////                        String fileUrl = zipLink.getAttribute(".zip");
////                        downloadFile(fileUrl, regionName, monthName);
////                    }
////                    driver.navigate().back();
////                }
////                driver.navigate().back();
////            }
////        } catch (IOException e) {
////            e.printStackTrace();
////        } finally {
////            driver.quit();
////        }
////    }
////
////    public static void downloadFile(String fileUrl, String regionName, String monthName) 
////    {
////        try 
////        {
////            HttpClient client = HttpClient.newHttpClient();
////            HttpRequest request = HttpRequest.newBuilder().uri(URI.create(fileUrl)).build();
////            HttpResponse<byte[]> response = client.send(request, HttpResponse.BodyHandlers.ofByteArray());
////
////            String fileName = fileUrl.substring(fileUrl.lastIndexOf("/") + 1);
////            Path filePath = Path.of("downloads", regionName, monthName, fileName);
////            Files.write(filePath, response.body(), StandardOpenOption.CREATE);
////
////            System.out.println("Arquivo baixado: " + filePath);
////        } catch (IOException | InterruptedException e) {
////            e.printStackTrace();
////        }
////    }
////}
//
//import org.apache.log4j.BasicConfigurator;
//import org.openqa.selenium.By;
//import org.openqa.selenium.Dimension;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.interactions.Actions;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.WebDriverWait;
//
//import java.io.IOException;
//import java.net.URI;
//import java.net.http.HttpClient;
//import java.net.http.HttpRequest;
//import java.net.http.HttpResponse;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.StandardOpenOption;
//import java.time.Duration;
//import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;
//import java.util.ArrayList;
//import java.util.List;
//
//
//// VERSÃO DO SICRO FUNCIONANDO SOMENTE A REGIÃO NORTE
//public class Teste {
//    public static void main(String[] args) throws InterruptedException {
//        BasicConfigurator.configure();
//        
//        System.setProperty("webdriver.chrome.driver", "C:\\Users\\walyson.ferreira\\chromedriver.exe");
//        System.setProperty("webdriver.http.factory", "jdk-http-client");
//        WebDriver driver = new ChromeDriver();
//        
//        try 
//        {
//            // Abre a página inicial
//            driver.get("https://www.gov.br/dnit/pt-br/assuntos/planejamento-e-pesquisa/custos-e-pagamentos/custos-e-pagamentos-dnit/sistemas-de-custos/sicro/relatorios-sicro/relatorios-sicro");
//
//            // Clica no centro da tela para fechar o popup, caso exista
//            Dimension windowSize = driver.manage().window().getSize();
//            int centerX = windowSize.getWidth() / 2;
//            int centerY = windowSize.getHeight() / 2;
//            Actions actions = new Actions(driver);
//            actions.moveByOffset(centerX, centerY).click().perform();
//            
//            // Pega os links das regiões do Brasil
//            List<WebElement> regionLinks = driver.findElements(By.xpath("//a[text()='Norte' or text()='nordeste' or text()='centro-oeste' or text()='sudeste' or text()='sul']"));
//            
//            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
//        	LocalDateTime now = LocalDateTime.now();
//        	
//        	// conversão de DateTime para String
//	    	String dataAtual = dtf.format(now);
//	    	// remoção das barras para espaços em branco
//	    	String mesAno = dataAtual.replaceAll("/", "");
//	    	// corte exato do mês e ano. Formato: "AAAAMM"
//	    	String ano = mesAno.substring(0, 4);
//	    	
//            for(WebElement regionLink : regionLinks) 
//            {
//            	
//                // Armazena o link da região
//                String regionHref = regionLink.getAttribute("href");
//                
//                // Navega para o link da região
//                driver.get(regionHref);
//                
//                // Pega os links dos meses dentro da região
//                List<WebElement> monthElements = driver.findElements(By.xpath(
//                		// 2024 até o momento (data: 21/11/2024) só vai até julho
//                		"//a[contains(@href," + ano + ")]"
////                	    "//a[contains(translate(text(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZÁÉÍÓÚÀÈÌÒÙÂÊÎÔÛÇ', 'abcdefghijklmnopqrstuvwxyzáéíóúàèìòùâêîôûç'), 'janeiro') or " +
////                	    "contains(translate(text(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZÁÉÍÓÚÀÈÌÒÙÂÊÎÔÛÇ', 'abcdefghijklmnopqrstuvwxyzáéíóúàèìòùâêîôûç'), 'fevereiro') or " +
////                	    "contains(translate(text(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZÁÉÍÓÚÀÈÌÒÙÂÊÎÔÛÇ', 'abcdefghijklmnopqrstuvwxyzáéíóúàèìòùâêîôûç'), 'marco') or " +
////                	    "contains(translate(text(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZÁÉÍÓÚÀÈÌÒÙÂÊÎÔÛÇ', 'abcdefghijklmnopqrstuvwxyzáéíóúàèìòùâêîôûç'), 'abril') or " +
////                	    "contains(translate(text(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZÁÉÍÓÚÀÈÌÒÙÂÊÎÔÛÇ', 'abcdefghijklmnopqrstuvwxyzáéíóúàèìòùâêîôûç'), 'maio') or " +
////                	    "contains(translate(text(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZÁÉÍÓÚÀÈÌÒÙÂÊÎÔÛÇ', 'abcdefghijklmnopqrstuvwxyzáéíóúàèìòùâêîôûç'), 'junho') or " +
////                	    "contains(translate(text(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZÁÉÍÓÚÀÈÌÒÙÂÊÎÔÛÇ', 'abcdefghijklmnopqrstuvwxyzáéíóúàèìòùâêîôûç'), 'julho') or " +
////                	    "contains(translate(text(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZÁÉÍÓÚÀÈÌÒÙÂÊÎÔÛÇ', 'abcdefghijklmnopqrstuvwxyzáéíóúàèìòùâêîôûç'), 'agosto') or " +
////                	    "contains(translate(text(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZÁÉÍÓÚÀÈÌÒÙÂÊÎÔÛÇ', 'abcdefghijklmnopqrstuvwxyzáéíóúàèìòùâêîôûç'), 'setembro') or " +
////                	    "contains(translate(text(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZÁÉÍÓÚÀÈÌÒÙÂÊÎÔÛÇ', 'abcdefghijklmnopqrstuvwxyzáéíóúàèìòùâêîôûç'), 'outubro') or " +
////                	    "contains(translate(text(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZÁÉÍÓÚÀÈÌÒÙÂÊÎÔÛÇ', 'abcdefghijklmnopqrstuvwxyzáéíóúàèìòùâêîôûç'), 'novembro') or " +
////                	    "contains(translate(text(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZÁÉÍÓÚÀÈÌÒÙÂÊÎÔÛÇ', 'abcdefghijklmnopqrstuvwxyzáéíóúàèìòùâêîôûç'), 'dezembro')]"
//                	));
//
//                
//                for(WebElement monthElement : monthElements)
//                { 
//                    String monthHref = monthElement.getAttribute("href");
//                    
//                    // Navega para o link do mês
//                    driver.get(monthHref);
//                    //downloadFile(monthHref);
//                    //Thread.sleep(5000);
//                    
//                    
//                    // Baixa todos os arquivos .zip na página
//                    //List<WebElement> zipLinks = driver.findElements(By.xpath("//a[contains(@href, '.zip')]"));
//                    List<WebElement> zipLinks = driver.findElements(By.xpath("//a[substring(@href, string-length(@href) - 3) = '.zip']"));
//                    
//                    
//                    for(WebElement zipLink : zipLinks)
//                    {	
//                        String fileUrl = zipLink.getAttribute("href");
//                        //downloadFile(fileUrl);
//                        String arquivoAtual = "";
//                        
//                        driver.get(fileUrl);
//                        
//                        arquivoAtual = fileUrl;
//                        int tamanhoURL = arquivoAtual.length();
//
//                        arquivoAtual = fileUrl.substring(tamanhoURL - 14);
//                        
//                        System.out.println("Arquivo baixado: " + arquivoAtual + "\n------------------");
//                        Thread.sleep(5000);
//                    }
//
//                    // Volta para a página anterior (da região)
//                    driver.navigate().back();
//                }
//
//                // Volta para a página inicial das regiões
//                driver.navigate().back();
//            }
//        } finally
//        {
//            driver.quit();
//        }
//    }
//
//    // Método para baixar o arquivo
//    public static void downloadFile(String fileUrl) 
//    {
//        try 
//        {
//            HttpClient client = HttpClient.newHttpClient();
//            HttpRequest request = HttpRequest.newBuilder().uri(URI.create(fileUrl)).build();
//            
//            HttpResponse<byte[]> response = client.send(request, HttpResponse.BodyHandlers.ofByteArray());
//            
//            // TODO mudar caminho de download dos arquivos e organizar por :região:estado:
//            // Salva o arquivo na pasta "Downloads"
//            String fileName = fileUrl.substring(fileUrl.lastIndexOf("/") + 1);
//            Path filePath = Path.of("Downloads", fileName);
//            Files.createDirectories(filePath.getParent());
//            Files.write(filePath, response.body(), StandardOpenOption.CREATE);
//
//            System.out.println("Arquivo baixado: " + fileName + "\nno caminho: " + filePath);
//        } catch (IOException | InterruptedException e) 
//        {
//            e.printStackTrace();
//        }
//    }
//}
//

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Arrays;
import java.util.Iterator;
import java.util.stream.Stream;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveInputStream;
import org.apache.log4j.BasicConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import codes.TextAreaOutputStream;

import java.awt.BorderLayout;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;
import java.nio.file.attribute.BasicFileAttributes;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;


public class Teste
{
	
	static String currentUser = System.getProperty("user.name");
	
	static String[] estados = 
        {
            "AC", "AL", "AP", 
            "AM", "BA", "CE", 
            "DF", "ES", "GO", 
            "MA", "MT", "MS", 
            "MG", "PA", "PB", 
            "PR", "PE", "PI", 
            "RJ", "RN", "RS", 
            "RO", "RR", "SC", 
            "SP", "SE", "TO"
        };
	
	public static void main(String[] args)
	{
		for(String estado : estados)
        {
//	       	 String s = "C:\\Users\\" + currentUser + "\\Desktop\\Arquivos SICRO\\Arquivos Excel";
//	       	 String t = "C:\\Users\\" + currentUser + "\\Desktop\\Arquivos SICRO\\Arquivos Excel\\" + estado;
//	       	 String f = estado;
//	       	 
//	       	 // remover arquivos com "SICRO" no nome
//	       	 
//	       	 try
//	       	 {
//	       		 moveFiles(s, t, f);
//	       	 }
//	       	 catch(IOException e)
//	       	 {
//	       		 System.err.println("Ocorreu um erro: \n" + e.getMessage());
//	       		 e.printStackTrace();
//	       	 }
	       	 
	       	 String s = "C:\\Users\\" + currentUser + "\\Desktop\\Arquivos SICRO\\Arquivos Excel\\RO";
	       	 String t = "C:\\Users\\" + currentUser + "\\Desktop\\Arquivos SICRO\\Arquivos Excel\\" + estado;
	       	 String f = estado + " ";
	       	 
	       	 // remover arquivos com "SICRO" no nome
	       	 
	       	 try
	       	 {
	       		 moveFiles(s, t, f);
	       	 }
	       	 catch(IOException e)
	       	 {
	       		 System.err.println("Ocorreu um erro: \n" + e.getMessage());
	       		 e.printStackTrace();
	       	 }
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