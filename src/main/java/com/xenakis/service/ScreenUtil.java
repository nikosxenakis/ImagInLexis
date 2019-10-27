package com.xenakis.service;

import com.xenakis.model.ScreenData;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ScreenUtil extends DatabaseUtil {

    public static ArrayList<ScreenData> getScreens(int screenTypeId) {
        ResultSet rs;
        String sql = "SELECT * FROM screens WHERE screenTypeId=" + screenTypeId;
        ArrayList<ScreenData> screenDataArray = new ArrayList<>();

        Connection conn = DatabaseUtil.connect();

        try {
            rs = DatabaseUtil.execute(conn, sql);
            while(rs.next()) {
                screenDataArray.add(new ScreenData(
                    rs.getInt("id"),
                    rs.getInt("screenTypeId"),
                    rs.getString("name"),
                    rs.getString("path")
                ));
            }
        } catch (Exception e) {
            logger.error("getScreens error");
            logger.error(e.getMessage());
        }
        DatabaseUtil.closeConnection(conn);
        return screenDataArray;
    }
}
