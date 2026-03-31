package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import ClientDriver.ClientController;

public class StudentWindow extends JFrame implements ActionListener {
	private JButton createReservation;
	private ClientController controller;
	
	
	public StudentWindow(int userId, ClientController controller) {
		this.controller = controller;
		initializeComponents();
		addToWindow();
		registerListeners();
		setProperties();
	}


	private void setProperties() {
		createReservation.setBounds(50, 100, 80, 50);
		this.setSize(400,300);
		this.setTitle("Admin");
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setLayout(null);
		this.setVisible(true);
		
	}


	private void addToWindow() {
		this.add(createReservation);
	}


	private void initializeComponents() {
		createReservation = new JButton("Create Reseration");
		
	}
	
	private void registerListeners() {
		createReservation.addActionListener(this);
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == createReservation) {
			new ReservationWindow(controller);
		}
		
	}
	
	

}