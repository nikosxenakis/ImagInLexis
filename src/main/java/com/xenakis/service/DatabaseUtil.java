package com.xenakis.service;

import com.xenakis.model.Score;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DatabaseUtil extends Database {

    public static void insertUser(String name) {
        String sql = "INSERT INTO users(name, active) VALUES(?, 0)";
        Connection conn = null;
        try{
            conn = Database.connect();
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, name);
            logger.info(statement.toString());
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }

        Database.closeConnection(conn);
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
        Connection conn = Database.connect();
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
        Database.closeConnection(conn);
        return user;
    }

    public static String getImagePath(String name){
        Connection conn = Database.connect();
        ResultSet rs;
        String sql;
        String path = null;

        sql = "SELECT * FROM images WHERE name='" + name + "'";
        logger.info("Image: " + name);

        try {
            PreparedStatement statement  = conn.prepareStatement(sql);
            rs = statement.executeQuery();

            try {
                while (rs.next()) {
                    path = rs.getString("path");
                    logger.info("Path: " + path);
                    break;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        Database.closeConnection(conn);
        return path;
    }

    public static void insertScore(String username, String time, String date, int score, String chapter, String category) {
        String sql = "INSERT INTO scores(username, time, date, score, chapter, category) VALUES(?,?,?,?,?,?)";
        Connection conn = null;
        try{
            conn = Database.connect();
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, username);
            statement.setString(2, time);
            statement.setString(3, date);
            statement.setInt(4, score);
            statement.setString(5, chapter);
            statement.setString(6, category);
            logger.info(statement.toString());
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }

        Database.closeConnection(conn);
    }

    public static void removeAllScores() {
        String sql = "DELETE FROM scores;";
        Connection conn = null;
        try{
            conn = Database.connect();
            PreparedStatement statement = conn.prepareStatement(sql);
            logger.info(statement.toString());
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }

        Database.closeConnection(conn);
    }

    public static List<Score> selectScores(String chapterName, String categoryName){
        Connection conn = Database.connect();
        ResultSet rs;
        String sql;

        if(chapterName == null) {
            sql = "SELECT * FROM scores";
        }
        else if(categoryName == null) {
            sql = "SELECT * FROM scores WHERE chapter == ?";
        }
        else {
            sql = "SELECT * FROM scores WHERE chapter == ? AND category == ?";
        }

        List<Score> scoreList = new ArrayList<>();

        try {
            PreparedStatement statement  = conn.prepareStatement(sql);

            if(chapterName == null) {
            }
            else if(categoryName == null) {
                statement.setString(1,chapterName);
            }
            else {
                statement.setString(1,chapterName);
                statement.setString(2,categoryName);
            }

            rs    = statement.executeQuery();

            try {
                while (rs.next()) {
                    String username = rs.getString("username");
                    int score = rs.getInt("score");
                    String time = rs.getString("time");
                    String date = rs.getString("date");
                    scoreList.add( new Score(username,score,date,time) );
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        Database.closeConnection(conn);
        return scoreList;
    }

}
