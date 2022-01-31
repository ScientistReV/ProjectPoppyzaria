package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import Model.Order;
import Util.ConnectDatabase;
import View.Orders;

public class OrderDAO {
	
	public OrderDAO() {
	}
	
	public void searchOrder(String search, List<String>ListaItens) {
		try {
			String SQLSearch = "select * from cardapios where cardapio_descricao like '%" + search + "%'";
			PreparedStatement ps = ConnectDatabase.getConnection().prepareStatement(SQLSearch);
			ResultSet result = ps.executeQuery();
			while(result.next()) {
				ListaItens.add(result.getString("cardapio_descricao"));
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao buscar cardápio", "Erro", 0, new ImageIcon("src/Icon/close.png"));
		}
	}
	
	public double priceOrder(String search) {
		try {
			String SQLSearch = "select * from cardapios where cardapio_descricao = ? ";
			PreparedStatement ps = ConnectDatabase.getConnection().prepareStatement(SQLSearch);
			ps.setString(1,search);
			ResultSet result = ps.executeQuery();
			if(result.next()) {
				return result.getDouble("cardapio_valor");
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro no valor do cardápio", "Erro", 0, new ImageIcon("src/Icon/close.png"));
		}
		return 0;
	}
	
	public int valueCodeOrder(String search) {
		try {
			String SQLSearch = "select * from cardapios where cardapio_descricao = ? ";
			PreparedStatement ps = ConnectDatabase.getConnection().prepareStatement(SQLSearch);
			ps.setString(1,search);
			ResultSet result = ps.executeQuery();
			if(result.next()) {
				return result.getInt("cardapio_id");
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro no código", "Erro", 0, new ImageIcon("src/Icon/close.png"));
		}
		return 0;
	}	
	
	public void registerOrder(String idClienteAux, String idFuncionarioAux, String Total, int tam_table, Order order){
	        Date Data = new Date();
	        SimpleDateFormat FormatData = new SimpleDateFormat("yyyy-MM-dd");
	        SimpleDateFormat FormatHora = new SimpleDateFormat("HH:mm:ss");
	        try {
	            String SQLInsert = "insert into pedidos(pedido_data, pedido_hora, pedido_valor, pedido_cliente_id, pedido_funcionario_id, pedido_entregador_id, pedido_status) values (?,?,?,?,?,?,?)";
	            PreparedStatement ps;
	            ps = ConnectDatabase.getConnection().prepareStatement(SQLInsert);
	            
	            ps.setString(1, FormatData.format(Data));
	            ps.setString(2, FormatHora.format(Data));
	            ps.setString(3, Total.replace(',', '.'));
	            ps.setString(4, idClienteAux);
	            ps.setString(5, idFuncionarioAux);
	            ps.setString(6, "1");
	            ps.setString(7, "Pedido Aberto");
	            
	            ps.execute();
	            registerItens(idClienteAux, idFuncionarioAux, codeOrder(), tam_table, order);
	            codeOrder();
	            ConnectDatabase.getConnection().commit();
	             JOptionPane.showMessageDialog(null, "Registro Salvo com Sucesso", "Salvo", 1, new ImageIcon("src/Icon/ok.png"));
	            
	        } catch (SQLException ex) {
				JOptionPane.showMessageDialog(null, "Erro ao registrar pedido", "Erro", 0, new ImageIcon("src/Icon/close.png"));
	        }
	          
	     }
	     
	 	public String codeOrder(){
	 		String Code = "0";
	         
	        try {
	            String SQLSelection = "select pedido_id from pedidos order by pedido_id desc limit 1";
	            PreparedStatement st = ConnectDatabase.getConnection().prepareStatement(SQLSelection);
	            ResultSet rs = st.executeQuery();
	            if(rs.next()){
	                Code = rs.getString("pedido_id");
	            }
	        } catch (SQLException ex) {
				JOptionPane.showMessageDialog(null, "Erro no código do pedido", "Erro", 0, new ImageIcon("src/Icon/close.png"));
	        }
	        return Code;
	     }
	     
	    public void registerItens(String idClienteAux, String idFuncionarioAux, String idPedidoAux, int tam_table, Order order){
	         for (int i = 0; i < tam_table; i++){
	             String SQLInsert = "insert into itens(item_entregador_codigo, item_funcionario_codigo, item_pedido_codigo, item_cliente_codigo, item_cardapio_codigo, item_quantidade) values (?,?,?,?,?,?)";
	             try {
	                 PreparedStatement ps = ConnectDatabase.getConnection().prepareStatement(SQLInsert);
	                 ps.setString(1, "1"); 
	                 ps.setString(2, idFuncionarioAux);
	                 ps.setString(3, idPedidoAux);
	                 ps.setString(4, idClienteAux);
	                 ps.setInt(5, order.getIdCardapioAux(i));
	                 ps.setInt(6, order.getQuantidade(i));
	                 
	                 ps.execute();
	             } catch (SQLException ex) {
	     			JOptionPane.showMessageDialog(null, "Erro ao registrar item", "Erro", 0, new ImageIcon("src/Icon/close.png"));
	             }
	         }
	     } 

}
