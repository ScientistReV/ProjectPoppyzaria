package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


import Model.Deliveryman;
import Util.ConnectDatabase;
import Util.ConvertDate;

public class DeliverymanDAO {
	
	public DeliverymanDAO() {
	}

	//Serve para pegar os dados do banco de dados.
	public void registerDeliveryman(Deliveryman Deliveryman) {
		try {
			String SQLInsertion = "insert into entregadores(entregador_nome, entregador_status, entregador_data_cadastro) values(?,?,?)";
			PreparedStatement ps = ConnectDatabase.getConnection().prepareStatement(SQLInsertion);
			ps.setString(1,Deliveryman.getNomeEntregador());
			ps.setString(2, "Livre");
			ps.setString(3,ConvertDate.ConvertToSql(Deliveryman.getDataCadastro()));

			ps.execute();
			ConnectDatabase.getConnection().commit();
			JOptionPane.showMessageDialog(null, "Registrado com sucesso", "Salvo", 0, new ImageIcon("src/Icon/ok.png"));
		} 		catch(SQLException ex){
			//JOptionPane.showMessageDialog(null, ex, "Erro", 0, new ImageIcon("src/Icon/close.png"));
			JOptionPane.showMessageDialog(null, "Erro ao cadastrar entregador", "Erro", 0, new ImageIcon("src/Icon/close.png"));
		}
	}
	//Passa para o próximo entregador
	public String nextDeliveryman() {
		String SQLSelection = "select * from entregadores order by entregador_id desc limit 1";
		try {
			PreparedStatement ps = ConnectDatabase.getConnection().prepareStatement(SQLSelection);
			ResultSet result= ps.executeQuery();
			if(result.next()) {
				return (Integer.parseInt(result.getString("entregador_id")) + 1) + "";
			}else{
				return "1";
			}
			}catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao buscar registro", "Erro", 0, new ImageIcon("src/Icon/close.png"));
			return "0";
		 	}
	}
	//Buscar
	public void searchDeliveryman(String search ,DefaultTableModel Model) {
		try {
			String SQLSelection = "select * from entregadores where entregador_nome like '%" + search + "%' ";
			PreparedStatement ps;
			ps = ConnectDatabase.getConnection().prepareStatement(SQLSelection);
			ResultSet result= ps.executeQuery();
			while(result.next()) {
				Model.addRow(new Object[] {result.getString("entregador_id"),result.getString("entregador_nome"),ConvertDate.ConvertToJava(result.getString("entregador_data_cadastro"))});
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao buscar entregador", "Erro", 0, new ImageIcon("src/Icon/close.png"));
		}
	}
	
	//Popula os campos da tabela
	public Deliveryman populatedField(int Codigo) {
		Deliveryman Deliveryman = new Deliveryman();
		try {
			String SQLSelection = "select * from entregadores where entregador_id = ? ";
			PreparedStatement ps = ConnectDatabase.getConnection().prepareStatement(SQLSelection);
			ps.setInt(1, Codigo);
			ResultSet result= ps.executeQuery();
			if(result.next()) {
				Deliveryman.setIdEntregador(result.getInt("entregador_id"));
				Deliveryman.setNomeEntregador(result.getString("entregador_nome"));
				Deliveryman.setDataCadastro(ConvertDate.ConvertToJava(result.getString("entregador_data_cadastro")));
				
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao buscar entregador", "Erro", 0, new ImageIcon("src/Icon/close.png"));
		}
		
		return Deliveryman;
	}
	
	//Edita entregador
	public void editDeliveryman(Deliveryman Deliveryman) {
		try {
			String SQLInsertion = "update entregadores set entregador_nome = ?  where entregador_id = ?";
			PreparedStatement ps = ConnectDatabase.getConnection().prepareStatement(SQLInsertion);
			ps.setString(1,Deliveryman.getNomeEntregador());
			ps.setInt(2,Deliveryman.getIdEntregador());

			ps.execute();
			ConnectDatabase.getConnection().commit();
			JOptionPane.showMessageDialog(null, "Editado com sucesso", "Salvo", 0, new ImageIcon("src/Icon/ok.png"));
		} 		catch(SQLException ex){
			JOptionPane.showMessageDialog(null, "Erro ao editar registro", "Erro", 0, new ImageIcon("src/Icon/close.png"));
		}
	}
}