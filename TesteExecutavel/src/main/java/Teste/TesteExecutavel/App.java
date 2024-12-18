package Teste.TesteExecutavel;


import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
//import org.apache.log4j.BasicConfigurator;
//import org.openqa.selenium.By;
//import org.openqa.selenium.Dimension;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.interactions.Actions;


public class App 
{
	public static void main(String[] args) 
	{
    	
		//BasicConfigurator.configure();
        
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\walyson.ferreira\\Desktop\\Programas-para-instalar\\chromedriver-win64\\chromedriver.exe");
        System.setProperty("webdriver.http.factory", "jdk-http-client");
        //WebDriver driver = new ChromeDriver();

        try 
        {
            
            //driver.get("https://www.caixa.gov.br/site/paginas/downloads.aspx#categoria_868");

            // Espera até que os links estejam visíveis (pode ser necessário ajustar dependendo da página)
            Thread.sleep(5000);  

            
            String[] estados = 
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
            	//https://www.caixa.gov.br/Downloads/sinapi-a-partir-jul-2009-ac/SINAPI_ref_Insumos_Composicoes_AC_202409_Desonerado.zip
            	//https://www.caixa.gov.br/Downloads/sinapi-a-partir-jul-2009-al/SINAPI_ref_Insumos_Composicoes_AL_202409_NaoDesonerado.zip
                String desoneradoLink = "https://www.caixa.gov.br/Downloads/sinapi-a-partir-jul-2009-" + estado + "/SINAPI_ref_Insumos_Composicoes_" + estado + "_" + anoMes + "_Desonerado.zip";
                String naoDesoneradoLink = "https://www.caixa.gov.br/Downloads/sinapi-a-partir-jul-2009-" + estado + "/SINAPI_ref_Insumos_Composicoes_" + estado + "_" + anoMes + "_NaoDesonerado.zip";
                
                
                // resgara a URL atual para verificar se a página existe ou não
                //String urlAtual = driver.getCurrentUrl();
                //System.out.println("LINK DA URL ATUAL: " + urlAtual);
                
//                if(urlAtual.contains("PageNotFoundError"))
//                {
//                	break;
//                }
                
                //Baixar os dois arquivos para cada estado
                //downloadFile(desoneradoLink);
                //downloadFile(naoDesoneradoLink);
                
                System.out.println("Hora atual: " + dtf.format(now)); 
                //driver.get(desoneradoLink);
                Thread.sleep(5000);
                //driver.get(naoDesoneradoLink);
                Thread.sleep(5000);

                // aviso de download 
                System.out.println("Arquivos baixados para o estado: " + estado);
                
                
            }
            System.out.println("Programa encerrado!");
        }
        catch(Exception e)
        {
        	System.out.println("ERRO: " + e);
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
            Path filePath = Path.of("SINAPI", fileName);
            Files.createDirectories(filePath.getParent());
            Files.write(filePath, response.body(), StandardOpenOption.CREATE);

            System.out.println("Arquivo baixado: " + fileName + "\n no caminho: " + filePath);
        } 
        catch (IOException | InterruptedException e) 
        {
            e.printStackTrace();
        }
    }
}
