package Client;

import model.Reservation;
import model.User;
import view.AdminDashboard;
import view.LoginWindow;
import view.ReservationRequestsPanel;
import view.StudentRequestPanel;

import javax.swing.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.*;

public class ClientController {
	private static final String ROLE_ADMIN = "ADMIN";
	private static final String ROLE_TECHNICIAN = "TECHNICIAN";
	private static final String ROLE_STUDENT = "STUDENT";

	Scanner scanner = new Scanner(System.in);
	Socket socket = null;
	ObjectOutputStream oos = null;
	ObjectInputStream ois = null;
	User clientUser = null;
	LoginWindow login;
	
	public ClientController() {
		connectToServer();
		createStreams();
		login = new LoginWindow();
		login.setVisible(true);
	}
	
		

	public void createStreams() {
		try {
			oos = new ObjectOutputStream(socket.getOutputStream());
			ois = new ObjectInputStream(socket.getInputStream());
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null,"Error: Unable to establish communication with server", "Error", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
	}
	
	public void connectToServer() {
		try {
			socket = new Socket("localhost", 8888);
		}catch(IOException e) {
			JOptionPane.showMessageDialog(null,"Error: Unable to connect to server", "Error", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
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
	public void addNewUser(User user) {
		try {
			oos.writeObject("Add User");
			oos.flush();
			oos.writeObject(user);
			oos.flush();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Error: Unable to create user", "Error", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
	}

	// Compatibility overload retained for callers that still pass primitive role values.
	public void addNewUser(String username, String password, int role) {
		String roleString = mapRole(role);
		if (roleString == null) {
			JOptionPane.showMessageDialog(null, "Invalid role value. Use 0=ADMIN, 1=TECHNICIAN, 2=STUDENT.", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}

		if (username == null || username.trim().isEmpty() || password == null || password.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Username and password are required.", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}

		ArrayList<byte[]> hashAndSalt = PasswordHashGen.encryptPassword(password);
		if (hashAndSalt.size() < 2) {
			JOptionPane.showMessageDialog(null, "Unable to hash password.", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}

		User user = new User(username.trim(), hashAndSalt.get(0), hashAndSalt.get(1), roleString);
		addNewUser(user);
	}

	private String mapRole(int role) {
		if (role == 0) {
			return ROLE_ADMIN;
		}
		if (role == 1) {
			return ROLE_TECHNICIAN;
		}
		if (role == 2) {
			return ROLE_STUDENT;
		}
		return null;
	}

	// Sends a reservation request to the server.
	public void createReservation(Reservation reservation) {
		try {
			oos.writeObject("Create Reservation");
			oos.writeObject(reservation);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Error: Unable to create reservation", "Error", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public Map<String, ArrayList<String>> getAvailableEquipmentByType(int labId) {
		try {
			oos.writeObject("Get Available Equipment");
			oos.writeObject(labId);
			Object response = ois.readObject();
			if (response instanceof Map) {
				Map<?, ?> rawMap = (Map<?, ?>) response;
				Map<String, ArrayList<String>> typedMap = new HashMap<String, ArrayList<String>>();
				for (Map.Entry<?, ?> entry : rawMap.entrySet()) {
					if (entry.getKey() instanceof String && entry.getValue() instanceof List) {
						typedMap.put((String) entry.getKey(), new ArrayList<String>((List<String>) entry.getValue()));
					}
				}
				return typedMap;
			}
		} catch (IOException | ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null, "Error: Unable to load available equipment", "Error", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
		return new HashMap<String, ArrayList<String>>();
	}
	


	//Used to send credentials to server for verification, The server responds with the user class
	//if  credentials are valid.
	public void login(String username, String password) {
		try {
			oos.writeObject("User Login");
			oos.flush();
			oos.writeObject(username);
			oos.flush();
			oos.writeObject(password);
			oos.flush();
			clientUser = (User) ois.readObject();
			if(clientUser != null) {
				openMenu(clientUser.getRole(), clientUser.getId());
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
	private void openMenu(String role, int userId) {
		if (login != null) {
			login.dispose();
		}
		if(role != null && role.equalsIgnoreCase(ROLE_ADMIN)) {
			AdminDashboard window = new AdminDashboard(clientUser != null ? clientUser.getUsername() : "Admin");
			window.setVisible(true);
		}else if(role != null && role.equalsIgnoreCase(ROLE_TECHNICIAN)) {
			JFrame window = new JFrame("Technician Portal");
			window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			window.setContentPane(new ReservationRequestsPanel());
			window.pack();
			window.setLocationRelativeTo(null);
			window.setVisible(true);
		}else if(role != null && role.equalsIgnoreCase(ROLE_STUDENT)) {
			JFrame window = new JFrame("Student Portal");
			window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			window.setContentPane(new StudentRequestPanel(userId));
			window.pack();
			window.setLocationRelativeTo(null);
			window.setVisible(true);
		}
		
	}



	public static void main(String[] args) {

		ClientController client = new ClientController();
		client.addNewUser("tech", "tech", 1);
	}

}
