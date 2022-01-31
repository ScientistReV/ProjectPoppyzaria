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
import Model.Menu;
import Controller.MenuController;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.SwingConstants;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.Point;


public class Menus extends JInternalFrame {
	private JTextField text_id = new JTextField();
	private JTextField text_description = new JTextField();
	private JTextField text_search = new JTextField();
	private JTable table_Menus;
	private JComboBox box_type = new JComboBox();;
    MaskFormatter phoneFormat;
    SimpleDateFormat dateFormat;
	Date todayDate;
	Menu Menu ;
	MenuController MenuController;
	DefaultTableModel Model;
	private JTextField text_value;


	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Menus frame = new Menus();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	//Construtor
	public Menus() {
		startApp();
		dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		
		Menu = new Menu();
		MenuController = new MenuController();
		Model = (DefaultTableModel)table_Menus.getModel();
	}
	
	public void startApp() {
		setTitle("Cadastrar Cardápio");
		setIconifiable(true);
		setClosable(true);
		setBounds(100, 100, 718, 535);
		
		JLabel label_id = new JLabel("C\u00F3digo");
		label_id.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		this.text_id.setEnabled(false);
		text_id.setEditable(false);
		text_id.setColumns(10);
		
		JLabel lblDescri = new JLabel("Descri\u00E7\u00E3o");
		lblDescri.setFont(new Font("Tahoma", Font.BOLD, 11));
		text_description.setEnabled(false);
		
		text_description.setColumns(10);
		
		JLabel label_tipo = new JLabel("Tipo");
		label_tipo.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		JLabel label_value = new JLabel("Valor");
		label_value.setFont(new Font("Tahoma", Font.BOLD, 11));
		
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
				MenuController.controlSearch(text_search.getText(), Model);
			}
		});
		
		text_search.setColumns(10);
		
		JSeparator separator_1 = new JSeparator();
		
		JScrollPane matrix_table_Menus = new JScrollPane();
		matrix_table_Menus.addMouseListener(new MouseAdapter() {
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
				enableField(true);
				MenuController.controll();
				text_id.setText(MenuController.controll());
				text_value.setText("0");

			}
			
		});
		
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
				populatedMenu();
				MenuController.checkData(Menu);
				clearFields();
				enableField(false);
			}
		});
		
		JButton btn_edit = new JButton("");
		btn_edit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				populatedMenu();
				MenuController.checkEditData(Menu);
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
		box_type.setEnabled(false);
		
		box_type.setModel(new DefaultComboBoxModel(new String[] {"Selecione um item", "Pizza", "Refrigerante", "Suco", "Petiscos"}));
		
		text_value = new JTextField();
		text_value.setEnabled(false);
		text_value.setColumns(10);
		
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(label_id)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(text_id, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(label_tipo)
									.addGap(18)
									.addComponent(box_type, GroupLayout.PREFERRED_SIZE, 155, GroupLayout.PREFERRED_SIZE)
									.addGap(30)
									.addComponent(label_value)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(text_value))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblDescri)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(text_description, GroupLayout.PREFERRED_SIZE, 330, GroupLayout.PREFERRED_SIZE)))
							.addGap(100))
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
							.addComponent(matrix_table_Menus, GroupLayout.PREFERRED_SIZE, 681, GroupLayout.PREFERRED_SIZE))
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
						.addComponent(lblDescri)
						.addComponent(text_description, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_tipo)
						.addComponent(box_type, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_value)
						.addComponent(text_value, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(separator, GroupLayout.PREFERRED_SIZE, 2, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_search)
						.addComponent(text_search, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(separator_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(26)
					.addComponent(matrix_table_Menus, GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE)
					.addGap(26)
					.addComponent(separator_2, GroupLayout.PREFERRED_SIZE, 2, GroupLayout.PREFERRED_SIZE)
					.addGap(34)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(btn_register, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE)
						.addComponent(btn_new, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE)
						.addComponent(btn_edit, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(40, Short.MAX_VALUE))
		);
		
		table_Menus = new JTable();
		table_Menus.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				enableField(true);
				Menu = MenuController.controlPopulatedField(Integer.parseInt(Model.getValueAt(table_Menus.getSelectedRow(), 0).toString()));
				text_id.setText(Menu.getIdCardapio() + "");
				text_description.setText(Menu.getDescricaoCardapio());
				box_type.setSelectedItem(Menu.getTipoCardapio());
				text_value.setText(Menu.getValorCardapio() + "");
			}
		});
		table_Menus.setShowVerticalLines(false);
		table_Menus.setShowHorizontalLines(false);
		table_Menus.setBackground(Color.WHITE);
		table_Menus.setForeground(Color.BLACK);
		table_Menus.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"C\u00F3digo", "Descri\u00E7\u00E3o", "Tipo", "Valor"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table_Menus.getColumnModel().getColumn(2).setPreferredWidth(155);
		table_Menus.getColumnModel().getColumn(3).setPreferredWidth(136);
		matrix_table_Menus.setViewportView(table_Menus);
		getContentPane().setLayout(groupLayout);

	}
	
	//Função para desativar os campos enquanto não clicar em novo
	final void enableField(boolean b) {
		this.text_description.setEnabled(b);
		this.box_type.setEnabled(b);
		this.text_value.setEnabled(b);
	}
	
	//Serve para popular o banco de dados
	final void populatedMenu() {
		Menu.setDescricaoCardapio(this.text_description.getText());
		Menu.setTipoCardapio(this.box_type.getSelectedItem().toString());
		Menu.setValorCardapio(Double.parseDouble(this.text_value.getText()));
	}
	
	final void clearFields() {
		this.text_id.setText("");
		this.text_description.setText("");
		this.box_type.setSelectedIndex(0);
		this.text_value.setText("");
	}
}
