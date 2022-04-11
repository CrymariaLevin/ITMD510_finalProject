package application;

/**
 * Final project for ITMD510.
 * JavaFX app for login page
 * @author Mingyi Li
 */

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Main extends Application {

	public static Stage stage; // set global stage object!!!

	@Override
	public void start(Stage primaryStage) {
		try {
			stage = primaryStage;
			//AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("/views/LoginView.fxml"));
		    FXMLLoader loader = new FXMLLoader(Main.class.getResource("/views/LoginView.fxml"));
	        AnchorPane root = (AnchorPane) loader.load();
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			stage.setTitle("Login View");
			stage.setScene(scene);
			stage.show();

		} catch (Exception e) {
			System.out.println("Error occured while inflating view: " + e);
		}

	}

	public static void main(String[] args) {
		launch(args);
	}
}
