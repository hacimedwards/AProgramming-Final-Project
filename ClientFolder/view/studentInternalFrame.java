
package view;



import java.awt.BorderLayout;

import javax.swing.GroupLayout;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;


public class studentInternalFrame extends JInternalFrame {

	
	private static final long serialVersionUID = 1L;
	// Variables declaration                      
    private JPanel studentPanel;
   
   
    public studentInternalFrame() {
    	super("Student Portal",true,true,true,true); 
        initComponents();
        addLogin();
    }

                          
    private void addLogin() {
    	this.studentPanel.removeAll();
    	studentLoginPanel studLPanel = new studentLoginPanel();
    	studLPanel.setVisible(true);
    	this.studentPanel.setLayout(new BorderLayout());
    	this.studentPanel.add(studLPanel);
    	this.studentPanel.repaint();
    	this.studentPanel.revalidate();
    }
    private void initComponents() {

        studentPanel = new JPanel();
        

        studentPanel.setBackground(new java.awt.Color(255, 255, 255));
        studentPanel.setMaximumSize(new java.awt.Dimension(914, 523));
        studentPanel.setMinimumSize(new java.awt.Dimension(914, 523));
        studentPanel.setPreferredSize(new java.awt.Dimension(914, 523));
       

        GroupLayout studentPanelLayout = new GroupLayout(studentPanel);
        studentPanel.setLayout(studentPanelLayout);
        studentPanelLayout.setHorizontalGroup(
            studentPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 914, Short.MAX_VALUE)
        );
        studentPanelLayout.setVerticalGroup(
            studentPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 523, Short.MAX_VALUE)
        );

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(studentPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(studentPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
       
        pack();
    }                       


                      
}
