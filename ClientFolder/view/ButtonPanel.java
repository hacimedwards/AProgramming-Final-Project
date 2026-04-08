
package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;


public class ButtonPanel extends JPanel {
	
	// Variables declaration                    
    private ActionButton cancelBtn;
    private ActionButton viewBtn;
    private ActionButton approveBtn;
    
    
    public ButtonPanel() {
        initComponents();
    }
    
    public void initEvent(TableActionEvent event, int row){
        viewBtn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                event.onView(row);
            }
            
         
        });
        
        cancelBtn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
               event.onDelete(row);
            }
            
        });

        approveBtn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
               event.onApprove(row);
            }
            
        });
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        cancelBtn = new ActionButton();
        viewBtn = new ActionButton();
        approveBtn = new ActionButton();

        cancelBtn.setIcon(new ImageIcon(getClass().getResource("/images/cancel_1_4.png"))); // NOI18N
        cancelBtn.addActionListener(this::cancelBtnActionPerformed);

        viewBtn.setIcon(new ImageIcon(getClass().getResource("/images/view_1_4.png"))); // NOI18N
        viewBtn.addActionListener(this::viewBtnActionPerformed);

        approveBtn.setIcon(new ImageIcon(getClass().getResource("/images/approve.png")));
        approveBtn.addActionListener(this::approveBtnActionPerformed);

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(viewBtn, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(approveBtn, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cancelBtn, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(viewBtn, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
                    .addComponent(approveBtn, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
                    .addComponent(cancelBtn, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
    }                      

    private void cancelBtnActionPerformed(java.awt.event.ActionEvent evt) {                                          
        // TODO add your handling code here:
    }                                         

    private void viewBtnActionPerformed(java.awt.event.ActionEvent evt) {                                        
        // TODO add your handling code here:
    }                                       

    private void approveBtnActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }                                       


                      
}
