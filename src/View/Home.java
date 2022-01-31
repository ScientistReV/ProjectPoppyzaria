package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Util.OutFace;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import java.awt.SystemColor;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.Cursor;
import java.awt.Toolkit;

public class Home extends JFrame {

	private JPanel contentPane;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Home frame = new Home();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	OutFace outface;
	Clients client;
	Employees employee;
	Deliverymans deliveryman;
	Menus menu;
	Orders order;
	ScreenOrders screen;
	

	public Home() {
		startApp();
	}
	
	public void startApp() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("src/Icon/icon_pizza.png"));
		setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
		setForeground(SystemColor.textText);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setTitle("Poppyzzaria");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu menu_cadastro = new JMenu("Cadastro");
		menu_cadastro.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		menu_cadastro.setIcon(new ImageIcon("src/Icon/icon_register.png"));
		menu_cadastro.setForeground(SystemColor.menuText);
		menuBar.add(menu_cadastro);
		
		JMenuItem cadastro_cliente = new JMenuItem("Cliente");
		cadastro_cliente.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		cadastro_cliente.setIcon(new ImageIcon("src/Icon/ico_clientes.png"));
		cadastro_cliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				client = new Clients();
				outface.add(client);
				client.setVisible(true);
			}
		});
		menu_cadastro.add(cadastro_cliente);
		
		JMenuItem cadastro_funcionario = new JMenuItem("Funcion\u00E1rio");
		cadastro_funcionario.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		cadastro_funcionario.setIcon(new ImageIcon("src/Icon/ico_func.png"));
		cadastro_funcionario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				employee = new Employees();
				outface.add(employee);
				employee.setVisible(true);
			}
		});
		menu_cadastro.add(cadastro_funcionario);
		
		JMenuItem cadastro_entregador = new JMenuItem("Entregador");
		cadastro_entregador.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		cadastro_entregador.setIcon(new ImageIcon("src/Icon/ico_boy.png"));
		cadastro_entregador.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deliveryman = new Deliverymans();
				outface.add(deliveryman);
				deliveryman.setVisible(true);
			}
		});
		menu_cadastro.add(cadastro_entregador);
		
		JMenuItem cadastro_cardapio = new JMenuItem("Card\u00E1pio");
		cadastro_cardapio.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		cadastro_cardapio.setIcon(new ImageIcon("src/Icon/ico_cardapio.png"));
		cadastro_cardapio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menu = new Menus();
				outface.add(menu);
				menu.setVisible(true);
			}
		});
		menu_cadastro.add(cadastro_cardapio);
		
		JMenu menu_caixa = new JMenu("Caixa");
		menu_caixa.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		menu_caixa.setIcon(new ImageIcon("src/Icon/ico_caixa.png"));
		menu_caixa.setForeground(SystemColor.menuText);
		menuBar.add(menu_caixa);
		
		JMenuItem caixa_pedido = new JMenuItem("Pedido");
		caixa_pedido.setIcon(new ImageIcon("src/Icon/ico_pedidos.png"));
		caixa_pedido.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		caixa_pedido.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				order = new Orders(1);
				outface.add(order);
				order.setVisible(true);
			}
		});
		menu_caixa.add(caixa_pedido);
		
		JMenu menu_tela_pedidos = new JMenu("Tela de Pedidos");
		menu_tela_pedidos.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		menu_tela_pedidos.setIcon(new ImageIcon("src/Icon/ico_tela.png"));
		menu_tela_pedidos.setForeground(SystemColor.menuText);
		menuBar.add(menu_tela_pedidos);
		
		JMenuItem tela = new JMenuItem("Tela");
		tela.setIcon(new ImageIcon("src/Icon/ico_tela.png"));
		tela.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		tela.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				screen = new ScreenOrders();
				outface.add(screen);
				screen.setVisible(true);
			}
		});
		menu_tela_pedidos.add(tela);
		
		
		JMenu menu_sair = new JMenu("Sair");
		menu_sair.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		menu_sair.setIcon(new ImageIcon("src/Icon/ico_sair.png"));
		menu_sair.setForeground(SystemColor.menuText);
		menuBar.add(menu_sair);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Sair");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		mntmNewMenuItem.setIcon(new ImageIcon("src/Icon/ico_sair.png"));
		menu_sair.add(mntmNewMenuItem);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		getContentPane().setLayout(new GridLayout());
		outface = new OutFace("src/Icon/outface.jpg");
		getContentPane().add(outface);
	}
}
