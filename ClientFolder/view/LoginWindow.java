package view;

import javax.swing.*;
import java.awt.BorderLayout;
import java.net.URL;

import model.User;

public class LoginWindow extends JFrame {
	private static final long serialVersionUID = 1L;

	private static final String ROLE_ADMIN = "ADMIN";
	private static final String ROLE_TECHNICIAN = "TECHNICIAN";
	private static final String ROLE_STUDENT = "STUDENT";

	private JLabel titleLabel;
	private JLabel logoLabel;
	private JLabel nameLabel1;
	private JLabel nameLabel2;
	private JPanel leftPanel;
	private JPanel rightPanel;

	private JLabel usernameLabel;
	private JTextField usernameField;
	private JLabel passwordLabel;
	private JPasswordField passwordField;
	private JLabel roleLabel;
	private JComboBox<String> roleComboBox;
	private JButton loginButton;
	private JTextField usernameErrorField;
	private JTextField passwordErrorField;

	public LoginWindow() {
		initComponents();
	}

	private void initComponents() {
		leftPanel = new JPanel();
		rightPanel = new JPanel();
		logoLabel = new JLabel();
		nameLabel1 = new JLabel();
		nameLabel2 = new JLabel();

		titleLabel = new JLabel();
		usernameLabel = new JLabel();
		usernameField = new JTextField();
		passwordLabel = new JLabel();
		passwordField = new JPasswordField();
		roleLabel = new JLabel();
		roleComboBox = new JComboBox<>(new String[]{ROLE_ADMIN, ROLE_TECHNICIAN, ROLE_STUDENT});
		loginButton = new JButton();
		usernameErrorField = new JTextField();
		passwordErrorField = new JTextField();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Campus Lab Booking - Login");

		leftPanel.setBackground(new java.awt.Color(255, 255, 255));
		logoLabel.setHorizontalAlignment(SwingConstants.CENTER);
		logoLabel.setIcon(loadIcon("Adobe_Express_-_file_30.png"));

		nameLabel1.setFont(new java.awt.Font("Showcard Gothic", 1, 18));
		nameLabel1.setForeground(new java.awt.Color(142, 82, 255));
		nameLabel1.setHorizontalAlignment(SwingConstants.CENTER);
		nameLabel1.setText("CAMPUS LAB & EQUIPMENT BOOKING");

		nameLabel2.setFont(new java.awt.Font("Showcard Gothic", 1, 18));
		nameLabel2.setForeground(new java.awt.Color(142, 82, 255));
		nameLabel2.setHorizontalAlignment(SwingConstants.CENTER);
		nameLabel2.setText("SYSTEM");

		GroupLayout leftLayout = new GroupLayout(leftPanel);
		leftPanel.setLayout(leftLayout);
		leftLayout.setHorizontalGroup(
			leftLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(leftLayout.createSequentialGroup()
					.addGap(112, 112, 112)
					.addComponent(logoLabel, GroupLayout.PREFERRED_SIZE, 159, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(112, Short.MAX_VALUE))
				.addGroup(leftLayout.createSequentialGroup()
					.addGap(28, 28, 28)
					.addGroup(leftLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
						.addComponent(nameLabel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(nameLabel2, GroupLayout.DEFAULT_SIZE, 328, Short.MAX_VALUE))
					.addContainerGap(27, Short.MAX_VALUE))
		);
		leftLayout.setVerticalGroup(
			leftLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(leftLayout.createSequentialGroup()
					.addGap(46, 46, 46)
					.addComponent(logoLabel, GroupLayout.PREFERRED_SIZE, 161, GroupLayout.PREFERRED_SIZE)
					.addGap(18, 18, 18)
					.addComponent(nameLabel1, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
					.addComponent(nameLabel2)
					.addContainerGap(153, Short.MAX_VALUE))
		);

		rightPanel.setBackground(new java.awt.Color(140, 82, 255));

		titleLabel.setFont(new java.awt.Font("Segoe UI", 1, 34));
		titleLabel.setForeground(new java.awt.Color(255, 255, 255));
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setText("USER LOGIN");

		usernameLabel.setForeground(new java.awt.Color(255, 255, 255));
		usernameLabel.setText("Username");

		usernameField.setBorder(null);

		passwordLabel.setForeground(new java.awt.Color(255, 255, 255));
		passwordLabel.setText("Password");

		passwordField.setBorder(null);

		roleLabel.setForeground(new java.awt.Color(255, 255, 255));
		roleLabel.setText("Role");

		loginButton.setFont(new java.awt.Font("Segoe UI", 0, 14));
		loginButton.setForeground(new java.awt.Color(140, 82, 255));
		loginButton.setText("LOGIN");
		loginButton.setBorder(null);
		loginButton.addActionListener(this::loginButtonActionPerformed);

		usernameErrorField.setEditable(false);
		usernameErrorField.setBackground(new java.awt.Color(140, 82, 255));
		usernameErrorField.setForeground(new java.awt.Color(255, 51, 51));
		usernameErrorField.setText("");
		usernameErrorField.setBorder(null);

		passwordErrorField.setEditable(false);
		passwordErrorField.setBackground(new java.awt.Color(140, 82, 255));
		passwordErrorField.setForeground(new java.awt.Color(255, 51, 51));
		passwordErrorField.setText("");
		passwordErrorField.setBorder(null);

		GroupLayout rightLayout = new GroupLayout(rightPanel);
		rightPanel.setLayout(rightLayout);
		rightLayout.setHorizontalGroup(
			rightLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(rightLayout.createSequentialGroup()
					.addGap(106, 106, 106)
					.addGroup(rightLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addComponent(roleLabel)
						.addGroup(rightLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
							.addComponent(passwordErrorField, GroupLayout.DEFAULT_SIZE, 312, Short.MAX_VALUE)
							.addComponent(usernameErrorField, GroupLayout.DEFAULT_SIZE, 312, Short.MAX_VALUE)
							.addComponent(loginButton, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
							.addComponent(passwordLabel)
							.addComponent(passwordField)
							.addComponent(usernameLabel)
							.addComponent(usernameField)
							.addComponent(roleComboBox, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
					.addContainerGap(106, Short.MAX_VALUE))
				.addGroup(GroupLayout.Alignment.TRAILING, rightLayout.createSequentialGroup()
					.addContainerGap(72, Short.MAX_VALUE)
					.addComponent(titleLabel, GroupLayout.PREFERRED_SIZE, 380, GroupLayout.PREFERRED_SIZE)
					.addGap(72, 72, 72))
		);
		rightLayout.setVerticalGroup(
			rightLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(rightLayout.createSequentialGroup()
					.addGap(60, 60, 60)
					.addComponent(titleLabel)
					.addGap(26, 26, 26)
					.addComponent(usernameLabel)
					.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
					.addComponent(usernameField, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
					.addComponent(usernameErrorField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(12, 12, 12)
					.addComponent(passwordLabel)
					.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
					.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
					.addComponent(passwordErrorField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(12, 12, 12)
					.addComponent(roleLabel)
					.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
					.addComponent(roleComboBox, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
					.addGap(18, 18, 18)
					.addComponent(loginButton, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(42, Short.MAX_VALUE))
		);

		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(
			layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
					.addComponent(leftPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(0, 0, 0)
					.addComponent(rightPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(0, 0, Short.MAX_VALUE))
		);
		layout.setVerticalGroup(
			layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addComponent(leftPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addComponent(rightPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
		);

		pack();
		setLocationRelativeTo(null);
	}

	private void loginButtonActionPerformed(java.awt.event.ActionEvent evt) {
		usernameErrorField.setText("");
		passwordErrorField.setText("");

		String username = usernameField.getText().trim();
		String password = new String(passwordField.getPassword());
		String selectedRole = (String) roleComboBox.getSelectedItem();

		boolean valid = true;
		if (username.isEmpty()) {
			usernameErrorField.setText("Username is required");
			valid = false;
		}
		if (password.isEmpty()) {
			passwordErrorField.setText("Password is required");
			valid = false;
		}
		if (!valid) {
			return;
		}

		try {
			ServerGateway gateway = ServerGateway.getInstance();
			User user = gateway.userLogin(username, password);

			if (user == null || user.getRole() == null) {
				usernameErrorField.setText("Invalid username or password");
				return;
			}

			if (!selectedRole.equalsIgnoreCase(user.getRole())) {
				usernameErrorField.setText("Role mismatch: account is " + user.getRole());
				return;
			}

			routeByRole(user);
			dispose();
		} catch (Exception e) {
			e.printStackTrace();
			passwordErrorField.setText("Unable to connect to server");
		}
	}

	private void routeByRole(User user) {
		String role = user.getRole();
		if (ROLE_ADMIN.equalsIgnoreCase(role)) {
			AdminDashboard adminDashboard = new AdminDashboard(user.getUsername());
			adminDashboard.setVisible(true);
			adminDashboard.pack();
			adminDashboard.setLocationRelativeTo(null);
			return;
		}

		if (ROLE_TECHNICIAN.equalsIgnoreCase(role)) {
			JFrame frame = new JFrame("Technician Portal");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setContentPane(new ReservationRequestsPanel());
			frame.pack();
			frame.setLocationRelativeTo(null);
			frame.setVisible(true);
			return;
		}

		if (ROLE_STUDENT.equalsIgnoreCase(role)) {
			JFrame frame = new JFrame("Student Portal");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setLayout(new BorderLayout());
			frame.add(new StudentRequestPanel(user.getId()), BorderLayout.CENTER);
			frame.pack();
			frame.setLocationRelativeTo(null);
			frame.setVisible(true);
		}
	}

	private ImageIcon loadIcon(String fileName) {
		URL location = getClass().getResource("/images/" + fileName);
		if (location == null) {
			location = getClass().getResource("/" + fileName);
		}
		return location != null ? new ImageIcon(location) : null;
	}

	public static void main(String[] args) {
		new LoginWindow().setVisible(true);
	}
}
