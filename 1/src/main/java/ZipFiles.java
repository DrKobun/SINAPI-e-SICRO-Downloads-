
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class ZipFiles
{
	public static void main(String[] args) throws Exception 
	{
		if(args.length > 2)
		{
			throw new Exception("Insufficient number of parameters. Required...");
			
		}
		String zipFileName = args[0];
		String destDirectory = args[1];
		File destDirectoryFolder = new File(destDirectory);
		
		if(!destDirectoryFolder.exists())
		{
			destDirectoryFolder.mkdir();
		}
		byte[] buffer = new byte[1024];
		ZipInputStream zis = new ZipInputStream(new FileInputStream(zipFileName));
		ZipEntry zipEntry =zis.getNextEntry();
		while(zipEntry != null)
		{
			String filePath = destDirectory + File.separator + zipEntry.getName();
			System.out.println("Unzipping " + filePath);
			if(!zipEntry.isDirectory())
			{
				FileOutputStream fos = new FileOutputStream(filePath);
				int len;
				while((len = zis.read(buffer)) > 0)
				{
					fos.write(buffer, 0, len);
				}
				fos.close();
			}
			else
			{
				File dir = new File(filePath);
				dir.mkdir();
			}
			zis.closeEntry();
			zipEntry = zis.getNextEntry();
		}
		zis.closeEntry();
		zis.close();
		System.out.println("Unzipping Complete!");
			
				
	}
}









































































//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.util.zip.ZipEntry;
//import java.util.zip.ZipInputStream;
//import java.util.zip.ZipException;
//
//public class ZipFiles {
//
//    public static void main(String[] args) {
//        String baseDir = "C:\\Users\\walyson.ferreira\\eclipse-workspace\\1\\src\\main\\java\\arquivos"; // Caminho para a pasta que contém os arquivos .zip
//        File downloadsDir = new File(baseDir);
//
//        if (downloadsDir.exists() && downloadsDir.isDirectory()) {
//            System.out.println("Iniciando a descompactação dos arquivos na pasta: " + downloadsDir.getAbsolutePath());
//
//            // Lista todos os arquivos dentro da pasta downloads
//            for (File file : downloadsDir.listFiles()) {
//                if (file.getName().endsWith(".zip")) {
//                    String destDir = downloadsDir.getAbsolutePath() + "/" + file.getName().replace(".zip", "");
//                    System.out.println("Descompactando: " + file.getName() + " para " + destDir);
//                    unzip(file.getAbsolutePath(), destDir);
//                }
//            }
//            System.out.println("Descompactação concluída.");
//        } else {
//            System.out.println("A pasta downloads não foi encontrada ou não é um diretório.");
//        }
//    }
//
//    public static void unzip(String zipFilePath, String destDir) {
//        File dir = new File(destDir);
//        if (!dir.exists()) {
//            dir.mkdirs(); // Cria o diretório se não existir
//            System.out.println("Criando diretório: " + destDir);
//        }
//
//        try (ZipInputStream zis = new ZipInputStream(new FileInputStream(zipFilePath))) {
//            ZipEntry zipEntry = zis.getNextEntry();
//
//            while (zipEntry != null) {
//                File newFile = new File(dir, zipEntry.getName());
//                if (zipEntry.isDirectory()) {
//                    newFile.mkdirs();
//                    System.out.println("Criando diretório: " + newFile.getAbsolutePath());
//                } else {
//                    new File(newFile.getParent()).mkdirs();
//                    try (FileOutputStream fos = new FileOutputStream(newFile)) {
//                        byte[] buffer = new byte[1024];
//                        int len;
//                        while ((len = zis.read(buffer)) > 0) {
//                            fos.write(buffer, 0, len);
//                        }
//                        System.out.println("Extraído: " + newFile.getAbsolutePath());
//                    }
//                }
//                zipEntry = zis.getNextEntry();
//            }
//            zis.closeEntry();
//        } catch (IOException e) {
//            System.out.println("Erro ao descompactar: " + zipFilePath);
//            e.printStackTrace();
//        }
//    }
//}
