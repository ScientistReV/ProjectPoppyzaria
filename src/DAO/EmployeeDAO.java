package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


import Model.Employee;
import Util.ConnectDatabase;
import Util.ConvertDate;

public class EmployeeDAO {
	public EmployeeDAO() {
	}

	//Serve para pegar os dados do banco de dados.
	public void registerEmployee(Employee Employee) {
		try {
			String SQLInsertion = "insert into funcionarios(funcionario_nome, funcionario_cargo, funcionario_data_cadastro) values(?,?,?)";
			PreparedStatement ps = ConnectDatabase.getConnection().prepareStatement(SQLInsertion);
			ps.setString(1,Employee.getNomeFuncionario());
			ps.setString(2,Employee.getCargoFuncionario());
			ps.setString(3,ConvertDate.ConvertToSql(Employee.getDataCadastro()));

			ps.execute();
			ConnectDatabase.getConnection().commit();
			JOptionPane.showMessageDialog(null, "Registrado com sucesso", "Salvo", 0, new ImageIcon("src/Icon/ok.png"));
		} 		catch(SQLException ex){
			JOptionPane.showMessageDialog(null, "Erro ao cadastrar funcionário", "Erro", 0, new ImageIcon("src/Icon/close.png"));
		}
	}
	//Passa para o próximo funcionário
	public String nextEmployee() {
		String SQLSelection = "select * from funcionarios order by funcionario_id desc limit 1";
		try {
			PreparedStatement ps = ConnectDatabase.getConnection().prepareStatement(SQLSelection);
			ResultSet result= ps.executeQuery();
			if(result.next()) {
				return (Integer.parseInt(result.getString("funcionario_id")) + 1) + "";
			}else{
				return "1";
			}
			}catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao buscar registro", "Erro", 0, new ImageIcon("src/Icon/close.png"));
			return "0";
		 	}
	}
	//Buscar
	public void searchEmployee(String search ,DefaultTableModel Model) {
		try {
			String SQLSelection = "select * from funcionarios where funcionario_nome like '%" + search + "%' ";
			PreparedStatement ps;
			ps = ConnectDatabase.getConnection().prepareStatement(SQLSelection);
			ResultSet result= ps.executeQuery();
			while(result.next()) {
				Model.addRow(new Object[] {result.getString("funcionario_id"),result.getString("funcionario_nome"),result.getString("funcionario_cargo"),ConvertDate.ConvertToJava(result.getString("funcionario_data_cadastro"))});
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao buscar funcionário", "Erro", 0, new ImageIcon("src/Icon/close.png"));
		}
	}
	
	//Popula os campos da tabela
	public Employee populatedField(int Codigo) {
		Employee Employee = new Employee();
		try {
			String SQLSelection = "select * from funcionarios where funcionario_id = ? ";
			PreparedStatement ps = ConnectDatabase.getConnection().prepareStatement(SQLSelection);
			ps.setInt(1, Codigo);
			ResultSet result= ps.executeQuery();
			if(result.next()) {
				Employee.setIdFuncionario(result.getInt("funcionario_id"));
				Employee.setNomeFuncionario(result.getString("funcionario_nome"));
				Employee.setCargoFuncionario(result.getString("funcionario_cargo"));
				Employee.setDataCadastro(ConvertDate.ConvertToJava(result.getString("funcionario_data_cadastro")));
				
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao buscar funcionário", "Erro", 0, new ImageIcon("src/Icon/close.png"));
		}
		
		return Employee;
	}
	
	//Edita funcionário
	public void editEmployee(Employee Employee) {
		try {
			String SQLInsertion = "update funcionarios set funcionario_nome = ?, funcionario_cargo = ? where funcionario_id = ?";
			PreparedStatement ps = ConnectDatabase.getConnection().prepareStatement(SQLInsertion);
			ps.setString(1,Employee.getNomeFuncionario());
			ps.setString(2,Employee.getCargoFuncionario());
			ps.setInt(3,Employee.getIdFuncionario());

			ps.execute();
			ConnectDatabase.getConnection().commit();
			JOptionPane.showMessageDialog(null, "Editado com sucesso", "Salvo", 0, new ImageIcon("src/Icon/ok.png"));
		} 		catch(SQLException ex){
			JOptionPane.showMessageDialog(null, "Erro ao editar registro", "Erro", 0, new ImageIcon("src/Icon/close.png"));
		}
	}
}