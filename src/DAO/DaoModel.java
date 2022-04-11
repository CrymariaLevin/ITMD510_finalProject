/*
   Lab4
   Program to use a database to store then present Loan analysis information from early data BankRecords objects(Lab2).
   Programmer: Li Mingyi Student, File Name: DaoModel.java
   This file represents Data Access Object, defines CRUD (Create Read Update Delete) operations;
*/

package DAO;

import models.RecordsModel;
import models.TypesModel;
import models.UsersModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DaoModel {

    //Declare DB objects
    DBConnect conn = null;
    Statement stmt = null;
    PreparedStatement pstmt = null;

    // constructor
    public DaoModel() { //create db object instance
        conn = new DBConnect();
    }

    // CREATE TABLE FOR THE FINAL PROJECT
    public void createTable() {
        try {
            // Open a connection
            System.out.println("Connecting to database to create Table...");
            // Execute create query
            System.out.println("Creating table in given database 510fp...");
            stmt = conn.connect().createStatement();
            System.out.println("Connected database 510fp successfully");

            // create table users
            String sql_user = "CREATE TABLE IF NOT EXISTS M_Li_fp_users (" + // yourFirstinitial_First4LettersOfYourLastName_tab ]
                    " uid INTEGER not NULL AUTO_INCREMENT, " +
                    " username VARCHAR(20) UNIQUE , " +
                    " password VARCHAR(32), " +
                    " is_admin INT (3), " +
                    " PRIMARY KEY ( uid ))";
            stmt.executeUpdate(sql_user);
            System.out.println("Created table M_Li_fp_users successfully");

            // create table types
            String sql_type = "CREATE TABLE IF NOT EXISTS M_Li_fp_types (" + // yourFirstinitial_First4LettersOfYourLastName_tab ]
                    " tid INTEGER not NULL AUTO_INCREMENT, " +
                    " typename VARCHAR(20) UNIQUE , " +
                    " `transaction` VARCHAR(20), " +
                    " PRIMARY KEY ( tid ))";
            stmt.executeUpdate(sql_type);
            System.out.println("Created table M_Li_fp_types successfully");

            // create table records
            String sql_record = "CREATE TABLE IF NOT EXISTS M_Li_fp_records (" + // yourFirstinitial_First4LettersOfYourLastName_tab ]
                    " rid INTEGER not NULL AUTO_INCREMENT, " +
                    " date datetime, " +
                    " amount numeric(8,2), " +
                    " location VARCHAR(50), " +
                    " memo VARCHAR(50), " +
                    " tid INTEGER(10), " +
                    " uid INTEGER(10), " +
                    " PRIMARY KEY ( rid ))";
            stmt.executeUpdate(sql_record);
            System.out.println("Created table M_Li_fp_records successfully");
            stmt.close();
            conn.connect().close(); //close db connection
        }catch (SQLException se) { // Handle errors for JDBC
            se.printStackTrace();
        }
    }

    // INSERT INTO DATABASE FOR USERS TABLE
    public void insertUsers(ArrayList<UsersModel> user_array){
        try {
            Connection connection = conn.connect();
            // start a query process
            System.out.println("Inserting records into the table M_Li_fp_users...");
//            using affairs with rollbacks and commits
            connection.setAutoCommit(false);
            // The SQL string used to insert into database table
            String sql = "INSERT INTO M_Li_fp_users (`username`,`password`,`is_admin`)VALUES (?,?,?)";
//            using prepared statements when inserting records
            pstmt = connection.prepareStatement(sql);
            // Include all object data to the database table
            for (UsersModel usersModel : user_array) {
                // insert each data (id, income, pep) into database table
                pstmt.setString(1, usersModel.getUsername());
                pstmt.setString(2, usersModel.getPassword());
                pstmt.setInt(3, usersModel.getIs_admin());
                pstmt.addBatch();
//                pstmt.executeUpdate();
            }
            pstmt.executeBatch();
            try {
                connection.commit();
            } catch (SQLException se){
                connection.rollback();
                System.out.println("SQL Error,roll back");
            }
            pstmt.close();
            System.out.println("Inserting records into table M_Li_fp_users successfully");
            connection.setAutoCommit(true);
            connection.close();
        } catch (Exception e) {
            System.out.println("Insert Error:\n");
            e.printStackTrace();
        }
    }

    // INSERT INTO DATABASE FOR TYPES TABLE
    public void insertTypes(ArrayList<TypesModel> type_array){
        try {
            Connection connection = conn.connect();
            // start a query process
            System.out.println("Inserting records into the table M_Li_fp_types...");
//            using affairs with rollbacks and commits
            connection.setAutoCommit(false);
            // The SQL string used to insert into database table
            String sql = "INSERT INTO M_Li_fp_types (`typename`,`transaction`)VALUES (?,?)";
//            using prepared statements when inserting records
            pstmt = connection.prepareStatement(sql);
            // Include all object data to the database table
            for (TypesModel typesModel : type_array) {
                // insert each data (id, income, pep) into database table
                pstmt.setString(1, typesModel.getTypename());
                pstmt.setString(2, typesModel.getTransaction());
                pstmt.addBatch();
//                pstmt.executeUpdate();
            }
            pstmt.executeBatch();
            try {
                connection.commit();
            } catch (SQLException se){
                connection.rollback();
                System.out.println("SQL Error,roll back");
            }
            pstmt.close();
            System.out.println("Inserting records into table M_Li_fp_types successfully");
            connection.setAutoCommit(true);
            connection.close();
        } catch (Exception e) {
            System.out.println("Insert Error:\n");
            e.printStackTrace();
        }
    }

    // INSERT INTO DATABASE FOR RECORDS TABLE
    public void insertRecords(ArrayList<RecordsModel> record_array){
        try {
            Connection connection = conn.connect();
            // start a query process
            System.out.println("Inserting records into the table M_Li_fp_records...");
//            using affairs with rollbacks and commits
            connection.setAutoCommit(false);

            // The SQL string used to insert into database table
            String sql = "INSERT INTO M_Li_fp_records (`date`,`amount`,`location`, `memo`, `tid`, `uid`)VALUES (?,?,?,?,?,?)";
//            using prepared statements when inserting records
            pstmt = connection.prepareStatement(sql);
            // Include all object data to the database table
            for (RecordsModel recordsModel : record_array) {
                // insert each data (id, income, pep) into database table
                pstmt.setString(1, recordsModel.getDate());
                pstmt.setDouble(2, recordsModel.getAmount());
                pstmt.setString(3, recordsModel.getLocaction());
                pstmt.setString(4, recordsModel.getMemo());
                pstmt.setInt(5, recordsModel.getTid());
                pstmt.setInt(6, recordsModel.getUid());
                pstmt.addBatch();
            }
            pstmt.executeBatch();
            try {
                connection.commit();
            } catch (SQLException se){
                connection.rollback();
                System.out.println("SQL Error,roll back");
            }
            pstmt.close();
            System.out.println("Inserting records into table M_Li_fp_records successfully");
            connection.setAutoCommit(true);
            connection.close();
        } catch (Exception se) {
            System.out.println("Insert Error:\n");
            se.printStackTrace();
        }
    }

    /**
     * RETRIEVE RECORDS FROM M_Li_fp_records TABLE FOR DISPLAY
     *
     * @return ResultSet for index page display
     */
    public ResultSet retrieveRecords() {
        ResultSet rs = null;
        System.out.println("Retrieving records from the table M_Li_fp_records");
        try {
            stmt = conn.connect().createStatement();
            String sql = "SELECT rid, amount, location, date, memo, t.`transaction`, t.typename, u.username \n" +
                        "FROM M_Li_fp_records r LEFT JOIN m_li_fp_types t ON r.tid = t.tid\n" +
                        "LEFT JOIN m_li_fp_users u ON r.uid = u.uid\n" +
                        "ORDER BY date DESC ";
            rs = stmt.executeQuery(sql);
//            stmt.close();
            conn.connect().close();
            System.out.println("Retrieving records success!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    /**
     * RETRIEVE username FROM M_Li_fp_users TABLE
     *
     * @param uid the id to be returned the corresponding username
     * @return ResultSet for username
     */
    public ResultSet retrieveAccount(int uid) {
        ResultSet rs = null;
        try {
            stmt = conn.connect().createStatement();
            String sql = "SELECT username \n" +
                    "FROM m_li_fp_users WHERE uid = " + uid;
            rs = stmt.executeQuery(sql);
//            stmt.close();
            conn.connect().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    /**
     * RETRIEVE typename FROM M_Li_fp_types TABLE
     *
     * @param tid the id to be returned the corresponding typename
     * @return ResultSet for typename
     */
    public ResultSet retrieveType(int tid) {
        ResultSet rs = null;
        try {
            stmt = conn.connect().createStatement();
            String sql = "SELECT typename \n" +
                    "FROM m_li_fp_types WHERE tid = " + tid;
            rs = stmt.executeQuery(sql);
//            stmt.close();
            conn.connect().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    /**
     * DELETE RECORD FROM M_Li_fp_records TABLE
     *
     * @param rid the id to be deleted
     */
    public void deleteRecord(String rid) {
        System.out.println(rid);
        try {
            stmt = conn.connect().createStatement();
            String sql = "DELETE FROM m_li_fp_records " +
                    "WHERE rid = " + rid;
            stmt.executeUpdate(sql);
            stmt.close();
            conn.connect().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
