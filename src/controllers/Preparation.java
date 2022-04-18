/**
 * Final Project for ITMD510.
 * Program to create a account book app to store and operate data with different users's privilege through database, display with JavaFX.
 * @author: Li Mingyi Student, File Name: Preparation.java
 * This file is to create some test data for the app;
 */

package controllers;

import DAO.DaoModel;
import models.RecordsModel;
import models.TypesModel;
import models.UsersModel;

import java.util.ArrayList;

public class Preparation{
    public static void main(String[] args) {
        DaoModel dao = new DaoModel();
//        create table for project
        dao.createTable();
//        insert data to tables
//        insert users
        System.out.println("Start inserting user data...");
        ArrayList<UsersModel> user_obj = new ArrayList<>();
        user_obj.add(new UsersModel("Wife","family001",1));
        user_obj.add(new UsersModel("Husband","family002",0));
        dao.insertUsers(user_obj);
        System.out.println("================data insert complete=========================");

        System.out.println("Start inserting type data...");
        ArrayList<TypesModel> type_obj = new ArrayList<>();
        type_obj.add(new TypesModel("Food","Expense"));
        type_obj.add(new TypesModel("Property costs","Expense"));
        type_obj.add(new TypesModel("Clothes","Expense"));
        type_obj.add(new TypesModel("Salary","Income"));
        type_obj.add(new TypesModel("Stock","Income"));
        type_obj.add(new TypesModel("accident","Income"));
        dao.insertTypes(type_obj);
        System.out.println("================data insert complete=========================");

        System.out.println("Start inserting record data...");
        ArrayList<RecordsModel> record_obj = new ArrayList<>();
        record_obj.add(new RecordsModel("2022-04-04 20:00:10",55.5,"DD Street","StarBuk",1,2));
        record_obj.add(new RecordsModel("2022-04-04 16:00:10",107.8,"Wallmart","",1,1));
        record_obj.add(new RecordsModel("2022-04-05 17:00:50",77.5,"AA Square","meeting",3,2));
        record_obj.add(new RecordsModel("2022-04-01 12:30:44",12.5,"District Building","Home",2,1));
        record_obj.add(new RecordsModel("2022-04-07 07:00:12",50000.0,"Workplace","April",4,2));
        record_obj.add(new RecordsModel("2022-04-06 20:00:10",98.5,"YHC","",1,2));
        record_obj.add(new RecordsModel("2022-04-04 16:00:10",59.9,"Yonikolo","shirts",3,1));
        record_obj.add(new RecordsModel("2022-04-05 17:00:50",77.5,"YHC","sea food",1,1));
        record_obj.add(new RecordsModel("2022-04-03 12:30:44",117.5,"YHC","Pizza",1,1));
        record_obj.add(new RecordsModel("2022-03-27 07:00:12",1314.52,"Home","anniversary",6,1));
        dao.insertRecords(record_obj);
        System.out.println("================data insert complete=========================");
    }


}
