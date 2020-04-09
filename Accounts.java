import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Accounts {
	public LinkedList userlist;
	public String filename = "accounts.txt";
	
	public Accounts() {
		userlist = new LinkedList();
	}
	
	public void Makeaccount(String username, String password) {
		User user = new User(username, password);
		userlist.add(user);
	}
	
	public void Makeaccount(String username, String password, boolean admin) {
		User user = new User(username, password, admin);
		userlist.add(user);
	}
	
	
	public void readUsers() throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(filename));
		String line;
		while((line = br.readLine()) !=null) {
			String name = br.readLine();
			System.out.println(name);
			String pass = br.readLine();
			System.out.println(pass);
			int admin = Integer.parseInt(br.readLine());
			System.out.println(admin);
			if(admin == 1) {
				userlist.add(name, pass, true);
			} else {
				userlist.add(name, pass, false);
			}
		}
		br.close();

	}
}
