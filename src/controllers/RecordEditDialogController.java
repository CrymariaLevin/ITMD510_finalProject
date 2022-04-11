/**
 * Final Project for ITMD510.
 * Program to create a account book app to store and operate data with different users's privilege through database, display with JavaFX.
 * @author: Li Mingyi Student, File Name: RecordEditDialogController.java
 * This file represents Dialog controller to edit details of a record;
 */

package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.ChoiceBox;
import models.RecordFXModel;
import utils.DateUtil;

import java.util.ArrayList;
import java.util.Arrays;

public class RecordEditDialogController {
    @FXML
    private TextField dateField;
    @FXML
    private TextField accountField;
    @FXML
    private TextField transactionField;
    @FXML
    private TextField typeField;
    @FXML
    private TextField locationField;
    @FXML
    private TextField amountField;
    @FXML
    private TextField memoField;
    @FXML
    private Label lblError;

    private Stage dialogStage;
    private RecordFXModel recordfx;
    private boolean okClicked = false;

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
     * Sets the record to be edited in the dialog.
     *
     * @param recordfx
     */
    public void setRecord(RecordFXModel recordfx) {
        this.recordfx = recordfx;

        dateField.setText(DateUtil.format(recordfx.getDate()));
        accountField.setText(recordfx.getAccount());
        transactionField.setText(recordfx.getTransaction());
        typeField.setText(recordfx.getType());
        locationField.setText(recordfx.getLocation());
        amountField.setText(Double.toString(recordfx.getAmount()));
        memoField.setPromptText(recordfx.getMemo());
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
            recordfx.setAccount(accountField.getText());
            recordfx.setTransaction(transactionField.getText());
            recordfx.setType(typeField.getText());
            recordfx.setAmount(Double.parseDouble(amountField.getText()));
            recordfx.setLocation(locationField.getText());
            recordfx.setMemo(memoField.getText());
            recordfx.setDate(DateUtil.parse(dateField.getText()));

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

        String account_list[] = {"Husband","Wife"};
        ArrayList<String> account_arrays=new ArrayList<>(Arrays.asList(account_list));

        String transaction_list[] = {"Income","Expense"};
        ArrayList<String> transaction_arrays=new ArrayList<>(Arrays.asList(transaction_list));

        if (accountField.getText() == null || accountField.getText().length() == 0 || !account_arrays.contains(accountField.getText())) {
            errorMessage += "No valid account name! Account don't exist'\n";
        }
        if (transactionField.getText() == null || transactionField.getText().length() == 0 || !transaction_arrays.contains(transactionField.getText())){
            errorMessage += "No valid transaction! Transaction must be 'Income' or 'Expense'";
        }
        if (typeField.getText() == null || typeField.getText().length() == 0) {
            errorMessage += "No valid street!\n";
        }

        if (amountField.getText() == null || amountField.getText().length() == 0) {
            errorMessage += "No valid amount number!\n";
        } else {
            // try to parse the amount into a double.
            try {
                Double.parseDouble(amountField.getText());
            } catch (NumberFormatException e) {
                errorMessage += "No valid amount (must be an double number)!\n";
            }
        }

        if (locationField.getText() == null || locationField.getText().length() == 0) {
            errorMessage += "No valid location!\n";
        }

        if (dateField.getText() == null || dateField.getText().length() == 0) {
            errorMessage += "No valid date type!\n";
        } else {
            if (!DateUtil.validDate(dateField.getText())) {
                errorMessage += "No valid date type. Use the format yyyy-MM-dd HH:mm:ss!\n";
            }
        }
//        if (memoField.getText() == null || memoField.getText().length() == 0) {
//            errorMessage += "No valid memo!\n";
//        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Show the error message.
            lblError.setText("Please correct invalid fields!\n" + errorMessage);
            return false;
        }
    }

}
