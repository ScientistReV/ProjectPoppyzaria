
package Controller;

import DAO.ScreenOrderDAO;
import javax.swing.table.DefaultTableModel;


public class ScreenOrderController {
    
	ScreenOrderDAO screenAux;
    
    public ScreenOrderController(){
    	screenAux = new ScreenOrderDAO();
    }
    
     public void controlSearchScreen(DefaultTableModel Model){
    	 screenAux.showOrder(Model);
    }
}
