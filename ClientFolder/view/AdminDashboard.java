
package view;

import java.awt.Color;

import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.LayoutStyle;
import javax.swing.WindowConstants;



public class AdminDashboard extends JFrame {
	
	private static final long serialVersionUID = 1L;
	// Variables declaration                     
    private JLabel adminLabel;
    private JPanel buttonPanel;
    private JPanel displayPanel;
    private JPanel headerPanel;
    private JLabel iconLable;
    private JLabel logoLabel;
    private JButton reservationBtn;
    private JLabel reservationIconLabel;
    private JButton scheduleBtn;
    private JLabel scheduleIconLabel;
    private JLabel usernameLabel;
    private JLabel welcomeLabel;      
    private JButton logoutBtn;
    private ReservationPanel reservationPanel; 
    private ReservationRequestsPanel requestsPanel; 
    private String username;

    
    public AdminDashboard(String username) {
        this.username = username;
        initializeComponents();
        setReservations(); 
    }
    
    private void setReservations(){
        scheduleBtn.setForeground(new Color(40,82,255));
        reservationBtn.setForeground(new Color(204,204,204));
        displayPanel.removeAll();
        reservationPanel = new ReservationPanel(); 
        displayPanel.add(reservationPanel); 
        displayPanel.revalidate();
        displayPanel.repaint();
    }
    
    private void setRequests(){
        reservationBtn.setForeground(new Color(40,82,255));
        scheduleBtn.setForeground(new Color(204,204,204));
        displayPanel.removeAll();
        requestsPanel = new ReservationRequestsPanel(); 
        displayPanel.add(requestsPanel); 
        displayPanel.revalidate();
        displayPanel.repaint();
    }
    
    

    
                            
    private void initializeComponents() {

        headerPanel = new JPanel();
        logoLabel = new JLabel();
        adminLabel = new JLabel();
        welcomeLabel = new JLabel();
        iconLable = new JLabel();
        usernameLabel = new JLabel();
        logoutBtn = new JButton();
        buttonPanel = new JPanel();
        scheduleBtn = new JButton();
        reservationBtn = new JButton();
        scheduleIconLabel = new JLabel();
        reservationIconLabel = new JLabel();
        displayPanel = new JPanel();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Admin Portal");

        headerPanel.setBackground(new java.awt.Color(255, 255, 255));

        logoLabel.setIcon(new ImageIcon(getClass().getResource("/images/Logo smaller.png"))); 

        adminLabel.setFont(new java.awt.Font("Segoe UI Historic", 1, 24)); 
        adminLabel.setForeground(new java.awt.Color(140, 82, 255));
        adminLabel.setText("Administrator Portal");

        welcomeLabel.setForeground(new java.awt.Color(140, 82, 255));
        welcomeLabel.setText("Welcome to the adminstrator portal");

        iconLable.setIcon(new ImageIcon(getClass().getResource("/images/Usericon (2).png"))); 
        iconLable.setText("jLabel5");

        usernameLabel.setText("Hello, " + username);

        logoutBtn.setFont(new java.awt.Font("Segoe UI", 0, 12));
        logoutBtn.setForeground(new java.awt.Color(140, 82, 255));
        logoutBtn.setText("Logout");
        logoutBtn.setBorder(null);
        logoutBtn.setBorderPainted(false);
        logoutBtn.addActionListener(this::logoutBtnActionPerformed);

        GroupLayout headerPanelLayout = new GroupLayout(headerPanel);
        headerPanel.setLayout(headerPanelLayout);
        headerPanelLayout.setHorizontalGroup(
            headerPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(headerPanelLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(logoLabel, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
                .addGap(59, 59, 59)
                .addGroup(headerPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(welcomeLabel)
                    .addComponent(adminLabel,GroupLayout.PREFERRED_SIZE, 256, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(logoutBtn)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(iconLable, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(usernameLabel)
                .addGap(75, 75, 75))
        );
        headerPanelLayout.setVerticalGroup(
            headerPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(headerPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(headerPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(headerPanelLayout.createSequentialGroup()
                        .addComponent(adminLabel)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(welcomeLabel))
                            .addComponent(logoutBtn)
                    .addComponent(logoLabel)
                    .addGroup(headerPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(iconLable, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
                        .addComponent(usernameLabel)))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        buttonPanel.setBackground(new java.awt.Color(255, 255, 255));

        scheduleBtn.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        scheduleBtn.setForeground(new java.awt.Color(140, 82, 255));
        scheduleBtn.setText("Reservation Schedule");
        scheduleBtn.setBorder(null);
        scheduleBtn.setBorderPainted(false);
        scheduleBtn.addActionListener(this::scheduleBtnActionPerformed);

        reservationBtn.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        reservationBtn.setForeground(new java.awt.Color(204, 204, 204));
        reservationBtn.setText("Reservation Requests");
        reservationBtn.setBorder(null);
        reservationBtn.setBorderPainted(false);
        reservationBtn.addActionListener(this::reservationBtnActionPerformed);

        scheduleIconLabel.setIcon(new ImageIcon(getClass().getResource("/images/schedule icon.png"))); 
        scheduleIconLabel.setText("jLabel6");

        reservationIconLabel.setIcon(new ImageIcon(getClass().getResource("/images/requesticon.png"))); 
        reservationIconLabel.setText("jLabel7");

        GroupLayout buttonPanelLayout = new GroupLayout(buttonPanel);
        buttonPanel.setLayout(buttonPanelLayout);
        buttonPanelLayout.setHorizontalGroup(
            buttonPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(buttonPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(buttonPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(scheduleIconLabel, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
                    .addComponent(reservationIconLabel, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(buttonPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(reservationBtn)
                    .addComponent(scheduleBtn))
                .addContainerGap())
        );
        buttonPanelLayout.setVerticalGroup(
            buttonPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(buttonPanelLayout.createSequentialGroup()
                .addGap(61, 61, 61)
                .addGroup(buttonPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(scheduleBtn)
                    .addComponent(scheduleIconLabel))
                .addGap(18, 18, 18)
                .addGroup(buttonPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(reservationBtn)
                    .addComponent(reservationIconLabel))
                .addContainerGap(354, Short.MAX_VALUE))
        );

        displayPanel.setMaximumSize(new java.awt.Dimension(754, 502));
        displayPanel.setMinimumSize(new java.awt.Dimension(754, 502));
        displayPanel.setPreferredSize(new java.awt.Dimension(754, 502));
        displayPanel.setLayout(new java.awt.BorderLayout());

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(headerPanel,GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(buttonPanel,GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(displayPanel, GroupLayout.DEFAULT_SIZE, 760, Short.MAX_VALUE)))
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(headerPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(buttonPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, 0)
                        .addComponent(displayPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }                        

    
    private void scheduleBtnActionPerformed(java.awt.event.ActionEvent evt) {                                            
        // TODO add your handling code here:
        setReservations(); 
    }                                           

    private void reservationBtnActionPerformed(java.awt.event.ActionEvent evt) {                                               
        // TODO add your handling code here:
        setRequests();
    }                                              

    private void logoutBtnActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            ServerGateway.getInstance().close();
        } catch (Exception ex) {
            // Ignore close errors and continue logout navigation.
        }

        LoginWindow loginWindow = new LoginWindow();
        loginWindow.setVisible(true);
        loginWindow.setLocationRelativeTo(null);
        dispose();
    }

    
    public static void main(String args[]) {
    

        
        new AdminDashboard("Admin").setVisible(true);
    }

                
}
