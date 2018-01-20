package application;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.*;
import java.util.Arrays;
import java.util.Arrays.*;


public class ImageHandler {
	private File importedFile;
	private byte[] byteData;
	private byte[] bytePNGHeader = {(byte)0x89, (byte)0x50, (byte)0x4E, (byte)0x47,
									(byte)0x0D, (byte)0x0A, (byte)0x1A, (byte)0x0A};
	private byte[] byteGIFHeader = {(byte)0x47, (byte)0x49, (byte)0x46};
	private byte[] byteJPEGHeader = {(byte)0xFF, (byte)0xD8};
	private byte[] byteHeader = new byte[3];
	
	private final String pngHeader = "89 50 4e 47 0d 0a 1a 0a";
	private final String gifHeader = "47 49 46";
	private final String jpegHeader = "FF D8 FF";

	public ImageHandler() {
		
	}
	
	public void determineImageType() {
		Path importedPath = Paths.get(importedFile.getPath());
		try {
			byteData = Files.readAllBytes(importedPath);
		} catch (IOException e) {
			System.err.println("Byte data could not be read from file...");
			e.printStackTrace();
		}
		//Check if PNG
		if(Arrays.equals(Arrays.copyOf(byteData, 8), bytePNGHeader)) {
			//if true then this file has a png signature
			System.out.println("File has png signature.");
		} else if (Arrays.equals(Arrays.copyOf(byteData, 3), byteGIFHeader)){
			System.out.println("File has gif signature.");
			String version = null;
			try {
				version = new String(Arrays.copyOfRange(byteData, 0, 6), "UTF-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.printf("Version: %s\n", version);
			
			
		} else if (Arrays.equals(Arrays.copyOf(byteData, 2), byteJPEGHeader)) {
			System.out.println("File has jpeg signature");
		} else {
			System.out.println("File signature could not be determined...");
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
