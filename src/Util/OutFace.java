package Util;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;

//Serve para manter a foto de fundo da tela
public class OutFace extends JDesktopPane {
	private Image image;
	
	public OutFace(String image) {
		this.image = new ImageIcon(image).getImage();
		//this.image = new ImageIcon(Home.class.getResource(image)).getImage();
	}
	
	@Override
	public void paintComponent(Graphics graphic) {
		graphic.drawImage(image, 0, 0, getWidth(), getHeight(), this);
	}
}
