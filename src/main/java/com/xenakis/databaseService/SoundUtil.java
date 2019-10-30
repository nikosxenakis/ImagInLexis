package com.xenakis.databaseService;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SoundUtil extends DatabaseUtil {


    public static String getSoundPath(String name) throws Exception {
        ResultSet rs;
        String sql = "SELECT * FROM sounds WHERE name='" + name + "'";
        String path;
        Connection conn = DatabaseUtil.connect();

        try {
            rs = DatabaseUtil.execute(conn, sql);
            rs.next();
            path = rs.getString("path");
        } catch (SQLException e) {
            throw new Exception("Sound with name = " + name + " was not found");
        }
        DatabaseUtil.closeConnection(conn);
        return path;
    }


}
