package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;


public class Main extends Application {
	
	FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/MainWindow.fxml"));
	
	@Override
	public void start(Stage primaryStage) {
		try {
			Parent view = loader.load();
			Scene scene = new Scene(view,400,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setTitle("MetaPortrait");
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}