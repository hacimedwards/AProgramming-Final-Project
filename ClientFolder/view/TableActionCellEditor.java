
package view;

import java.awt.Component;

import javax.swing.DefaultCellEditor;
import javax.swing.JTable;
import javax.swing.JCheckBox;



public class TableActionCellEditor extends DefaultCellEditor{

 
	private static final long serialVersionUID = 1L;
	private TableActionEvent event; 
    public TableActionCellEditor(TableActionEvent event){
        super(new JCheckBox()); 
        this.event = event; 
    }
    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        ButtonPanel action = new ButtonPanel(); 
        action.initEvent(event, row);
        action.setBackground(table.getSelectionBackground());
        return action; 
    }

   
    
}
