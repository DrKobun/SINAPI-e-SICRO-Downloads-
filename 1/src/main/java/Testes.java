//import org.apache.log4j.BasicConfigurator;
//import org.openqa.selenium.Alert;
//import org.openqa.selenium.By;
//import org.openqa.selenium.Dimension;
//import org.openqa.selenium.JavascriptExecutor;
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
//import java.util.ArrayList;
//import java.util.List;
//
//public class Teste {
//    public static void main(String[] args) {
//    	BasicConfigurator.configure();
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
//            // para clicar no centro da tela (ignorar mensagem popup)
//            Dimension windowSize = driver.manage().window().getSize();
//            int centerX = windowSize.getWidth() / 2;
//            int centerY = windowSize.getHeight() / 2;
//
//            // Clica no centro da tela
//            Actions actions = new Actions(driver);
//            actions.moveByOffset(centerX, centerY).click().perform();
//
//            //String linkHref = driver.findElement(By.xpath("//a[contains(@href, 'norte')]")).getAttribute("href");
//            String linkHref = driver.findElement(By.xpath("//a[contains(@href, 'norte') or contains(@href, 'nordeste') or contains(@href, 'centro-oeste') or contains(@href, 'sudeste') or contains(@href, 'sul')]")).getAttribute("href");
// 
//            // linkHref para fazer o download diretamente com o HttpClient
//	         downloadFile(linkHref);
//	         
//	         
//	         
//
//
//	        // "//a[text()='Nordeste' or text()='Sul' or text()='Centro-Oeste' or text()='Sudeste' ]"
//            // Pega os links das regiões											// remover regiões que já correram
//	        
//	        //List<WebElement> regionLinks = driver.findElements(By.xpath("//a[contains(text(), 'Norte') or contains(text(), 'Nordeste') or contains(text(), 'Centro-Oeste') or contains(text(), 'Sudeste') or contains(text(), 'Sul')]"));
//            //List<WebElement> regionLinks = driver.findElements(By.xpath("//a[text()='Norte']"));
//	        List<WebElement> regionLinks = driver.findElements(By.xpath("//a[text()='Norte' or text()='Nordeste' or text()='Centro-Oeste' or text()='Sudeste' or text()='Sul']"));
//            
//            for(WebElement regionLink : regionLinks)
//            {
//                regionLink.click(); // Clica no link da região para abrir a página dos meses
//
//                // Pega os hrefs dos meses dentro da região e armazena em uma lista de Strings
//                
//              //List<WebElement> monthElements = driver.findElements(By.xpath("//a[contains(text(), 'Janeiro') or contains(text(), 'Fevereiro') or contains(text(), 'Março') or contains(text(), 'Abril') or contains(text(), 'Maio') or contains(text(), 'Junho') or contains(text(), 'Julho') or contains(text(), 'Agosto') or contains(text(), 'Setembro') or contains(text(), 'Outubro') or contains(text(), 'Novembro') or contains(text(), 'Dezembro')]"));
//                
//              //List<WebElement> monthElements = driver.findElements(By.xpath("//a[contains(translate(text(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'janeiro') or contains(translate(text(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'fevereiro') or contains(translate(text(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'março') or contains(translate(text(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'abril') or contains(translate(text(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'maio') or contains(translate(text(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'junho') or contains(translate(text(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'julho') or contains(translate(text(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'agosto') or contains(translate(text(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'setembro') or contains(translate(text(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'outubro') or contains(translate(text(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'novembro') or contains(translate(text(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'dezembro')]"));
//
//                List<WebElement> monthElements = driver.findElements(By.xpath("//a[(contains(translate(text(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'janeiro') or contains(translate(text(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'fevereiro') or contains(translate(text(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'março') or contains(translate(text(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'abril') or contains(translate(text(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'maio') or contains(translate(text(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'junho') or contains(translate(text(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'julho') or contains(translate(text(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'agosto') or contains(translate(text(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'setembro') or contains(translate(text(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'outubro') or contains(translate(text(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'novembro') or contains(translate(text(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'dezembro')) and contains(@href, 'tocantins')]"));
//
//                List<String> monthLinks = new ArrayList<>();
//                
//                for (WebElement monthElement : monthElements) 
//                {
//                    monthLinks.add(monthElement.getAttribute("href"));
//                }
//
//                // Itera sobre cada link dos meses usando os hrefs
//                for (String monthLink : monthLinks) 
//                {
//                    driver.get(monthLink); // Navega para o link do mês
//
//                    																			 //.zip	//remover ,'2017' // REFAZER CONDIÇÕES PARA DOWNLOAD
//                    List<WebElement> zipLinks = driver.findElements(By.xpath("//a[contains(@href, '.zip')]"));
//
//                    
//                    for (WebElement zipLink : zipLinks) 
//                    {
//                        String fileUrl = zipLink.getAttribute(".zip");
//                        downloadFile(fileUrl); // Função de download
//                    }
//
//                    // Volta para a página anterior (da região)
//                    driver.navigate().back();
//                }
//
//                // Volta para a página inicial das regiões
//                driver.navigate().back();
//            }
//        } 
//        finally 
//        {
//            driver.quit();
//        }
//    }
//
//    // Método para baixar o arquivo
//    public static void downloadFile(String fileUrl) {
//        try {
//            HttpClient client = HttpClient.newHttpClient();
//            HttpRequest request = HttpRequest.newBuilder().uri(URI.create(fileUrl)).build();
//
//            HttpResponse<byte[]> response = client.send(request, HttpResponse.BodyHandlers.ofByteArray());
//
//            // Salva o arquivo na pasta
//            String fileName = fileUrl.substring(fileUrl.lastIndexOf("/") + 1);
//            Path filePath = Path.of("Downloads", fileName);
//            Files.createDirectories(filePath.getParent());
//            Files.write(filePath, response.body(), StandardOpenOption.CREATE);
//
//            System.out.println("Arquivo baixado: " + fileName + "\n no caminho: " + filePath);
//        } catch (IOException | InterruptedException e) {
//            e.printStackTrace();
//        }
//    }
//}


