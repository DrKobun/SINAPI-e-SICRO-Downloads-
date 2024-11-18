/*
 * 	@author DrKobun github.com/DrKobun
 */


import org.apache.commons.compress.archivers.zip.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.BasicFileAttributes;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.FutureTask;

import org.apache.log4j.BasicConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import codes.TextAreaOutputStream;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PrintStream;

// TODO SEPARAR ARQUIVOS .xlsx EM PASTAS DE CADA ESTADO

// ESTE PROGRAMA POSSUI CONSOLE EM JANELA SEPARADA!
public class Testes 
{
	// nome do Path do usuário
	static String currentUser = System.getProperty("user.name");

	public static void main(String[] args) 
	{
		
	
		
		
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
            
            
            JFrame botao = new JFrame();
            // Set up the frame
            botao.setTitle("Close Frame Example");
            botao.setSize(300, 200);
            botao.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            botao.setLocationRelativeTo(null);

            // Create the button
            JButton closeButton = new JButton("TESTE");
            
            // Add action listener to close the frame when clicked
            closeButton.addActionListener(new ActionListener() 
            {
    			
    			@Override
    			public void actionPerformed(ActionEvent e) 
    			{
    				int validador = 0;
    				// TODO Auto-generated method stub
    				BasicConfigurator.configure();
    				
    				//TODO método para procurar automaticamente o caminho do ChromeDriver.exe
    				
    				//solução provisória(EM USO): colocar o arquivo do ChromeDriver diretamente na pasta do usuário atual
    		        System.setProperty("webdriver.chrome.driver", "C:\\Users\\" + currentUser + "\\chromedriver.exe");
    		        System.setProperty("webdriver.http.factory", "jdk-http-client");
    		        WebDriver driver = new ChromeDriver();

    		        try 
    		        {
    		            
    		            driver.get("https://www.caixa.gov.br/site/paginas/downloads.aspx#categoria_868");

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
//    		            String[] estados = 
//    		            {
//    		            		"AC","AL","AP"
//    		            };
    		      
    		            // Para cada estado, gera o link de download
    		            for(String estado : estados) 
    		            {
    		            	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
    		            	LocalDateTime now = LocalDateTime.now();
    		            	
    		            	// conversão de DateTime para String
    		            	String dataAtual = dtf.format(now);
    		            	// remoção das barras para espaços em branco
    		            	String mesAno = dataAtual.replaceAll("/", "");
    		            	// corte exato do mês e ano. Formato: "AAAAMM"
    		            	String anoMes = mesAno.substring(0, 6);
    		            	
    		            	
    		                // modelo de links para navegação e download
//    		            	String desoneradoLink = "https://www.caixa.gov.br/Downloads/sinapi-a-partir-jul-2009-" + estado + "/SINAPI_ref_Insumos_Composicoes_" + estado + "_202409_Desonerado.zip";
//    		            	String naoDesoneradoLink = "https://www.caixa.gov.br/Downloads/sinapi-a-partir-jul-2009-" + estado + "/SINAPI_ref_Insumos_Composicoes_" + estado + "_202409_NaoDesonerado.zip";
    		            	
    		            	String desoneradoLink = "https://www.caixa.gov.br/Downloads/sinapi-a-partir-jul-2009-" + estado + "/SINAPI_ref_Insumos_Composicoes_" + estado + "_" + anoMes + "_Desonerado.zip";
    		            	String naoDesoneradoLink = "https://www.caixa.gov.br/Downloads/sinapi-a-partir-jul-2009-" + estado + "/SINAPI_ref_Insumos_Composicoes_" + estado + "_" + anoMes + "_NaoDesonerado.zip";
    		                
    		                               
    		                System.out.println("Hora atual: " + dtf.format(now)); 
    		                String urlAtual = driver.getCurrentUrl();
    		                
    		                
    		                System.out.println("URL atual: " + urlAtual);
    		                // resgara a URL atual para verificar se a página existe ou não
    		                driver.get(desoneradoLink);
    		                System.out.println("Link da URL atual *DESONERADO*: " + desoneradoLink);                
    		                Thread.sleep(5000);
    		                
    		                driver.get(naoDesoneradoLink);
    		                System.out.println("Link da URL atual *NÃO DESONERADO*: " + naoDesoneradoLink);
    		                Thread.sleep(5000);
    		                
    		                
    		                urlAtual = driver.getCurrentUrl();
    		                if(urlAtual.contains("PageNotFoundError"))
    		                {
    		                	validador++;
    		                	break;
    		                }
    		                
    		                //aviso de download
    		                if(estado == "TO")
    		                {
    		                	Thread.sleep(10000); // espera 10 segundos pra garantir que todos os downloads terminaram
    		                }
    		                System.out.println("Arquivos baixados para o estado: " + estado);
    		                System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
    		            }
    		            	if(validador == 0)
    		            	{
    		            		System.out.println("Todos os estados foram baixados!");
    		            		
    		            	}
    		            	else
    		            	{
    		            		System.out.println("Programa encerrado!\nNão há nenhum download disponível na data atual!");
    		            		System.out.println("FECHANDO EM 20 SEGUNDOS!");
    		            		Thread.sleep(20000);
    		            		
    		            		frame.dispose();
    		            		botao.dispose();
    		            		driver.close();
   		            		
    		            	}
    			            Thread.sleep(5000);
    			        }
    			        catch(Exception e1)
    			        {
    			        	System.out.println("ERRO: " + e1);
    			        }
    		                
    		//--------------------------------------------------------------------------------------------------------------------------------                
    		if(validador == 0)
    		{
    			        
    			                // TODO MOVER ARQUIVOS BAIXADOS PARA PASTA NO DESKTOP
    			                String sourceDirectory = "C:\\Users\\" + currentUser + "\\Downloads\\";
    			                // TODO procurar automaticamente qual é a pasta do usuário atual
    			                String targetDirectory = "C:\\Users\\" + currentUser + "\\Desktop\\RelatóriosTeste\\Arquivos ZIP";
    			                String fileNameToFind = "SINAPI"; // Substitua pelo nome do arquivo a ser procurado               
    			                
    			                try
    			                {
    			                    moveFiles(sourceDirectory, targetDirectory, fileNameToFind);
    			                }
    			                catch(IOException e1)
    			                {
    			                    System.err.println("Ocorreu um erro: \n" + e1.getMessage());
    			                    e1.printStackTrace();
    			                }
    		               
    		//------------------------------------------------------------------------------------------------------------------------------  
    		                //*EXTRAIR* ARQUIVOS MOVIDOS (procura todos os arquivos .zip da pasta e todo o conteúdo de extração vai para outra pasta)
    		    
    		    	        String origem = "C:\\Users\\" + currentUser + "\\Desktop\\RelatóriosTeste\\Arquivos ZIP"; // path de onde tem arquivos zipados para extração
    		    	        String destino = "C:\\Users\\" + currentUser + "\\Desktop\\RelatóriosTeste\\Resto";  // Caminho de destino onde os arquivos serão extraídos

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
    								catch (IOException e1) 
    								{
    									// TODO Auto-generated catch block
    									e1.printStackTrace();
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
    		    	                } catch (IOException e1) 
    		    	                {
    		    	                    e1.printStackTrace();
    		    	                }
    		    	            }
    		    	        } else 
    		    	        {
    		    	            System.out.println("Nenhum arquivo .zip encontrado no diretório especificado.");
    		    	        }
    		                
    		//---------------------------------------------------------------------------------------------------------
    		    	        //Mover apenas arquivos selecionados APÓS a extração
    		    	        String sourceDirector = "C:\\Users\\" + currentUser + "\\Desktop\\RelatóriosTeste"; // Substitua pelo caminho da pasta de origem
    		                //TODO procurar automaticamente qual é a pasta do usuário atual
    		                String targetDirector = "C:\\Users\\" + currentUser + "\\Desktop\\RelatóriosTeste\\Arquivos Excel"; // Substitua pelo caminho da pasta de destino
    		                // Nome do arquivo para ser movido
    		                String fileNameToFin = ".xlsx"; // busca por todos os arquivos Excel
    		                
    		                
    		                // MOVER APENAS ARQUIVOS BAIXADOS
    		            	 try
    		            	 {
    			                 moveFiles(sourceDirector, targetDirector, fileNameToFin);

    		            	 }
    		            	 catch(IOException e1)
    		            	 {
    		            		 System.out.println("OCORREU UM ERRO: " + e1);
    		            	 }
    		            	 
    		//---------------------------------------------------------------------------------------------------------
    		             // *SEPARAR E MOVER TODOS OS ARQUIVOS DE CADA ESTADO*
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
    		            	 
//    			            String[] estados = 
//    			            {
//    			            	 "AC", "AL", "AP"
//    			            };
    		             	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
    			         	LocalDateTime now = LocalDateTime.now();
    			         	
    			         	// conversão de DateTime para String
    			         	String dataAtual = dtf.format(now);
    			         	// remoção das barras para espaços em branco
    			         	String mesAno = dataAtual.replaceAll("/", "");
    			         	// corte exato do mês e ano. Formato: "AAAAMM"
    			         	String ano = mesAno.substring(0, 4);
    			         	
    		            	 for(String estado : estados)
    		            	 {
    		            		 
    		            			 String fonte = "C:\\Users\\" + currentUser + "\\Desktop\\RelatóriosTeste\\Arquivos Excel";
    		            			 String alvo = "C:\\Users\\" + currentUser + "\\Desktop\\RelatóriosTeste\\Arquivos Excel\\" + estado;
    		            			 String procurar = "_" + estado + "_" + ano;
    		            			 
    		            			 
    		            			 try 
    		            			 {
    		            				 moveFiles(fonte, alvo, procurar);
    		            				 System.out.println("Arquivo movido para a pasta: " + estado);
    		            				 if(estado == "TO")
    		            				 {
    		            					 System.out.println("Programa encerrado!");
    		            				 }
    		            			 }
    		            			 catch(IOException e1)
    		            			 {
    		            				 System.out.println("OCORREU UM ERRO: " + e1);
    		            			 }
    		            	 } // fim do foreach dos estados
    		//---------------------------------------------------------------------------------------------------------
    		            	 // mover arquivos dentro da pasta dos estados (INSUMOS E SINTÉTICOS)
    		  	         	 // arquivos para serem movidos
    		            	 // 1 - SINAPI_Custo_Ref_Composicoes_Sintetico_AC_202409_NaoDesonerado	 	* NÃO DESONERADO (sintético)		
    		            	 // 2 - SINAPI_Preco_Ref_Insumos_AC_202409_Desonerado						* DESONERADO (insumos)
    		            	 
    		            	 // 3 - SINAPI_Custo_Ref_Composicoes_Sintetico_AC_202409_Desonerado			* DESONERADO (sintético)
    		            	 // 4 - SINAPI_Preco_Ref_Insumos_AC_202409_NaoDesonerado					* NÃO DESONERADO (insumos)
    		            	 
    		            	 // obs: todos possuem o padrão "_AC_202409_(Desonerado/NaoDesonerado).xlsx"
    		            	 
    		            	 
    		            	 
    		              	 for(String estado : estados)
    		              	 {
    		              		 	 // loop para rodar 4x
    		              		 	 for(int i = 0; i < 4; i++)
    		              		 	 {
    		              		 		 // INSUMOS
    		              		 		 if(i % 2 == 0)
    		              		 		 {
    		              		 			 String fonte = "C:\\Users\\" + currentUser + "\\Desktop\\RelatóriosTeste\\Arquivos Excel";
    		              		 			 String alvo = "C:\\Users\\" + currentUser + "\\Desktop\\RelatóriosTeste\\Arquivos Excel\\" + estado + "\\SINTETICOS";
    		              		 			 // alterar arquivo para ser movido
    		              		 			 String procurar = "Sintetico_" + estado + "_" + ano;
    		              		 			 
    		              		 			 try 
    		                 		 		 {
    		                 		 			 moveFiles(fonte, alvo, procurar);
    		                 		 			 System.out.println("Arquivo *INSUMO* movido para a pasta: " + estado);
    		                 		 		 }
    		                 		 		 catch(IOException e1)
    		                 		 		 {
    		                 		 			 System.out.println("OCORREU UM ERRO: " + e1);
    		                 		 		 }
    		              		 			 
    		              		 		 }
    		              		 		 // SINTÉTICO
    		              		 		 else
    		              		 		 {
    		              		 			 String fonte = "C:\\Users\\" + currentUser + "\\Desktop\\RelatóriosTeste\\Arquivos Excel";
    		              		 			 // SINAPI_Preco_Ref_Insumos_AC_202409_NaoDesonerado
    		              		 			 String alvo = "C:\\Users\\" + currentUser + "\\Desktop\\RelatóriosTeste\\Arquivos Excel\\" + estado + "\\INSUMOS";
    		              		 			 // alterar arquivo para ser movido
    		              		 			 String procurar = "Insumos_" + estado + "_" + ano;
    		              		 			 
    		              		 			 try 
    		                 		 		 {
    		                 		 			 moveFiles(fonte, alvo, procurar);
    		                 		 			 System.out.println("Arquivo *SINTÉTICO* movido para a pasta: " + estado);

    		                 		 		 }
    		                 		 		 catch(IOException e1)
    		                 		 		 {
    		                 		 			 System.out.println("OCORREU UM ERRO: " + e1);
    		                 		 		 }
    		              		 			 
    		              		 		 }
    		              		 	 } // fim do for loop
    		              		 	 
    		              		 	 
    		              		 	 
    		              	}// fim do foreach dos estados
    		              	 String fonte = "C:\\Users\\" + currentUser + "\\Desktop\\RelatóriosTeste\\Arquivos Excel";
    		      			 String alvo = "C:\\Users\\" + currentUser + "\\Desktop\\RelatóriosTeste\\Arquivos Excel\\ANALÍTICOS DE TODOS OS ESTADOS";
    		      			 // alterar arquivo para ser movido
    		      			 String procurar = "Analitico";
    			 			 
    			 			 try 
    				 		 {
    				 			 moveFiles(fonte, alvo, procurar);
    				 			 System.out.println("Arquivos *ANALÍTICO* movidos para a pasta: ANALÍTICOS DE TODOS OS ESTADOS\nCaminho: " + alvo);
    				 		 }
    				 		 catch(IOException e1)
    				 		 {
    				 			 System.out.println("OCORREU UM ERRO: " + e1);
    				 		 }
    		}// fim do validador == 0            		 	 
//    		 								 código modelo *NÃO APAGAR*
//    		              		 		 String fonte = "C:\\Users\\" + currentUser + "\\Desktop\\RelatóriosTeste\\Arquivos Excel";
//    		              		 		 String alvo = "C:\\Users\\" + currentUser + "\\Desktop\\RelatóriosTeste\\Arquivos Excel\\" + estado + "";
//    		              		 		 String procurar = "_" + estado + "_" + ano;             			 
//    		              		 		               		 		 
//    		              		 		 try 
//    		              		 		 {
//    		              		 			 moveFiles(fonte, alvo, procurar);
//    		              		 			 System.out.println("Arquivo movido para a pasta: " + estado);
//    		              		 			 if(estado == "AP")
//    		              		 			 {
//    		              		 				 System.out.println("Programa encerrado!");
//    		              		 			 }
//    		              		 		 }
//    		              		 		 catch(IOException e)
//    		              		 		 {
//    		              		 			 System.out.println("OCORREU UM ERRO: " + e);
//    		              		 		 }

    		              		 	  
    		              		 	 
    		              	 
    			}
    		});

//            int counter = 0;
//            counter++;
//            if(counter == 1)
//            {
//            	botao.dispose();
//            }
            // Add the button to the frame
            botao.add(closeButton);

            // Make the frame visible
            botao.setVisible(true);	
            
        });
    	
		
//---------------------------------------------------------------------------------------------------------
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