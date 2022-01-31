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
import Model.Deliveryman;
import Controller.DeliverymanController;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.SwingConstants;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.Point;


public class Deliverymans extends JInternalFrame {
	private JTextField text_id = new JTextField();
	private JTextField text_name = new JTextField();
	private JTextField text_search = new JTextField();
	private JFormattedTextField text_date_create_account = new JFormattedTextField();
	private JTable table_deliverymans;
    MaskFormatter phoneFormat;
    SimpleDateFormat dateFormat;
	Date todayDate;
	Deliveryman Deliveryman ;
	DeliverymanController DeliverymanController;
	DefaultTableModel Model;


	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Deliverymans frame = new Deliverymans();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	//Construtor
	public Deliverymans() {
		startApp();
		dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		
		Deliveryman = new Deliveryman();
		DeliverymanController = new DeliverymanController();
		Model = (DefaultTableModel)table_deliverymans.getModel();
	}
	
	public void startApp() {
		setTitle("Cadastrar Entregador");
		setIconifiable(true);
		setClosable(true);
		setBounds(100, 100, 718, 535);
		
		JLabel label_id = new JLabel("C\u00F3digo");
		label_id.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		this.text_id.setEnabled(false);
		text_id.setEditable(false);
		text_id.setColumns(10);
		
		JLabel label_name = new JLabel("Nome");
		label_name.setFont(new Font("Tahoma", Font.BOLD, 11));
		text_name.setEnabled(false);
		
		text_name.setColumns(10);
		
		JLabel labe_date_create_account = new JLabel("Data de Cadastro");
		labe_date_create_account.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		try {
            phoneFormat = new MaskFormatter("(##) #####-####");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro na máscara", "Erro", 0);
        }
		
		JSeparator separator = new JSeparator();
		separator.setForeground(Color.GRAY);
		
		JLabel label_search = new JLabel("Buscar");
		label_search.setFont(new Font("Tahoma", Font.BOLD, 11));
		text_search.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				Model.setNumRows(0);
				DeliverymanController.controlSearch(text_search.getText(), Model);
			}
		});
		
		text_search.setColumns(10);
		
		JSeparator separator_1 = new JSeparator();
		
		JScrollPane matrix_table_deliverymans = new JScrollPane();
		matrix_table_deliverymans.addMouseListener(new MouseAdapter() {
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
				DeliverymanController.controll();
				text_id.setText(DeliverymanController.controll());
			}
			
		});
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
				populatedDeliveryman();
				DeliverymanController.checkData(Deliveryman);
				clearFields();
				enableField(false);
			}
		});
		
		JButton btn_edit = new JButton("");
		btn_edit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btn_edit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				populatedDeliveryman();
				DeliverymanController.checkEditData(Deliveryman);
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
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(label_id)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(text_id, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(labe_date_create_account)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(text_date_create_account, GroupLayout.PREFERRED_SIZE, 126, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(label_name)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(text_name, GroupLayout.PREFERRED_SIZE, 330, GroupLayout.PREFERRED_SIZE)))
							.addGap(178))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(14)
							.addComponent(btn_new, GroupLayout.PREFERRED_SIZE, 98, GroupLayout.PREFERRED_SIZE)
							.addGap(10)
							.addComponent(btn_register, GroupLayout.PREFERRED_SIZE, 104, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btn_edit, GroupLayout.PREFERRED_SIZE, 104, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(label_search, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(text_search, GroupLayout.PREFERRED_SIZE, 199, GroupLayout.PREFERRED_SIZE))
						.addComponent(separator, GroupLayout.PREFERRED_SIZE, 702, Short.MAX_VALUE)
						.addComponent(separator_1, GroupLayout.PREFERRED_SIZE, 702, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(matrix_table_deliverymans, GroupLayout.PREFERRED_SIZE, 681, GroupLayout.PREFERRED_SIZE))
						.addComponent(separator_2, GroupLayout.PREFERRED_SIZE, 702, Short.MAX_VALUE))
					.addContainerGap())
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
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(text_date_create_account, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(labe_date_create_account))
					.addGap(24)
					.addComponent(separator, GroupLayout.PREFERRED_SIZE, 2, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_search)
						.addComponent(text_search, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(separator_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(26)
					.addComponent(matrix_table_deliverymans, GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE)
					.addGap(26)
					.addComponent(separator_2, GroupLayout.PREFERRED_SIZE, 2, GroupLayout.PREFERRED_SIZE)
					.addGap(34)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(btn_register, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE)
						.addComponent(btn_new, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE)
						.addComponent(btn_edit, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(40, Short.MAX_VALUE))
		);
		
		table_deliverymans = new JTable();
		table_deliverymans.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				enableField(true);
				Deliveryman = DeliverymanController.controlPopulatedField(Integer.parseInt(Model.getValueAt(table_deliverymans.getSelectedRow(), 0).toString()));
				text_id.setText(Deliveryman.getIdEntregador() + "");
				text_name.setText(Deliveryman.getNomeEntregador());
				text_date_create_account.setText(Deliveryman.getDataCadastro());
			}
		});
		table_deliverymans.setShowVerticalLines(false);
		table_deliverymans.setShowHorizontalLines(false);
		table_deliverymans.setBackground(Color.WHITE);
		table_deliverymans.setForeground(Color.BLACK);
		table_deliverymans.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null},
			},
			new String[] {
				"C\u00F3digo", "Nome", "Data do Cadastro"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table_deliverymans.getColumnModel().getColumn(2).setPreferredWidth(136);
		matrix_table_deliverymans.setViewportView(table_deliverymans);
		getContentPane().setLayout(groupLayout);

	}
	
	//Função para desativar os campos enquanto não clicar em novo
	final void enableField(boolean b) {
		this.text_name.setEnabled(b);
		this.text_date_create_account.setEnabled(b);
	}
	
	//Serve para popular o banco de dados
	final void populatedDeliveryman() {
		Deliveryman.setNomeEntregador(this.text_name.getText());
		Deliveryman.setDataCadastro(this.text_date_create_account.getText());
	}
	
	final void clearFields() {
		this.text_id.setText("");
		this.text_name.setText("");
		this.text_date_create_account.setText("");
	}
}
