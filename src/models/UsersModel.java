package models;


import static DAO.MD5.getMD5String;

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
        this.password = password;
    }

    public int getIs_admin() {
        return is_admin;
    }

    public void setIs_admin(int is_admin) {
        this.is_admin = is_admin;
    }
}
