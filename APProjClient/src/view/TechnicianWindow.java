package view;

import javax.swing.JButton;
import javax.swing.JFrame;

import ClientDriver.ClientController;

public class TechnicianWindow extends JFrame {
	private JButton review;
	private JButton cancel;
	private ClientController controller;
	
	
	public TechnicianWindow(int userId, ClientController controller) {
		this.controller = controller;
		initializeComponents();
		addToWindow();
		setProperties();
	}


	private void setProperties() {
		review.setBounds(50, 50, 80, 50);
		cancel.setBounds(50, 100, 80, 50);
		this.setSize(400,300);
		this.setTitle("Admin");
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setLayout(null);
		this.setVisible(true);
		
	}


	private void addToWindow() {
		this.add(review);
		this.add(cancel);
		
	}


	private void initializeComponents() {
		review = new JButton("Review");
		cancel = new JButton("Cancel");
		
	}
	
	

}