//import org.apache.log4j.BasicConfigurator;
//import org.openqa.selenium.By;
//import org.openqa.selenium.Dimension;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.interactions.Actions;
//
//import java.io.IOException;
//import java.net.URI;
//import java.net.http.HttpClient;
//import java.net.http.HttpRequest;
//import java.net.http.HttpResponse;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.StandardOpenOption;
//import java.util.ArrayList;
//import java.util.List;
//
//public class Teste {
//    public static void main(String[] args) throws InterruptedException {
//        BasicConfigurator.configure();
//        System.setProperty("webdriver.chrome.driver", "C:\\Users\\walyson.ferreira\\chromedriver.exe");
//        System.setProperty("webdriver.http.factory", "jdk-http-client");
//        WebDriver driver = new ChromeDriver();
//
//        try {
//            driver.get("https://www.gov.br/dnit/pt-br/assuntos/planejamento-e-pesquisa/custos-e-pagamentos/custos-e-pagamentos-dnit/sistemas-de-custos/sicro/relatorios-sicro/relatorios-sicro");
//
//            Dimension windowSize = driver.manage().window().getSize();
//            int centerX = windowSize.getWidth() / 2;
//            int centerY = windowSize.getHeight() / 2;
//            Actions actions = new Actions(driver);
//            actions.moveByOffset(centerX, centerY).click().perform();
//
//            List<WebElement> regionLinks = driver.findElements(By.xpath("//a[text()='Nordeste' or text()='Sul' or text()='Centro-Oeste' or text()='Sudeste']"));
//
//            for (WebElement regionLink : regionLinks) 
//            {
//                String regionName = regionLink.getText();
//                regionLink.click();
//
//                List<WebElement> monthElements = driver.findElements(By.xpath("//a[contains(text(), 'Janeiro') or contains(text(), 'Fevereiro') or contains(text(), 'Março') or contains(text(), 'Abril') or contains(text(), 'Maio') or contains(text(), 'Junho') or contains(text(), 'Julho') or contains(text(), 'Agosto') or contains(text(), 'Setembro') or contains(text(), 'Outubro') or contains(text(), 'Novembro') or contains(text(), 'Dezembro')]"));
//                List<String> monthLinks = new ArrayList<>();
//
//                for (WebElement monthElement : monthElements) 
//                {											//href
//                    monthLinks.add(monthElement.getAttribute(".zip"));
//                }
//
//                for (String monthLink : monthLinks) 
//                {
//                    driver.get(monthLink);
//                    // Declaração Throw lançada no método main
//                    Thread.sleep(5000);
//                    String monthName = driver.findElement(By.tagName("h1")).getText();
//
//                    // Cria diretório para a região e o mês, antes de baixar qualquer arquivo
//                    Path directoryPath = Path.of("downloads", regionName, monthName);
//                    Files.createDirectories(directoryPath);
//
//                    List<WebElement> zipLinks = driver.findElements(By.xpath("//a[contains(@href, '.zip')]"));
//                    for (WebElement zipLink : zipLinks) 
//                    {										//href
//                        String fileUrl = zipLink.getAttribute(".zip");
//                        downloadFile(fileUrl, regionName, monthName);
//                    }
//                    driver.navigate().back();
//                }
//                driver.navigate().back();
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            driver.quit();
//        }
//    }
//
//    public static void downloadFile(String fileUrl, String regionName, String monthName) 
//    {
//        try 
//        {
//            HttpClient client = HttpClient.newHttpClient();
//            HttpRequest request = HttpRequest.newBuilder().uri(URI.create(fileUrl)).build();
//            HttpResponse<byte[]> response = client.send(request, HttpResponse.BodyHandlers.ofByteArray());
//
//            String fileName = fileUrl.substring(fileUrl.lastIndexOf("/") + 1);
//            Path filePath = Path.of("downloads", regionName, monthName, fileName);
//            Files.write(filePath, response.body(), StandardOpenOption.CREATE);
//
//            System.out.println("Arquivo baixado: " + filePath);
//        } catch (IOException | InterruptedException e) {
//            e.printStackTrace();
//        }
//    }
//}

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



