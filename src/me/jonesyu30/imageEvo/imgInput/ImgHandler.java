package me.jonesyu30.imageEvo.imgInput;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImgHandler {
	
	private BufferedImage img;
	
	public ImgHandler(File file) {
		try {
		    img = ImageIO.read(file);
		} catch (IOException e) {
		}
	}
	public BufferedImage getImage() {
		return img;
	}
}
