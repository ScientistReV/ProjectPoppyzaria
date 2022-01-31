package Controller;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import DAO.DeliverymanDAO;
import Model.Deliveryman;

public class DeliverymanController {
	
	DeliverymanDAO DeliverymanAux;
	
	public DeliverymanController() {
		
		DeliverymanAux = new DeliverymanDAO();
	}
	
	//Serve para validar os dados do entregador
	public boolean checkData(Deliveryman Deliveryman) {
		if(Deliveryman.getNomeEntregador().equals("")) {
			JOptionPane.showMessageDialog(null, "Preencha o nome", "Erro", 0, new ImageIcon("img/close.png"));
			return false;
		}
		
		DeliverymanAux.registerDeliveryman(Deliveryman);
		return true;
	}
	
	//Controla o próximo campo a ser buscado
	public String controll() {
		return DeliverymanAux.nextDeliveryman();
	}
	
	//Controla a Busca
	public void controlSearch(String search, DefaultTableModel Model) {
		DeliverymanAux.searchDeliveryman(search,Model);
	}
	
	//Controla a população dos campos
	public Deliveryman controlPopulatedField(int Codigo) {
		return DeliverymanAux.populatedField(Codigo);
	}
	
	//Serve para checar os campos editados
	public boolean checkEditData(Deliveryman Deliveryman) {
		if(Deliveryman.getNomeEntregador().equals("")) {
			JOptionPane.showMessageDialog(null, "Preencha o nome", "Erro", 0, new ImageIcon("img/close.png"));
			return false;
		}

		DeliverymanAux.editDeliveryman(Deliveryman);
		return true;
	}
	
	
}
