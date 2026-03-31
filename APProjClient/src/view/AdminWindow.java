package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import ClientDriver.ClientController;

public class AdminWindow extends JFrame implements ActionListener {
	private JButton review;
	private JButton cancel;
	private JButton addUser;
	private JButton deleteUser;
	private ClientController controller;
	
	
	public AdminWindow(int userId, ClientController controller) {
		this.controller = controller;
		initializeComponents();
		addToWindow();
		registerListeners();
		setProperties();
	}


	private void registerListeners() {
		addUser.addActionListener(this);
		deleteUser.addActionListener(this);
		review.addActionListener(this);
		cancel.addActionListener(this);
		
	}


	private void setProperties() {
		review.setBounds(50, 50, 80, 50);
		cancel.setBounds(50, 100, 80, 50);
		addUser.setBounds(200, 60 , 150, 25);
		deleteUser.setBounds(200, 110, 150, 25);
		this.setSize(400,300);
		this.setTitle("Admin");
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setLayout(null);
		this.setVisible(true);
		
	}


	private void addToWindow() {
		this.add(review);
		this.add(cancel);
		this.add(addUser);
		this.add(deleteUser);
		
	}


	private void initializeComponents() {
		review = new JButton("Review");
		cancel = new JButton("Cancel");
		addUser = new JButton("Add User");
		deleteUser = new JButton("Delete User");
		
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == addUser) {
			new UserDataWindow(controller);
		}else if(e.getSource() == deleteUser) {
			String userID = JOptionPane.showInputDialog(null, "Enter the user id", "Delete User");
			if(userID != null) {
				controller.deleteUser(Integer.parseInt(userID));
			}
			
		}
		
	}
	
	

}