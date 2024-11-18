package arquivos;

public class TesteExtracao 
{
	public static void main(String[] args) {
        String zipFilePath = "C:\\Users\\walyson.ferreira\\Downloads\\al-01-2024.zip";
        String destDirectory = "C:\\Users\\walyson.ferreira\\Downloads\\teste";
        Extracao unzipper = new Extracao();
        
        try 
        {
            unzipper.unzip(zipFilePath, destDirectory);
            
        } 
        catch (Exception ex) 
        {
            // some errors occurred
            ex.printStackTrace();
        }
    }
}
