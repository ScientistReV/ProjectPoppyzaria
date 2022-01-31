package View;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JSeparator;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.JFormattedTextField;
import Model.Client;
import Controller.ClientController;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.SwingConstants;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class Clients extends JInternalFrame {
	private JTextField text_id = new JTextField();
	private JTextField text_name = new JTextField();
	private JTextField text_address = new JTextField();
	private JTextField text_complement_address = new JTextField();
	private JTextField text_neighborhood = new JTextField();
	private JTextField text_search = new JTextField();
	private JFormattedTextField text_phone = new JFormattedTextField();
	private JFormattedTextField text_date_create_account = new JFormattedTextField ();
	private JTable table_clients;
    MaskFormatter phoneFormat;
    SimpleDateFormat dateFormat;
	Date todayDate;
	Client Client ;
	ClientController ClientController;
	DefaultTableModel Model;


	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Clients frame = new Clients();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	//Construtor
	public Clients() {
		startApp();
		dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		
		Client = new Client();
		ClientController = new ClientController();
		Model = (DefaultTableModel)table_clients.getModel();
	}
	
	public void startApp() {
		setTitle("Cadastrar Cliente");
		setIconifiable(true);
		setClosable(true);
		setBounds(100, 100, 718, 574);
		
		JLabel label_id = new JLabel("C\u00F3digo");
		label_id.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		this.text_id.setEnabled(false);
		text_id.setEditable(false);
		text_id.setColumns(10);
		
		JLabel label_name = new JLabel("Nome");
		label_name.setFont(new Font("Tahoma", Font.BOLD, 11));
		text_name.setEnabled(false);
		
		text_name.setColumns(10);
		
		JLabel label_address = new JLabel("Endereco");
		label_address.setFont(new Font("Tahoma", Font.BOLD, 11));
		text_address.setEnabled(false);
		
		text_address.setColumns(10);
		
		JLabel label_complement_address = new JLabel("Complemento");
		label_complement_address.setFont(new Font("Tahoma", Font.BOLD, 11));
		text_complement_address.setEnabled(false);
		
		text_complement_address.setColumns(10);
		
		JLabel label_neighborhood = new JLabel("Bairro");
		label_neighborhood.setFont(new Font("Tahoma", Font.BOLD, 11));
		text_neighborhood.setEnabled(false);
		
		text_neighborhood.setColumns(10);
		
		JLabel labe_date_create_account = new JLabel("Data de Cadastro");
		labe_date_create_account.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		JLabel label_phone = new JLabel("Telefone");
		label_phone.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		try {
            phoneFormat = new MaskFormatter("(##) #####-####");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro na máscara", "Erro", 0);
        }
        text_phone = new JFormattedTextField(phoneFormat);
        text_phone.setEnabled(false);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(Color.GRAY);
		
		JLabel label_search = new JLabel("Buscar");
		label_search.setFont(new Font("Tahoma", Font.BOLD, 11));
		text_search.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				Model.setNumRows(0);
				ClientController.controlSearch(text_search.getText(), Model);
			}
		});
		
		text_search.setColumns(10);
		
		JSeparator separator_1 = new JSeparator();
		
		JScrollPane matrix_table_clients = new JScrollPane();
		matrix_table_clients.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		
		JSeparator separator_2 = new JSeparator();
		
		JButton btn_new = new JButton("");
		btn_new.setPreferredSize(new Dimension(97, 23));
		btn_new.setMinimumSize(new Dimension(97, 23));
		btn_new.setMaximumSize(new Dimension(97, 23));
		btn_new.setIconTextGap(3);
		btn_new.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btn_new.setHorizontalTextPosition(SwingConstants.CENTER);
		btn_new.setVerticalTextPosition(SwingConstants.BOTTOM);
		btn_new.setIcon(new ImageIcon("src/Icon/new.png"));
		btn_new.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				todayDate = new Date();
				text_date_create_account.setText(dateFormat.format(todayDate));
				enableField(true);
				ClientController.controll();
				text_id.setText(ClientController.controll());
			}
			
		});
		
		text_phone.setColumns(10);
		text_date_create_account.setEditable(false);
		
		text_date_create_account.setColumns(10);
		
		JButton btn_register = new JButton("");
		btn_register.setMinimumSize(new Dimension(97, 23));
		btn_register.setPreferredSize(new Dimension(97, 23));
		btn_register.setMaximumSize(new Dimension(97, 23));
		btn_register.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btn_register.setVerticalTextPosition(SwingConstants.BOTTOM);
		btn_register.setHorizontalTextPosition(SwingConstants.CENTER);
		btn_register.setIcon(new ImageIcon("src/Icon/sav.png"));
		btn_register.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				populatedClient();
				ClientController.checkData(Client);
				enableField(false);
				clearFields();
			}
		});
		
		JButton btn_edit = new JButton("");
		btn_edit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btn_edit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				populatedClient();
				ClientController.checkEditData(Client);
				clearFields();
				text_search.setText("");
				enableField(false);
			}
		});
		btn_edit.setIcon(new ImageIcon("src/Icon/edit.png"));
		btn_edit.setVerticalTextPosition(SwingConstants.BOTTOM);
		btn_edit.setPreferredSize(new Dimension(97, 23));
		btn_edit.setMinimumSize(new Dimension(97, 23));
		btn_edit.setMaximumSize(new Dimension(97, 23));
		btn_edit.setHorizontalTextPosition(SwingConstants.CENTER);
		
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
									.addGroup(groupLayout.createSequentialGroup()
										.addComponent(label_id)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(text_id, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE))
									.addGroup(groupLayout.createSequentialGroup()
										.addComponent(label_name)
										.addPreferredGap(ComponentPlacement.UNRELATED)
										.addComponent(text_name, GroupLayout.PREFERRED_SIZE, 330, GroupLayout.PREFERRED_SIZE))
									.addGroup(groupLayout.createSequentialGroup()
										.addComponent(label_address)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(text_address))
									.addGroup(groupLayout.createSequentialGroup()
										.addComponent(label_complement_address)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(text_complement_address)))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(label_neighborhood)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(text_neighborhood, GroupLayout.PREFERRED_SIZE, 167, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(label_phone)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(text_phone, GroupLayout.PREFERRED_SIZE, 135, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(labe_date_create_account)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(text_date_create_account, GroupLayout.PREFERRED_SIZE, 206, GroupLayout.PREFERRED_SIZE))))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(13)
							.addComponent(label_search, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(text_search, GroupLayout.PREFERRED_SIZE, 199, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(186, Short.MAX_VALUE))
				.addComponent(separator_1, GroupLayout.PREFERRED_SIZE, 702, Short.MAX_VALUE)
				.addComponent(separator, GroupLayout.PREFERRED_SIZE, 702, Short.MAX_VALUE)
				.addComponent(separator_2, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 702, Short.MAX_VALUE)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(15)
					.addComponent(btn_new, GroupLayout.PREFERRED_SIZE, 98, GroupLayout.PREFERRED_SIZE)
					.addGap(9)
					.addComponent(btn_register, GroupLayout.PREFERRED_SIZE, 104, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btn_edit, GroupLayout.PREFERRED_SIZE, 104, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(362, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(matrix_table_clients, GroupLayout.PREFERRED_SIZE, 681, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(11, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_id)
						.addComponent(text_id, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_name)
						.addComponent(text_name, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_address)
						.addComponent(text_address, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_complement_address)
						.addComponent(text_complement_address, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_neighborhood)
						.addComponent(text_neighborhood, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(label_phone)
							.addComponent(text_phone, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(labe_date_create_account)
							.addComponent(text_date_create_account, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(separator, GroupLayout.PREFERRED_SIZE, 2, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_search)
						.addComponent(text_search, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(separator_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(24)
					.addComponent(matrix_table_clients, GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE)
					.addGap(29)
					.addComponent(separator_2, GroupLayout.PREFERRED_SIZE, 2, GroupLayout.PREFERRED_SIZE)
					.addGap(14)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(btn_new, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE)
						.addComponent(btn_register, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE)
						.addComponent(btn_edit, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(19, Short.MAX_VALUE))
		);
		
		table_clients = new JTable();
		table_clients.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				enableField(true);
				Client = ClientController.controlPopulatedField(Integer.parseInt(Model.getValueAt(table_clients.getSelectedRow(), 0).toString()));
				text_id.setText(Client.getIdCliente() + "");
				text_name.setText(Client.getNomeCliente());
				text_address.setText(Client.getEnderecoCliente());
				text_complement_address.setText(Client.getComplementoEnderecoCliente());
				text_neighborhood.setText(Client.getBairroCliente());
				text_phone.setText(Client.getTelefoneCliente());
				text_date_create_account.setText(Client.getDataCadastro());
			}
		});
		table_clients.setShowVerticalLines(false);
		table_clients.setShowHorizontalLines(false);
		table_clients.setBackground(Color.WHITE);
		table_clients.setForeground(Color.BLACK);
		table_clients.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"C\u00F3digo", "Nome", "Endere\u00E7o", "Complemento", "Bairro", "Telefone"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table_clients.getColumnModel().getColumn(2).setPreferredWidth(155);
		table_clients.getColumnModel().getColumn(3).setPreferredWidth(91);
		matrix_table_clients.setViewportView(table_clients);
		getContentPane().setLayout(groupLayout);

	}
	
	//Função para desativar os campos enquanto não clicar em novo
	final void enableField(boolean b) {
		this.text_name.setEnabled(b);
		this.text_address.setEnabled(b);
		this.text_complement_address.setEnabled(b);
		this.text_neighborhood.setEnabled(b);
		this.text_phone.setEnabled(b);
		this.text_date_create_account.setEnabled(b);
	}
	
	//Serve para popular o banco de dados
	final void populatedClient() {
		Client.setNomeCliente(this.text_name.getText());
		Client.setEnderecoCliente(this.text_address.getText());
		Client.setComplementoEnderecoCliente(this.text_complement_address.getText());
		Client.setBairroCliente(this.text_neighborhood.getText());
		Client.setTelefoneCliente(this.text_phone.getText());
		Client.setDataCadastro(this.text_date_create_account.getText());
	}
	
	final void clearFields() {
		this.text_id.setText("");
		this.text_name.setText("");
		this.text_address.setText("");
		this.text_complement_address.setText("");
		this.text_neighborhood.setText("");
		this.text_phone.setText("");
		this.text_date_create_account.setText("");
	}
}
