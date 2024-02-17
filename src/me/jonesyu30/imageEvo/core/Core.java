package me.jonesyu30.imageEvo.core;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import me.jonesyu30.imageEvo.core.gui.ImagePanel;
import me.jonesyu30.imageEvo.core.gui.ImageScrollable;
import me.jonesyu30.imageEvo.gui.CoreGui;
import me.jonesyu30.imageEvo.utils.QuickSort;
import me.jonesyu30.imageEvo.utils.Timer;
import me.jonesyu30.imageEvo.utils.Utils;

public class Core {

	private ArrayList<GenImage> genImages = new ArrayList<GenImage>();
	private ArrayList<ImagePanel> imagePanes;
	private final int NUMBER_OF_IMAGES = 500;
	private final int NUMBER_OF_SHAPES = 300;

	private int originWidth, originHeight;
	private int scaledWidth, scaledHeight;

	private final int MAX_IMAGES_SHOWN = 10;
	private CoreGui coreGui;
	private ImageScrollable scrollPane;

	Timer timer;

	public Core(int width, int height, Image img) {
		coreGui = new CoreGui(width, height, NUMBER_OF_IMAGES);
		scrollPane = coreGui.getScrollPane();
	}

	public void mainLoop() {
		new Thread(new Runnable() {
			public void run() {
				try {
					while (genImages.get(0).getNoOfShapes() < NUMBER_OF_SHAPES) {

						addPatterns();
						for (int j = 0; j < 100; j++) {// 100
							calDiff();
							killHalf();
							drawPatterns();
						}
						float percent = (float) genImages.get(0).getNoOfShapes() * 100 / (float) NUMBER_OF_SHAPES;
						System.out.println(String.format("%.2f%%", percent));
					}
				} finally {
					end();
				}
			}
		}).start();
	}

	public void start(BufferedImage OriginImg) {
		originWidth = OriginImg.getWidth();
		originHeight = OriginImg.getHeight();
		int imageMaxHeight = OriginImg.getHeight()/2;
		int imageMaxWidth = OriginImg.getWidth()/2;

		BufferedImage scaledOriginImg = Utils.scaleImage(OriginImg, imageMaxWidth, imageMaxHeight);
		scaledWidth = scaledOriginImg.getWidth();
		scaledHeight = scaledOriginImg.getHeight();

		scrollPane.createImgPanels(MAX_IMAGES_SHOWN, scaledWidth, scaledHeight);
		for (int i = 0; i < NUMBER_OF_IMAGES; i++) {
			genImages.add(new GenImage(scaledOriginImg, i));
		}

		imagePanes = scrollPane.getImgPanels();

		mainLoop();
		timer = new Timer();
		timer.start();
	}

	private void end() {
		System.out.println("ENDED");
		System.out.printf("Time used: ");
		Timer.printTime(timer.getTime());

		GenImage finalResult = genImages.get(0);

		drawPatterns();

		Utils.createVid(finalResult.getShapes(), finalResult.getImg(), originWidth, originHeight);
//		Utils.createVideo(finalResult.getShapes(), finalResult.getImg(), originWidth, originHeight);

		System.out.printf("Time used: ");
		Timer.printTime(timer.getTime());
	}

	int count;

	public void addPatterns() {
		count = 0;
		genImages.forEach(i -> {
			i.addPattern();
			i.setID(count);
			count += 1;
		});
	}

	public void drawPatterns() {
		int loopTime = Math.min(imagePanes.size(), NUMBER_OF_IMAGES);
		for (int i = 0; i < loopTime; i++) {
			imagePanes.get(i).setImage(genImages.get(i).getImg());
		}
	}

	public void calDiff() {
		genImages.forEach(i -> {
			if (i.getIsShapeRemoved())
				return;
			i.findDifference();
		});

	}

	public void killHalf() {
		QuickSort.quickSort(genImages, 0, genImages.size() - 1);
		int HALF_NUMBER = NUMBER_OF_IMAGES / 2;
		for (int i = 0; i < HALF_NUMBER; i++) {
			GenImage x = genImages.get(i).clone();
			x.changeLittle();
			genImages.set(i + HALF_NUMBER, x);
		}
	}

	public CoreGui getGui() {
		return coreGui;
	}
}
