/**
 * Final Project for ITMD510.
 * Program to create a account book app to store and operate data with different users's privilege through database, display with JavaFX.
 * @author: Li Mingyi Student, File Name: RecordFXModel.java
 * This file represents Model class for a Record in SceneBuilder type (Property);
 */

package models;

import java.time.LocalDateTime;

import javafx.beans.property.*;

public class RecordFXModel {

//    private final IntegerProperty rid;
    private final StringProperty rid;
    private final StringProperty location;
    private final StringProperty transaction;
    private final DoubleProperty amount;
    private final StringProperty memo;
//    private final IntegerProperty uid;
//    private final IntegerProperty tid;
    private final StringProperty account;
    private final StringProperty type;
    private final ObjectProperty<LocalDateTime> date;

    /**
     * Default constructor.
     */
    public RecordFXModel() {
        this(null, null, null, null, null, null, null, null
        );
    }

    /**
     * Constructor with some initial data.
     *
     * @param date
     * @param transaction
     * @param amount
     */
    public RecordFXModel(String rid, LocalDateTime date, String transaction, Double amount, String location, String account, String type, String memo) {
//        this.rid = new SimpleIntegerProperty(rid);
        this.rid = new SimpleStringProperty(rid);
        this.date = new SimpleObjectProperty<LocalDateTime>(date);
        this.transaction = new SimpleStringProperty(transaction);
        this.amount = new SimpleDoubleProperty(amount);

        // Data that wont show in TableView
        this.location = new SimpleStringProperty(location);
//        this.uid = new SimpleIntegerProperty(uid);
//        this.tid = new SimpleIntegerProperty(tid);
        this.account = new SimpleStringProperty(account);
        this.type = new SimpleStringProperty(type);
        this.memo = new SimpleStringProperty(memo);
//        this.date = new SimpleObjectProperty<LocalDate>(LocalDate.of(2022, 3, 21));
    }

    public String getLocation() {
        return location.get();
    }

    public StringProperty locationProperty() {
        return location;
    }

    public void setLocation(String location) {
        this.location.set(location);
    }

    public String getRid() {
        return rid.get();
    }

    public StringProperty ridProperty() {
        return rid;
    }

    public void setRid(String rid) {
        this.rid.set(rid);
    }

    public String getTransaction() {
        return transaction.get();
    }

    public StringProperty transactionProperty() {
        return transaction;
    }

    public void setTransaction(String transaction) {
        this.transaction.set(transaction);
    }

    public double getAmount() {
        return amount.get();
    }

    public DoubleProperty amountProperty() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount.set(amount);
    }

    public String getMemo() {
        return memo.get();
    }

    public StringProperty memoProperty() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo.set(memo);
    }

    public String getAccount() {
        return account.get();
    }

    public StringProperty accountProperty() {
        return account;
    }

    public void setAccount(String account) {
        this.account.set(account);
    }

    public String getType() {
        return type.get();
    }

    public StringProperty typeProperty() {
        return type;
    }

    public void setType(String type) {
        this.type.set(type);
    }

    public LocalDateTime getDate() {
        return date.get();
    }

    public ObjectProperty<LocalDateTime> dateProperty() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date.set(date);
    }
}
