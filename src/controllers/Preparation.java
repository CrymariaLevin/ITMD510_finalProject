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
        dao.insertTypes(type_obj);
        System.out.println("================data insert complete=========================");

        System.out.println("Start inserting record data...");
        ArrayList<RecordsModel> record_obj = new ArrayList<>();
        record_obj.add(new RecordsModel("2022-04-04 20:00:10",55.5,"DD Street","StarBuk",1,2));
        record_obj.add(new RecordsModel("2022-04-04 16:00:10",107.8,"Wallmart","",1,1));
        record_obj.add(new RecordsModel("2022-04-05 17:00:50",77.5,"AA Square","meeting",3,2));
        record_obj.add(new RecordsModel("2022-04-01 12:30:44",12.5,"District Building","Home",2,1));
        record_obj.add(new RecordsModel("2022-04-07 07:00:12",50000.0,"Workplace","April",4,2));
        dao.insertRecords(record_obj);
        System.out.println("================data insert complete=========================");
    }


}
