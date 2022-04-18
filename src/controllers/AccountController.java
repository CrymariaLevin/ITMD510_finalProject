/**
 * Final Project for ITMD510.
 * Program to create a account book app to store and operate data with different users's privilege through database, display with JavaFX.
 * @author: Li Mingyi Student, File Name: AccountController.java
 * This file represents Dialog controller to the account operation;
 */

package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.control.ChoiceBox;
import models.UsersModel;

import java.util.ArrayList;
import java.util.Arrays;

public class AccountController {
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField pwdField;
    @FXML
    private Label lblOperation;
    @FXML
    private ChoiceBox<Integer> privField;
    @FXML
    private Pane panePwd;
    @FXML
    private Pane panePriv;
    @FXML
    private Label lblMessage;

//    private final int privNum;
    private Stage dialogStage;
    private boolean okClicked = false;
    public static UsersModel user = new UsersModel("Client","***",0);
    private String operation;
    private boolean visible_priv;
    private boolean visible_pwd;

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
    }

    /**
     * Sets the stage of this dialog.
     *
     * @param dialogStage
     */
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    /**
     * Sets the operation of this dialog.
     *
     * @param operation
     */
    public void setOperation(String operation) {
        this.operation = operation;
        lblOperation.setText(operation);
    }

    /**
     * Sets the choicebox of this dialog.
     *
     * @param
     */
    public void setChoiceBox() {
        privField.getItems().addAll(0,1);
        privField.getSelectionModel().select(0);//default admin
//        this.privField = choicebox;
    }

    /**
     * Gets the pane's visible login to the page.
     *
     * @param visible_priv,visible_pwd
     */
    public void setVisible(boolean visible_priv, boolean visible_pwd) {
        this.visible_priv = visible_priv;
        panePriv.setVisible(visible_priv);

        this.visible_pwd = visible_pwd;
        panePwd.setVisible(visible_pwd);
    }


    /**
     * Returns true if the user clicked OK, false otherwise.
     *
     * @return
     */
    public boolean isOkClicked() {
        return okClicked;
    }

    /**
     * Called when the user clicks ok.
     */
    @FXML
    private void handleOk() {
        if (isInputValid()) {
            user.setUsername(usernameField.getText());
            user.setPassword(pwdField.getText());
            user.setIs_admin(privField.getValue());
//            System.out.println(user.getUsername()+", " + user.getPassword()+", " + user.getIs_admin());

            okClicked = true;
            dialogStage.close();
        }
    }

    /**
     * Called when the user clicks cancel.
     */
    @FXML
    private void handleCancel() {
        dialogStage.close();
    }

    /**
     * Validates the user input in the text fields.
     *
     * @return true if the input is valid
     */
    private boolean isInputValid() {
        String errorMessage = "";

//        Integer admin_list[] = {1,0};
//        ArrayList<Integer> account_arrays=new ArrayList<>(Arrays.asList(admin_list));


        if (usernameField.getText() == null || usernameField.getText().length() == 0) {
            errorMessage += "No valid account name!\n";
        }
        if ((pwdField.getText() == null || pwdField.getText().length() == 0) && lblOperation.getText().equals("New user")){
            errorMessage += "No valid password!";
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Show the error message.
            lblMessage.setText("Please correct invalid fields!\n" + errorMessage);
            return false;
        }
    }

    public UsersModel getUser() {
        return user;
    }

    public void setUser(UsersModel user) {
        this.user = user;
    }
}
