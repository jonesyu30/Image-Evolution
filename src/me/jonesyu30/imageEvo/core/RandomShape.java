package me.jonesyu30.imageEvo.core;

import java.awt.Color;

public class RandomShape implements Cloneable{

	public int x, y, width, height;
	public Color color;
	public boolean isOutOfBounds;
	private int boundWidth, boundHeight;

	public RandomShape(int imgWidth, int imgHeight) {
		boundWidth = imgWidth;
		boundHeight = imgHeight;
		width = (int) (Math.random() * imgWidth);
		height = (int) (Math.random() * imgWidth);
		x = (int) (Math.random() * imgWidth) - width/2;
		y = (int) (Math.random() * imgHeight) - height/2;
		isOutOfBounds = false;
		color = new Color((float) Math.random(), (float) Math.random(), (float) Math.random());
	}

	public void changeLittle(int degree) {
		x += Math.random() * degree-degree/2;
		y += Math.random() * degree-degree/2;
		width += Math.random() * degree-degree/2;
		height += Math.random() * degree-degree/2;
		float red = manageOverFlow((float) (Math.random()*degree + color.getRed()), 255);
		float green = manageOverFlow((float) (Math.random()*degree + color.getGreen()), 255);
		float blue = manageOverFlow((float) (Math.random()*degree + color.getBlue()), 255);
		color = new Color(red/255, green/255, blue/255);
		calOutOfBounds();
	}
	private void calOutOfBounds() {
		if(x+width < 0 || x>boundWidth || y+height < 0 || y>boundHeight || width < 0 || height < 0) {
			isOutOfBounds = true;
			return;
		}
		isOutOfBounds = false;
	}
	private float manageOverFlow(float x, int max) {
		if(x > max) {
			return max - (x-max);
		}
		return x;
	}
	public RandomShape clone() {
		try {
			RandomShape i = (RandomShape) super.clone();
			i.boundHeight = boundHeight;
			i.boundWidth = boundWidth;
//			RandomShape i = new RandomShape(boundWidth, boundHeight);
			i.width = width;
			i.height = height;
			i.x = x;
			i.y = y;
			i.color = new Color(color.getRGB());
			return i;
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
