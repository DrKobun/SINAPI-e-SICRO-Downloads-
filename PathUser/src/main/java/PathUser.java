import java.io.IOException;

public class PathUser 
{
	public static void main(String[] args) throws IOException 
	{
		
		String currentUser = System.getProperty("user.name");
		System.out.println("Working Directory = " + currentUser);
	}
}
