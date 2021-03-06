/**
 * Final project for ITMD510.
 * Program to create a account book app to store and operate data with different users's privilege through database, display with JavaFX.
 * Programmer: Li Mingyi Student, File Name: Main.java
 * this file is to create an login page
 */

package application;

import controllers.IndexController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

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

	/**
	 * Opens the page of index, with account's name.
	 *
	 * @param username the account's name to pass to the index
	 * @return
	 */
	public void showIndexScene(String username, boolean visible) {
		try {
			IndexAPP indexAPP = new IndexAPP();
			// Set the record into the controller.
			indexAPP.setUsername(username);
			indexAPP.setVisible(visible);
//			System.out.println("Main username input");
			indexAPP.start(Main.stage);

		} catch (Exception e) {
			e.printStackTrace();
//			return false;
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
