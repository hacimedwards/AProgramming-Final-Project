
package view;

import javax.swing.GroupLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.io.IOException;
import java.util.List;
import model.Reservation;


public class ReservationPanel extends JPanel {

	// Variables declaration                      
    private JScrollPane jScrollPane1;
    private JScrollPane jScrollPane2;
    private JTable jTable1;
    private JTable reservationTable;
        
    
	private static final long serialVersionUID = 1L;
	
    public ReservationPanel() {
        initComponents();
        loadReservations();
    }

    private void loadReservations() {
        try {
            ServerGateway gateway = ServerGateway.getInstance();
            List<Reservation> reservations = gateway.getAllReservations();
            
            populateTable(reservations);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            System.err.println("Failed to load reservations: " + e.getMessage());
        }
    }

    private void populateTable(List<Reservation> reservations) {
        DefaultTableModel model = (DefaultTableModel) reservationTable.getModel();
        
        // Clear existing rows
        model.setRowCount(0);
        
        // Add reservation data to table
        for (Reservation res : reservations) {
            Object[] row = {
                res.getReservationId(),
                res.getUserId(),
                res.getLabId(),
                res.getSeatId(),
                res.getEquipmentId()
            };
            model.addRow(row);
        }
    }

                            
    private void initComponents() {

        jScrollPane1 = new JScrollPane();
        jTable1 = new JTable();
        jScrollPane2 = new JScrollPane();
        reservationTable = new JTable();

        jTable1.setModel(new DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Reservation ID", "Student ID", "Lab ID", "Seat ID", "Equipments"
            }
        ) {
            
			private static final long serialVersionUID = 1L;
			Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class
            };

            @SuppressWarnings("unchecked")
			public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        setMaximumSize(new java.awt.Dimension(754, 502));
        setMinimumSize(new java.awt.Dimension(754, 502));

        reservationTable.setModel(new DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Reservation ID", "User ID", "Lab ID", "Seat ID", "Equipment ID"
            }
        ) {
            
			private static final long serialVersionUID = 1L;
			Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class
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

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(102, 102, 102)
                .addComponent(jScrollPane2, GroupLayout.PREFERRED_SIZE, 544, GroupLayout.PREFERRED_SIZE)
                .addContainerGap(108, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jScrollPane2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addContainerGap(44, Short.MAX_VALUE))
        );
    }                   


                  
}
