package codes;

import javax.swing.*;
import java.awt.*;
import java.io.PrintStream;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentListener;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.BasicFileAttributes;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveInputStream;
import org.apache.log4j.BasicConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.FluentWait;


public class TestesDescartavel 
{
	static int regiao = 0;
	static String currentUser = System.getProperty("user.name");
	static boolean baixado;
	
    public static void main(String[] args) 
    {
        SwingUtilities.invokeLater(() -> 
        {
        	 // Criar o JFrame principal
            JFrame tela = new JFrame("Janela de Downloads");
            tela.setVisible(true);
            tela.setSize(500, 500);
            tela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            tela.setLocation(710, 290);
            
            JPanel panel = new JPanel();
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS)); // Layout vertical
            tela.add(panel);

            
            JButton button = new JButton("SINAPI");
            panel.add(button);
            
            panel.add(Box.createVerticalStrut(10));
            
            JButton sicro = new JButton("SICRO");
            panel.add(sicro);

            
            panel.add(Box.createVerticalStrut(10));
            
            JTextArea textArea = new JTextArea();
            textArea.setEditable(false);
            textArea.setWrapStyleWord(true);
            textArea.setSize(400, 400);
            textArea.setBackground(Color.gray);
            if(baixado == true)
            {
            	textArea.setForeground(Color.green);
            }
            else
            {
            	textArea.setForeground(Color.white);
            }
            	
            	
            JScrollPane scrollPane = new JScrollPane(textArea);
            panel.add(scrollPane);

            PrintStream printStream = new PrintStream(new TextAreaOutputStream(textArea));
            System.setOut(printStream);
            System.setErr(printStream);

            button.addActionListener(new ActionListener() 
            {
                public void actionPerformed(ActionEvent e) 
                {
                    new TaskWorker(button, 1).execute();
                    button.setEnabled(false);
                    
                }
            });
            
            sicro.addActionListener(new ActionListener() 
            {
                public void actionPerformed(ActionEvent e) 
                {
                    new TaskWorker(sicro, 2).execute();
                    sicro.setEnabled(false);
                    
                }
            });
        });
    }
	    
//Usando SwingWorker para a execução assíncrona
public static class TaskWorker extends SwingWorker<Void, Void> 
{
	private JButton button; // Referência para o botão
	private int taskNumber;

    // Construtor para receber o botão
    public TaskWorker(JButton button, int taskNumber) 
    {
        this.button = button;
        this.taskNumber = taskNumber;
    }
    
    @Override
    protected Void doInBackground() throws Exception 
    {
        try 
        {
        	if(taskNumber == 1)
        	{
        		executar(baixado);
        	}
        	else if(taskNumber == 2)
        	{
        		sicro();
        	}
		} 
        catch (Exception e) 
        {
			System.out.println("Ocorreu um erro: " + e);
			
		}
        return null; 
    }

