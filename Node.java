
public class Node {
	
    String username; // stores data
    String password;
    boolean admin;
	Node next; // references the next node in the list
	
	public Node(){
		
	}
	
	public Node(String username, String password, boolean admin) {
		this.username = username;
		this.password = password;
		this.admin = admin;
	}

}
