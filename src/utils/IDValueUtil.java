/**
 * Helper functions for handling ids' corresponding data.
 * Includiung transfer and validate
 * @author Mingyi Li
 */

package utils;

import DAO.DaoModel;

import java.sql.ResultSet;

public class IDValueUtil {

    /**
     * Converts a String with uid
     *
     * Returns null if the String could not be converted.
     *
     * @param uid the date as String
     * @return the account name or null if it could not be converted
     */
    public static String conver_uid(int uid) {
        DaoModel dao = new DaoModel();
        ResultSet rs = dao.retrieveAccount(uid);
        try {
            String username = rs.getString("username");
            return username;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Converts a String with tid
     *
     * Returns null if the String could not be converted.
     *
     * @param tid the date as String
     * @return the account name or null if it could not be converted
     */
    public static String conver_tid(int tid) {
        DaoModel dao = new DaoModel();
        ResultSet rs = dao.retrieveType(tid);
        try {
            String typename = rs.getString("typename");
            return typename;
        } catch (Exception e) {
            return null;
        }
    }

}
