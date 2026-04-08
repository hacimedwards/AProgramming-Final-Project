
package view;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import model.Reservation;


public class StudentRequestPanel extends JPanel {

    private static final long serialVersionUID = 1L;
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    // View Reservations tab
    private JPanel viewPanel;
    private JScrollPane tableScrollPane;
    private JTable reservationTable;
    private JButton refreshBtn;

    // Make Reservation tab
    private JPanel formPanel;
    private JLabel formTitle;
    private JLabel labLabel;
    private JTextField labIdField;
    private JButton loadEquipBtn;
    private JLabel seatLabel;
    private JTextField seatIdField;
    private JLabel equipTypeLabel;
    private JComboBox<String> equipTypeCBox;
    private JLabel equipIdLabel;
    private JComboBox<String> equipIdCBox;
    private JLabel startLabel;
    private JTextField startTimeField;
    private JLabel endLabel;
    private JTextField endTimeField;
    private JButton requestBtn;

    private JTabbedPane tabbedPane;

    private Map<String, ArrayList<String>> availableEquipmentByType;
    private int loadedLabId = -1;
    private int userId;

    public StudentRequestPanel(int userId) {
        this.userId = userId;
        initComponents();
        loadReservations();
    }

    // ─── View Reservations ───────────────────────────────────────

