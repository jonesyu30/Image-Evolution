package me.jonesyu30.imageEvo.utils;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.jcodec.api.awt.AWTSequenceEncoder;
import org.jcodec.common.io.NIOUtils;
import org.jcodec.common.io.SeekableByteChannel;
import org.jcodec.common.model.Rational;

import com.xuggle.mediatool.IMediaWriter;
import com.xuggle.mediatool.ToolFactory;
import com.xuggle.xuggler.ICodec;
import com.xuggle.xuggler.IRational;

import me.jonesyu30.imageEvo.core.RandomShape;

public class Utils {
	public static BufferedImage scaleImage(BufferedImage bufferedImg, int maxHeight, int maxWidth) {
		float scale = Math.max((float) maxWidth / (float) bufferedImg.getWidth(),
				(float) maxHeight / (float) bufferedImg.getHeight());
		int x = (int) (bufferedImg.getWidth() * scale);
		int y = (int) (bufferedImg.getHeight() * scale);
		
		BufferedImage bi = new BufferedImage(x, y, BufferedImage.TYPE_3BYTE_BGR);
		Graphics g = bi.getGraphics();
		g.drawImage(bufferedImg, 0, 0, x, y, null);
		g.dispose();
		
		return bi;
	}

	public static void createVideo(ArrayList<RandomShape> genShapes, BufferedImage image, int WIDTH, int HEIGHT) {

		// WIDTH = 100; // HEIGHT = 200;

		ArrayList<RandomShape> shapes = new ArrayList<RandomShape>(genShapes);
		shapes.remove(shapes.size() - 1);
		int name = (int) (Math.random()*1000);
		String outputVideo = "./vid/"+String.valueOf(name)+"_video.mp4";

		int frameRate = 20;

		IMediaWriter writer = ToolFactory.makeWriter(outputVideo);

		BufferedImage scaled = Utils.scaleImage(image, WIDTH, HEIGHT);
		writer.addVideoStream(0, 0, ICodec.ID.CODEC_ID_H264, IRational.make(frameRate), scaled.getWidth(),
				scaled.getHeight());

		image = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_3BYTE_BGR);
		Graphics graphics = image.createGraphics();
		long startTime = System.nanoTime();
		int count = 0;
		for (RandomShape shape : shapes) {
			try {

				graphics.setColor(shape.color);
				graphics.fillRect(shape.x, shape.y, shape.width, shape.height);

				scaled = Utils.scaleImage(image, WIDTH, HEIGHT);

				writer.encodeVideo(0, scaled, System.nanoTime() - startTime, TimeUnit.NANOSECONDS);

				Thread.sleep(1000 / frameRate);
				count++;
				System.out.print(count);
				System.out.println(" printing...");
			} catch (Exception e) {
				e.printStackTrace();
//				writer.close();
			}
		}

		System.out.println("videdo created"); // Close the writer to finalize the video file
		writer.close();
	}


	public static BufferedImage copyOfBufImg(BufferedImage bufimg) {
		BufferedImage out = new BufferedImage(bufimg.getWidth(), bufimg.getHeight(), BufferedImage.TYPE_3BYTE_BGR);
		Graphics g = out.createGraphics();
		g.drawImage(bufimg, 0, 0, null);
		g.dispose();
		return out;
	}
	public static byte[] pixelFromBufImg(BufferedImage bufimg) {
		return ((DataBufferByte) bufimg.getRaster().getDataBuffer()).getData();
	}

	public static void createVid(ArrayList<RandomShape> genShapes, BufferedImage image, int WIDTH, int HEIGHT) {
//		WIDTH = 1125;
//		HEIGHT = 2000;
//		System.out.println(WIDTH);
//		System.out.println(HEIGHT);
		SeekableByteChannel out = null;
		try {
			int name = (int) (Math.random()*1000);
			out = NIOUtils.writableFileChannel("./vid/"+String.valueOf(name)+"_output.mp4");

			BufferedImage scaled = Utils.scaleImage(image, WIDTH, HEIGHT);
			image = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_3BYTE_BGR);
			Graphics graphics = image.createGraphics();

			AWTSequenceEncoder encoder = new AWTSequenceEncoder(out, Rational.R(10, 1));
			int count = 0;
			for (RandomShape shape : genShapes) {
				scaled = Utils.scaleImage(image, WIDTH, HEIGHT);
				encoder.encodeImage(scaled);
				graphics.setColor(shape.color);
				graphics.fillRect(shape.x, shape.y, shape.width, shape.height);
				count++;
				System.out.println(count);
				Thread.sleep(1);
			}
			// Finalize the encoding, i.e. clear the buffers, write the header, etc.
			encoder.finish();
			System.out.println("Video generated");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			NIOUtils.closeQuietly(out);
		}
	}
}
