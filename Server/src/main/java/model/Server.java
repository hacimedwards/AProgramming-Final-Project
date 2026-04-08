package model;

import java.io.IOException; 
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.EOFException;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Base64;
import java.util.List;
import java.util.Map;

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
		config.addAnnotatedClass(Equipment.class);
		config.addAnnotatedClass(Reservation.class);
		config.addAnnotatedClass(Lab.class);
		config.addAnnotatedClass(LabSeat.class);
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
		try {
			transaction = session.beginTransaction();
			session.persist(reservation);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null && transaction.isActive()) {
				transaction.rollback();
			}
			session.clear();
			e.printStackTrace();
		}
	}

	private static Map<String, ArrayList<String>> getAvailableEquipmentByType(int labId) {
		String hq = "FROM Equipment e WHERE upper(e.status) = :status AND e.labId = :labId";
		Query<Equipment> query = session.createQuery(hq, Equipment.class);
		query.setParameter("status", "AVAILABLE");
		query.setParameter("labId", labId);
		List<Equipment> availableEquipment = query.getResultList();

		Map<String, ArrayList<String>> equipmentByType = new HashMap<String, ArrayList<String>>();
		for (Equipment equipment : availableEquipment) {
			ArrayList<String> ids = equipmentByType.get(equipment.getType());
			if (ids == null) {
				ids = new ArrayList<String>();
				equipmentByType.put(equipment.getType(), ids);
			}
			ids.add(equipment.getEquipmentId());
		}

		return equipmentByType;
	}

	private static List<Reservation> getAllReservations() {
		String hq = "FROM Reservation r ORDER BY r.reservationId DESC";
		Query<Reservation> query = session.createQuery(hq, Reservation.class);
		return query.getResultList();
	}

	private static boolean updateReservationStatus(int reservationId, String status) {
		try {
			transaction = session.beginTransaction();
			Reservation reservation = session.get(Reservation.class, reservationId);
			if (reservation == null) {
				transaction.rollback();
				return false;
			}
			reservation.setStatus(status);
			reservation.setUpdatedAt(java.time.LocalDateTime.now());
			session.merge(reservation);
			transaction.commit();
			return true;
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
			return false;
		}
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
						Reservation reservation = (Reservation) objectIS.readObject();
						if (reservation != null) {
							createReservation(reservation);
						}
					}else if(request.equalsIgnoreCase("Get Available Equipment")) {
						int labId = (int) objectIS.readObject();
						objectOS.writeObject(getAvailableEquipmentByType(labId));
					}else if(request.equalsIgnoreCase("Get All Reservations")) {
						List<Reservation> reservations = getAllReservations();
						objectOS.writeObject(reservations);
					}else if(request.equalsIgnoreCase("Update Reservation Status")) {
						int reservationId = (int) objectIS.readObject();
						String status = (String) objectIS.readObject();
						boolean result = updateReservationStatus(reservationId, status);
						objectOS.writeObject(result);
					}
				}catch(EOFException e) {
					// Client disconnected gracefully or abruptly
					System.out.println("Client disconnected: " + e.getMessage());
					break;
				}catch(ClassNotFoundException | IOException e) {
					e.printStackTrace(); 
					System.out.println("Server communication error: " + e.getMessage());
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
