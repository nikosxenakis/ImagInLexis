package com.xenakis.service;

import com.xenakis.model.Score;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ScoreUtil extends DatabaseUtil {

    private static List<Score> selectScores(String chapterName, String categoryName) {
        Connection conn = DatabaseUtil.connect();
        ResultSet rs;
        String sql;

// TODO what if just category
        if(chapterName == null) {
            sql = "SELECT * FROM scores";
        }
        else if(categoryName == null) {
            sql = "SELECT * FROM scores WHERE chapter = ?";
        }
        else {
            sql = "SELECT * FROM scores WHERE chapter = ? AND category = ?";
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
        DatabaseUtil.closeConnection(conn);
        return scoreList;
    }

    public static List<Score> getScoreList(String chapterName, String categoryName){

        List<Score> scoreList;

        if(chapterName.equals("Όλα")) {
            scoreList = ScoreUtil.selectScores(null, null);
        }
        else if(categoryName.equals("Όλα")) {
            scoreList = ScoreUtil.selectScores(chapterName, null);
        }
        else {
            scoreList = ScoreUtil.selectScores(chapterName, categoryName);
        }

        return scoreList;
    }

    public static void insertScore(String username, String time, String date, int score, String chapter, String category) {
        String sql = "INSERT INTO scores(username, time, date, score, chapter, category) VALUES(?,?,?,?,?,?)";
        Connection conn = null;
        try{
            conn = DatabaseUtil.connect();
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

        DatabaseUtil.closeConnection(conn);
    }

    public static void removeAllScores() {
        String sql = "DELETE FROM scores;";
        Connection conn = null;
        try{
            conn = DatabaseUtil.connect();
            PreparedStatement statement = conn.prepareStatement(sql);
            logger.info(statement.toString());
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }

        DatabaseUtil.closeConnection(conn);
    }
}
