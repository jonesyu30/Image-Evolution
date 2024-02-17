package me.jonesyu30.imageEvo.gui;

import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import me.jonesyu30.imageEvo.core.Core;
import me.jonesyu30.imageEvo.utils.Utils;

public class Display extends JFrame{
	private static final long serialVersionUID = 1L;
	
	private int WIDTH, HEIGHT;
	

	// constructor
	public Display(BufferedImage bufferedImage, Core core, int width, int height)
    {

		HEIGHT = height;
		WIDTH = width;
		
		JPanel left = new JPanel();
		JLabel picLabel = new JLabel(new ImageIcon(bufferedImage));
		Image dimg = Utils.scaleImage(bufferedImage, WIDTH, HEIGHT);
		picLabel.setIcon(new ImageIcon(dimg));
		left.add(picLabel);
		add(left, BorderLayout.WEST);
	
 
        //create new button
		JPanel right = new JPanel();
        JButton button = new JButton("START");
        right.add(button);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
        		remove(left);
        		remove(right);
        		add(core.getGui());
        		revalidate();
        		repaint();
            	core.start(bufferedImage);
            }
        });
        add(right, BorderLayout.EAST);
        
        
        // finally
        setResizable(false);
		setSize(WIDTH, HEIGHT);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
	}
}
