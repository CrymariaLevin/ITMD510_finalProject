/**
 * Final Project
 * Program to create a account book app to store and operate data with different users's privilege through database, display with JavaFX.
 * @author: Li Mingyi Student, File Name: DaoModel.java
 * This file represents Data Access Object, defines CRUD (Create Read Update Delete) operations;
 */

package DAO;

import models.RecordFXModel;
import models.RecordsModel;
import models.TypesModel;
import models.UsersModel;
import utils.DateUtil;

import java.sql.*;
import java.util.ArrayList;

public class DaoModel {

    //Declare DB objects
    DBConnect conn = null;
    Statement stmt = null;
    PreparedStatement pstmt = null;

    // constructor
    public DaoModel() { //create db object instance
        conn = new DBConnect();
    }

    /**
     * CREATE TABLE FOR THE FINAL PROJECT
     *
     * @param
     */
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

    /**
     * Insert into M_Li_fp_users TABLE
     *
     * @param user_array the UsersModel object list to be inserted
     */
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

    /**
     * Insert into M_Li_fp_types TABLE
     *
     * @param type_array the TypesModel object list to be inserted
     */
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

    /**
     * Insert into M_Li_fp_records TABLE
     *
     * @param record_array the Record object list to be inserted
     */
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
     * Get uid by username in database
     *
     * Returns -1 if the String could not be converted.
     *
     * @param username the account name as String
     * @return the account name or null if it could not be converted
     */
    public int retrieveAccountID(String username) {
        int uid;
        try {
            stmt = conn.connect().createStatement();
            String sql = "SELECT uid \n" +
                    "FROM m_li_fp_users WHERE username = '" + username + "'";
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                uid = rs.getInt("uid");
            }else{
                uid = -1;
            }
            stmt.close();
            conn.connect().close();
            return uid;
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    /**
     * Get tid by typename in database
     *
     * Returns -1 if the String could not be converted.
     *
     * @param typename the typename as String
     * @return the account name or null if it could not be converted
     */
    public int retrieveTypeID(String typename, String transaction) {
        int tid;
        try {
            stmt = conn.connect().createStatement();
            String sql = "SELECT tid \n" +
                    "FROM m_li_fp_types WHERE typename = '" + typename + "'" +
                    " and `TRANSACTION` =  '" + transaction + "'";
//            System.out.println(sql);
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                tid = rs.getInt("tid");
//                create new type
            }else if((transaction == "Expense" || transaction == "Income") && typename.length() > 0){
                ArrayList<TypesModel> new_type = new ArrayList<>();
                new_type.add(new TypesModel(typename,transaction));
                insertTypes(new_type);
                tid = retrieveTypeID(typename,transaction);
            }
            else{
                tid = -1;
            }
            stmt.close();
            conn.connect().close();
            return tid;
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
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

    /**
     * Insert into M_Li_fp_records TABLE
     *
     * @param recordfx the RecordFXModel object to be inserted
     */
    public void insertNewRecords(RecordFXModel recordfx){
        int tid = retrieveTypeID(recordfx.getType(),recordfx.getTransaction());
        int uid = retrieveAccountID(recordfx.getAccount());
        try {
            Connection connection = conn.connect();
//            using affairs with rollbacks and commits
            connection.setAutoCommit(false);
            System.out.println("Inserting records into the table M_Li_fp_records with new data...");
            // The SQL string used to insert into database table
            String sql = "INSERT INTO M_Li_fp_records (`date`,`amount`,`location`, `memo`, `tid`, `uid`)VALUES (?,?,?,?,?,?)";
//            using prepared statements when inserting records
            pstmt = connection.prepareStatement(sql);

            // insert data into database table
            pstmt.setString(1, DateUtil.format(recordfx.getDate()));
            pstmt.setDouble(2, recordfx.getAmount());
            pstmt.setString(3, recordfx.getLocation());
            pstmt.setString(4, recordfx.getMemo());
            if (tid > 0)
                pstmt.setInt(5,tid);
            if (uid > 0)
                pstmt.setInt(6, uid);
            pstmt.executeUpdate();
            try {
                connection.commit();
            } catch (SQLException se){
                connection.rollback();
                System.out.println("SQL Error,roll back");
            }
            pstmt.close();
            System.out.println("Inserting records into table M_Li_fp_records with new data successfully");
            connection.setAutoCommit(true);
            connection.close();
        } catch (Exception se) {
            System.out.println("Insert Error:\n");
            se.printStackTrace();
        }
    }

    /**
     * Update M_Li_fp_records TABLE
     *
     * @param recordfx the RecordFXModel object to be updated
     */
    public void updateEditRecords(RecordFXModel recordfx){
        int tid = retrieveTypeID(recordfx.getType(),recordfx.getTransaction());
        int uid = retrieveAccountID(recordfx.getAccount());
        try {
            Connection connection = conn.connect();
//            using affairs with rollbacks and commits
            connection.setAutoCommit(false);
            System.out.println("updating records into the table M_Li_fp_records with edited data...");
            // The SQL string used to update database table
            String sql = "UPDATE M_Li_fp_records SET `date` = ?,`amount` = ?,`location` = ?, `memo` = ?, `tid` = ?, `uid` = ?" +
                    " WHERE rid = ?";
//            using prepared statements when inserting records
            pstmt = connection.prepareStatement(sql);

            // insert data into database table
            pstmt.setString(1, DateUtil.format(recordfx.getDate()));
            pstmt.setDouble(2, recordfx.getAmount());
            pstmt.setString(3, recordfx.getLocation());
            pstmt.setString(4, recordfx.getMemo());
            if (tid > 0)
                pstmt.setInt(5,tid);
            if (uid > 0)
                pstmt.setInt(6, uid);
            pstmt.setInt(7, Integer.parseInt(recordfx.getRid()));
//            System.out.println(pstmt);
            pstmt.executeUpdate();
            try {
                connection.commit();
            } catch (SQLException se){
                connection.rollback();
                System.out.println("SQL Error,roll back");
            }
            pstmt.close();
            System.out.println("Update records into table M_Li_fp_records with edit data successfully");
            connection.setAutoCommit(true);
            connection.close();
        } catch (Exception se) {
            System.out.println("Insert Error:\n");
            se.printStackTrace();
        }
    }
}
