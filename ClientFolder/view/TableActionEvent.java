
package view;


public interface TableActionEvent {
   
    public void onDelete(int row); 
    public void onView(int row);
    public void onApprove(int row);
}
