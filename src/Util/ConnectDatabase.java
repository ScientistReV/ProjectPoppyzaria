package Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

import org.gjt.mm.mysql.Driver;



public class ConnectDatabase {
	private final String URL = "jdbc:mysql://localhost:3306/pizzaria";
	private final String Driver = "org.gjt.mm.mysql.Driver";
	private final String User = "root";
	private final String Password = "";
	private static Connection connect;
	
	public ConnectDatabase() {
		try {
			connect = DriverManager.getConnection(URL, User, Password);
			connect.setAutoCommit(false);
		}
		catch(Exception e){
			JOptionPane.showMessageDialog(null, "Erro ao Conectar com o banco", "Erro", 0);
		}
	}
	
	public static Connection getConnection() {
		if(connect == null) {
			new ConnectDatabase();
		}
		return connect;
	}
	
	public static void closedConnect() {
		try {
			if(!connect.isClosed()) {
				connect.close();
			}
		}
		catch(SQLException ex){
			Logger.getLogger(ConnectDatabase.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
}


