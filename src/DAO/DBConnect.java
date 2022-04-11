/*
   Lab4
   Program to use a database to store then present Loan analysis information from early data BankRecords objects(Lab2).
   Programmer: Li Mingyi Student, File Name: DBConnect.java
   This file allows an object to connect / close a database connection;
*/

package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnect {
//    // Code database URL
//    static final String DB_URL = "jdbc:mysql://www.papademas.net:3307/510fp?autoReconnect=true&useServerPrepStmts=true&useSSL=false&serverTimezone=UTC";
//    // Database credentials
//    static final String USER = "fp510", PASS = "510";

    static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/itmd?useServerPrepStmts=true&useSSL=false&serverTimezone=UTC";
    // Database credentials
    static final String USER = "root", PASS = "580225";

    protected Connection connection;

    public Connection connect() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }

    public DBConnect() {
        try {
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (SQLException e) {
            System.out.println("Error creating connection to database: " + e);
            System.exit(-1);
        }
    }

}
