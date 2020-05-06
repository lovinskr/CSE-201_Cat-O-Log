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

/**
 * Creates a linked list used to hold all user information
 *
 */
public class Accounts {
	public LinkedList userlist;
	public String filename = "accounts.txt";
	
	public Accounts() throws IOException {
		userlist = new LinkedList();
		readUsers(); 
	}
	/**
	 * Creates an account when a new user is made and saves the user in a text file
	 * @param newUser
	 * @throws IOException
	 */
	public void Makaccount(User newUser) throws IOException
	{
		userlist.add(newUser);
		writeUser(newUser);
	}
	/**
	 * Makes an account and saves their information from a username and password
	 * @param username
	 * @param password
	 * @throws IOException
	 */
	public void Makeaccount(String username, String password) throws IOException {
		User user = new User(username, password);
		userlist.add(user);
		writeUser(user);
	}
	/**
	 * Creates an account and saves their information from a username, password and admin privilages
	 * @param username
	 * @param password
	 * @param admin
	 * @throws IOException
	 */
	public void Makeaccount(String username, String password, boolean admin) throws IOException {
		User user = new User(username, password, admin);
		userlist.add(user);
		writeUser(user);
	}
	
	 /**
	  * Reads in the list of users from the accounts.txt file and stores then in the account list
	  * @throws IOException
	  */
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
	/**
	 * Allows a user to change their password
	 * @param un
	 * @param newP
	 * @throws IOException
	 */
	public void changePassword(String un, String newP) throws IOException
	{
		ListIterator<User> userIterator = userlist.listIterator();
		for(int c  = 0; c < userlist.size(); c++)
		{
			User temp = userIterator.next();
			if((temp.username).equals(un))
			{
				temp.password = newP; 
				User changedUser = temp; 
				userlist.remove(temp); 
				userlist.add(temp); 
				writeUser(temp);
			}
		}
	}
	/**
	 * Allows a user to change their username
	 * @param un
	 * @param newU
	 * @throws IOException
	 */
	public void changeUsername(String un, String newU) throws IOException
	{
		ListIterator<User> userIterator = userlist.listIterator();
		for(int c  = 0; c < userlist.size(); c++)
		{
			User temp = userIterator.next();
			if((temp.username).equals(un))
			{
				temp.username = newU; 
				User changedUser = temp; 
				userlist.remove(temp); 
				userlist.add(temp); 
				writeUser(temp);
			}
		}
	}
	/**
	 * Allows a user to change their phone number
	 * @param un
	 * @param newN
	 * @throws IOException
	 */
	public void changePhoneNumber(String un, String newN) throws IOException
	{
		ListIterator<User> userIterator = userlist.listIterator();
		for(int c  = 0; c < userlist.size(); c++)
		{
			User temp = userIterator.next();
			if((temp.username).equals(un))
			{
			String[] number = {newN}; 
			temp.setPhoneNumber(number); 
			}
		}
	}
	/**
	 * Allows a user to change their email
	 * @param un
	 * @param newE
	 * @throws IOException
	 */
	public void changeEmail(String un, String newE) throws IOException
	{
		ListIterator<User> userIterator = userlist.listIterator();
		for(int c  = 0; c < userlist.size(); c++)
		{
			User temp = userIterator.next();
			if((temp.username).equals(un))
			{
			String[] em = {newE}; 
			temp.setPhoneNumber(em); 
			}
		}
	}
	
	/*
	 * NEED FOR GUI PLEASE DON'T CHANGE 
	 * checks that user can log in with that username and password  
	 */
	int hasAccount(String username, String password) throws IOException
	{
		ListIterator<User> userIterator = userlist.listIterator();
		for(int c  = 0; c < userlist.size(); c++)
		{
			User temp = userIterator.next();
			if((temp.username).equals(username))
			{
				if((temp.password).equals(password))
				{
					return c;
				}
			}
		}
		
		return -66; 
	}
	/**
	 * Writes the user to the accounts file
	 * @param usr
	 * @throws IOException
	 */
	void writeUser(User usr) throws IOException 
	{
		//readUsers(); // get all current accounts in the linked list

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
		
		to.println();
		to.close();
	}
}
