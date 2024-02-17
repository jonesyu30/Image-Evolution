package me.jonesyu30.imageEvo.imgInput;

import java.io.File;

import javax.swing.JFileChooser;

public class FileSelect extends JFileChooser{

	private static final long serialVersionUID = 1L;
	private File file;

	public FileSelect() {
		setCurrentDirectory(new File("./Images"));
		int respose = showSaveDialog(this);
		if(respose == JFileChooser.APPROVE_OPTION) {
			file = new File(getSelectedFile().getAbsolutePath());
		}
	}
	public File getFile() {
		return file;
	}
}
