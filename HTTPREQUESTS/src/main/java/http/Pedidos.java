package http;

import java.net.Authenticator;
import java.net.InetSocketAddress;
import java.net.ProxySelector;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpClient.Redirect;
import java.net.http.HttpClient.Version;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;


public class Pedidos
{
	public static void main(String[] args) 
	{
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder()
		      .uri(URI.create("http://google.com/"))
		      .build();
		client.sendAsync(request, BodyHandlers.ofString())
		      .thenApply(HttpResponse::body)
		      .thenAccept(System.out::println)
		      .join();
		
		//System.out.println(client);
		HttpClient cliente = HttpClient.newBuilder()
			      .version(Version.HTTP_2)
			      .followRedirects(Redirect.NORMAL)
			      .proxy(ProxySelector.of(new InetSocketAddress("www-proxy.com", 8080)))
			      .authenticator(Authenticator.getDefault())
			      .build();
	}
}