    @Override
    protected void done() 
    {
        System.out.println("PROGRAMA ENCERRADO!");
        button.setEnabled(true);
    }
}

    public static void executar(boolean baixado) 
	{
//	@Override
//	public void actionPerformed(ActionEvent e) 
//	{
		int validador = 0;
	 
//		SwingUtilities.invokeLater(() ->
//		{
//            // Criação do JFrame
//            JFrame frame = new JFrame("console");
//            frame.setSize(500, 400);
//            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);                     
//            
//            // TODO botão para baixar SINAPI e SICRO
//            
//            // Criação do JTextArea para exibir o console
//            JTextArea textArea = new JTextArea();
//            textArea.setVisible(true);
//            textArea.setEditable(false);
//            JScrollPane scrollPane = new JScrollPane(textArea);
//            frame.add(scrollPane, BorderLayout.CENTER);
//            
//            //configuraçõoes para localização e tamanho da janela (janela do console)
//            frame.setLocation(960, 0); // aqui é Localização (onde a janela vai ficar) o primeiro argumento é a largura
//            frame.setSize(960, 1080);  // aqui é tamanho (qual o espaço que a janela vai ocupar)
//            
//            // Redirecionar o System.out e System.err para o JTextArea
//            PrintStream printStream = new PrintStream(new TextAreaOutputStream(textArea));
//            System.setOut(printStream);
//            System.setErr(printStream);
//
//            frame.setVisible(true);
//        });
//    	
//		BasicConfigurator.configure();
		
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
//            String[] estados = 
//            {
//            		"AC","AL","AP"
//            };
      
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
    	    	String ano = mesAno.substring(0, 4);
    	    	String mes = mesAno.substring(4, 6);
    	    	
    	    	int mesInteiro = Integer.parseInt(mes);
    	    	
    	    	
    	    	if(mesInteiro != 1)
    	    	{
    	    		mesInteiro -= 1;
    	    	} 
    	    	else
    	    	{
    	    		mesInteiro = 12;
    	    	}
    	    	//ano = String.valueOf(ano);
    	    	String mesConvertido = String.valueOf(mesInteiro);
    	    	String anoMes = ano + mesConvertido;
    	    	
                // modelo de links para navegação e download
            	//String desoneradoLink = "https://www.caixa.gov.br/Downloads/sinapi-a-partir-jul-2009-" + estado + "/SINAPI_ref_Insumos_Composicoes_" + estado + "_202410_Desonerado.zip";
            	//String naoDesoneradoLink = "https://www.caixa.gov.br/Downloads/sinapi-a-partir-jul-2009-" + estado + "/SINAPI_ref_Insumos_Composicoes_" + estado + "_202410_NaoDesonerado.zip";
            	
            	String desoneradoLink = "https://www.caixa.gov.br/Downloads/sinapi-a-partir-jul-2009-" + estado + "/SINAPI_ref_Insumos_Composicoes_" + estado + "_" + anoMes + "_Desonerado.zip";
            	String naoDesoneradoLink = "https://www.caixa.gov.br/Downloads/sinapi-a-partir-jul-2009-" + estado + "/SINAPI_ref_Insumos_Composicoes_" + estado + "_" + anoMes + "_NaoDesonerado.zip";
                
                               
                System.out.println("Hora atual: " + dtf.format(now)); 
                String urlAtual = driver.getCurrentUrl();
                
                
                String caminho = "C:\\Users\\" + currentUser + "\\Downloads";
                File downloads = new File(caminho); // arquivos do caminho
                
                File[] arquivos = downloads.listFiles(); // lista de arquivos do caminho
                
            	
            	
                System.out.println("URL atual: " + urlAtual);
                Thread.sleep(4000);
                String verificar = driver.getCurrentUrl();
                if(verificar.contains("PageNotFoundError"))
                {
                	System.out.println("Link para o mês de " + mes + " não disponível no momento");
                }
                
                System.out.println("URL atual: " + urlAtual);
                // click no link desonerado
                driver.get(desoneradoLink);
                System.out.println("Link da URL atual *DESONERADO*: " + desoneradoLink);                
                //Thread.sleep(5000);               
                
                // teste de solução ##########################################################################################
                String pathDownload = "C:\\Users\\" + currentUser + "\\Downloads";
                String nomeArquivo = "SINAPI_ref_Insumos_Composicoes_"+ estado + "_" + anoMes + "_Desonerado.zip";
                
                File downloaded = new File(pathDownload, nomeArquivo);
                
                FluentWait<File> wait = new FluentWait<File>(downloaded)
                		.withTimeout(Duration.ofMinutes(5))
                		.pollingEvery(Duration.ofSeconds(5))
                		.ignoring(Exception.class)
                		.withMessage("erro no download");
                
                
                // exemplo de nome de arquivo DESONERADO: SINAPI_ref_Insumos_Composicoes_AC_202410_Desonerado
                String nomeCortado;
                nomeCortado = nomeArquivo.substring(31, 40);
                
                
                boolean isDownloaded = wait.until(f -> f.exists() && f.canRead());
                
                
                if(isDownloaded)
                {
                	System.out.println("------------------------------\nArquivo *DESONERADO*: " + nomeCortado + " 100% baixado com sucesso!\n------------------------------");
                }
                else
                {
                	System.out.println("Arquivo não baixado.");
                }
                // teste de solução ##########################################################################################
                
                
                // click no link de não desonerados
                driver.get(naoDesoneradoLink);
                System.out.println("Link da URL atual *NÃO DESONERADO*: " + naoDesoneradoLink);
                //Thread.sleep(5000);
                
                // teste de solução ##########################################################################################
                pathDownload = "C:\\Users\\" + currentUser + "\\Downloads";
                nomeArquivo = "SINAPI_ref_Insumos_Composicoes_"+ estado + "_" + anoMes + "_NaoDesonerado.zip";
                
                downloaded = new File(pathDownload, nomeArquivo);
                
                wait = new FluentWait<File>(downloaded)
                		.withTimeout(Duration.ofMinutes(5))
                		.pollingEvery(Duration.ofSeconds(5))
                		.ignoring(Exception.class)
                		.withMessage("erro no download");
                
                // exemplo de nome de arquivo NÃO DESONERADO: SINAPI_ref_Insumos_Composicoes_AC_202410_NaoDesonerado
                
                nomeCortado = nomeArquivo.substring(31, 40);
                
                
                isDownloaded = wait.until(f -> f.exists() && f.canRead());
                
                if(isDownloaded)
                {
                	System.out.println("------------------------------\nArquivo *NÃO DESONERADO*: " + nomeCortado + " 100% baixado com sucesso!\n------------------------------");
                }
                else
                {
                	System.out.println("Arquivo não baixado.");
                }
                // teste de solução ##########################################################################################
                
                urlAtual = driver.getCurrentUrl();
                if(urlAtual.contains("PageNotFoundError"))
                {
                	validador++;
                	break;
                }
                
                
                if(estado == "AP")
                {
            		System.out.println("Iniciando espera final de conclusão de downloads... (espera de 10 segundos)");
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
	                String targetDirectory = "C:\\Users\\" + currentUser + "\\Desktop\\Arquivos SINAPI\\Arquivos ZIP";
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
    
    	        String origem = "C:\\Users\\" + currentUser + "\\Desktop\\Arquivos SINAPI\\Arquivos ZIP"; // path de onde tem arquivos zipados para extração
    	        String destino = "C:\\Users\\" + currentUser + "\\Desktop\\Arquivos SINAPI\\Resto";  // Caminho de destino onde os arquivos serão extraídos

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
    	        String sourceDirector = "C:\\Users\\" + currentUser + "\\Desktop\\Arquivos SINAPI";
                String targetDirector = "C:\\Users\\" + currentUser + "\\Desktop\\Arquivos SINAPI\\Arquivos Excel";
                String fileNameToFin = ".xlsx";

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
            	 
//	            String[] estados = 
//	            {
//	            	 "AC", "AL", "AP"
//	            };
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
            		 
            			 String fonte = "C:\\Users\\" + currentUser + "\\Desktop\\Arquivos SINAPI\\Arquivos Excel";
            			 String alvo = "C:\\Users\\" + currentUser + "\\Desktop\\Arquivos SINAPI\\Arquivos Excel\\" + estado;
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
              		 			 String fonte = "C:\\Users\\" + currentUser + "\\Desktop\\Arquivos SINAPI\\Arquivos Excel";
              		 			 String alvo = "C:\\Users\\" + currentUser + "\\Desktop\\Arquivos SINAPI\\Arquivos Excel\\" + estado + "\\SINTETICOS";
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
              		 			 String fonte = "C:\\Users\\" + currentUser + "\\Desktop\\Arquivos SINAPI\\Arquivos Excel";
              		 			 // SINAPI_Preco_Ref_Insumos_AC_202409_NaoDesonerado
              		 			 String alvo = "C:\\Users\\" + currentUser + "\\Desktop\\Arquivos SINAPI\\Arquivos Excel\\" + estado + "\\INSUMOS";
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
              	 String fonte = "C:\\Users\\" + currentUser + "\\Desktop\\Arquivos SINAPI\\Arquivos Excel";
      			 String alvo = "C:\\Users\\" + currentUser + "\\Desktop\\Arquivos SINAPI\\Arquivos Excel\\ANALÍTICOS DE TODOS OS ESTADOS";
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
//}
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
	
	public static void sicro()
	{
		final String[] estados = 
	          {
	              "AC", "AL", "AP", 
	              "AM", "BA", "CE", 
	              "DF", "ES", "GO", 
	              "MA", "MT", "MS", 
	              "MG", "PA", "PB", 
	              "PR", "PE", "PI", 
	              "RJ", "RN", "RS", 
	              "RO", "RR", "SC", 
	              "SP", "SE", "TO",
	          };
	// região norte apenas	
	//	static String[] estados = 
	//        {
	//            "AM", "PA", "AC",
	//            "RR", "RO", "AP",
	//            "TO"
	//        };
	
	
	
	

 
  	
  	// CHAMANDO CONSOLE EXTERNO
//  	SwingUtilities.invokeLater(() -> {
//          // Criação do JFrame
//          JFrame frame = new JFrame("console");
//          frame.setSize(500, 400);
//          frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);                       
//          
//          // TODO botão para baixar SINAPI e SICRO
//          
//
//          // Criação do JTextArea para exibir o console
//          JTextArea textArea = new JTextArea();
//          textArea.setEditable(false);
//          JScrollPane scrollPane = new JScrollPane(textArea);
//          frame.add(scrollPane, BorderLayout.CENTER);
//          
//          //configuraçõoes para localização e tamanho da janela (janela do console)
//          frame.setLocation(960, 0); // aqui é Localização (onde a janela vai ficar) o primeiro argumento é a largura
//          frame.setSize(960, 1080);  // aqui é tamanho (qual o espaço que a janela vai ocupar)
//          
//          // Redirecionar o System.out e System.err para o JTextArea
//          PrintStream printStream = new PrintStream(new TextAreaOutputStream(textArea));
//          System.setOut(printStream);
//          System.setErr(printStream);
//
//          frame.setVisible(true);
//      });
  	
  	
     //BasicConfigurator.configure();
      
      System.setProperty("webdriver.chrome.driver", "C:\\Users\\walyson.ferreira\\chromedriver.exe");
      System.setProperty("webdriver.http.factory", "jdk-http-client");
      WebDriver driver = new ChromeDriver();
      
      try 
      {
          // Abre a página inicial
          driver.get("https://www.gov.br/dnit/pt-br/assuntos/planejamento-e-pesquisa/custos-e-pagamentos/custos-e-pagamentos-dnit/sistemas-de-custos/sicro/relatorios-sicro/relatorios-sicro");

          // Clica no centro da tela para fechar o popup
          Dimension windowSize = driver.manage().window().getSize();
          int centerX = windowSize.getWidth() / 2;
          int centerY = windowSize.getHeight() / 2;
          Actions actions = new Actions(driver);
          actions.moveByOffset(centerX, centerY).click().perform();
          
          
          // links das regiões
          String[] links = 
          {
          		// NORTE
          		"https://www.gov.br/dnit/pt-br/assuntos/planejamento-e-pesquisa/custos-e-pagamentos/custos-e-pagamentos-dnit/sistemas-de-custos/sicro_antiga/norte/norte",
          		// NORDESTE
          		"https://www.gov.br/dnit/pt-br/assuntos/planejamento-e-pesquisa/custos-e-pagamentos/custos-e-pagamentos-dnit/sistemas-de-custos/sicro_antiga/nordeste/nordeste",
          		// CENTRO-OESTE
          		"https://www.gov.br/dnit/pt-br/assuntos/planejamento-e-pesquisa/custos-e-pagamentos/custos-e-pagamentos-dnit/sistemas-de-custos/sicro_antiga/centro-oeste/centro-oeste",
          		// SUDESTE
          		"https://www.gov.br/dnit/pt-br/assuntos/planejamento-e-pesquisa/custos-e-pagamentos/custos-e-pagamentos-dnit/sistemas-de-custos/sicro_antiga/sudeste/sudeste",
          		// SUL
          		"https://www.gov.br/dnit/pt-br/assuntos/planejamento-e-pesquisa/custos-e-pagamentos/custos-e-pagamentos-dnit/sistemas-de-custos/sicro_antiga/sul/sul",
          };
          
          DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
      	LocalDateTime now = LocalDateTime.now();
      	
      	// conversão de DateTime para String
	    	String dataAtual = dtf.format(now);
	    	// remoção das barras para espaços em branco
	    	String mesAno = dataAtual.replaceAll("/", "");
	    	// corte exato do mês e ano. Formato: "AAAAMM"
	    	String ano = mesAno.substring(0, 4);
	    	
          for(String link : links)
          {
          	regiao += 1;
          	System.out.println("Link atual: " + link + "\n------------------");
              
              // clica no link da região
              driver.get(link);
              
              // Pega os links dos meses dentro da região
              List<WebElement> monthElements = driver.findElements(By.xpath("//a[contains(@href," + ano + ")]"));

              for(WebElement monthElement : monthElements)
              {
                  String monthHref = monthElement.getAttribute("href");
                  
                  // clica no link do mês
                  driver.get(monthHref);
                  
                  // Baixa todos os arquivos .zip na página
                  //List<WebElement> zipLinks = driver.findElements(By.xpath("//a[contains(@href, '.zip')]")); (modelo de teste)
                  List<WebElement> zipLinks = driver.findElements(By.xpath("//a[substring(@href, string-length(@href) - 3) = '.zip']"));

                  for (WebElement zipLink : zipLinks) 
                  {	
                  	String fileUrl = zipLink.getAttribute("href");
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
                      
                      // verificação de download ###################################################################
                        
                              String pathDownload = "C:\\Users\\" + currentUser + "\\Downloads";
                              // modelo de arquivos baixados: 'ac-01-2024' // 4 meses
                              //String nomeArquivo = estado.toLowerCase() + "-" + mes + "-" + ano;
                              
                              String linhaCortada = arquivoAtual;
                              
                              File downloaded = new File(pathDownload, linhaCortada);
                              
                              // espera para download de arquivos
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
                      // ##########################################################################################
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

	                        fileName = sanitizeFileName(fileName);
	                        // System.out.println("ARQUIVO COM ALTERAÇÃO: " + fileName);

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
      	 //--------------------------------------------------------------------------------------------------------------
           // separação de estados antes de mover para pastas de CUSTOS, MATERIAIS, EQUIPAMENTOS 
           for(String estado : estados)
           {
          	 source = "C:\\Users\\" + currentUser + "\\Desktop\\Arquivos SICRO\\Arquivos Excel";
          	 target = "C:\\Users\\" + currentUser + "\\Desktop\\Arquivos SICRO\\Arquivos Excel\\" + estado;
          	 find = estado;
          	 
          	 try
          	 {
	            		 moveFiles(source, target, find);
          	 }
          	 catch(IOException e)
          	 {
          		 System.err.println("Ocorreu um erro: \n" + e.getMessage());
          		 e.printStackTrace();
          	 }
           }
//---------------------------------------------------------------------------------------------------------------------------------------
           // separar todos os arquivos Sintéticos de Composições de Custos
           String s = "C:\\Users\\" + currentUser + "\\Desktop\\Arquivos SICRO\\Arquivos Excel";
           String t = "C:\\Users\\" + currentUser + "\\Desktop\\Arquivos SICRO\\CUSTOS Sintéticos";
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
           // separar todos os arquivos Sintéticos de Equipamentos
           s = "C:\\Users\\" + currentUser + "\\Desktop\\Arquivos SICRO\\Arquivos Excel";
           t = "C:\\Users\\" + currentUser + "\\Desktop\\Arquivos SICRO\\EQUIPAMENTOS Sintéticos";
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
           // separar todos os arquivos Sintéticos de Materiais
           s = "C:\\Users\\" + currentUser + "\\Desktop\\Arquivos SICRO\\Arquivos Excel";
           t = "C:\\Users\\" + currentUser + "\\Desktop\\Arquivos SICRO\\MATERIAIS Sintéticos";
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
           // *SEPARAR MATERIAIS SINTÉTICOS*
           for(String estado : estados)
           {
             //Norte
          	  if(estado == "AC" || estado == "AM" || estado  == "AP" || estado == "PA" || estado == "RO" || estado == "RR" || estado == "TO")
          	  {
          		  s = "C:\\Users\\" + currentUser + "\\Desktop\\Arquivos SICRO\\MATERIAIS Sintéticos";
          		  t = "C:\\Users\\" + currentUser + "\\Desktop\\Arquivos SICRO\\MATERIAIS Sintéticos\\NORTE";
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
          	  else if(estado == "AL" || estado == "BA" || estado == "CE" || estado == "MA" || estado == "PB" || estado == "PE" || estado == "PI" || estado == "RN" || estado == "SE")
          	  {
          		// Nordeste
          		  s = "C:\\Users\\" + currentUser + "\\Desktop\\Arquivos SICRO\\MATERIAIS Sintéticos";
          		  t = "C:\\Users\\" + currentUser + "\\Desktop\\Arquivos SICRO\\MATERIAIS Sintéticos\\NORDESTE";
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
          	  else if(estado == "DF" || estado == "GO" || estado == "MS" || estado == "MT")
          	  {
          		  // centro-oeste
          		  s = "C:\\Users\\" + currentUser + "\\Desktop\\Arquivos SICRO\\MATERIAIS Sintéticos";
          		  t = "C:\\Users\\" + currentUser + "\\Desktop\\Arquivos SICRO\\MATERIAIS Sintéticos\\CENTRO-OESTE";
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
          	  else if(estado == "ES" || estado == "MG" || estado == "RJ" || estado == "SP")
          	  {
          		  // Sudeste
          		  s = "C:\\Users\\" + currentUser + "\\Desktop\\Arquivos SICRO\\MATERIAIS Sintéticos";
          		  t = "C:\\Users\\" + currentUser + "\\Desktop\\Arquivos SICRO\\MATERIAIS Sintéticos\\SUDESTE";
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
          	  else if(estado == "PR" || estado == "RS" || estado == "SC")
          	  {
          		  // Sul
          		  s = "C:\\Users\\" + currentUser + "\\Desktop\\Arquivos SICRO\\MATERIAIS Sintéticos";
          		  t = "C:\\Users\\" + currentUser + "\\Desktop\\Arquivos SICRO\\MATERIAIS Sintéticos\\SUL";
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
               
           }
           // *SEPARAR EQUIPAMENTOS SINTÉTICOS*
           for(String estado : estados)
           {
               
             //Norte
         	  if(estado == "AC" || estado == "AM" || estado  == "AP" || estado == "PA" || estado == "RO" || estado == "RR" || estado == "TO")
         	  {
         		  s = "C:\\Users\\" + currentUser + "\\Desktop\\Arquivos SICRO\\EQUIPAMENTOS Sintéticos";
         		  t = "C:\\Users\\" + currentUser + "\\Desktop\\Arquivos SICRO\\EQUIPAMENTOS Sintéticos\\NORTE";
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
         	  else if(estado == "AL" || estado == "BA" || estado == "CE" || estado == "MA" || estado == "PB" || estado == "PE" || estado == "PI" || estado == "RN" || estado == "SE")
         	  {
         		// Nordeste
         		  s = "C:\\Users\\" + currentUser + "\\Desktop\\Arquivos SICRO\\EQUIPAMENTOS Sintéticos";
         		  t = "C:\\Users\\" + currentUser + "\\Desktop\\Arquivos SICRO\\EQUIPAMENTOS Sintéticos\\NORDESTE";
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
         	  else if(estado == "DF" || estado == "GO" || estado == "MS" || estado == "MT")
         	  {
         		  // centro-oeste
         		  s = "C:\\Users\\" + currentUser + "\\Desktop\\Arquivos SICRO\\EQUIPAMENTOS Sintéticos";
         		  t = "C:\\Users\\" + currentUser + "\\Desktop\\Arquivos SICRO\\EQUIPAMENTOS Sintéticos\\CENTRO-OESTE";
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
         	  else if(estado == "ES" || estado == "MG" || estado == "RJ" || estado == "SP")
         	  {
         		  // Sudeste
         		  s = "C:\\Users\\" + currentUser + "\\Desktop\\Arquivos SICRO\\EQUIPAMENTOS Sintéticos";
         		  t = "C:\\Users\\" + currentUser + "\\Desktop\\Arquivos SICRO\\EQUIPAMENTOS Sintéticos\\SUDESTE";
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
         	  else if(estado == "PR" || estado == "RS" || estado == "SC")
         	  {
         		  // Sul
         		  s = "C:\\Users\\" + currentUser + "\\Desktop\\Arquivos SICRO\\EQUIPAMENTOS Sintéticos";
         		  t = "C:\\Users\\" + currentUser + "\\Desktop\\Arquivos SICRO\\EQUIPAMENTOS Sintéticos\\SUL";
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
           }
           // *SEPARAR CUSTOS SINTÉTICOS*
           for(String estado : estados)
           { 
        	  //Norte
        	  if(estado == "AC" || estado == "AM" || estado  == "AP" || estado == "PA" || estado == "RO" || estado == "RR" || estado == "TO")
        	  {
        		  s = "C:\\Users\\" + currentUser + "\\Desktop\\Arquivos SICRO\\CUSTOS Sintéticos";
        		  t = "C:\\Users\\" + currentUser + "\\Desktop\\Arquivos SICRO\\CUSTOS Sintéticos\\NORTE";
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
        	  else if(estado == "AL" || estado == "BA" || estado == "CE" || estado == "MA" || estado == "PB" || estado == "PE" || estado == "PI" || estado == "RN" || estado == "SE")
        	  {
        		// Nordeste
        		  s = "C:\\Users\\" + currentUser + "\\Desktop\\Arquivos SICRO\\CUSTOS Sintéticos";
        		  t = "C:\\Users\\" + currentUser + "\\Desktop\\Arquivos SICRO\\CUSTOS Sintéticos\\NORDESTE";
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
        	  else if(estado == "DF" || estado == "GO" || estado == "MS" || estado == "MT")
        	  {
        		  // centro-oeste
        		  s = "C:\\Users\\" + currentUser + "\\Desktop\\Arquivos SICRO\\CUSTOS Sintéticos";
        		  t = "C:\\Users\\" + currentUser + "\\Desktop\\Arquivos SICRO\\CUSTOS Sintéticos\\CENTRO-OESTE";
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
        	  else if(estado == "ES" || estado == "MG" || estado == "RJ" || estado == "SP")
        	  {
        		  // Sudeste
        		  s = "C:\\Users\\" + currentUser + "\\Desktop\\Arquivos SICRO\\CUSTOS Sintéticos";
        		  t = "C:\\Users\\" + currentUser + "\\Desktop\\Arquivos SICRO\\CUSTOS Sintéticos\\SUDESTE";
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
        	  else if(estado == "PR" || estado == "RS" || estado == "SC")
        	  {
        		  // Sul
        		  s = "C:\\Users\\" + currentUser + "\\Desktop\\Arquivos SICRO\\CUSTOS Sintéticos";
        		  t = "C:\\Users\\" + currentUser + "\\Desktop\\Arquivos SICRO\\CUSTOS Sintéticos\\SUL";
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
           }
//----------------------------------------------------------------------------------------------------------------------------
           // solução para arquivos aleatórios de outros estados na pasta 'RO'
           for(String estado : estados)
           {
          	 source = "C:\\Users\\" + currentUser + "\\Desktop\\Arquivos SICRO\\Arquivos Excel\\RO";
          	 target = "C:\\Users\\" + currentUser + "\\Desktop\\Arquivos SICRO\\Arquivos Excel\\" + estado;
          	 find = estado;
          	 
          	 try
          	 {
	            		 moveFiles(source, target, find);
          	 }
          	 catch(IOException e)
          	 {
          		 System.err.println("Ocorreu um erro: \n" + e.getMessage());
          		 e.printStackTrace();
          	 }
           }
           
//----------------------------------------------------------------------------------------------------------------------------             
           System.out.println("PROGRAMA ENCERRADO!");
//----------------------------------------------------------------------------------------------------------------------------
      } finally
      {
          driver.quit();
      }
   
	}
}
