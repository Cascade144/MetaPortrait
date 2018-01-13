package application;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;

public class ImageHandler {
	private File importedFile;
	private byte[] byteData;
	
	private final String pngHeader = "89 50 4e 47 0d 0a 1a 0a";

	public ImageHandler() {
		
	}
	
	public void determineImageType() {
		Path importedPath = Paths.get(importedFile.getPath());
		try {
			byteData = Files.readAllBytes(importedPath);
		} catch (IOException e) {
			System.err.println("Byte data could not be read from file.");
			e.printStackTrace();
		}
		StringBuilder byteHeader = new StringBuilder();
		//Check if png
		for (int i = 0; i < 10; i++) {
			byteHeader.append(String.format("%02x", byteData[i]));
		}
		if(pngHeader.contentEquals(byteHeader)) {
			//if true then this file has a png signature
			System.out.println("File has png signature.");
		} else {
			System.out.println("File does not have a png signature.");
		}
	}
	
	public void closeFile() {
		importedFile = null;
	}
	
	//Getters and setters
	public File getImportedFile() {
		return importedFile;
	}

	public void setImportedFile(File importedFile) {
		this.importedFile = importedFile;
	}
}
