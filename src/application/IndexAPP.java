/**
 Program using an MVC format to accomplish a family account book.
 Programmer: Li Mingyi Student, File Name: IndexAPP.java
 This file is to create an observable list of Records for the index page.
 */

package application;

import DAO.DaoModel;
import controllers.IndexController;
import controllers.RecordEditDialogController;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import models.RecordFXModel;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class IndexAPP extends Application {

    private Stage primaryStage; // set global stage object!!!
    private BorderPane rootLayout;

    private ObservableList<RecordFXModel> recordsData = FXCollections.observableArrayList();

    public IndexAPP(){
//        get data from database
        initData();
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;

//        System.out.println("init complete");
        showIndexView();
    }

//    retrieve data from database
    public void initData(){
        DaoModel dao = new DaoModel();
        ResultSet rs = dao.retrieveRecords();
        try {
            while (rs.next()) {
                String rid = rs.getString("rid");
                LocalDateTime date = LocalDateTime.parse(rs.getString("date"), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                String transaction = rs.getString("transaction");
                Double amount = rs.getDouble("amount");
                String type = rs.getString("typename");
                String location = rs.getString("location");
                String account = rs.getString("username");
                String memo = rs.getString("memo");

                recordsData.add(new RecordFXModel(rid, date, transaction, amount, location, account, type, memo));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("get "+ recordsData.size() + " item(s) of data.");

    }

//    Returns the data as an observable list of RecordFXModel.
    public ObservableList<RecordFXModel> getRecordsData() {
        return recordsData;
    }



    /**
     * Shows the index overview inside the root layout.
     */
    public void showIndexView() {
        try {
            // Load index overview.
//            FXMLLoader loader = new FXMLLoader();
//            loader.setLocation(IndexAPP.class.getResource("/views/IndexViewAdmin.fxml"));
//            AnchorPane indexView = (AnchorPane) loader.load();

            FXMLLoader loader = new FXMLLoader(IndexAPP.class.getResource("/views/IndexViewAdmin.fxml"));
            AnchorPane indexView = (AnchorPane) loader.load();
            // Show the scene containing the root
            Scene scene = new Scene(indexView);
            primaryStage.setTitle("Admin View");
            primaryStage.setScene(scene);
            primaryStage.show();

            // Set person overview into the center of root layout.
//            rootLayout.setCenter(indexView);

            // Give the controller access to the main app.
            IndexController controller = loader.getController();
            controller.setIndexAPP(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


//    Returns the main stage.
    public Stage getPrimaryStage() {
        return primaryStage;
    }

    /**
     * Opens a dialog to edit details for the specified record. If the user
     * clicks OK, the changes are saved into the provided record object and true
     * is returned.
     *
     * @param recordfx the RecordFXModel object to be edited
     * @return true if the user clicked OK, false otherwise.
     */
    public boolean showRecordEditDialog(RecordFXModel recordfx) {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(IndexAPP.class.getResource("/views/RecordEditDialog.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit Record");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the record into the controller.
            RecordEditDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setRecord(recordfx);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

}
