package Controller;

import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import DAO.ClientDAO;
import Model.Client;

public class ClientController {
	
	ClientDAO ClientAux;
	
	public ClientController() {
		
		ClientAux = new ClientDAO();
	}
	
	//Serve para validar os dados do cliente
	public boolean checkData(Client Client) {
		if(Client.getNomeCliente().equals("")) {
			JOptionPane.showMessageDialog(null, "Preencha o nome", "Erro", 0, new ImageIcon("img/close.png"));
			return false;
		}
		
		if(Client.getEnderecoCliente().equals("")) {
			JOptionPane.showMessageDialog(null, "Preencha o endereço", "Erro", 0, new ImageIcon("img/close.png"));
			return false;
		}
		
		if(Client.getComplementoEnderecoCliente().equals("")) {
			JOptionPane.showMessageDialog(null, "Preencha o complemento do endereço", "Erro", 0, new ImageIcon("img/close.png"));
			return false;
		}
		
		if(Client.getBairroCliente().equals("")) {
			JOptionPane.showMessageDialog(null, "Preencha o bairro", "Erro", 0, new ImageIcon("img/close.png"));
			return false;
		}
		
		if(Client.getTelefoneCliente().equals("(  )      -    ")) {
			JOptionPane.showMessageDialog(null, "Preencha o telefone", "Erro", 0, new ImageIcon("img/close.png"));
			return false;
		}
		ClientAux.registerClient(Client);
		return true;
	}
	
	public String controll() {
		return ClientAux.nextClient();
	}
	
	public void controlSearch(String search, DefaultTableModel Model) {
		ClientAux.searchClient(search,Model);
	}
	
	public void controlSearch(String search, List<String> Lista) {
		ClientAux.searchClient(search,Lista);
	}
	
	public Client controlPopulatedField(int Codigo) {
		return ClientAux.populatedField(Codigo);
	}
	
	public boolean checkEditData(Client Client) {
		if(Client.getNomeCliente().equals("")) {
			JOptionPane.showMessageDialog(null, "Preencha o nome", "Erro", 0, new ImageIcon("img/close.png"));
			return false;
		}
		
		if(Client.getEnderecoCliente().equals("")) {
			JOptionPane.showMessageDialog(null, "Preencha o endereço", "Erro", 0, new ImageIcon("img/close.png"));
			return false;
		}
		
		if(Client.getComplementoEnderecoCliente().equals("")) {
			JOptionPane.showMessageDialog(null, "Preencha o complemento do endereço", "Erro", 0, new ImageIcon("img/close.png"));
			return false;
		}
		
		if(Client.getBairroCliente().equals("")) {
			JOptionPane.showMessageDialog(null, "Preencha o bairro", "Erro", 0, new ImageIcon("img/close.png"));
			return false;
		}
		
		if(Client.getTelefoneCliente().equals("(  )      -    ")) {
			JOptionPane.showMessageDialog(null, "Preencha o telefone", "Erro", 0, new ImageIcon("img/close.png"));
			return false;
		}
		ClientAux.editClient(Client);
		return true;
	}
	
	
}
