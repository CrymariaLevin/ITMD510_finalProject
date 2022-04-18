/**
 * Final Project for ITMD510.
 * Program to create a account book app to store and operate data with different users's privilege through database, display with JavaFX.
 * @author: Li Mingyi Student, File Name: UsersModel.java
 * This file represents Model class for a User object;
 */

package models;


import static utils.MD5.getMD5String;

public class UsersModel {
    private String username;
    private String password;
    private int is_admin;

    public UsersModel() {
    }

    public UsersModel(String username, String password, int is_admin) {
        this.username = username;
        this.password = password;
        this.is_admin = is_admin;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
//         encryption password string with md5
        String password_md5 = getMD5String(password);
        return password_md5;
    }

    public void setPassword(String password) {
//        String password_md5 = getMD5String(password);
        this.password = password;
    }

    public int getIs_admin() {
        return is_admin;
    }

    public void setIs_admin(int is_admin) {
        this.is_admin = is_admin;
    }
}
