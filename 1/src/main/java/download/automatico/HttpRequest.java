package download.automatico;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.Socket;
import java.net.URI;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.net.ssl.HttpsURLConnection;

public class HttpRequest 
{
	public static void main(String[] args) 
	{
		try 
		{
            // Create a URI object for the website URL
            URI url = new URI("https://www.caixa.gov.br/site/paginas/downloads.aspx#categoria_868");

            // Check if the Desktop is supported on the current platform
            if(Desktop.isDesktopSupported()) 
            {
                // Get the desktop instance
                Desktop desktop = Desktop.getDesktop();

                // Open the URL in the default web browser
                desktop.browse(url);
                
                Thread.sleep(1000);
                
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
	   	   		 
		   	   	for(String estado : estados) 
	            {
		   	   		
			   	   	String desoneradoLink = "https://www.caixa.gov.br/Downloads/sinapi-a-partir-jul-2009-" + estado + "/SINAPI_ref_Insumos_Composicoes_" + estado + "_" + anoMes + "_Desonerado.zip";
			   	   	URI uriUm = new URI(desoneradoLink);
	                String naoDesoneradoLink = "https://www.caixa.gov.br/Downloads/sinapi-a-partir-jul-2009-" + estado + "/SINAPI_ref_Insumos_Composicoes_" + estado + "_" + anoMes + "_NaoDesonerado.zip";
	                URI uriDois = new URI(naoDesoneradoLink);
	                
	                String urlAtual = "";
	                System.out.println("LINK DA URL ATUAL: " + urlAtual);
	                
	                
	                System.out.println("Hora atual: " + dtf.format(now)); 
	                desktop.browse(uriUm);
	                Thread.sleep(5000);
	                desktop.browse(uriDois);
	                Thread.sleep(5000);
	            }
		   	   	
            }
	   	   	else 
            {
                System.out.println("Desktop is not supported on this platform.");
            }
	   	  
        } 
		catch (Exception e) 
		{
            e.printStackTrace();
        }

	}
	
	
}