
package view;

import javax.swing.GroupLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.LayoutStyle;
import javax.swing.table.DefaultTableModel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import model.Reservation;


public class ReservationRequestsPanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	// Variables declaration                     
    private JScrollPane jScrollPane1;
    private JLabel requestLabel;
    private JTable requestTable;
    private List<Reservation> pendingReservations = new ArrayList<>();
   
    public ReservationRequestsPanel() {
        initComponents();
        loadPendingReservations();

        TableActionEvent event = new TableActionEvent(){
            @Override
            public void onView(int row) {
                if (row < 0 || row >= pendingReservations.size()) return;
                Reservation res = pendingReservations.get(row);
                String details = "Reservation ID: " + res.getReservationId()
                        + "\nUser ID: " + res.getUserId()
                        + "\nLab ID: " + res.getLabId()
                        + "\nSeat ID: " + res.getSeatId()
                        + "\nEquipment ID: " + res.getEquipmentId()
                        + "\nStart: " + res.getStartTime()
                        + "\nEnd: " + res.getEndTime()
                        + "\nStatus: " + res.getStatus();
                JOptionPane.showMessageDialog(ReservationRequestsPanel.this, details, "Reservation Details", JOptionPane.INFORMATION_MESSAGE);
            }

            @Override
            public void onDelete(int row) {
                if (row < 0 || row >= pendingReservations.size()) return;
                Reservation res = pendingReservations.get(row);

                int confirm = JOptionPane.showConfirmDialog(
                        ReservationRequestsPanel.this,
                        "Reject reservation #" + res.getReservationId() + "?",
                        "Confirm Reject",
                        JOptionPane.YES_NO_OPTION);
                if (confirm != JOptionPane.YES_OPTION) return;

                try {
                    ServerGateway gateway = ServerGateway.getInstance();
                    boolean success = gateway.updateReservationStatus(res.getReservationId(), "REJECTED");

                    if (success) {
                        if (requestTable.isEditing()) {
                            requestTable.getCellEditor().stopCellEditing();
                        }
                        loadPendingReservations();
                        JOptionPane.showMessageDialog(ReservationRequestsPanel.this,
                                "Reservation #" + res.getReservationId() + " has been REJECTED.",
                                "Rejected", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(ReservationRequestsPanel.this,
                                "Failed to reject reservation.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (IOException | ClassNotFoundException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(ReservationRequestsPanel.this,
                            "Server error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }

            @Override
            public void onApprove(int row) {
                if (row < 0 || row >= pendingReservations.size()) return;
                Reservation res = pendingReservations.get(row);

                int confirm = JOptionPane.showConfirmDialog(
                        ReservationRequestsPanel.this,
                        "Approve reservation #" + res.getReservationId() + "?",
                        "Confirm Approve",
                        JOptionPane.YES_NO_OPTION);
                if (confirm != JOptionPane.YES_OPTION) return;

                try {
                    ServerGateway gateway = ServerGateway.getInstance();
                    boolean success = gateway.updateReservationStatus(res.getReservationId(), "APPROVED");

                    if (success) {
                        if (requestTable.isEditing()) {
                            requestTable.getCellEditor().stopCellEditing();
                        }
                        loadPendingReservations();
                        JOptionPane.showMessageDialog(ReservationRequestsPanel.this,
                                "Reservation #" + res.getReservationId() + " has been APPROVED.",
                                "Approved", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(ReservationRequestsPanel.this,
                                "Failed to approve reservation.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (IOException | ClassNotFoundException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(ReservationRequestsPanel.this,
                            "Server error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        };
        requestTable.getColumnModel().getColumn(3).setCellRenderer(new TableActionRender());
        requestTable.getColumnModel().getColumn(3).setCellEditor(new TableActionCellEditor(event));
    }

    private void loadPendingReservations() {
        try {
            ServerGateway gateway = ServerGateway.getInstance();
            List<Reservation> allReservations = gateway.getAllReservations();

            pendingReservations.clear();
            for (Reservation res : allReservations) {
                if (res.getStatus() != null && res.getStatus().equalsIgnoreCase("PENDING")) {
                    pendingReservations.add(res);
                }
            }

            DefaultTableModel model = (DefaultTableModel) requestTable.getModel();
            model.setRowCount(0);

            for (Reservation res : pendingReservations) {
                model.addRow(new Object[]{
                    res.getReservationId(),
                    "User: " + res.getUserId() + " | Lab: " + res.getLabId()
                            + " | Seat: " + res.getSeatId() + " | Equip: " + res.getEquipmentId(),
                    res.getStartTime() + " → " + res.getEndTime(),
                    null  // Action buttons column
                });
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            System.err.println("Failed to load pending reservations: " + e.getMessage());
        }
    }

    private void initComponents() {

        requestLabel = new JLabel();
        jScrollPane1 = new JScrollPane();
        requestTable = new JTable();

        setBackground(new java.awt.Color(140, 82, 255));
        setMaximumSize(new java.awt.Dimension(754, 502));
        setMinimumSize(new java.awt.Dimension(754, 502));

        requestLabel.setFont(new java.awt.Font("Tahoma", 1, 24));
        requestLabel.setForeground(new java.awt.Color(255, 255, 255));
        requestLabel.setText("Pending Requests");

        requestTable.setModel(new DefaultTableModel(
            new Object [][] {},
            new String [] {
                "Reservation ID", "Details", "Time", "Action"
            }
        ) {
            private static final long serialVersionUID = 1L;
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        requestTable.setGridColor(new java.awt.Color(0, 0, 0));
        requestTable.setRowHeight(40);
        jScrollPane1.setViewportView(requestTable);

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(requestLabel)
                    .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 638, GroupLayout.PREFERRED_SIZE))
                .addContainerGap(81, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(requestLabel)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addContainerGap(7, Short.MAX_VALUE))
        );
    }
}
