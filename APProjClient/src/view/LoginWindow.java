package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingWorker;

import ClientDriver.ClientController;

public class LoginWindow extends JFrame implements ActionListener{
	private JTextField usernameText;
	private JTextField passwordText;
	private JLabel userLabel;
	private JLabel passwordLabel;
	private JButton loginButton;
	private ClientController controller;
	
	public LoginWindow(ClientController controller) {
		this.controller = controller;
		initializeComponents();
		addToWindow();
		registerListeners();
		setProperties();
	}
	
	public void initializeComponents() {
		usernameText = new JTextField();
		passwordText = new JTextField();
		userLabel = new JLabel("Username:");
		passwordLabel = new JLabel("Password:");
		loginButton = new JButton("Login");
	}
	
	public void addToWindow() {
		this.add(userLabel);
		this.add(usernameText);
		this.add(passwordLabel);
		this.add(passwordText);
		this.add(loginButton);
	}
	
	public void setProperties() {
		userLabel.setBounds(50, 50, 80, 50);
		passwordLabel.setBounds(50, 100, 80, 50);
		usernameText.setBounds(200, 60 , 150, 25);
		passwordText.setBounds(200, 110, 150, 25);
		loginButton.setBounds(250, 300, 80, 25);
		this.setSize(900,600);
		this.setTitle("Login");
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setLayout(null);
		this.setVisible(true);
		
	}
	
	public void registerListeners() {
		loginButton.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		SwingWorker<Void, Void> worker = new SwingWorker<Void,Void>(){

			@Override
			protected Void doInBackground() throws Exception {
				if(e.getSource() == loginButton) {
					String username = usernameText.getText().trim();
					String password = passwordText.getText().trim();
					controller.login(username, password);
				}
				return null;
			}
			
		};
		
		worker.execute();
	}
	
	

}
