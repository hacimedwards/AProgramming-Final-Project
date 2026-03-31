package view;

import java.awt.Dimension;
import java.awt.PopupMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;

import ClientDriver.ClientController;
import model.Role;

import javax.swing.JButton;
import javax.swing.JComboBox;

public class UserDataWindow extends JFrame implements ActionListener{
	private JLabel usernameLabel;
	private JLabel passwordLabel;
	private JLabel roleLabel;
	private JTextField usernameText;
	private JTextField passwordText;
	private JComboBox<String> roleText;
	private JButton addButton;
	private JButton closeButton;
	private ClientController controller;
	
	public UserDataWindow(ClientController controller) {
		this.controller = controller;
		initializeComponents();
		registerListeners();
		addToWindow();
		setWindowProperties();
	}
	
	public void initializeComponents() {
		usernameLabel = new JLabel("Username");
		passwordLabel = new JLabel("Passoword");
		roleLabel = new JLabel("Role");
		usernameText = new JTextField();
		passwordText = new JTextField();
		String[] roles = {Role.ADMIN, Role.TECHNICIAN, Role.STUDENT};
		roleText = new JComboBox(roles);
		addButton = new JButton("Add User");
		closeButton = new JButton("Close");
	}
		
	
	
	public void addToWindow() {
		this.add(usernameLabel);
		this.add(passwordLabel);
		this.add(roleLabel);
		this.add(usernameText);
		this.add(passwordText);
		this.add(roleText);
		this.add(addButton);
		this.add(closeButton);
	}
	
	public void setWindowProperties() {
		usernameLabel.setBounds(50, 50, 80, 50);
		passwordLabel.setBounds(50, 100, 80, 50);
		roleLabel.setBounds(50, 150, 80,50);
		usernameText.setBounds(200, 60 , 150, 25);
		passwordText.setBounds(200, 110, 150, 25);
		roleText.setBounds(200, 160, 150, 25);
		addButton.setBounds(150, 300, 80, 25);
		closeButton.setBounds(250, 300, 80, 25);
		this.setTitle("Enter User Data");
		this.setSize(new Dimension(600,400));
		this.setLayout(null);
		this.setVisible(true);
	}
	

	public void registerListeners() {
		addButton.addActionListener(this);
		closeButton.addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == addButton) {
			String username = usernameText.getText().trim();
			String password = passwordText.getText().trim();
			int role = roleText.getSelectedIndex();
			controller.addNewUser(username, password, role);
		}
		
		if(e.getSource() == closeButton) {
			this.dispose();
		}
		
		
	}

}
