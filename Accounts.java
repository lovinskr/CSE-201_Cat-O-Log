import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.ListIterator;

public class Accounts {
	public LinkedList<User> userlist;
	public String filename = "account.txt";
	
	public Accounts() throws IOException {
		userlist = new LinkedList();
		readUsers(); 
	}
	
	public void Makeaccount(String username, String password) throws IOException {
		User user = new User(username, password);
		userlist.add(user);
		saveUser(user);
	}
	
	public void Makeaccount(String username, String password, boolean admin) throws IOException {
		User user = new User(username, password, admin);
		userlist.add(user);
		saveUser(user);
	}
	
	
	public void readUsers() throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(filename));
		String line;
		while((line = br.readLine()) != null) {
			String name = br.readLine().trim();
			String pass = br.readLine().trim();
			int admin = Integer.parseInt(br.readLine());
			User user = new User(name, pass, true);
				userlist.add(user);
			
		}
		br.close();
	}
	
	
	/*
	 * NEED FOR GUI PLEASE DON'T CHANGE 
	 * checks that account exists or not 
	 */
	boolean hasAccount(String username, String password) throws IOException
	{
		ListIterator<User> userIterator = userlist.listIterator();
		for(int c  = 0; c < userlist.size(); c++)
		{
			User temp = userIterator.next();
			if((temp.username).equals(username))
			{
				return true; 
			}
		}
		
		return false; 
	}
	
	/*
	 * NEED FOR GUI PLEASE DON'T CHANGE 
	 * checks that passowrd matches account 
	 */
	boolean canLogIn(String username, String password)
	{
		ListIterator<User> userIterator = userlist.listIterator();
		for(int c  = 0; c < userlist.size(); c++)
		{
			User temp = userIterator.next();
			if((temp.username).equals(username) && (temp.password).equals(password))
			{
				return true; 
			}
		}
		return false; 
	}

	public void Makaccount(User newUser) throws IOException {
		userlist.add(newUser);
		saveUser(newUser);
		
	}
	
	public void saveUser(User user) throws IOException {
		FileWriter fstream = new FileWriter(filename);
        BufferedWriter out = new BufferedWriter(fstream);
        out.write(user.username);
    	out.newLine();
    	out.write(user.password);
    	out.newLine();
    	if(user.administrator) {
    		out.write(1);
    	} else {
    		out.write(0);
    	}
    	fstream.close();
    	out.close();
	}
	
	
}
