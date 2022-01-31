package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import Model.Client;
import Util.ConnectDatabase;
import Util.ConvertDate;

public class ClientDAO {
	public ClientDAO() {
	}

	//Serve para pegar os dados do banco de dados.
	public void registerClient(Client Client) {
		try {
			String SQLInsertion = "insert into clientes(cliente_nome, cliente_endereco, cliente_complemento_endereco, cliente_bairro, cliente_telefone, cliente_data_cadastro) values(?,?,?,?,?,?)";
			PreparedStatement ps = ConnectDatabase.getConnection().prepareStatement(SQLInsertion);
			ps.setString(1,Client.getNomeCliente());
			ps.setString(2,Client.getEnderecoCliente());
			ps.setString(3,Client.getComplementoEnderecoCliente());
			ps.setString(4,Client.getBairroCliente());
			ps.setString(5,Client.getTelefoneCliente());
			ps.setString(6,ConvertDate.ConvertToSql(Client.getDataCadastro()));

			ps.execute();
			ConnectDatabase.getConnection().commit();
			JOptionPane.showMessageDialog(null, "Registrado com sucesso", "Salvo", 0, new ImageIcon("src/Icon/ok.png"));
		} 		catch(SQLException ex){
			JOptionPane.showMessageDialog(null, "Erro ao cadastrar cliente", "Erro", 0, new ImageIcon("src/Icon/close.png"));
		}
	}
	//Passa para o próximo cliente
	public String nextClient() {
		String SQLSelection = "select * from clientes order by cliente_id desc limit 1";
		try {
			PreparedStatement ps = ConnectDatabase.getConnection().prepareStatement(SQLSelection);
			ResultSet result= ps.executeQuery();
			if(result.next()) {
				return (Integer.parseInt(result.getString("cliente_id")) + 1) + "";
			}else{
				return "1";
			}
			}catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao buscar registro", "Erro", 0, new ImageIcon("src/Icon/close.png"));
			return "0";
		 	}
	}
	//Buscar
	public void searchClient(String search ,DefaultTableModel Model) {
		try {
			String SQLSelection = "select * from clientes where cliente_nome like '%" + search + "%' ";
			PreparedStatement ps;
			ps = ConnectDatabase.getConnection().prepareStatement(SQLSelection);
			ResultSet result= ps.executeQuery();
			while(result.next()) {
				Model.addRow(new Object[] {result.getString("cliente_id"),result.getString("cliente_nome"), result.getString("cliente_endereco"), result.getString("cliente_complemento_endereco"),result.getString("cliente_bairro"),result.getString("cliente_telefone")});
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao buscar cliente", "Erro", 0, new ImageIcon("src/Icon/close.png"));
		}
	}
	
	//Serve para checar os campos editados
	public Client populatedField(int Codigo) {
		Client Cliente = new Client();
		try {
			String SQLSelection = "select * from clientes where cliente_id = ? ";
			PreparedStatement ps = ConnectDatabase.getConnection().prepareStatement(SQLSelection);
			ps.setInt(1, Codigo);
			ResultSet result= ps.executeQuery();
			if(result.next()) {
				Cliente.setIdCliente(result.getInt("cliente_id"));
				Cliente.setNomeCliente(result.getString("cliente_nome"));
				Cliente.setEnderecoCliente(result.getString("cliente_endereco"));
				Cliente.setComplementoEnderecoCliente(result.getString("cliente_complemento_endereco"));
				Cliente.setBairroCliente(result.getString("cliente_bairro"));
				Cliente.setTelefoneCliente(result.getString("cliente_telefone"));
				Cliente.setDataCadastro(ConvertDate.ConvertToJava(result.getString("cliente_data_cadastro")));
				
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao buscar cliente", "Erro", 0, new ImageIcon("src/Icon/close.png"));
		}
		
		return Cliente;
	}
	
	public void searchClient(String search, List<String> Lista) {
		try {
			String SQLSelection = "select * from clientes where cliente_nome like '%" + search + "%' ";
			PreparedStatement ps;
			ps = ConnectDatabase.getConnection().prepareStatement(SQLSelection);
			ResultSet result= ps.executeQuery();
			while(result.next()) {
				Lista.add(result.getString("cliente_id") + " - " + result.getString("cliente_nome") + " ---- " + result.getString("cliente_telefone"));
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao buscar cliente", "Erro", 0, new ImageIcon("src/Icon/close.png"));
		}
	}
	
	
	//Edita cliente
	public void editClient(Client Client) {
		try {
			String SQLInsertion = "update clientes set cliente_nome = ?, cliente_endereco = ?, cliente_complemento_endereco = ?, cliente_bairro = ?, cliente_telefone = ? where cliente_id = ?";
			PreparedStatement ps = ConnectDatabase.getConnection().prepareStatement(SQLInsertion);
			ps.setString(1,Client.getNomeCliente());
			ps.setString(2,Client.getEnderecoCliente());
			ps.setString(3,Client.getComplementoEnderecoCliente());
			ps.setString(4,Client.getBairroCliente());
			ps.setString(5,Client.getTelefoneCliente());
			ps.setInt(6,Client.getIdCliente());

			ps.execute();
			ConnectDatabase.getConnection().commit();
			JOptionPane.showMessageDialog(null, "Editado com sucesso", "Salvo", 0, new ImageIcon("src/Icon/ok.png"));
		} 		catch(SQLException ex){
			JOptionPane.showMessageDialog(null, "Erro ao editar registro", "Erro", 0, new ImageIcon("src/Icon/close.png"));
		}
	}
}