// VERSÃO DO SICRO FUNCIONANDO TODAS AS REGIÕES
// TODO modificação para fazer downloads somente do mês mais recente (mês atual)
// TODO testar novamente para corrigir erro da pasta "CUSTOS"
// TODO transformar em .jar
public class Testes 
{
//	static String[] estados = 
//	          {
//	              "AC", "AL", "AP", 
//	              "AM", "BA", "CE", 
//	              "DF", "ES", "GO", 
//	              "MA", "MT", "MS", 
//	              "MG", "PA", "PB", 
//	              "PR", "PE", "PI", 
//	              "RJ", "RN", "RS", 
//	              "RO", "RR", "SC", 
//	              "SP", "SE", "TO"
//	          };
	// região norte apenas	
	static String[] estados = 
        {
            "AC", "AP", "AM",
            "PA", "RO", "RR",
            "TO",
        };
	
	
	static int regiao = 0;
	static String currentUser = System.getProperty("user.name");

    public static void main(String[] args) throws InterruptedException 
    {
    	
    	int teste = 0;
    	// TESTE CONSOLE EXTERNO #1
    	SwingUtilities.invokeLater(() -> {
            // Criação do JFrame
            JFrame frame = new JFrame("console");
            frame.setSize(500, 400);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);                       
            
            // TODO botão para baixar SINAPI e SICRO
            
  
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
        
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\walyson.ferreira\\chromedriver.exe");
        System.setProperty("webdriver.http.factory", "jdk-http-client");
        WebDriver driver = new ChromeDriver();
        
        try 
        {
        	
        		
        	
            // Abre a página inicial
            driver.get("https://www.gov.br/dnit/pt-br/assuntos/planejamento-e-pesquisa/custos-e-pagamentos/custos-e-pagamentos-dnit/sistemas-de-custos/sicro/relatorios-sicro/relatorios-sicro");

            // Clica no centro da tela para fechar o popup, caso exista
            Dimension windowSize = driver.manage().window().getSize();
            int centerX = windowSize.getWidth() / 2;
            int centerY = windowSize.getHeight() / 2;
            Actions actions = new Actions(driver);
            actions.moveByOffset(centerX, centerY).click().perform();
            
            // Pega os links das regiões do Brasil
            List<WebElement> regionLinks = driver.findElements(By.xpath("//a[text()='Norte' or text()='nordeste' or text()='centro-oeste' or text()='sudeste' or text()='sul']"));
            
            String[] links = 
            {
            		// NORTE
            		"https://www.gov.br/dnit/pt-br/assuntos/planejamento-e-pesquisa/custos-e-pagamentos/custos-e-pagamentos-dnit/sistemas-de-custos/sicro_antiga/norte/norte",
            		// NORDESTE
            		//"https://www.gov.br/dnit/pt-br/assuntos/planejamento-e-pesquisa/custos-e-pagamentos/custos-e-pagamentos-dnit/sistemas-de-custos/sicro_antiga/nordeste/nordeste",
            		// CENTRO-OESTE
            		//"https://www.gov.br/dnit/pt-br/assuntos/planejamento-e-pesquisa/custos-e-pagamentos/custos-e-pagamentos-dnit/sistemas-de-custos/sicro_antiga/centro-oeste/centro-oeste",
            		// SUDESTE
            		//"https://www.gov.br/dnit/pt-br/assuntos/planejamento-e-pesquisa/custos-e-pagamentos/custos-e-pagamentos-dnit/sistemas-de-custos/sicro_antiga/sudeste/sudeste",
            		// SUL
            		//"https://www.gov.br/dnit/pt-br/assuntos/planejamento-e-pesquisa/custos-e-pagamentos/custos-e-pagamentos-dnit/sistemas-de-custos/sicro_antiga/sul/sul",
            };
            
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        	LocalDateTime now = LocalDateTime.now();
        	
        	// conversão de DateTime para String
	    	String dataAtual = dtf.format(now);
	    	// remoção das barras para espaços em branco
	    	String mesAno = dataAtual.replaceAll("/", "");
	    	// corte exato do mês e ano. Formato: "AAAAMM"
	    	String ano = mesAno.substring(0, 4);
	    	
            for (String link : links) 
            {
            	regiao += 1;
            	System.out.println("Link atual: " + link + "\n------------------");
                // Armazena o link da região
                //String regionHref = regionLink.getAttribute("href");
                
                // Navega para o link da região
                driver.get(link);
                
                // Pega os links dos meses dentro da região
                List<WebElement> monthElements = driver.findElements(By.xpath("//a[contains(@href," + ano + ")]"));

                for(WebElement monthElement : monthElements)
                {
                    String monthHref = monthElement.getAttribute("href");
                    
                    // Navega para o link do mês
                    driver.get(monthHref);
                    //Thread.sleep(5000);
                    // Baixa todos os arquivos .zip na página
                    //List<WebElement> zipLinks = driver.findElements(By.xpath("//a[contains(@href, '.zip')]"));
                    List<WebElement> zipLinks = driver.findElements(By.xpath("//a[substring(@href, string-length(@href) - 3) = '.zip']"));

                    for (WebElement zipLink : zipLinks) 
                    {	
                    	String fileUrl = zipLink.getAttribute("href");
                        //downloadFile(fileUrl);
                    	String arquivoAtual = "";
                    	
                        driver.get(fileUrl);
 
                        
                        arquivoAtual = fileUrl;
                        int tamanhoURL = arquivoAtual.length();

                        arquivoAtual = fileUrl.substring(tamanhoURL - 14);
                        
                        if(regiao == 1)
                        {
                        	System.out.println("REGIÃO NORTE");
                        } 
                        else if(regiao == 2)
                        {
                        	System.out.println("REGIÃO NORDESTE");
                        }
                        else if(regiao == 3)
                        {
                        	System.out.println("REGIÃO CENTRO-OESTE");
                        }
                        else if(regiao == 4)
                        {
                        	System.out.println("REGIÃO SUDESTE");
                        }
                        else if(regiao == 5)
                        {
                        	System.out.println("REGIÃO SUL");
                        }
                        System.out.println("Baixando arquivo: " + arquivoAtual + "...\n------------------");
                        
                     // verificação de download ##########################################################################################
                        
                        
                            
                                String pathDownload = "C:\\Users\\" + currentUser + "\\Downloads";
                                // modelo de arquivos baixados: ac-01-2024 // 4 meses
                                //String nomeArquivo = estado.toLowerCase() + "-" + mes + "-" + ano;
                                
                                String linhaCortada = arquivoAtual;
                                
                                File downloaded = new File(pathDownload, linhaCortada);
                                
                                
                                FluentWait<File> wait = new FluentWait<File>(downloaded)
                                		.withTimeout(Duration.ofMinutes(5))
                                		.pollingEvery(Duration.ofSeconds(5))
                                		.ignoring(Exception.class)
                                		.withMessage("erro no download");
                                
                                boolean isDownloaded = wait.until(f -> f.exists() && f.canRead());
                                
                                
                                if(isDownloaded)
                                {
                                	System.out.println("------------------------------\nArquivo:" + arquivoAtual + " 100% baixado com sucesso!\n------------------------------");
                                }
                                else
                                {
                                	System.out.println("Arquivo não baixado ou não encontrado");
                                }
                            
                        
                        // verificação de download  ##########################################################################################
                        
                        
                        
                        
                          // teste sem Thread.sleep
//                        if(arquivoAtual.contains("sc"))
//                        {
//                        	Thread.sleep(5000);
//                        }
//                        Thread.sleep(1000);
                    }

                    // Volta para a página anterior (da região)
                    driver.navigate().back();
                }

                // Volta para a página inicial das regiões
                driver.navigate().back();
            }
        	 	
            
         
//---------------------------------------------------------------------------------------------------------------------------------------
            
            // TODO MOVER ARQUIVOS BAIXADOS PARA PASTA NO DESKTOP
            String sourceDirectory = "C:\\Users\\" + currentUser + "\\Downloads\\";
            // TODO procurar automaticamente qual é a pasta do usuário atual
            String targetDirectory = "C:\\Users\\" + currentUser + "\\Desktop\\Arquivos SICRO\\Arquivos ZIP";
            String fileNameToFind = ano; // Substitua pelo nome do arquivo a ser procurado               
            
            try
            {
                moveFiles(sourceDirectory, targetDirectory, fileNameToFind);
            }
            catch(IOException e)
            {
                System.err.println("Ocorreu um erro: \n" + e.getMessage());
                e.printStackTrace();
            }
            
            
//---------------------------------------------------------------------------------------------------------------------------------------
            
            //*EXTRAIR* ARQUIVOS MOVIDOS (procura todos os arquivos .zip da pasta e todo o conteúdo de extração vai para outra pasta)
            
	        String origem = "C:\\Users\\" + currentUser + "\\Desktop\\Arquivos SICRO\\Arquivos ZIP"; // path de onde tem arquivos zipados para extração
	        String destino = "C:\\Users\\" + currentUser + "\\Desktop\\Arquivos SICRO\\Resto";  // Caminho de destino onde os arquivos serão extraídos

	        // Listar todos os arquivos no diretório de origem
	        File folder = new File(origem);
	        File[] zipFiles = folder.listFiles((dir, name) -> name.toLowerCase().endsWith(".zip"));

	        // Verificar se há arquivos .zip
	        if (zipFiles != null && zipFiles.length > 0) 
	        {
	            for (File zipFile : zipFiles) 
	            {
	                System.out.println("Extraindo o arquivo: " + zipFile.getName());

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
	                     ZipArchiveInputStream zipStream = new ZipArchiveInputStream(bis)) 
	                {

	                    ZipArchiveEntry ze;
	                    byte[] buffer = new byte[16384];  // 16KB buffer
	                    int readBytes;

	                    while ((ze = zipStream.getNextZipEntry()) != null) 
	                    {
	                        // Obtenha o nome do arquivo dentro do ZIP
	                        String fileName = new String(ze.getName().getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
	                        System.out.println("Nome do arquivo: " + fileName);

	                        // Sanitize the file name to ensure it's valid for the file system
	                        fileName = sanitizeFileName(fileName);
	                        // System.out.println("ARQUIVO COM ALTERAÇÃO: " + fileName);

	                        // Se for um diretório, cria o diretório no destino
	                        File fileOut = new File(destino + File.separator + fileName);
	                        if (ze.isDirectory()) {
	                            fileOut.mkdirs();  // Cria o diretório, se necessário
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
	        } else 
	        {
	            System.out.println("Nenhum arquivo .zip encontrado no diretório especificado.");
	        }
        	
//---------------------------------------------------------------------------------------------------------------------------------------
        	
	         // separar todos os arquivos excel em uma pasta
             String source = "C:\\Users\\" + currentUser + "\\Desktop\\Arquivos SICRO\\Resto";
             // TODO procurar automaticamente qual é a pasta do usuário atual
             String target= "C:\\Users\\" + currentUser + "\\Desktop\\Arquivos SICRO\\Arquivos Excel";
             String find = ".xlsx";             
             
             try
             {
                 moveFiles(source, target, find);
             }
             catch(IOException e)
             {
                 System.err.println("Ocorreu um erro: \n" + e.getMessage());
                 e.printStackTrace();
             }
        	
        	
//---------------------------------------------------------------------------------------------------------------------------------------
             // separar todos os arquivos Sintéticos de Composições de Custos
             String s = "C:\\Users\\" + currentUser + "\\Desktop\\Arquivos SICRO\\Arquivos Excel";
             String t = "C:\\Users\\" + currentUser + "\\Desktop\\Arquivos SICRO\\CUSTOS";
             String f = "Sint_tico de Composi__es de Custos";
             
             try
             {
                 moveFiles(s, t, f);
             }
             catch(IOException e)
             {
                 System.err.println("Ocorreu um erro: \n" + e.getMessage());
                 e.printStackTrace();
             }
//---------------------------------------------------------------------------------------------------------------------------------------
          // separar todos os arquivos Sintéticos de Composições de Custos
             s = "C:\\Users\\" + currentUser + "\\Desktop\\Arquivos SICRO\\Arquivos Excel";
             t = "C:\\Users\\" + currentUser + "\\Desktop\\Arquivos SICRO\\EQUIPAMENTOS";
             f = "Sint_tico de Equipamentos";
             
             try
             {
                 moveFiles(s, t, f);
             }
             catch(IOException e)
             {
                 System.err.println("Ocorreu um erro: \n" + e.getMessage());
                 e.printStackTrace();
             }
//---------------------------------------------------------------------------------------------------------------------------------------
             s = "C:\\Users\\" + currentUser + "\\Desktop\\Arquivos SICRO\\Arquivos Excel";
             t = "C:\\Users\\" + currentUser + "\\Desktop\\Arquivos SICRO\\MATERIAIS";
             f = "Relat_rio Sint_tico de Materiais";
             
             try
             {
                 moveFiles(s, t, f);
             }
             catch(IOException e)
             {
                 System.err.println("Ocorreu um erro: \n" + e.getMessage());
                 e.printStackTrace();
             }
//---------------------------------------------------------------------------------------------------------------------------------------
             // TODO separar, dentro de cada pasta ("CUSTOS, EQUIPAMENTOS, MATERIAIS") os estados de cada arquivo em suas respectivas pastas
             
             for(String estado : estados)
             {
            	 s = "C:\\Users\\" + currentUser + "\\Desktop\\Arquivos SICRO\\MATERIAIS";
                 t = "C:\\Users\\" + currentUser + "\\Desktop\\Arquivos SICRO\\MATERIAIS\\" + estado;
                 f = estado;
                 
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
             
             for(String estado : estados)
             {
            	 s = "C:\\Users\\" + currentUser + "\\Desktop\\Arquivos SICRO\\EQUIPAMENTOS";
                 t = "C:\\Users\\" + currentUser + "\\Desktop\\Arquivos SICRO\\EQUIPAMENTOS\\" + estado;
                 f = estado;
                 
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
             
             for(String estado : estados)
             {
            	 s = "C:\\Users\\" + currentUser + "\\Desktop\\Arquivos SICRO\\CUSTOS";
            	 t = "C:\\Users\\" + currentUser + "\\Desktop\\Arquivos SICRO\\CUSTOS\\" + estado;
            	 f = estado;
            	 
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
             // separar arquivos por estado "Arquivos Excel"
             for(String estado : estados)
             {
            	 s = "C:\\Users\\" + currentUser + "\\Desktop\\Arquivos SICRO\\Arquivos Excel";
            	 t = "C:\\Users\\" + currentUser + "\\Desktop\\Arquivos SICRO\\Arquivos Excel\\" + estado;
            	 f = estado;
            	 
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
             System.out.println("PROGRAMA ENCERRADO!");
//---------------------------------------------------------------------------------------------------------------------------------------
        } finally
        {
            driver.quit();
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
            
            // Salva o arquivo na pasta "Downloads"
            String fileName = fileUrl.substring(fileUrl.lastIndexOf("/") + 1);
            Path filePath = Path.of("Downloads", fileName);
            Files.createDirectories(filePath.getParent());
            Files.write(filePath, response.body(), StandardOpenOption.CREATE);

            System.out.println("Arquivo baixado: " + fileName + "\nno caminho: " + filePath);
        } catch (IOException | InterruptedException e) 
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
	private static String sanitizeFileName(String fileName)
    {
        // Substitui caracteres inválidos com um caractere seguro, como um underscore.
        return fileName.replaceAll("[<>:\"/\\|?*]", "_");
    }
}

