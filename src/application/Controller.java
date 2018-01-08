package application;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class Controller implements Initializable{
	
	@FXML
	private ImageView ImportedImageView;
	
	@SuppressWarnings("unused")
	@FXML
	private void ImportFile(ActionEvent e) {
		FileChooser fileImporter = new FileChooser();
		fileImporter.setTitle("Open Image File");
		File imageFile = fileImporter.showOpenDialog(new Stage());
		if(imageFile == null) {
			return;
		}
		//Open file in image viewer for user
		Image importedImage = new Image(imageFile.toURI().toString());
		ImportedImageView.setImage(importedImage);
	}
	
	@FXML
	private void CloseImportedFile(ActionEvent e) {
		//TODO find a way to close the file
		ImportedImageView.setImage(null);
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
	
	
}

