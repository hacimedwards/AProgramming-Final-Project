

package view;

import java.net.URL;

import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;

import model.User;

public class AdminLoginWindow extends JFrame {
	// Variables declaration                   
    private JLabel adminLabel;
    private JButton adminOptionBtn;
    private JTextField emailErrorText;
    private JLabel emailLabel;
    private JTextField emailTextField;
    private JPanel jPanel1;
    private JPanel jPanel2;
    private JButton loginBtn;
    private JLabel logoLabel;
    private JLabel nameLabel1;
    private JLabel nameLabel2;
    private JTextField passwordErrorField;
    private JPasswordField passwordField;
    private JLabel passwordLabel;
    private JButton techOptiomBtn;

    
    public AdminLoginWindow() {
        initComponents();
    }

   
                           
    private void initComponents() {

        jPanel1 = new JPanel();
        logoLabel = new JLabel();
        nameLabel1 = new JLabel();
        nameLabel2 = new JLabel();
        techOptiomBtn = new JButton();
        adminOptionBtn = new JButton();
        jPanel2 = new JPanel();
        adminLabel = new JLabel();
        emailLabel = new JLabel();
        emailTextField = new JTextField();
        passwordLabel = new JLabel();
        passwordField = new JPasswordField();
        loginBtn = new JButton();
        emailErrorText = new JTextField();
        passwordErrorField = new JTextField();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Admin Login Window");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        logoLabel.setHorizontalAlignment(SwingConstants.CENTER);
        logoLabel.setIcon(loadIcon("Adobe_Express_-_file_30.png"));

        nameLabel1.setFont(new java.awt.Font("Showcard Gothic", 1, 18)); // NOI18N
        nameLabel1.setForeground(new java.awt.Color(142, 82, 255));
        nameLabel1.setHorizontalAlignment(SwingConstants.CENTER);
        nameLabel1.setText("CAMPUS LAB & EQUIPMENT BOOKING ");

        nameLabel2.setFont(new java.awt.Font("Showcard Gothic", 1, 18)); // NOI18N
        nameLabel2.setForeground(new java.awt.Color(142, 82, 255));
        nameLabel2.setHorizontalAlignment(SwingConstants.CENTER);
        nameLabel2.setText("SYSTEM");

        techOptiomBtn.setBackground(new java.awt.Color(140, 82, 255));
        techOptiomBtn.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        techOptiomBtn.setForeground(new java.awt.Color(255, 255, 255));
        techOptiomBtn.setText("TECH");
        techOptiomBtn.setBorder(null);
        techOptiomBtn.addActionListener(this::techOptiomBtnActionPerformed);

        adminOptionBtn.setBackground(new java.awt.Color(75, 0, 130));
        adminOptionBtn.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        adminOptionBtn.setForeground(new java.awt.Color(255, 255, 255));
        adminOptionBtn.setText("ADMIN");
        adminOptionBtn.setBorder(null);
        adminOptionBtn.addActionListener(this::adminOptionBtnActionPerformed);

        GroupLayout jPanel1Layout = new GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(121, 121, 121)
                        .addComponent(logoLabel, GroupLayout.PREFERRED_SIZE, 159, GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(nameLabel1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(153, 153, 153)
                        .addComponent(nameLabel2, GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(43, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                    .addComponent(adminOptionBtn, GroupLayout.DEFAULT_SIZE, 146, Short.MAX_VALUE)
                    .addComponent(techOptiomBtn, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(logoLabel, GroupLayout.PREFERRED_SIZE, 161, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(nameLabel1, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(nameLabel2)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addComponent(adminOptionBtn, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(techOptiomBtn, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
                .addGap(70, 70, 70))
        );

        jPanel2.setBackground(new java.awt.Color(140, 82, 255));
        jPanel2.setMaximumSize(new java.awt.Dimension(547, 383));
        jPanel2.setMinimumSize(new java.awt.Dimension(547, 383));

        adminLabel.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        adminLabel.setForeground(new java.awt.Color(255, 255, 255));
        adminLabel.setHorizontalAlignment(SwingConstants.CENTER);
        adminLabel.setText("ADMIN LOGIN");

        emailLabel.setForeground(new java.awt.Color(255, 255, 255));
        emailLabel.setText("Username");

        emailTextField.setBorder(null);
        emailTextField.addActionListener(this::emailTextFieldActionPerformed);

        passwordLabel.setForeground(new java.awt.Color(255, 255, 255));
        passwordLabel.setText("Password");

        passwordField.setBorder(null);
        passwordField.addActionListener(this::passwordFieldActionPerformed);

        loginBtn.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        loginBtn.setForeground(new java.awt.Color(140, 82, 255));
        loginBtn.setText("LOGIN");
        loginBtn.setBorder(null);
        loginBtn.addActionListener(this::loginBtnActionPerformed);

        emailErrorText.setEditable(false);
        emailErrorText.setBackground(new java.awt.Color(140, 82, 255));
        emailErrorText.setForeground(new java.awt.Color(255, 51, 51));
        emailErrorText.setText("");
        emailErrorText.setBorder(null);
        emailErrorText.addActionListener(this::emailErrorTextActionPerformed);

        passwordErrorField.setEditable(false);
        passwordErrorField.setBackground(new java.awt.Color(140, 82, 255));
        passwordErrorField.setForeground(new java.awt.Color(255, 51, 51));
        passwordErrorField.setText("");
        passwordErrorField.setBorder(null);
        passwordErrorField.addActionListener(this::passwordErrorFieldActionPerformed);

        GroupLayout jPanel2Layout = new GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(122, 122, 122)
                .addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(passwordErrorField, GroupLayout.PREFERRED_SIZE, 243, GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                        .addComponent(passwordField, GroupLayout.DEFAULT_SIZE, 310, Short.MAX_VALUE)
                        .addComponent(emailLabel, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
                        .addComponent(emailTextField)
                        .addComponent(passwordLabel))
                    .addComponent(loginBtn, GroupLayout.PREFERRED_SIZE, 87, GroupLayout.PREFERRED_SIZE)
                    .addComponent(emailErrorText, GroupLayout.PREFERRED_SIZE, 243, GroupLayout.PREFERRED_SIZE))
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(98, Short.MAX_VALUE)
                .addComponent(adminLabel, GroupLayout.PREFERRED_SIZE, 364, GroupLayout.PREFERRED_SIZE)
                .addGap(85, 85, 85))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(67, 67, 67)
                .addComponent(adminLabel)
                .addGap(30, 30, 30)
                .addComponent(emailLabel)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(emailTextField, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(emailErrorText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(passwordLabel)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(passwordField, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(passwordErrorField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(loginBtn, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }//                         

    private void emailTextFieldActionPerformed(java.awt.event.ActionEvent evt) {                                               
        // TODO add your handling code here:
    }                                              

    private void loginBtnActionPerformed(java.awt.event.ActionEvent evt) {
        emailErrorText.setText("");
        passwordErrorField.setText("");

        String email = emailTextField.getText().trim();
        String password = new String(passwordField.getPassword());

        boolean valid = true;
        if (email.isEmpty()) {
            emailErrorText.setText("Username is required");
            valid = false;
        }
        if (password.isEmpty()) {
            passwordErrorField.setText("Password is required");
            valid = false;
        }
        if (!valid) return;

        try {
            ServerGateway gateway = new ServerGateway("localhost", 8888);
            User user = gateway.userLogin(email, password);
            gateway.close();

            if (user != null && user.getRole() != null && user.getRole().equalsIgnoreCase("ADMIN")) {
                AdminDashboard adminDash = new AdminDashboard(user.getUsername());
                adminDash.setVisible(true);
                adminDash.pack();
                adminDash.setLocationRelativeTo(null);
                this.dispose();
            } else if (user != null) {
                emailErrorText.setText("Access denied. Admin credentials required.");
            } else {
                emailErrorText.setText("Invalid email or password");
            }
        } catch (Exception e) {
            e.printStackTrace();
            passwordErrorField.setText("Unable to connect to server");
        }
    }                                        

    private void emailErrorTextActionPerformed(java.awt.event.ActionEvent evt) {                                               
        // TODO add your handling code here:
    }                                              

    private void passwordFieldActionPerformed(java.awt.event.ActionEvent evt) {                                              
        // TODO add your handling code here:
    }                                             

    private void passwordErrorFieldActionPerformed(java.awt.event.ActionEvent evt) {                                                   
        // TODO add your handling code here:
    }                                                  

    private void techOptiomBtnActionPerformed(java.awt.event.ActionEvent evt) {                                              
        // TODO add your handling code here:
        TechLoginWindow techFrame = new TechLoginWindow(); 
        techFrame.setVisible(true);
        techFrame.pack();
        techFrame.setLocationRelativeTo(null);
        this.dispose(); 
    }                                             

    private void adminOptionBtnActionPerformed(java.awt.event.ActionEvent evt) {                                               
        // TODO add your handling code here:
    }                                              

    
    public static void main(String args[]) {
       
        
       new AdminLoginWindow().setVisible(true);
    }

    private ImageIcon loadIcon(String fileName) {
        URL location = getClass().getResource("/images/" + fileName);
        if (location == null) {
            // Fallback for projects where image files are placed at classpath root.
            location = getClass().getResource("/" + fileName);
        }
        return location != null ? new ImageIcon(location) : null;
    }
    
    
                    
}
