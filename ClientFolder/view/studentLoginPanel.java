
package view;



import java.awt.BorderLayout;

import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.SwingConstants;

import model.User;


public class studentLoginPanel extends JPanel {

	
	private static final long serialVersionUID = 1L;
	// Variables declaration                     
    private JTextField idField;
    private JLabel jLabel1;
    private JButton loginBtn;
    private JLabel loginLabel;
    private JPasswordField passwordField;
    private JLabel passwordLabel;
    private JLabel sIdLabel;

     
    public studentLoginPanel() {
        initComponents();
    }

                             
    private void initComponents() {

        jLabel1 = new JLabel();
        loginLabel = new JLabel();
        sIdLabel = new JLabel();
        idField = new JTextField();
        passwordLabel = new JLabel();
        passwordField = new JPasswordField();
        loginBtn = new JButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setMaximumSize(new java.awt.Dimension(914, 523));
        setMinimumSize(new java.awt.Dimension(914, 523));
      

        jLabel1.setIcon(new ImageIcon(getClass().getResource("/images/Logo smaller.png"))); // NOI18N

        loginLabel.setBackground(new java.awt.Color(140, 82, 255));
        loginLabel.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        loginLabel.setForeground(new java.awt.Color(140, 82, 255));
        loginLabel.setHorizontalAlignment(SwingConstants.CENTER);
        loginLabel.setText("STUDENT LOGIN");

        sIdLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        sIdLabel.setForeground(new java.awt.Color(140, 82, 255));
        sIdLabel.setText("Username");

        passwordLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        passwordLabel.setForeground(new java.awt.Color(140, 82, 255));
        passwordLabel.setText("Password");

        passwordField.addActionListener(this::passwordFieldActionPerformed);

        loginBtn.setBackground(new java.awt.Color(140, 82, 255));
        loginBtn.setForeground(new java.awt.Color(255, 255, 255));
        loginBtn.setText("LOGIN");
        loginBtn.addActionListener(this::loginBtnActionPerformed); 

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(jLabel1)
                .addGap(247, 247, 247)
                .addComponent(loginLabel)
                .addContainerGap(287, Short.MAX_VALUE))
            .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(loginBtn, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
                    .addComponent(passwordField, GroupLayout.PREFERRED_SIZE, 351, GroupLayout.PREFERRED_SIZE)
                    .addComponent(passwordLabel)
                    .addComponent(sIdLabel)
                    .addComponent(idField, GroupLayout.PREFERRED_SIZE, 351, GroupLayout.PREFERRED_SIZE))
                .addGap(264, 264, 264))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(loginLabel)
                    .addComponent(jLabel1))
                .addGap(46, 46, 46)
                .addComponent(sIdLabel)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(idField, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47)
                .addComponent(passwordLabel)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(passwordField, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(loginBtn, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
                .addContainerGap(148, Short.MAX_VALUE))
        );
    }                       

    private void passwordFieldActionPerformed(java.awt.event.ActionEvent evt) {                                              
        // TODO add your handling code here:
    }                                             

    private void loginBtnActionPerformed(java.awt.event.ActionEvent evt) {
        String username = idField.getText().trim();
        String password = new String(passwordField.getPassword());

        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter both Student ID and Password.", "Login", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            ServerGateway gateway = new ServerGateway("localhost", 8888);
            User user = gateway.userLogin(username, password);
            gateway.close();

            if (user != null) {
                StudentRequestPanel studentRPanel = new StudentRequestPanel(user.getId());
                studentRPanel.setVisible(true);
                this.removeAll();
                this.setLayout(new BorderLayout());
                this.add(studentRPanel);
                this.repaint();
                this.revalidate();
            } else {
                JOptionPane.showMessageDialog(this, "Invalid credentials. Please try again.", "Login", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Unable to connect to server.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

                   
}