    private void loadReservations() {
        try {
            ServerGateway gateway = ServerGateway.getInstance();
            List<Reservation> reservations = gateway.getAllReservations();

            DefaultTableModel model = (DefaultTableModel) reservationTable.getModel();
            model.setRowCount(0);

            for (Reservation res : reservations) {
                model.addRow(new Object[]{
                    res.getReservationId(),
                    res.getLabId(),
                    res.getSeatId(),
                    res.getEquipmentId(),
                    res.getStartTime() != null ? res.getStartTime().format(DATE_TIME_FORMATTER) : "",
                    res.getEndTime() != null ? res.getEndTime().format(DATE_TIME_FORMATTER) : "",
                    res.getStatus()
                });
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            System.err.println("Failed to load reservations: " + e.getMessage());
        }
    }

    // ─── Equipment Loading ───────────────────────────────────────

    private void loadAvailableEquipment(int labId) {
        try {
            ServerGateway gateway = ServerGateway.getInstance();
            availableEquipmentByType = gateway.getAvailableEquipment(labId);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Failed to load equipment: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        equipTypeCBox.removeAllItems();
        equipIdCBox.removeAllItems();
        loadedLabId = labId;

        if (availableEquipmentByType == null || availableEquipmentByType.isEmpty()) {
            requestBtn.setEnabled(false);
            equipTypeCBox.setEnabled(false);
            equipIdCBox.setEnabled(false);
            JOptionPane.showMessageDialog(this, "No available equipment found for this lab.", "Info", JOptionPane.WARNING_MESSAGE);
            return;
        }

        requestBtn.setEnabled(true);
        equipTypeCBox.setEnabled(true);
        for (String type : availableEquipmentByType.keySet()) {
            equipTypeCBox.addItem(type);
        }
        updateEquipmentIdsForSelectedType();
    }

    private void updateEquipmentIdsForSelectedType() {
        equipIdCBox.removeAllItems();
        String selectedType = (String) equipTypeCBox.getSelectedItem();
        if (selectedType == null || availableEquipmentByType == null) {
            return;
        }

        ArrayList<String> ids = availableEquipmentByType.get(selectedType);
        if (ids == null || ids.isEmpty()) {
            equipIdCBox.setEnabled(false);
            return;
        }

        equipIdCBox.setEnabled(true);
        for (String id : ids) {
            equipIdCBox.addItem(id);
        }
    }

    // ─── Create Reservation ──────────────────────────────────────

    private void createReservation() {
        try {
            int labId = parsePositiveInt(labIdField.getText(), "Lab ID");
            int seatId = parsePositiveInt(seatIdField.getText(), "Seat ID");

            // Ensure equipment is loaded for this lab
            if (loadedLabId != labId || availableEquipmentByType == null) {
                loadAvailableEquipment(labId);
            }
            if (availableEquipmentByType == null || availableEquipmentByType.isEmpty()) {
                JOptionPane.showMessageDialog(this, "No available equipment for the selected lab.", "Validation Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String equipmentId = (String) equipIdCBox.getSelectedItem();
            if (equipmentId == null || equipmentId.trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please select an equipment ID.", "Validation Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            LocalDateTime startTime = parseDateTime(startTimeField.getText(), "Start Time");
            LocalDateTime endTime = parseDateTime(endTimeField.getText(), "End Time");
            if (!endTime.isAfter(startTime)) {
                JOptionPane.showMessageDialog(this, "End time must be after start time.", "Validation Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Reservation reservation = new Reservation(userId, labId, seatId, equipmentId, startTime, endTime, "PENDING", null, null);

            ServerGateway gateway = ServerGateway.getInstance();
            gateway.createReservation(reservation);

            JOptionPane.showMessageDialog(this, "Reservation request submitted!", "Success", JOptionPane.INFORMATION_MESSAGE);

            // Clear fields
            labIdField.setText("");
            seatIdField.setText("");
            startTimeField.setText("");
            endTimeField.setText("");
            equipTypeCBox.removeAllItems();
            equipIdCBox.removeAllItems();
            equipTypeCBox.setEnabled(false);
            equipIdCBox.setEnabled(false);
            loadedLabId = -1;

            // Refresh the view tab
            loadReservations();

        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Validation Error", JOptionPane.ERROR_MESSAGE);
        } catch (IOException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Server error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private int parsePositiveInt(String value, String fieldName) {
        try {
            int parsed = Integer.parseInt(value.trim());
            if (parsed <= 0) throw new IllegalArgumentException(fieldName + " must be a positive integer.");
            return parsed;
        } catch (NumberFormatException ex) {
            throw new IllegalArgumentException(fieldName + " must be a positive integer.");
        }
    }

    private LocalDateTime parseDateTime(String value, String fieldName) {
        try {
            return LocalDateTime.parse(value.trim(), DATE_TIME_FORMATTER);
        } catch (DateTimeParseException ex) {
            throw new IllegalArgumentException(fieldName + " must be in format yyyy-MM-dd HH:mm.");
        }
    }

    // ─── UI Init ─────────────────────────────────────────────────

    private void initComponents() {

        tabbedPane = new JTabbedPane();

        // ── View Reservations tab ──
        viewPanel = new JPanel();
        tableScrollPane = new JScrollPane();
        reservationTable = new JTable();
        refreshBtn = new JButton();

        viewPanel.setBackground(new java.awt.Color(153, 255, 204));

        reservationTable.setModel(new DefaultTableModel(
            new Object[][]{},
            new String[]{"Reservation ID", "Lab ID", "Seat ID", "Equipment", "Start Time", "End Time", "Status"}
        ) {
            private static final long serialVersionUID = 1L;
            boolean[] canEdit = new boolean[]{false, false, false, false, false, false, false};

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        reservationTable.setGridColor(new java.awt.Color(51, 51, 51));
        tableScrollPane.setViewportView(reservationTable);

        refreshBtn.setBackground(new java.awt.Color(140, 82, 255));
        refreshBtn.setForeground(new java.awt.Color(255, 255, 255));
        refreshBtn.setText("Refresh");
        refreshBtn.addActionListener(e -> loadReservations());

        GroupLayout viewLayout = new GroupLayout(viewPanel);
        viewPanel.setLayout(viewLayout);
        viewLayout.setHorizontalGroup(
            viewLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(viewLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(viewLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(tableScrollPane, GroupLayout.PREFERRED_SIZE, 850, GroupLayout.PREFERRED_SIZE)
                    .addComponent(refreshBtn, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE))
                .addContainerGap(34, Short.MAX_VALUE))
        );
        viewLayout.setVerticalGroup(
            viewLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(viewLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(refreshBtn, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(tableScrollPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        tabbedPane.addTab("View Reservations Made", viewPanel);

        // ── Make Reservation tab ──
        formPanel = new JPanel();
        formPanel.setBackground(new java.awt.Color(255, 255, 255));

        formTitle = new JLabel();
        formTitle.setFont(new java.awt.Font("Segoe UI", 1, 24));
        formTitle.setForeground(new java.awt.Color(140, 82, 255));
        formTitle.setHorizontalAlignment(SwingConstants.CENTER);
        formTitle.setText("Reservation Form");

        labLabel = new JLabel("Lab ID");
        labLabel.setFont(new java.awt.Font("Segoe UI", 0, 16));
        labLabel.setForeground(new java.awt.Color(140, 82, 255));
        labIdField = new JTextField();

        loadEquipBtn = new JButton("Load Equipment");
        loadEquipBtn.setBackground(new java.awt.Color(140, 82, 255));
        loadEquipBtn.setForeground(new java.awt.Color(255, 255, 255));
        loadEquipBtn.addActionListener(e -> {
            try {
                int labId = parsePositiveInt(labIdField.getText(), "Lab ID");
                loadAvailableEquipment(labId);
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Validation Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        seatLabel = new JLabel("Seat ID");
        seatLabel.setFont(new java.awt.Font("Segoe UI", 0, 16));
        seatLabel.setForeground(new java.awt.Color(140, 82, 255));
        seatIdField = new JTextField();

        equipTypeLabel = new JLabel("Equipment Type");
        equipTypeLabel.setFont(new java.awt.Font("Segoe UI", 0, 16));
        equipTypeLabel.setForeground(new java.awt.Color(140, 82, 255));
        equipTypeCBox = new JComboBox<>();
        equipTypeCBox.setEnabled(false);
        equipTypeCBox.addActionListener(e -> updateEquipmentIdsForSelectedType());

        equipIdLabel = new JLabel("Equipment ID");
        equipIdLabel.setFont(new java.awt.Font("Segoe UI", 0, 16));
        equipIdLabel.setForeground(new java.awt.Color(140, 82, 255));
        equipIdCBox = new JComboBox<>();
        equipIdCBox.setEnabled(false);

        startLabel = new JLabel("Start (yyyy-MM-dd HH:mm)");
        startLabel.setFont(new java.awt.Font("Segoe UI", 0, 16));
        startLabel.setForeground(new java.awt.Color(140, 82, 255));
        startTimeField = new JTextField();

        endLabel = new JLabel("End (yyyy-MM-dd HH:mm)");
        endLabel.setFont(new java.awt.Font("Segoe UI", 0, 16));
        endLabel.setForeground(new java.awt.Color(140, 82, 255));
        endTimeField = new JTextField();

        requestBtn = new JButton("Send Reservation Request");
        requestBtn.setBackground(new java.awt.Color(140, 82, 255));
        requestBtn.setForeground(new java.awt.Color(255, 255, 255));
        requestBtn.addActionListener(e -> createReservation());

        GroupLayout formLayout = new GroupLayout(formPanel);
        formPanel.setLayout(formLayout);
        formLayout.setHorizontalGroup(
            formLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(formLayout.createSequentialGroup()
                .addGap(63, 63, 63)
                .addGroup(formLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                    .addComponent(labLabel)
                    .addComponent(labIdField, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE)
                    .addComponent(loadEquipBtn)
                    .addComponent(seatLabel)
                    .addComponent(seatIdField, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE)
                    .addComponent(startLabel)
                    .addComponent(startTimeField, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 100, Short.MAX_VALUE)
                .addGroup(formLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                    .addComponent(equipTypeLabel)
                    .addComponent(equipTypeCBox, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE)
                    .addComponent(equipIdLabel)
                    .addComponent(equipIdCBox, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE)
                    .addComponent(endLabel)
                    .addComponent(endTimeField, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE)
                    .addComponent(requestBtn))
                .addGap(63, 63, 63))
            .addGroup(GroupLayout.Alignment.TRAILING, formLayout.createSequentialGroup()
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(formTitle)
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        formLayout.setVerticalGroup(
            formLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(formLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(formTitle)
                .addGap(25, 25, 25)
                .addGroup(formLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(labLabel)
                    .addComponent(equipTypeLabel))
                .addGap(10, 10, 10)
                .addGroup(formLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(labIdField, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
                    .addComponent(equipTypeCBox, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(formLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(loadEquipBtn)
                    .addComponent(equipIdLabel))
                .addGap(10, 10, 10)
                .addGroup(formLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(seatLabel)
                    .addComponent(equipIdCBox, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addComponent(seatIdField, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addGroup(formLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(startLabel)
                    .addComponent(endLabel))
                .addGap(10, 10, 10)
                .addGroup(formLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(startTimeField, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
                    .addComponent(endTimeField, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addComponent(requestBtn, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
                .addContainerGap(30, Short.MAX_VALUE))
        );

        tabbedPane.addTab("Make Reservation", formPanel);

        // ── Main layout ──
        setBackground(new java.awt.Color(255, 255, 255));
        setMaximumSize(new java.awt.Dimension(914, 523));
        setMinimumSize(new java.awt.Dimension(914, 523));
        setPreferredSize(new java.awt.Dimension(914, 523));

        tabbedPane.setBackground(new java.awt.Color(140, 82, 255));

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tabbedPane)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(tabbedPane)
                .addContainerGap())
        );
    }
}
