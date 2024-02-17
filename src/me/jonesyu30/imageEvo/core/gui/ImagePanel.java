package me.jonesyu30.imageEvo.core.gui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class ImagePanel extends JPanel{

	private static final long serialVersionUID = 1L;
	private BufferedImage bufferedImage;
	
	public ImagePanel(int width, int height) {
		setPreferredSize(new Dimension(width, height));
	}
	public void setImage(BufferedImage img) {
		bufferedImage = img;
		getGraphics().drawImage(img, 0, 0, null);
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
//		if(bufferedImage == null)return;
		g.drawImage(bufferedImage, 0, 0, null);
	}
}
