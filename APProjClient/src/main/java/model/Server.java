package model;

import java.io.IOException; 
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

import javax.swing.JOptionPane;

import model.User;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.junit.jupiter.api.Test;

import controller.ClientHandler;
import controller.PasswordHashGen;


public class Server{
	private ServerSocket servSocket = null;
	private Socket socket = null;
	
	private SessionFactory factory = null;
	private Configuration config = null;
	private static Transaction transaction = null;
	private static Session session = null;
	
	//Constructor- Creates server and establish connection DB
	public Server() {
		try {
			servSocket = new ServerSocket(8888, 1);
			objectManagement();
			while(true) {
				waitForRequest();
				System.out.print("Server is listening");
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	//Configure Connection to Database with Hibernate
	public void objectManagement() {
		config = new Configuration();
		config.addAnnotatedClass(User.class);
		config.configure("hibernate.cfg.xml");
		factory = config.buildSessionFactory();
		session = factory.openSession();
	}
	
	
	//Wait for client request. 
	public void waitForRequest() {
		while(true) {
			try {
				socket = servSocket.accept();
				System.out.println("Client Connected");
				ClientHandler client = new ClientHandler(socket);
				new Thread(client).start();
			}catch(IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	
	public static void addUserToDB(User user) {
		transaction = session.beginTransaction();
		session.persist(user);
		transaction.commit();
	}
	
	public static void deleteUserFromDB(int userid) {
		transaction = session.beginTransaction();
		User user = session.get(User.class, userid);
		session.remove(user);
		transaction.commit();
	}
	
	public static User userLogin(String username, String password) {
		String hq = "FROM User u WHERE u.username = :username";
		Query query = session.createQuery(hq);
		query.setParameter("username", username);
		User user = (User) query.uniqueResult();
		if(user != null) {
			System.out.println(user.getUsername());
			boolean isValid = checkPassword(user, password);
			if(!isValid) {
				user = null;
			}
		}
		return user;
	}
	
	private static boolean checkPassword(User user, String password) {
		boolean result = false;
		System.out.println(Base64.getEncoder().encodeToString(user.getPassword()));
		try {
			
			byte[] passHash = PasswordHashGen.genPasswordHash(password, user.getSalt());
			System.out.println(Base64.getEncoder().encodeToString(passHash));
			if (Arrays.equals(passHash, user.getPassword())) {
				result = true;
			}
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidKeySpecException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
			
	}
	
	private static void createReservation(Reservation reservation) {
		
		
	}

	private static class ClientHandler implements Runnable {
		private ObjectInputStream objectIS;
		private ObjectOutputStream objectOS;
		private Socket clientSocket = null;
		
		
		public ClientHandler(Socket clientSocket) {
			this.clientSocket = clientSocket;
			
		}
		
		public void configureStreams() {
			try {
				objectIS = new ObjectInputStream(this.clientSocket.getInputStream());
				objectOS = new ObjectOutputStream(this.clientSocket.getOutputStream());
			}catch(IOException e) {
				e.printStackTrace();
			}
		}

		@Override
		public void run() {
			configureStreams();
			while(true) {
				try {
					String request = (String) objectIS.readObject();
					if (request.equalsIgnoreCase("Add User")) {
						User user = (User) objectIS.readObject();
						if(user != null) {
							addUserToDB(user);
						}
					}else if(request.equalsIgnoreCase("User Login")) {
						String username = (String) objectIS.readObject();
						String password = (String) objectIS.readObject();
						User user = userLogin(username, password);
						objectOS.writeObject(user);
					}else if(request.equals("Delete User")) {
						int confirmation = (int) objectIS.readObject();
						if(confirmation == 0) {
							int userid = (int) objectIS.readObject();
							deleteUserFromDB(userid);
						}
						
						
					}else if(request.equalsIgnoreCase("View Reservations")) {
						
					}else if(request.equalsIgnoreCase("Cancel Reservation")) {
						
					}else if(request.equalsIgnoreCase("Create Reservation")) {
						
					}
				}catch(ClassNotFoundException | IOException e) {
					e.printStackTrace(); 
					JOptionPane.showMessageDialog(null, "Server issues", "Error", JOptionPane.ERROR_MESSAGE);
					break;
				}
			}
			
		}
		

	}

	
	public static void main(String[] args) {
		System.out.println("Hellow wolrd");
		new Server();

	}

	

}
