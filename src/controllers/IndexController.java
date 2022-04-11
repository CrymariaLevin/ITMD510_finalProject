/**
 * Final Project for ITMD510.
 * Program to create a account book app to store and operate data with different users's privilege through database, display with JavaFX.
 * @author: Li Mingyi Student, File Name: IndexController.java
 * This file represents index controller for index page;
 */

package controllers;

import DAO.DaoModel;
import application.IndexAPP;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import models.RecordFXModel;
import utils.DateUtil;

import java.time.LocalDateTime;

public class IndexController {

    @FXML
    private TableView<RecordFXModel> recordsTable;
    @FXML
    private TableColumn<RecordFXModel, LocalDateTime> dateColumn;
    @FXML
    private TableColumn<RecordFXModel, String> transColumn;
    @FXML
    private TableColumn<RecordFXModel, Number> amountColumn; // <Double> won't work

    @FXML
    private Label lblDate;

    @FXML
    private Label lblAccount;

    @FXML
    private Label lblTransaction;

    @FXML
    private Label lblType;

    @FXML
    private Label lblLocation;

    @FXML
    private Label lblAmount;

    @FXML
    private Label lblMemo;


    private String rid;

    // Reference to the index application.
    private IndexAPP indexApp;

    public IndexController() {
    }

    /**
     * Initializes the controller class. This method is automatically called
     */
    @FXML
    private void initialize() {
        // Initialize the person table with the 3 columns.
        dateColumn.setCellValueFactory(cellData -> cellData.getValue().dateProperty());
        transColumn.setCellValueFactory(cellData -> cellData.getValue().transactionProperty());
        amountColumn.setCellValueFactory(cellData -> cellData.getValue().amountProperty());

        // Clear person details.
        showRecordDetails(null);

        // Listen for selection changes and show the record details when changed.
        recordsTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showRecordDetails(newValue));
    }

    /**
     * Is called by the index application to give a reference back to itself.
     */
    public void setIndexAPP(IndexAPP indexApp) {
        this.indexApp = indexApp;

        // Add observable list data to the table
        recordsTable.setItems(indexApp.getRecordsData());
    }

    /**
     * Fills all text fields to show details about the each record.
     * If the specified record is null, all text fields are cleared.
     *
     * @param recordfx the record or null
     */
    private void showRecordDetails(RecordFXModel recordfx) {
        if (recordfx != null) {
            // Fill the labels with info from the person object.
            lblDate.setText(DateUtil.format(recordfx.getDate()));
//            lblAccount.setText(IDValueUtil.conver_uid(recordfx.getUid()));
//            lblType.setText(IDValueUtil.conver_tid(recordfx.getTid()));
            lblAccount.setText(recordfx.getAccount());
            lblType.setText(recordfx.getType());
            lblTransaction.setText(recordfx.getTransaction());
            lblLocation.setText(recordfx.getLocation());
            lblAmount.setText(Double.toString(recordfx.getAmount()));
            lblMemo.setText(recordfx.getMemo());
//            record the record id
            this.rid = recordfx.getRid();

        } else {
            // Record is null, remove all the text.
            lblDate.setText("");
            lblAccount.setText("");
            lblTransaction.setText("");
            lblType.setText("");
            lblLocation.setText("");
            lblAmount.setText("");
            lblMemo.setText("");
        }
    }

    /**
     * Called when the user clicks on the delete button.
     */
    @FXML
    private void handleDeleteRecord() {
        int selectedIndex = recordsTable.getSelectionModel().getSelectedIndex();
//        first get the record's id then delete
        String rid_d = recordsTable.getItems().get(selectedIndex).getRid();
        recordsTable.getItems().remove(selectedIndex);
        DaoModel dao = new DaoModel();
        dao.deleteRecord(rid_d);
    }

    /**
     * Called when the user clicks the new button. Opens a dialog to edit
     * details for a new record.
     */
    @FXML
    private void handleNewRecord() {
//        RecordFXModel tempRecord = new RecordFXModel();
        RecordFXModel tempRecord = new RecordFXModel("", LocalDateTime.now(),"Expense",0.0,"","","","");
        boolean okClicked = indexApp.showRecordEditDialog(tempRecord);
        if (okClicked) {
//            now record's data has been edited
            indexApp.getRecordsData().add(tempRecord);
            DaoModel dao = new DaoModel();
            dao.insertNewRecords(tempRecord);
        }
    }

    /**
     * Called when the user clicks the edit button. Opens a dialog to edit
     * details for the selected record.
     */
    @FXML
    private void handleEditRecord() {
        RecordFXModel selectedRecord = recordsTable.getSelectionModel().getSelectedItem();
        if (selectedRecord != null) {
            boolean okClicked = indexApp.showRecordEditDialog(selectedRecord);
            if (okClicked) {
                showRecordDetails(selectedRecord);
                DaoModel dao = new DaoModel();
                dao.updateEditRecords(selectedRecord);
            }

        } else {
            // Nothing selected.
            System.out.println("nothing selected");
        }
    }

}
