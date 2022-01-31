package Controller;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import DAO.EmployeeDAO;
import Model.Employee;

public class EmployeeController {
	
	EmployeeDAO EmployeeAux;
	
	public EmployeeController() {
		
		EmployeeAux = new EmployeeDAO();
	}
	
	//Serve para validar os dados do funcionário
	public boolean checkData(Employee Employee) {
		if(Employee.getNomeFuncionario().equals("")) {
			JOptionPane.showMessageDialog(null, "Preencha o nome", "Erro", 0, new ImageIcon("img/close.png"));
			return false;
		}
		
		if(Employee.getCargoFuncionario().equals("Selecione um cargo")) {
			JOptionPane.showMessageDialog(null, "Selecione um cargo", "Erro", 0, new ImageIcon("img/close.png"));
			return false;
		}
		
		EmployeeAux.registerEmployee(Employee);
		return true;
	}
	
	//Controla o próximo campo a ser buscado
	public String controll() {
		return EmployeeAux.nextEmployee();
	}
	
	//Controla a Busca
	public void controlSearch(String search, DefaultTableModel Model) {
		EmployeeAux.searchEmployee(search,Model);
	}
	
	//Controla a população dos campos
	public Employee controlPopulatedField(int Codigo) {
		return EmployeeAux.populatedField(Codigo);
	}
	
	//Serve para checar os campos editados
	public boolean checkEditData(Employee Employee) {
		if(Employee.getNomeFuncionario().equals("")) {
			JOptionPane.showMessageDialog(null, "Preencha o nome", "Erro", 0, new ImageIcon("img/close.png"));
			return false;
		}
		
		if(Employee.getCargoFuncionario().equals("Selecione um cargo")) {
			JOptionPane.showMessageDialog(null, "Selecione um cargo", "Erro", 0, new ImageIcon("img/close.png"));
			return false;
		}

		EmployeeAux.editEmployee(Employee);
		return true;
	}
	
	
}
