package controller;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import model.User;

public class ClientHandler implements Runnable {
	private Socket clientSocket = null;
	private ObjectInputStream objIN = null;
	private ObjectOutputStream objOUT = null;
	
	public ClientHandler(Socket clientSocket) {
		this.clientSocket = clientSocket;
		
	}
	
	public void getStreams() {
		try {
			this.objIN = new ObjectInputStream(this.clientSocket.getInputStream());
			this.objOUT = new ObjectOutputStream(this.clientSocket.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		getStreams();
		while(true) {
			try {
				String request = (String) objIN.readObject();
				if (request.equalsIgnoreCase("Add User")) {
					User user = (User) objIN.readObject();
					if(user != null) {
						//addUserToDB(user);
					}
				}else if(request.equalsIgnoreCase("User Login")) {
					String username = (String) objIN.readObject();
					String password = (String) objIN.readObject();
					//userLogin(username, password);
				}
			}catch(ClassNotFoundException | IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	

}
