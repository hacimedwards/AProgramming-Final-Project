package view;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
import model.Reservation;
import model.User;
import java.util.Map;
import java.util.ArrayList;

/**
 * Singleton ServerGateway that maintains a persistent connection
 * throughout the user session, eliminating unnecessary connect/disconnect cycles.
 */
public class ServerGateway {
    private static ServerGateway instance;
    private Socket socket;
    private ObjectOutputStream objectOS;
    private ObjectInputStream objectIS;
    private String host;
    private int port;

    /**
     * Private constructor - use getInstance() instead
     */
    ServerGateway(String host, int port) throws IOException {
        this.host = host;
        this.port = port;
        connect();
    }

    /**
     * Get or create the singleton instance. 
     * If no instance exists or connection is closed, creates a new one.
     */
    public static ServerGateway getInstance(String host, int port) throws IOException {
        if (instance == null || instance.isClosed()) {
            instance = new ServerGateway(host, port);
        }
        return instance;
    }

    /**
     * Convenience method using default localhost:8888
     */
    public static ServerGateway getInstance() throws IOException {
        return getInstance("localhost", 8888);
    }

    /**
     * Establish the socket connection and object streams
     */
    private void connect() throws IOException {
        socket = new Socket(host, port);
        objectOS = new ObjectOutputStream(socket.getOutputStream());
        objectIS = new ObjectInputStream(socket.getInputStream());
    }

    /**
     * Check if the connection is still alive
     */
    public boolean isClosed() {
        return socket == null || socket.isClosed();
    }

    /**
     * Close the connection and clear the singleton instance.
     * Only call this when you're completely done (e.g., on logout).
     */
    public void close() throws IOException {
        if (socket != null && !socket.isClosed()) {
            socket.close();
        }
        instance = null;
    }

    public User userLogin(String username, String password) throws IOException, ClassNotFoundException {
        objectOS.writeObject("User Login");
        objectOS.flush();
        objectOS.writeObject(username);
        objectOS.flush();
        objectOS.writeObject(password);
        objectOS.flush();
        User user = (User) objectIS.readObject();
        return user;
    }

    public void addUser(User user) throws IOException {
        objectOS.writeObject("Add User");
        objectOS.flush();
        objectOS.writeObject(user);
        objectOS.flush();
    }

    public void deleteUser(int userId) throws IOException {
        objectOS.writeObject("Delete User");
        objectOS.flush();
        objectOS.writeObject(0);
        objectOS.flush();
        objectOS.writeObject(userId);
        objectOS.flush();
    }

    public void createReservation(Reservation reservation) throws IOException {
        objectOS.writeObject("Create Reservation");
        objectOS.flush();
        objectOS.writeObject(reservation);
        objectOS.flush();
    }

    public Map<String, ArrayList<String>> getAvailableEquipment(int labId) throws IOException, ClassNotFoundException {
        objectOS.writeObject("Get Available Equipment");
        objectOS.flush();
        objectOS.writeObject(labId);
        objectOS.flush();
        @SuppressWarnings("unchecked")
        Map<String, ArrayList<String>> equipment = (Map<String, ArrayList<String>>) objectIS.readObject();
        return equipment;
    }

    public List<Reservation> getAllReservations() throws IOException, ClassNotFoundException {
        objectOS.writeObject("Get All Reservations");
        objectOS.flush();
        @SuppressWarnings("unchecked")
        List<Reservation> reservations = (List<Reservation>) objectIS.readObject();
        return reservations;
    }

    public boolean updateReservationStatus(int reservationId, String status) throws IOException, ClassNotFoundException {
        objectOS.writeObject("Update Reservation Status");
        objectOS.flush();
        objectOS.writeObject(reservationId);
        objectOS.flush();
        objectOS.writeObject(status);
        objectOS.flush();
        return (boolean) objectIS.readObject();
    }
}
