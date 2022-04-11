package models;

import DAO.DBConnect;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static DAO.MD5.getMD5String;


public class LoginModel extends DBConnect {

    private Boolean admin;

    public Boolean isAdmin() {
        return admin;
    }
    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }

//    check valid and set the admin field
    public Boolean getCredentials(String username, String password){
        String password_md5 = getMD5String(password);
//        System.out.println(password_md5);
        String query = "SELECT * FROM M_Li_fp_users WHERE username = ? and password = ?;";
        try(PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, username);
            stmt.setString(2, password_md5);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()) {
                setAdmin(rs.getBoolean("is_admin"));
                return true;
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

}
