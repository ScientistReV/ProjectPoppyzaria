package Controller;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import DAO.MenuDAO;
import Model.Menu;

public class MenuController {
	
	MenuDAO MenuAux;
	
	public MenuController() {
		
		MenuAux = new MenuDAO();
	}
	
	//Serve para validar os dados do funcionário
	public boolean checkData(Menu Menu) {
		if(Menu.getDescricaoCardapio().equals("")) {
			JOptionPane.showMessageDialog(null, "Preencha a descrição", "Erro", 0, new ImageIcon("img/close.png"));
			return false;
		}
		
		if(Menu.getTipoCardapio().equals("Selecione um tipo de cardápio")) {
			JOptionPane.showMessageDialog(null, "Selecione um tipo de cardápio", "Erro", 0, new ImageIcon("img/close.png"));
			return false;
		}
		
		if(Menu.getValorCardapio() ==  0) {
			JOptionPane.showMessageDialog(null, "Preencha com um valor", "Erro", 0, new ImageIcon("img/close.png"));
			return false;
		}
		
		MenuAux.registerMenu(Menu);
		return true;
	}
	
	//Controla o próximo campo a ser buscado
	public String controll() {
		return MenuAux.nextMenu();
	}
	
	//Controla a Busca
	public void controlSearch(String search, DefaultTableModel Model) {
		MenuAux.searchMenu(search,Model);
	}
	
	//Controla a população dos campos
	public Menu controlPopulatedField(int Codigo) {
		return MenuAux.populatedField(Codigo);
	}
	
	//Serve para checar os campos editados
	public boolean checkEditData(Menu Menu) {
		if(Menu.getDescricaoCardapio().equals("")) {
			JOptionPane.showMessageDialog(null, "Preencha a descrição", "Erro", 0, new ImageIcon("img/close.png"));
			return false;
		}
		
		if(Menu.getTipoCardapio().equals("Selecione um item")) {
			JOptionPane.showMessageDialog(null, "Selecione um item", "Erro", 0, new ImageIcon("img/close.png"));
			return false;
		}
		
		if(Menu.getValorCardapio() ==  0) {
			JOptionPane.showMessageDialog(null, "Preencha com um valor", "Erro", 0, new ImageIcon("img/close.png"));
			return false;
		}
		

		MenuAux.editMenu(Menu);
		return true;
	}
	
	
}
