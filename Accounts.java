import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Scanner;

public class Accounts {
	public LinkedList userlist;
	public String filename = "accounts.txt";
	
	public Accounts() throws IOException {
		userlist = new LinkedList();
		readUsers(); 
	}
	
	public void Makaccount(User newUser) throws IOException
	{
		userlist.add(newUser);
		writeUser(newUser);
	}
	
	public void Makeaccount(String username, String password) throws IOException {
		User user = new User(username, password);
		userlist.add(user);
		writeUser(user);
	}
	
	public void Makeaccount(String username, String password, boolean admin) throws IOException {
		User user = new User(username, password, admin);
		userlist.add(user);
		writeUser(user);
	}
	
	 
	public void readUsers() throws IOException 
	{
		Scanner read = new Scanner(new File(filename));
		
		while(read.hasNext())
		{
			String username = read.next().trim();
			String password = read.next().trim();
			int a = read.nextInt(); 
			 
			Boolean admin = false; 
			if(a == 1)
				admin = true; 
			User user = new User(username, password, admin);
			userlist.add(user); 
		}
		
		read.close();
	}
	
	public void changePassword(String un, String newP) throws IOException
	{
		ListIterator<User> userIterator = userlist.listIterator();
		for(int c  = 0; c < userlist.size(); c++)
		{
			User temp = userIterator.next();
			if((temp.username).equals(un))
			{
				temp.password = newP; 
				userlist.remove(temp); 
				writeUser(temp);
			}
		}
	}
	
	/*
	 * NEED FOR GUI PLEASE DON'T CHANGE 
	 * checks that user can log in with that username and password  
	 */
	boolean hasAccount(String username, String password) throws IOException
	{
		ListIterator<User> userIterator = userlist.listIterator();
		for(int c  = 0; c < userlist.size(); c++)
		{
			User temp = userIterator.next();
			if((temp.username).equals(username))
			{
				if((temp.password).equals(password))
				{
					return true;
				}
			}
		}
		
		return false; 
	}
	
	void writeUser(User usr) throws IOException 
	{
		readUsers(); // get all current accounts in the linked list
		
		PrintWriter to = new PrintWriter(new File(filename));
		String line;
		ListIterator<User> ui = userlist.listIterator();
		to.println(); 
		
		while(ui.hasNext())
		{
			User temp = ui.next(); 
			to.println(temp.username);
			to.println(temp.password);
			to.println(temp.isAdmin());
		}
		
		to.println(usr.username);
		to.println(usr.password);
		to.println(usr.isAdmin());
		to.println();
		to.close();
	}
}
