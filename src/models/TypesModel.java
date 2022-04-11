/**
 * Final Project for ITMD510.
 * Program to create a account book app to store and operate data with different users's privilege through database, display with JavaFX.
 * @author: Li Mingyi Student, File Name: TypesModel.java
 * This file represents Model class for a Type ;
 */

package models;

public class TypesModel {
    private String typename;
    private String transaction;

    public TypesModel() {
    }

    public TypesModel(String typename, String transaction) {
        this.typename = typename;
        this.transaction = transaction;
    }

    public String getTypename() {
        return typename;
    }

    public void setTypename(String typename) {
        this.typename = typename;
    }

    public String getTransaction() {
        return transaction;
    }

    public void setTransaction(String transaction) {
        this.transaction = transaction;
    }
}
