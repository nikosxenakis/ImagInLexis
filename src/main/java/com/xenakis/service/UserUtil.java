package com.xenakis.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserUtil extends DatabaseUtil {

    public static void insertUser(String name) {
        String sql = "INSERT INTO users(name, active) VALUES(?, 0)";
        Connection conn = null;
        try{
            conn = DatabaseUtil.connect();
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, name);
            logger.info(statement.toString());
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }

        DatabaseUtil.closeConnection(conn);
    }

//    public static String deactivateUsers(){
//        String sql = "UPDATE users(name, active) VALUES(?, 0)";
//        Connection conn = null;
//        try{
//            conn = Database.connect();
//            PreparedStatement statement = conn.prepareStatement(sql);
//            statement.setString(1, name);
//            logger.info(statement.toString());
//            statement.executeUpdate();
//        } catch (SQLException e) {
//            logger.error(e.getMessage());
//        }
//
//        Database.closeConnection(conn);
//    }

    public static String getActiveUser(){
        Connection conn = DatabaseUtil.connect();
        ResultSet rs;
        String sql;
        String user = "unknown user";

        sql = "SELECT name FROM users WHERE active=1";
        logger.info("Active user: " + user);

        try {
            PreparedStatement statement  = conn.prepareStatement(sql);
            rs = statement.executeQuery();

            try {
                while (rs.next()) {
                    user = rs.getString("user");
                    logger.info("Active user: " + user);
                    break;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        DatabaseUtil.closeConnection(conn);
        return user;
    }
}
