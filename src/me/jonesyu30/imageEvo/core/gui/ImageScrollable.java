package me.jonesyu30.imageEvo.core.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;

public class ImageScrollable extends JScrollPane {

	private static final long serialVersionUID = 1L;

	private JPanel picFrame;

	ArrayList<ImagePanel> imgPanels = new ArrayList<ImagePanel>();

	public ImageScrollable(int width, int height) {
		setPreferredSize(new Dimension(width, height));
		setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		
		picFrame = new JPanel();
//		picFrame.setPreferredSize(new Dimension(width, 1000));
		getViewport().add(picFrame);
	}
	
	public void createImgPanels(int noOfPanels, int scaledWidth, int scaledHeight) {
		for(int i = 0; i < noOfPanels; i++) {
			ImagePanel x = new ImagePanel(scaledWidth, scaledHeight);
			x.setBorder(new LineBorder(Color.black));
			picFrame.add(x);
			imgPanels.add(x);
		}
	}

	public ArrayList<ImagePanel> getImgPanels(){
		return imgPanels;
	}
	
	public JPanel getScrollPane() {
		return picFrame;
	}

}
