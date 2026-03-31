package ClientDriver;

import java.io.IOException;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Scanner;

import javax.swing.JOptionPane;

import model.User;
import view.AdminWindow;
import view.LoginWindow;
import view.StudentWindow;
import view.TechnicianWindow;
import model.PasswordHashGen;
import model.Role;

public class ClientController {
	Scanner scanner = new Scanner(System.in);
	Socket socket = null;
	ObjectOutputStream oos = null;
	ObjectInputStream ois = null;
	User clientUser = null;
	LoginWindow login;
	private final String serverHost;
	private final int serverPort;
	
	public ClientController() {
		this.serverHost = System.getProperty("approj.server.host", "localhost");
		this.serverPort = Integer.parseInt(System.getProperty("approj.server.port", "8888"));
		if (!connectToServer()) {
			JOptionPane.showMessageDialog(null,
					"Error: Unable to connect to server at " + serverHost + ":" + serverPort,
					"Error",
					JOptionPane.ERROR_MESSAGE);
			return;
		}
		if (!createStreams()) {
			return;
		}
		login = new LoginWindow(this);
	}
	
		

	public boolean createStreams() {
		if (socket == null || socket.isClosed()) {
			JOptionPane.showMessageDialog(null,
					"Error: No active server connection",
					"Error",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}
		try {
			oos = new ObjectOutputStream(socket.getOutputStream());
			ois = new ObjectInputStream(socket.getInputStream());
			return true;
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null,"Error: Unable to establish communication with server", "Error", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean connectToServer() {
		try {
			socket = new Socket(serverHost, serverPort);
			return true;
		}catch(IOException e) {
			return false;
		}
	}
	

	
	//This method is used to send delete user signal to server. 
	public void deleteUser(int userid) {
		try {
			oos.writeObject("Delete User");
			int confirmation = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this account? ");
			oos.writeObject(confirmation);
			if (confirmation == 0) {
				oos.writeObject(userid);
				JOptionPane.showMessageDialog(null, "Account has been deleted", "Delete Account", JOptionPane.INFORMATION_MESSAGE);
			}
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null,"Error: Unable to communicte with server", "Error", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
		
	}
	
	//Used to signal to the server to create and add a user to the database
	public void addNewUser(String username, String password, int role) {
		ArrayList<byte[]> hashAndSalt = new ArrayList<>();
		hashAndSalt = PasswordHashGen.encryptPassword(password);//Create user hash and salt
		String roleString = Role.getRoleString(role);
		User user = new User(username, hashAndSalt.get(0), hashAndSalt.get(1), roleString);
		try {
			oos.writeObject("Add User");
			oos.writeObject(user);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Error: Unable to create user", "Error", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
	}
	


	//Used to send credentials to server for verification, The server responds with the user class
	//if  credentials are valid.
	public void login(String username, String password) {
		try {
			oos.writeObject("User Login");
			oos.writeObject(username);
			oos.writeObject(password);
			clientUser = (User) ois.readObject();
			if(clientUser != null) {
				openMenu(clientUser.getRole(), clientUser.getId(), this.login);
			} else if(clientUser == null) {
				JOptionPane.showMessageDialog(null, "Invalid credentials(Please enter correct username/password)", "Login", JOptionPane.ERROR_MESSAGE);
			}
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Error: Unable to communicate with server", "Error", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null, "Error: User not found", "Error", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
		
	}
	
	
	//Displays main window. The user.role attribute is used to select the window the user will see.
	private void openMenu(String role, int userId, LoginWindow login) {
		login.dispose();
		if(role.equalsIgnoreCase(Role.ADMIN)) {
			AdminWindow window = new AdminWindow(userId, this);
		}else if(role.equalsIgnoreCase(Role.TECHNICIAN)) {
			TechnicianWindow window = new TechnicianWindow(userId, this);
		}else if(role.equalsIgnoreCase(Role.STUDENT)) {
			StudentWindow window = new StudentWindow(userId, this);
		}
		
	}



	public static void main(String[] args) {
		new ClientController();
	}

}
