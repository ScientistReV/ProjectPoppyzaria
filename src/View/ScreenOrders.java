package View;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Controller.ScreenOrderController;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ScreenOrders extends JInternalFrame {
	private JTable table;
	DefaultTableModel Model;
	ScreenOrderController screenController;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ScreenOrders frame = new ScreenOrders();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public ScreenOrders() {
		setTitle("Tela de Pedidos");
		setBounds(100, 100, 808, 529);
		startApp();
		screenController = new ScreenOrderController();
		Model = (DefaultTableModel)table.getModel();
		Model.setNumRows(0);
		screenController.controlSearchScreen(Model);
	}
	
	public void startApp() {
		JButton btn_att = new JButton("Atualizar");
		btn_att.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Model.setNumRows(0);
				screenController.controlSearchScreen(Model);
			}
		});
		
		JScrollPane scrollPane = new JScrollPane();
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(btn_att, Alignment.TRAILING)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 414, Short.MAX_VALUE))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(btn_att)
					.addGap(18)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 207, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Pedido", "Data", "Hora", "Status"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		scrollPane.setViewportView(table);
		getContentPane().setLayout(groupLayout);
	}
}
