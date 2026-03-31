package ClientDriver;

import java.util.Scanner;

public class Login {
	private Scanner scanner = null;
	 String username;
	private String password;
	
	public Login() {
		scanner = new Scanner(System.in);
		System.out.println("-------Login Screen---------");
		System.out.print("Username: ");
		username = scanner.next();
		System.out.print("Password: ");
		password = scanner.next();
		
		
	}

}
