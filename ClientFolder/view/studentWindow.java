
package view;

import java.awt.Color;

import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;


public class studentWindow extends JFrame {
	
	 // Variables declaration                
    private JDesktopPane jDesktopPane1;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JMenuBar jMenuBar1;
    private JMenu scheduleMenu;
    private JMenuItem logoutMenuItem;
    private JMenuItem studentLoginMenuItem;
    private JMenu studentMenu;
    private JMenuItem viewScheduleMenuItem;
    
       
    
	private static final long serialVersionUID = 1L;
	private studentInternalFrame studIFrame; 
    

    
    public studentWindow() {
        initComponents();
         
    }

                        
    private void initComponents() {

        jDesktopPane1 = new JDesktopPane();
        jLabel1 = new JLabel();
        jLabel2 = new JLabel();
        jMenuBar1 = new JMenuBar();
        studentMenu = new JMenu();
        studentLoginMenuItem = new JMenuItem();
        logoutMenuItem = new JMenuItem();
        scheduleMenu = new JMenu();
        viewScheduleMenuItem = new JMenuItem();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Student Portal");
        setBackground(new java.awt.Color(140, 82, 255));

        jLabel1.setIcon(new ImageIcon(getClass().getResource("/images/Adobe_Express_-_file_30.png"))); 
        jLabel1.setText("jLabel1");

        jLabel2.setFont(new java.awt.Font("Segoe UI Historic", 1, 36)); 
        jLabel2.setForeground(new java.awt.Color(140, 82, 255));
        jLabel2.setText("STUDENT PORTAL");

        jDesktopPane1.setLayer(jLabel1, JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jLabel2, JLayeredPane.DEFAULT_LAYER);
        
        

        GroupLayout jDesktopPane1Layout = new GroupLayout(jDesktopPane1);
        jDesktopPane1.setLayout(jDesktopPane1Layout);
        jDesktopPane1Layout.setHorizontalGroup(
            jDesktopPane1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jDesktopPane1Layout.createSequentialGroup()
                .addGap(426, 426, 426)
                .addComponent(jLabel1, GroupLayout.PREFERRED_SIZE, 151, GroupLayout.PREFERRED_SIZE)
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(GroupLayout.Alignment.TRAILING, jDesktopPane1Layout.createSequentialGroup()
                .addContainerGap(351, Short.MAX_VALUE)
                .addComponent(jLabel2, GroupLayout.PREFERRED_SIZE, 333, GroupLayout.PREFERRED_SIZE)
                .addGap(348, 348, 348))
        );
        jDesktopPane1Layout.setVerticalGroup(
            jDesktopPane1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, jDesktopPane1Layout.createSequentialGroup()
                .addContainerGap(165, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(28, 28, 28)
                .addComponent(jLabel2)
                .addGap(129, 129, 129))
        );

        studentMenu.setText("Student");

        studentLoginMenuItem.setText("Student Login");
        studentLoginMenuItem.addActionListener(this::studentLoginMenuItemActionPerformed);
        studentMenu.add(studentLoginMenuItem);

        logoutMenuItem.setText("Logout");
        logoutMenuItem.addActionListener(this::logoutMenuItemActionPerformed);
        studentMenu.add(logoutMenuItem);

        jMenuBar1.add(studentMenu);

        scheduleMenu.setText("Current Schedule");

        viewScheduleMenuItem.setText("View Schedule");
        viewScheduleMenuItem.addActionListener(this::viewScheduleMenuItemActionPerformed);
        scheduleMenu.add(viewScheduleMenuItem);

        setJMenuBar(jMenuBar1);

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jDesktopPane1)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jDesktopPane1)
                .addGap(0, 0, 0))
        );

        pack();
        
    }                    

    private void viewScheduleMenuItemActionPerformed(java.awt.event.ActionEvent evt) {                                                     
     
        schedulePanel sPanel = new schedulePanel(); 
        
      
        JInternalFrame scheduleFrame = new JInternalFrame("Reservation Schedule", true, true, true, true);
        scheduleFrame.setContentPane(sPanel); 
        scheduleFrame.pack(); 
        scheduleFrame.setVisible(true);
        
       
        jDesktopPane1.add(scheduleFrame);
    }     
    
    private void studentLoginMenuItemActionPerformed(java.awt.event.ActionEvent evt) {
    	studentInternalFrame studIFrame = new studentInternalFrame(); 
    	studIFrame.setVisible(true);
    	jDesktopPane1.add(studIFrame);
    	
    }

    private void logoutMenuItemActionPerformed(java.awt.event.ActionEvent evt) {
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
        

        
        new studentWindow().setVisible(true);
    }

                  
}
