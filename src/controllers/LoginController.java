/**
 * Final project for ITMD510.
 * Login controller for login page
 * @author Mingyi Li
 */
package controllers;

import application.IndexAPP;
import application.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import models.LoginModel;

public class LoginController {

	@FXML
	private TextField txtUsername;

	@FXML
	private PasswordField txtPassword;

	@FXML
	private Label lblError;

    private LoginModel model;


	public LoginController() {
		model = new LoginModel();
	}

	public void login() {

		lblError.setText("");
		String username = this.txtUsername.getText();
		String password = this.txtPassword.getText();

		// Validations
		if (username == null || username.trim().equals("")) {
			lblError.setText("Username Cannot be empty or spaces");
			return;
		}
		if (password == null || password.trim().equals("")) {
			lblError.setText("Password Cannot be empty or spaces");
			return;
		}
		if (username == null || username.trim().equals("") && (password == null || password.trim().equals(""))) {
			lblError.setText("User name / Password Cannot be empty or spaces");
			return;
		}

		// authentication check
		checkCredentials(username, password);

	}

	public void checkCredentials(String username, String password) {
		Boolean isValid = model.getCredentials(username, password);
		if (!isValid) {
			lblError.setText("User does not exist or the password is wrong!");
			return;
		}
		try {
			AnchorPane root;
			if (model.isAdmin()) {
				// If user is admin, inflate admin view
//				root = (AnchorPane)  FXMLLoader.load(getClass().getResource("/views/IndexViewAdmin.fxml"));
//				Main.stage.setTitle("Admin View");
				IndexAPP indexAPP = new IndexAPP();
				indexAPP.start(Main.stage);
			} else {
				// If user is customer, inflate customer view
				root = (AnchorPane) FXMLLoader.load(getClass().getResource("/views/IndexViewClient.fxml"));
				// ***Set user ID acquired from db****
				int user_id = 1;  //hard coded for testing purposes only!!
				//ClientController.setUser(user_id);
				Main.stage.setTitle("Client View");
			}
//			Scene scene = new Scene(root);
//			Main.stage.setScene(scene);
		} catch (Exception e) {
			System.out.println("Error occured while inflating view: " + e);
		}

	}

	public void exit(){
	    System.exit(0);
    }

}
