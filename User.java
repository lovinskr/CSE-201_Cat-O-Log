
public class User 
{
	// there has to at least be 1 of each except email
	private String[] email = new String[2]; 
	private String[] phoneNumber = new String[2]; 
	private String lastName, firstName; 
	private boolean administrator = false; 
	
	public User()
	{
	}
	
	boolean setFName(String firstName)
	{
		this.firstName = firstName; 
		return false; 
	}
	
	boolean setLName(String lastName)
	{
		this.lastName = lastName; 
		return false; 
	}
	
	boolean setEmail(String[] email)
	{
		this.email = email; 
		return true; 
	}
	
	boolean setPhoneNumber(String[] phoneNumber)
	{
		this.phoneNumber = phoneNumber; 
		return true; 
	}
	
	boolean setAdmin(boolean admin)
	{
		administrator = admin; 
		return true; 
	}
	boolean requestAnimal(Animal newAnimal)
	{
		return false; 
	}
	
	boolean loggedIn(String username, String password)
	{
		return false;
	}
	
	boolean isAdmin()
	{
		return administrator ? true : false ; 
	}
	
	void commentOnAnimal(String comment)
	{
		
	}
	
}
