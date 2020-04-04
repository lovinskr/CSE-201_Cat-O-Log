import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.Scanner;

public class Accounts 
{
	static String username = ""; 
	static String password = "";
	
	boolean login(String usrnm, String pswrd) throws FileNotFoundException 
	{ 
		username = usrnm; 
		password = pswrd;
		
		File accounts = new File("account.txt"); 
		Scanner reader = new Scanner(accounts); 
		
		while(reader.hasNext())
		{
			String temp = reader.next(); 
			if(username.equals(temp))
			{
				temp = reader.next(); 
				if(password.contentEquals(temp))
				{
					reader.close();
					return true; 
				}
			}
		}
		
		reader.close(); 
		return false; 
	}

}
