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

//Window to create reservation
public class ReservationWindow extends JFrame implements ActionListener{
	private JLabel labLabel;
	private JLabel seatLabel;
	private JLabel startDateLabel;
	private JLabel endDateLabel;
	private JComboBox<String> labText;
	private JComboBox<String> seatText;
	private JComboBox<String> roleText;
	private JComboBox<String> equipmentText;
	private JTextField startDay;
	private JComboBox<String> startMonth;
	private JTextField startYear;
	private JComboBox<String> startHour;
	private JComboBox<String> startMinute;
	private JComboBox<String> hoursNeeded;
	private JComboBox<String> endHour;
	private JComboBox<String> endMinute;
	private String[] labs = {"Lab 1", "Lab 2", "Lab3"};
	private String[] seats = {"Seat 1", "Seat 2", "Seat 3"};
	private String[] hours = {"8","9","10","11","12","1", "2","3","4","5","6"};
	private String[] minutes = {"00","15","30","45"};
	private String[] timeReserved = {"1 Hour","2 Hours", "3 Hours"};
	private String[] months = {"January", "February", "March", "April", "May", "June", "July", "August",
			"September", "October", "Novemeber", "December"};
	
	private JButton addButton;
	private JButton closeButton;
	private ClientController controller;
	private JLabel startTimeLabel;
	
	public ReservationWindow(ClientController controller) {
		this.controller = controller;
		initializeComponents();
		registerListeners();
		addToWindow();
		setWindowProperties();
	}
	
	public void initializeComponents() {
		labLabel = new JLabel("Lab Id:");
		seatLabel = new JLabel("Seat Id");
		startDateLabel = new JLabel("Date(mm/dd/yyyy)");
		startTimeLabel = new JLabel("Time(hh-mm)");
		endDateLabel = new JLabel("Hours: ");
		labText = new JComboBox<String>(labs);
		seatText = new JComboBox<String>(seats);
		roleText = new JComboBox<String>();
		equipmentText = new JComboBox<String>();
		startDay = new JTextField();
		startMonth = new JComboBox<String>();
		startYear = new JTextField();
		startHour = new JComboBox<String>();
		startMinute = new JComboBox<String>();
		hoursNeeded = new JComboBox<String>();
		
		addButton = new JButton("Add User");
		closeButton = new JButton("Close");
	}
		
	
	
	public void addToWindow() {
		this.add(labLabel);
		this.add(seatLabel);
		this.add(startDateLabel);
		this.add(endDateLabel);
		this.add(labText);
		this.add(seatText);
		this.add(equipmentText);
		this.add(startDay);
		this.add(startMonth);
		this.add(startYear);
		this.add(startHour);
		this.add(startMinute);
		this.add(hoursNeeded);
		this.add(roleText);
		this.add(addButton);
		this.add(closeButton);
	}
	
	public void setWindowProperties() {
		labLabel.setBounds(50, 50, 80, 50);
		seatLabel.setBounds(50, 100, 80, 50);
		startDateLabel.setBounds(50, 150, 150, 50);
		startTimeLabel.setBounds(50, 200, 150, 50);
		endDateLabel.setBounds(50, 200, 150, 25);
		labText.setBounds(100, 60, 80, 25);
		seatText.setBounds(100, 110, 80, 25);
		startMonth.setBounds(200, 160, 30, 20);
		startDay.setBounds(240, 160, 30, 20);
		startYear.setBounds(280, 160, 30, 20);
		startHour.setBounds(200, 200, 30, 20);
		startMinute.setBounds(240, 200, 30, 20);
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
			String labId = (String) labText.getSelectedItem();
			String seatId = (String) seatText.getSelectedItem();
			System.out.println(labId+" "+seatId);
			//controller.addNewUser(username, password, role);
		}
		
		if(e.getSource() == closeButton) {
			this.dispose();
		}
		
		
	}

}
