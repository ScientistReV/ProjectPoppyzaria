
package DAO;

import Util.ConnectDatabase;
import Util.ConvertDate;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


public class ScreenOrderDAO {
    
    public ScreenOrderDAO(){
    
    }
    
    public void showOrder(DefaultTableModel Model){
        try {
            String SQLSearch = "select * from pedidos order by pedido_id desc limit 15";
            PreparedStatement st = ConnectDatabase.getConnection().prepareStatement(SQLSearch);
            
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                Model.addRow(new Object[] {rs.getString("pedido_id"), ConvertDate.ConvertToJava(rs.getString("pedido_data")), rs.getString("pedido_hora"), rs.getString("pedido_status") } );
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Pesquisar", "Erro", 0,  new ImageIcon("src/Icon/close.png"));
        }
    }
}  