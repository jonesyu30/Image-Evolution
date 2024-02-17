package me.jonesyu30.imageEvo;

import me.jonesyu30.imageEvo.core.Core;
import me.jonesyu30.imageEvo.gui.Display;
import me.jonesyu30.imageEvo.imgInput.FileSelect;
import me.jonesyu30.imageEvo.imgInput.ImgHandler;

public class Main {
	private final static int WIDTH = 720;
	private final static int HEIGHT = 480;
	public static void main(String args[]) {
		FileSelect file = new FileSelect();
		ImgHandler handler = new ImgHandler(file.getFile());
		Core core = new Core(WIDTH, HEIGHT, handler.getImage());
		new Display(handler.getImage(), core, WIDTH, HEIGHT);
		
		
		System.out.println(handler.getImage());
		
	}
}
