package me.jonesyu30.imageEvo.gui;

import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import me.jonesyu30.imageEvo.core.gui.ImageScrollable;

public class CoreGui extends JPanel {

	private static final long serialVersionUID = 1L;
	private JPanel downFrame;
	private ImageScrollable scrollPane;

	int count;

	public CoreGui(int width, int height, int noOfImg) {
		
		scrollPane = new ImageScrollable(width, height/2);
		add(scrollPane, BorderLayout.WEST);

		downFrame = new JPanel();
		downFrame.add(new JLabel("ASD"));
		add(downFrame, BorderLayout.SOUTH);
	}

	public JPanel getPictureJFrame() {
		return scrollPane.getScrollPane();
	}
	public ImageScrollable getScrollPane() {
		return scrollPane;
	}

}
