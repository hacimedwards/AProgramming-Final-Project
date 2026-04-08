
package view;

import javax.swing.GroupLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


public class schedulePanel extends JPanel {
	 // Variables declaration                    
    private JScrollPane jScrollPane2;
    private JScrollPane jScrollPane3;
    private JTable reservationTable;
    private JTable reservationTable1;
    

	private static final long serialVersionUID = 1L;
	public schedulePanel() {
        initComponents();
    }

  
                            
    private void initComponents() {

        jScrollPane2 = new JScrollPane();
        reservationTable = new JTable();
        jScrollPane3 = new JScrollPane();
        reservationTable1 = new JTable();

        reservationTable.setModel(new DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Reservation ID", "Student ID", "Lab ID", "Seat ID", "Equipments "
            }
        ) {
           
			private static final long serialVersionUID = 1L;
			Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        reservationTable.setGridColor(new java.awt.Color(0, 0, 0));
        jScrollPane2.setViewportView(reservationTable);

        setBackground(new java.awt.Color(255, 255, 255));
        setMaximumSize(new java.awt.Dimension(914, 523));
        setMinimumSize(new java.awt.Dimension(914, 523));
        setPreferredSize(new java.awt.Dimension(914, 523));
        

        reservationTable1.setModel(new DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Reservation ID", "Student ID", "Lab ID", "Seat ID", "Equipments "
            }
        ) {
            
			private static final long serialVersionUID = 1L;
			Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        reservationTable1.setGridColor(new java.awt.Color(0, 0, 0));
        jScrollPane3.setViewportView(reservationTable1);

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(177, 177, 177)
                .addComponent(jScrollPane3, GroupLayout.PREFERRED_SIZE, 544, GroupLayout.PREFERRED_SIZE)
                .addContainerGap(193, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(jScrollPane3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addContainerGap(51, Short.MAX_VALUE))
        );
    }                       


                    
}
