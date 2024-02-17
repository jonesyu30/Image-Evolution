package me.jonesyu30.imageEvo.core;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import me.jonesyu30.imageEvo.utils.Utils;

public class GenImage implements Cloneable {

	private final BufferedImage originImage;
	private final byte[] originPixels;
	private byte[] generatingImagePixels;
	private BufferedImage settledImage;
	private int id;
	private double diff;
	private ImageIcon icon;
	private final int IMG_WIDTH, IMG_HEIGHT;

	private RandomShape newShape;

	private boolean isShapeRemoved;

	private ArrayList<RandomShape> shapes = new ArrayList<RandomShape>();

	public GenImage(BufferedImage img, int i) {

		id = i;
		
		isShapeRemoved = true;
		
		originImage = img;
		IMG_WIDTH = img.getWidth();
		IMG_HEIGHT = img.getHeight();

//		generatingImage = new BufferedImage(IMG_WIDTH, IMG_HEIGHT, BufferedImage.TYPE_3BYTE_BGR);
		originPixels = Utils.pixelFromBufImg(originImage);
		
		settledImage = new BufferedImage(IMG_WIDTH, IMG_HEIGHT, BufferedImage.TYPE_3BYTE_BGR);
		generatingImagePixels = Utils.pixelFromBufImg(settledImage);
	}

	public void addPattern() {
		if (!isShapeRemoved) {
			
			Graphics g = settledImage.createGraphics();
			g.setColor(newShape.color);
			g.fillRect(newShape.x, newShape.y, newShape.width, newShape.height);
			g.dispose();
			
			generatingImagePixels = Utils.pixelFromBufImg(settledImage); 
			
			shapes.add(newShape);
		}
		newShape = new RandomShape(IMG_WIDTH, IMG_HEIGHT);
		isShapeRemoved = false;
	}

	public ImageIcon getIcon() {
		return icon;
	}

	public double findDifference() {

		BufferedImage generating = Utils.copyOfBufImg(settledImage);
		Graphics g = generating.createGraphics();
		g.setColor(newShape.color);
		g.fillRect(newShape.x, newShape.y, newShape.width, newShape.height);
		generatingImagePixels = Utils.pixelFromBufImg(generating);
		
		diff = 0;
	
		if (originPixels.length != generatingImagePixels.length) {
			System.out.println("ASDASD");
		}

		for (int i = 0; i < generatingImagePixels.length; i++) {
			diff += Math.abs((originPixels[i] & 0xFF) - (generatingImagePixels[i] & 0xFF));
		}
		diff = diff / (IMG_WIDTH * IMG_HEIGHT);
		return diff;
	}

	public int getID() {
		return id;
	}

	public int getNoOfShapes() {
		return shapes.size();
	}

	public double getDiff() {
		return diff;
	}

	public void changeLittle() {

		if (shapes.isEmpty() || isShapeRemoved)
			return;
		
		newShape.changeLittle(30);
		if (newShape.isOutOfBounds) {
//			shapes.remove(shapes.size() - 1);
			isShapeRemoved = true;
		} 
	}

	@Override
	public GenImage clone() {
		try {
			GenImage t = (GenImage) super.clone();

			t.settledImage = Utils.copyOfBufImg(settledImage);

			t.shapes = new ArrayList<RandomShape>(shapes);
			t.newShape = newShape.clone();

			return t;
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return null;
	}

	public ArrayList<RandomShape> getShapes() {
		return shapes;
	}

	public BufferedImage getImg() {
		BufferedImage generatingImage = Utils.copyOfBufImg(settledImage);
		Graphics g = generatingImage.createGraphics();
		g.setColor(newShape.color);
		g.fillRect(newShape.x, newShape.y, newShape.width, newShape.height);
		
		return generatingImage;
	}

	public void setID(int x) {
		id = x;
	}
	public boolean getIsShapeRemoved() {
		return isShapeRemoved;
	}
}
