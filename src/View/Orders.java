package View;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JTabbedPane;
import java.awt.BorderLayout;
import javax.swing.JSplitPane;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout.Alignment;
import java.awt.Cursor;
import javax.swing.JComboBox;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ListSelectionModel;
import javax.swing.text.MaskFormatter;

import Controller.ClientController;
import Controller.OrderController;
import Model.Client;
import Model.Order;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JFormattedTextField;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class Orders extends JInternalFrame {
	private JTextField text_id = new JTextField();
	private JTextField text_name = new JTextField();
	private JTextField text_address = new JTextField();
	private JTextField text_complement_address = new JTextField();
	private JTextField text_neighborhood = new JTextField();
	private JFormattedTextField text_phone = new JFormattedTextField();
	private JFormattedTextField text_date_create_account = new JFormattedTextField ();
	MaskFormatter phoneFormat;
	private JTextField text_client_name;
	private JTextField text_item;
	private JTextField text_value;
	public static JTextField text_quantity;
	private JTextField text_id_order;
	private JTextField text_total;
	private JComboBox box_select = new JComboBox();
	private JComboBox box_client = new JComboBox();
	private JTable table_order = new JTable();
	Client Client = new Client() ;
	ClientController ClientController = new ClientController();
	List<String>Lista = new ArrayList<>();
	List<String>ListaItens = new ArrayList<>();
	Order order = new Order();
	Order codigoFuncionario = new Order();
	OrderController OrderController = new OrderController();
	private JButton btn_value;
	private JButton btn_final;
	private JTabbedPane panel_father;
	DefaultTableModel Model;
	DecimalFormat formatoDecimal;
	Date today;
	SimpleDateFormat formatDate, formatTime;


	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Orders frame = new Orders(1);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public Orders(int idEmployee) {
		setTitle("Pedidos");
		setClosable(true);
		setIconifiable(true);
		startApp();
		enableField(false);
		panel_father.setEnabledAt(1, false);
		table_order.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		Model = (DefaultTableModel)table_order.getModel();
		formatoDecimal = new DecimalFormat();
		this.codigoFuncionario = codigoFuncionario;
		formatDate = new SimpleDateFormat("yyyy-MM-dd");
		formatTime = new SimpleDateFormat("HH:mm:ss");

	}
	
	public void startApp() {
		setBounds(100, 100, 732, 557);
		
		panel_father = new JTabbedPane(JTabbedPane.TOP);
		panel_father.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		getContentPane().add(panel_father, BorderLayout.CENTER);
		
		JPanel panel_cliente = new JPanel();
		panel_cliente.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		panel_cliente.setToolTipText("");
		panel_father.addTab("Clientes", null, panel_cliente, null);
		
		JButton btn_search = new JButton("Pesquisar");
		btn_search.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btn_search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				box_client.removeAllItems();
				Lista.clear();
				String search = JOptionPane.showInputDialog(null, "Informe o nome do cliente", "Pesquisar", 3);
				ClientController.controlSearch(search, Lista);
				for(String item: Lista ) {
					box_client.addItem(item);
				}
			}
		});
		
		
		JLabel label_id = new JLabel("C\u00F3digo");
		label_id.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		text_id = new JTextField();
		text_id.setEnabled(false);
		text_id.setColumns(10);
		
		JLabel label_name = new JLabel("Nome");
		label_name.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		text_name = new JTextField();
		text_name.setColumns(10);
		
		JLabel label_address = new JLabel("Endereco");
		label_address.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		text_address = new JTextField();
		text_address.setColumns(10);
		
		JLabel label_complement_address = new JLabel("Complemento");
		label_complement_address.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		text_complement_address = new JTextField();
		text_complement_address.setColumns(10);
		
		JLabel label_neighborhood = new JLabel("Bairro");
		label_neighborhood.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		text_neighborhood = new JTextField();
		text_neighborhood.setColumns(10);
		
		JLabel label_phone = new JLabel("Telefone");
		label_phone.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		try {
            phoneFormat = new MaskFormatter("(##) #####-####");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro na máscara", "Erro", 0);
        }
        text_phone = new JFormattedTextField(phoneFormat);
		text_phone.setColumns(10);
		
		JLabel labe_date_create_account = new JLabel("Data de Cadastro");
		labe_date_create_account.setFont(new Font("Tahoma", Font.BOLD, 11));
		text_date_create_account.setColumns(10);
		
		JButton btn_order = new JButton("Pedido");
		btn_order.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel_father.setEnabledAt(1, true);
				panel_father.setEnabledAt(0, false);
				panel_father.setSelectedIndex(1);
				text_client_name.setText(text_name.getText());
				btn_final.setEnabled(false);
			}
		});
		btn_order.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		JButton btn_close = new JButton("Fechar");
		btn_close.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btn_close.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		JSeparator separator_1_1 = new JSeparator();
		
		JSeparator separator_1_2 = new JSeparator();
		
		
		GroupLayout gl_panel_cliente = new GroupLayout(panel_cliente);
		gl_panel_cliente.setHorizontalGroup(
			gl_panel_cliente.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_cliente.createSequentialGroup()
					.addContainerGap()
					.addComponent(btn_search)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(box_client, GroupLayout.PREFERRED_SIZE, 279, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(147, Short.MAX_VALUE))
				.addGroup(gl_panel_cliente.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_cliente.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_cliente.createSequentialGroup()
							.addComponent(label_id, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
							.addGap(4)
							.addComponent(text_id, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_cliente.createSequentialGroup()
							.addComponent(label_name, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
							.addGap(10)
							.addComponent(text_name, GroupLayout.PREFERRED_SIZE, 330, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_cliente.createSequentialGroup()
							.addComponent(label_address, GroupLayout.PREFERRED_SIZE, 52, GroupLayout.PREFERRED_SIZE)
							.addGap(4)
							.addComponent(text_address, GroupLayout.PREFERRED_SIZE, 316, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_cliente.createSequentialGroup()
							.addComponent(label_complement_address, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE)
							.addGap(4)
							.addComponent(text_complement_address, GroupLayout.PREFERRED_SIZE, 289, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_cliente.createSequentialGroup()
							.addComponent(label_neighborhood, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
							.addGap(4)
							.addComponent(text_neighborhood, GroupLayout.PREFERRED_SIZE, 167, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_cliente.createSequentialGroup()
							.addComponent(label_phone, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
							.addGap(4)
							.addComponent(text_phone, GroupLayout.PREFERRED_SIZE, 135, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(139, Short.MAX_VALUE))
				.addGroup(gl_panel_cliente.createSequentialGroup()
					.addContainerGap()
					.addComponent(labe_date_create_account, GroupLayout.PREFERRED_SIZE, 98, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(text_date_create_account, GroupLayout.PREFERRED_SIZE, 206, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(203, Short.MAX_VALUE))
				.addGroup(Alignment.TRAILING, gl_panel_cliente.createSequentialGroup()
					.addContainerGap(369, Short.MAX_VALUE)
					.addComponent(btn_order)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btn_close)
					.addGap(26))
				.addComponent(separator_1_1, GroupLayout.DEFAULT_SIZE, 662, Short.MAX_VALUE)
				.addComponent(separator_1_2, GroupLayout.DEFAULT_SIZE, 662, Short.MAX_VALUE)
		);
		box_client.setModel(new DefaultComboBoxModel(new String[] {"Selecione um cliente"}));
		box_client.setSelectedIndex(0);
		box_client.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!(box_client.getSelectedItem() == null)) {
					String code = box_client.getSelectedItem().toString();
					code = code.substring(0, code.indexOf(" "));
					Client = ClientController.controlPopulatedField(Integer.parseInt(code));
					text_id.setText(Client.getIdCliente() + "");
					text_name.setText(Client.getNomeCliente());
					text_address.setText(Client.getEnderecoCliente());
					text_complement_address.setText(Client.getComplementoEnderecoCliente());
					text_neighborhood.setText(Client.getBairroCliente());
					text_phone.setText(Client.getTelefoneCliente());
					text_date_create_account.setText(Client.getDataCadastro());
				}
			}
		});
		box_client.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		gl_panel_cliente.setVerticalGroup(
			gl_panel_cliente.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_cliente.createSequentialGroup()
					.addGap(11)
					.addGroup(gl_panel_cliente.createParallelGroup(Alignment.BASELINE)
						.addComponent(box_client, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btn_search))
					.addGap(28)
					.addComponent(separator_1_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel_cliente.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_cliente.createSequentialGroup()
							.addGap(3)
							.addComponent(label_id))
						.addComponent(text_id, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(11)
					.addGroup(gl_panel_cliente.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_cliente.createSequentialGroup()
							.addGap(3)
							.addComponent(label_name))
						.addComponent(text_name, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(11)
					.addGroup(gl_panel_cliente.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_cliente.createSequentialGroup()
							.addGap(3)
							.addComponent(label_address))
						.addComponent(text_address, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(11)
					.addGroup(gl_panel_cliente.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_cliente.createSequentialGroup()
							.addGap(3)
							.addComponent(label_complement_address))
						.addComponent(text_complement_address, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(11)
					.addGroup(gl_panel_cliente.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_cliente.createSequentialGroup()
							.addGap(3)
							.addComponent(label_neighborhood))
						.addComponent(text_neighborhood, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_cliente.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_cliente.createSequentialGroup()
							.addGap(3)
							.addComponent(label_phone))
						.addComponent(text_phone, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel_cliente.createParallelGroup(Alignment.BASELINE)
						.addComponent(text_date_create_account, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(labe_date_create_account))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(separator_1_2, GroupLayout.PREFERRED_SIZE, 6, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_cliente.createParallelGroup(Alignment.BASELINE)
						.addComponent(btn_order)
						.addComponent(btn_close))
					.addGap(182))
		);
		panel_cliente.setLayout(gl_panel_cliente);
		
		JPanel panel_pedido = new JPanel();
		panel_father.addTab("Pedidos", null, panel_pedido, null);
		
		text_client_name = new JTextField();
		text_client_name.setEditable(false);
		text_client_name.setHorizontalAlignment(SwingConstants.CENTER);
		text_client_name.setColumns(10);
		
		JLabel label_item = new JLabel("Item");
		
		text_item = new JTextField();
		text_item.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				text_value.setText("");
				box_select.removeAllItems();
				ListaItens.clear();
				OrderController.controlSearchOrder(text_item.getText(), ListaItens);
				for(String item: ListaItens ) {
					box_select.addItem(item);
				}
			}
		});
		
		text_item.setColumns(10);
		
		JLabel label_select = new JLabel("Selecionar");
		box_select.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				text_value.setText("");
				text_quantity.setText("1");
			}
		});
		box_select.setModel(new DefaultComboBoxModel(new String[] {"Selecione um item"}));
		
		
		box_select.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		btn_value = new JButton("Valor");
		btn_value.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				text_value.setText(OrderController.controlValueOrder(box_select.getSelectedItem().toString()) + "");
				text_id_order.setText(OrderController.controlCodeOrder(box_select.getSelectedItem().toString()) + "");

			}
		});
		btn_value.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		JLabel label_value = new JLabel("Valor");
		
		text_value = new JTextField();
		text_value.setEditable(false);
		text_value.setColumns(10);
		
		JLabel label_quantity = new JLabel("Quantidade");
		
		text_quantity = new JTextField();
		text_quantity.setText("1");
		text_quantity.setColumns(10);
		
		JLabel label_id_order = new JLabel("C\u00F3digo");
		
		text_id_order = new JTextField();
		text_id_order.setEditable(false);
		text_id_order.setColumns(10);
		
		JSeparator separator = new JSeparator();
		
		
		
		JButton btn_add = new JButton("+");
		btn_add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		        if(OrderController.checkOrder(text_value.getText(), text_quantity.getText(), text_id_order.getText(), box_select.getSelectedItem().toString())){
		            double Total = Double.parseDouble(text_value.getText()) * Integer.parseInt(text_quantity.getText());
		            Model.addRow(new Object[]{text_id_order.getText(), box_select.getSelectedItem(), text_value.getText(), text_quantity.getText(), Total});
		            clearField();
		            price();
				}
			}
		});
		btn_add.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		JButton btn_remove = new JButton("-");
		btn_remove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Model.removeRow(table_order.getSelectedRow());
				price();
				
			}
		});
		btn_remove.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		JLabel label_maximum = new JLabel("Total");
		
		text_total = new JTextField();
		text_total.setColumns(10);
		
		JButton btn_calculator = new JButton("Calcular");
		btn_calculator.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				price();
			}
		});
		btn_calculator.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		JScrollPane matrix_table_order = new JScrollPane();
		
		JSeparator separator_1 = new JSeparator();
		
		btn_final = new JButton("Finalizar");
		btn_final.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				populated();
				OrderController.controlOrder(text_id.getText(), codigoFuncionario.getIdFuncionarioAux() + " ", text_total.getText(), table_order.getRowCount(), order);
				cleanFinal();
			}
		});
		btn_final.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		JButton btn_close_1 = new JButton("Fechar");
		btn_close_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btn_close_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		GroupLayout gl_panel_pedido = new GroupLayout(panel_pedido);
		gl_panel_pedido.setHorizontalGroup(
			gl_panel_pedido.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_pedido.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_pedido.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel_pedido.createSequentialGroup()
							.addComponent(btn_value)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(label_value, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(text_value, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 57, Short.MAX_VALUE)
							.addComponent(label_quantity, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(text_quantity, GroupLayout.PREFERRED_SIZE, 114, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(label_id_order, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(text_id_order, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addComponent(text_client_name, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 629, Short.MAX_VALUE)
						.addGroup(Alignment.LEADING, gl_panel_pedido.createSequentialGroup()
							.addComponent(label_item, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(text_item, GroupLayout.PREFERRED_SIZE, 191, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(label_select)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(box_select, 0, 329, Short.MAX_VALUE)))
					.addGap(23))
				.addComponent(separator, GroupLayout.PREFERRED_SIZE, 662, Short.MAX_VALUE)
				.addGroup(gl_panel_pedido.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_pedido.createParallelGroup(Alignment.TRAILING)
						.addComponent(btn_calculator)
						.addGroup(gl_panel_pedido.createSequentialGroup()
							.addComponent(btn_add)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btn_remove, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 421, Short.MAX_VALUE)
							.addComponent(label_maximum, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(text_total, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)))
					.addGap(19))
				.addGroup(gl_panel_pedido.createSequentialGroup()
					.addContainerGap(496, Short.MAX_VALUE)
					.addComponent(btn_final)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btn_close_1)
					.addGap(20))
				.addComponent(separator_1, GroupLayout.DEFAULT_SIZE, 662, Short.MAX_VALUE)
				.addGroup(gl_panel_pedido.createSequentialGroup()
					.addContainerGap()
					.addComponent(matrix_table_order, GroupLayout.DEFAULT_SIZE, 642, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_panel_pedido.setVerticalGroup(
			gl_panel_pedido.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_pedido.createSequentialGroup()
					.addContainerGap()
					.addComponent(text_client_name, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(16)
					.addGroup(gl_panel_pedido.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_item, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
						.addComponent(text_item, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_select)
						.addComponent(box_select, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel_pedido.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_pedido.createParallelGroup(Alignment.BASELINE)
							.addComponent(btn_value)
							.addComponent(label_value, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
							.addComponent(text_value, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_pedido.createParallelGroup(Alignment.BASELINE)
							.addComponent(text_quantity, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(label_quantity, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
							.addComponent(label_id_order, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
							.addComponent(text_id_order, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addGap(18)
					.addComponent(separator, GroupLayout.PREFERRED_SIZE, 2, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel_pedido.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_pedido.createParallelGroup(Alignment.BASELINE)
							.addComponent(btn_add)
							.addComponent(btn_remove))
						.addGroup(gl_panel_pedido.createParallelGroup(Alignment.BASELINE)
							.addComponent(label_maximum, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
							.addComponent(text_total, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addGap(5)
					.addComponent(btn_calculator)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(separator_1, GroupLayout.PREFERRED_SIZE, 2, GroupLayout.PREFERRED_SIZE)
					.addGap(26)
					.addComponent(matrix_table_order, GroupLayout.PREFERRED_SIZE, 135, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 83, Short.MAX_VALUE)
					.addGroup(gl_panel_pedido.createParallelGroup(Alignment.BASELINE)
						.addComponent(btn_close_1)
						.addComponent(btn_final))
					.addContainerGap())
		);
		
		table_order = new JTable();
		table_order.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Item", "Descri\u00E7\u00E3o", "Valor Unit\u00E1rio", "Quantidade", "Total"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table_order.getColumnModel().getColumn(0).setPreferredWidth(96);
		table_order.getColumnModel().getColumn(1).setPreferredWidth(142);
		table_order.getColumnModel().getColumn(3).setPreferredWidth(102);
		table_order.getColumnModel().getColumn(4).setPreferredWidth(140);
		matrix_table_order.setViewportView(table_order);
		panel_pedido.setLayout(gl_panel_pedido);

	}
	
	final void enableField(boolean b) {
		this.text_name.setEnabled(b);
		this.text_address.setEnabled(b);
		this.text_complement_address.setEnabled(b);
		this.text_neighborhood.setEnabled(b);
		this.text_phone.setEnabled(b);
		this.text_date_create_account.setEnabled(b);
	}
	
	final void clearField() {
		this.text_value.setText("");
		this.box_select.removeAllItems();
		this.text_quantity.setText("1");
		this.text_item.setText("");
	}
	
	final void price() {
		double priceOrder = 0;
		for(int i=0; i < table_order.getRowCount(); i++) {
			priceOrder += Double.parseDouble(Model.getValueAt(i,4).toString());
		}
		if(priceOrder > 0) {
			btn_final.setEnabled(true);
		}
		text_total.setText(formatoDecimal.format(priceOrder).replace('.', ','));
	}
	
	final void populated() {
		today = new Date();
		order.setDataPedido(formatDate.format(today));
		order.setHoraPedido(formatTime.format(today));
		order.setValorPedido(Double.parseDouble(text_total.getText().replace(',' , '.')));
		order.setIdClienteAux(Integer.parseInt(text_id.getText()));
		order.setIdFuncionarioAux(codigoFuncionario.getIdFuncionarioAux());
		order.setIdEntregadorAux(1);
		order.setStatusPedido("Pedido aberto");
		
		for(int i = 0; i < table_order.getRowCount(); i++ ) {
			order.setIdCardapioAux(Integer.parseInt(Model.getValueAt(i,0).toString()));
			order.setQuantidade(Integer.parseInt(Model.getValueAt(i,3).toString()));

		}
	}
	
	final void cleanFinal() {
		text_total.setText("");
		text_id_order.setText("");
		btn_final.setEnabled(false);
		Model.setNumRows(0);
	}
}
