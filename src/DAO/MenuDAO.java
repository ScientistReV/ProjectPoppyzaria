package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


import Model.Menu;
import Util.ConnectDatabase;
import Util.ConvertDate;

public class MenuDAO {
	
	public MenuDAO() {
	}

	//Serve para pegar os dados do banco de dados.
	public void registerMenu(Menu Menu) {
		try {
			String SQLInsertion = "insert into cardapios(cardapio_descricao, cardapio_tipo, cardapio_valor) values(?,?,?)";
			PreparedStatement ps = ConnectDatabase.getConnection().prepareStatement(SQLInsertion);
			ps.setString(1,Menu.getDescricaoCardapio());
			ps.setString(2,Menu.getTipoCardapio());
			ps.setDouble(3,Menu.getValorCardapio());

			ps.execute();
			ConnectDatabase.getConnection().commit();
			JOptionPane.showMessageDialog(null, "Registrado com sucesso", "Salvo", 0, new ImageIcon("src/Icon/ok.png"));
		} 		catch(SQLException ex){
			JOptionPane.showMessageDialog(null, "Erro ao cadastrar cardapio", "Erro", 0, new ImageIcon("src/Icon/close.png"));
		}
		
	}
	//Passa para o próximo cardapio
	public String nextMenu() {
		String SQLSelection = "select * from cardapios order by cardapio_id desc limit 1";
		try {
			PreparedStatement ps = ConnectDatabase.getConnection().prepareStatement(SQLSelection);
			ResultSet result= ps.executeQuery();
			if(result.next()) {
				return (Integer.parseInt(result.getString("cardapio_id")) + 1) + "";
			}else{
				return "1";
			}
			}catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao buscar registro", "Erro", 0, new ImageIcon("src/Icon/close.png"));
			return "0";
		 	}
	}
	//Buscar
	public void searchMenu(String search ,DefaultTableModel Model) {
		try {
			String SQLSelection = "select * from cardapios where cardapio_descricao like '%" + search + "%' ";
			PreparedStatement ps;
			ps = ConnectDatabase.getConnection().prepareStatement(SQLSelection);
			ResultSet result= ps.executeQuery();
			while(result.next()) {
				Model.addRow(new Object[] {result.getString("cardapio_id"),result.getString("cardapio_descricao"),result.getString("cardapio_tipo"),result.getDouble("cardapio_valor")});
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao buscar cardápio", "Erro", 0, new ImageIcon("src/Icon/close.png"));
		}
	}
	
	//Popula os campos da tabela
	public Menu populatedField(int Codigo) {
		Menu Menu = new Menu();
		try {
			String SQLSelection = "select * from cardapios where cardapio_id = ? ";
			PreparedStatement ps = ConnectDatabase.getConnection().prepareStatement(SQLSelection);
			ps.setInt(1, Codigo);
			ResultSet result= ps.executeQuery();
			if(result.next()) {
				Menu.setIdCardapio(result.getInt("cardapio_id"));
				Menu.setDescricaoCardapio(result.getString("cardapio_descricao"));
				Menu.setTipoCardapio(result.getString("cardapio_tipo"));
				Menu.setValorCardapio(result.getDouble("cardapio_valor"));

				
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao buscar cardápio", "Erro", 0, new ImageIcon("src/Icon/close.png"));
		}
		
		return Menu;
	}
	
	//Editar cardapio
	public void editMenu(Menu Menu) {
		try {
			String SQLInsertion = "update cardapios set cardapio_descricao = ?, cardapio_tipo = ?, cardapio_valor = ? where cardapio_id = ?";
			PreparedStatement ps = ConnectDatabase.getConnection().prepareStatement(SQLInsertion);
			ps.setString(1,Menu.getDescricaoCardapio());
			ps.setString(2,Menu.getTipoCardapio());
			ps.setDouble(3,Menu.getValorCardapio());
			ps.setInt(4,Menu.getIdCardapio());

			ps.execute();
			ConnectDatabase.getConnection().commit();
			JOptionPane.showMessageDialog(null, "Editado com sucesso", "Salvo", 0, new ImageIcon("src/Icon/ok.png"));
		} 		catch(SQLException ex){
			JOptionPane.showMessageDialog(null, "Erro ao editar registro", "Erro", 0, new ImageIcon("src/Icon/close.png"));
		}
	}
}