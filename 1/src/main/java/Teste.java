import org.apache.log4j.BasicConfigurator;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class Teste {
    public static void main(String[] args) {
    	BasicConfigurator.configure();
        
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\walyson.ferreira\\Desktop\\Programas-para-instalar\\chromedriver-win64\\chromedriver.exe");
        System.setProperty("webdriver.http.factory", "jdk-http-client");
        WebDriver driver = new ChromeDriver();

        try 
        {
            // Abre a página inicial
            driver.get("https://www.gov.br/dnit/pt-br/assuntos/planejamento-e-pesquisa/custos-e-pagamentos/custos-e-pagamentos-dnit/sistemas-de-custos/sicro/relatorios-sicro/relatorios-sicro");

            // para clicar no centro da tela (ignorar mensagem popup)
            Dimension windowSize = driver.manage().window().getSize();
            int centerX = windowSize.getWidth() / 2;
            int centerY = windowSize.getHeight() / 2;

            // Clica no centro da tela
            Actions actions = new Actions(driver);
            actions.moveByOffset(centerX, centerY).click().perform();

            String linkHref = driver.findElement(By.xpath("//a[contains(@href, 'norte')]")).getAttribute("href");
	         // linkHref para fazer o download diretamente com o HttpClient
	         downloadFile(linkHref);
	         
	         
	         


	        // "//a[text()='Nordeste' or text()='Sul' or text()='Centro-Oeste' or text()='Sudeste' ]"
            // Pega os links das regiões											// remover regiões que já correram
            List<WebElement> regionLinks = driver.findElements(By.xpath("//a[text()='Norte']"));
            
            for (WebElement regionLink : regionLinks) 
            {
                regionLink.click(); // Clica no link da região para abrir a página dos meses

                // Pega os hrefs dos meses dentro da região e armazena em uma lista de Strings
                
              //List<WebElement> monthElements = driver.findElements(By.xpath("//a[contains(text(), 'Janeiro') or contains(text(), 'Fevereiro') or contains(text(), 'Março') or contains(text(), 'Abril') or contains(text(), 'Maio') or contains(text(), 'Junho') or contains(text(), 'Julho') or contains(text(), 'Agosto') or contains(text(), 'Setembro') or contains(text(), 'Outubro') or contains(text(), 'Novembro') or contains(text(), 'Dezembro')]"));
                
              //List<WebElement> monthElements = driver.findElements(By.xpath("//a[contains(translate(text(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'janeiro') or contains(translate(text(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'fevereiro') or contains(translate(text(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'março') or contains(translate(text(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'abril') or contains(translate(text(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'maio') or contains(translate(text(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'junho') or contains(translate(text(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'julho') or contains(translate(text(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'agosto') or contains(translate(text(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'setembro') or contains(translate(text(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'outubro') or contains(translate(text(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'novembro') or contains(translate(text(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'dezembro')]"));

                List<WebElement> monthElements = driver.findElements(By.xpath("//a[(contains(translate(text(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'janeiro') or contains(translate(text(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'fevereiro') or contains(translate(text(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'março') or contains(translate(text(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'abril') or contains(translate(text(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'maio') or contains(translate(text(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'junho') or contains(translate(text(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'julho') or contains(translate(text(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'agosto') or contains(translate(text(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'setembro') or contains(translate(text(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'outubro') or contains(translate(text(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'novembro') or contains(translate(text(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'dezembro')) and contains(@href, 'tocantins')]"));

                List<String> monthLinks = new ArrayList<>();
                
                for (WebElement monthElement : monthElements) 
                {
                    monthLinks.add(monthElement.getAttribute("href"));
                }

                // Itera sobre cada link dos meses usando os hrefs
                for (String monthLink : monthLinks) 
                {
                    driver.get(monthLink); // Navega para o link do mês

                    																			 //.zip	//remover ,'2017' // REFAZER CONDIÇÕES PARA DOWNLOAD
                    List<WebElement> zipLinks = driver.findElements(By.xpath("//a[contains(@href, '.zip')]"));

                    
                    for (WebElement zipLink : zipLinks) 
                    {
                        String fileUrl = zipLink.getAttribute("href");
                        downloadFile(fileUrl); // Função de download
                    }

                    // Volta para a página anterior (da região)
                    driver.navigate().back();
                }

                // Volta para a página inicial das regiões
                driver.navigate().back();
            }
        } 
        finally 
        {
            driver.quit();
        }
    }

    // Método para baixar o arquivo
    public static void downloadFile(String fileUrl) {
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder().uri(URI.create(fileUrl)).build();

            HttpResponse<byte[]> response = client.send(request, HttpResponse.BodyHandlers.ofByteArray());

            // Salva o arquivo na pasta
            String fileName = fileUrl.substring(fileUrl.lastIndexOf("/") + 1);
            Path filePath = Path.of("downloads/NORTE/TOCANTINS", fileName);
            Files.createDirectories(filePath.getParent());
            Files.write(filePath, response.body(), StandardOpenOption.CREATE);

            System.out.println("Arquivo baixado: " + fileName + "\n no caminho: " + filePath);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}


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
//    public static void main(String[] args) {
//        BasicConfigurator.configure();
//        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Wally\\Desktop\\chromedriver-win64\\chromedriver.exe");
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
//            for (WebElement regionLink : regionLinks) {
//                String regionName = regionLink.getText();
//                regionLink.click();
//
//                List<WebElement> monthElements = driver.findElements(By.xpath("//a[contains(text(), 'Janeiro') or contains(text(), 'Fevereiro') or contains(text(), 'Março') or contains(text(), 'Abril') or contains(text(), 'Maio') or contains(text(), 'Junho') or contains(text(), 'Julho') or contains(text(), 'Agosto') or contains(text(), 'Setembro') or contains(text(), 'Outubro') or contains(text(), 'Novembro') or contains(text(), 'Dezembro')]"));
//                List<String> monthLinks = new ArrayList<>();
//
//                for (WebElement monthElement : monthElements) {
//                    monthLinks.add(monthElement.getAttribute("href"));
//                }
//
//                for (String monthLink : monthLinks) {
//                    driver.get(monthLink);
//                    String monthName = driver.findElement(By.tagName("h1")).getText();
//
//                    // Cria diretório para a região e o mês, antes de baixar qualquer arquivo
//                    Path directoryPath = Path.of("downloads", regionName, monthName);
//                    Files.createDirectories(directoryPath);
//
//                    List<WebElement> zipLinks = driver.findElements(By.xpath("//a[contains(@href, '.zip')]"));
//                    for (WebElement zipLink : zipLinks) {
//                        String fileUrl = zipLink.getAttribute("href");
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
//    public static void downloadFile(String fileUrl, String regionName, String monthName) {
//        try {
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


