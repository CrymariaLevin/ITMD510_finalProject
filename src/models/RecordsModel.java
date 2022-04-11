/**
 * Final Project for ITMD510.
 * Program to create a account book app to store and operate data with different users's privilege through database, display with JavaFX.
 * @author: Li Mingyi Student, File Name: RecordModel.java
 * This file represents Model class for a Record Object;
 */

package models;

import java.util.ArrayList;
import java.util.List;

public class RecordsModel {
    private String date;
    private Double amount;
    private String locaction;
    private String memo;
    private int tid;
    private int uid;


    public RecordsModel() {
    }

    public RecordsModel(String date, Double amount, String locaction, String memo, int tid, int uid) {
        this.date = date;
        this.amount = amount;
        this.locaction = locaction;
        this.memo = memo;
        this.tid = tid;
        this.uid = uid;
    }

    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getLocaction() {
        return locaction;
    }

    public void setLocaction(String locaction) {
        this.locaction = locaction;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }
}
