package application;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ScrollEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class Controller implements Initializable{
	
	@FXML
	private ImageView importedImageView;
	@FXML
	private ScrollPane metaScrollPane;
	@FXML
	private TextArea commandPrompt;
	
	private File importedFile;
	private ImageHandler imageHandler;
	
	//No args constructor
	public Controller() {
		
	}
	
	@SuppressWarnings("unused")
	@FXML
	private void ImportFile(ActionEvent e) {
		FileChooser fileImporter = new FileChooser();
		fileImporter.setTitle("Open Image File");
		importedFile = fileImporter.showOpenDialog(new Stage());
		if(importedFile == null) {
			System.err.println("Unable to open the file.");
			return;
		}
		imageHandler.setImportedFile(importedFile);
		System.out.printf("New file imported %s\n", importedFile.toURI().toString());
		Image importedImage = new Image(importedFile.toURI().toString());
		importedImageView.setImage(importedImage);	
		imageHandler.determineImageType();
	}
	
	@FXML
	private void CloseImportedFile(ActionEvent e) {
		//TODO find a way to close the file
		importedImageView.setImage(null);
		imageHandler.closeFile();
	}
	
	public void appendText(String str) {
	    Platform.runLater(() -> commandPrompt.appendText(str));
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		OutputStream out = new OutputStream() {
	        @Override
	        public void write(int b) throws IOException {
	            appendText(String.valueOf((char) b));
	        }
	    };
	    System.setOut(new PrintStream(out, true));
	}
	
	//Getters and setters
	
	public void setImageHandler(ImageHandler imageHandler) {
		this.imageHandler = imageHandler;
	}
}

