package Controller;

import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import DAO.OrderDAO;
import Model.Order;
import View.Orders;

public class OrderController {
	
	OrderDAO PedidoAux;

	public OrderController() {
		PedidoAux = new OrderDAO();
	}
	
	public void controlSearchOrder(String search, List<String> ListaItens) {
		PedidoAux.searchOrder(search,ListaItens);
	}
	
	public double controlValueOrder(String search) {
		return PedidoAux.priceOrder(search);
	}
	
	public int controlCodeOrder(String search) {
		return PedidoAux.valueCodeOrder(search);
	}
	
	 public boolean checkOrder(String value, String quantity, String code, String item){
         try{
           int number = Integer.parseInt(quantity);
           if(number == 0){
                JOptionPane.showMessageDialog(null, "A quantidade não pode ser 0", "Erro", 0, new ImageIcon("img/close.png"));
                Orders.text_quantity.setText("1");
                Orders.text_quantity.grabFocus();
           }
       }catch(NumberFormatException Ex){
            JOptionPane.showMessageDialog(null, "Insira um número inteiro", "Erro", 0, new ImageIcon("img/close.png"));
            Orders.text_quantity.setText("1");
            Orders.text_quantity.grabFocus();
       }
        
       if(quantity.equals("")){
           return false;
       }
        
       if(value.equals("")){
           JOptionPane.showMessageDialog(null, "Preencha o campo de Valor", "Erro", 0, new ImageIcon("img/close.png"));
           return false;
       }
        
        if(code.equals("")){
           JOptionPane.showMessageDialog(null, "Preencha o campo de Código", "Erro", 0, new ImageIcon("img/close.png"));
           return false;
       }
        
        if(item.equals("")){
           JOptionPane.showMessageDialog(null, "Preencha o campo de Item", "Erro", 0, new ImageIcon("img/close.png"));
           return false;
       }
        
        return true;
    }
    
   public void controlOrder(String idClienteAux, String idFuncionarioAux, String Total, int tam_table, Order order){
    	PedidoAux.registerOrder(idClienteAux, idFuncionarioAux, Total, tam_table, order);
    }

}