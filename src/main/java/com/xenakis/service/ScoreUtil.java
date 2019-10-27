package com.xenakis.service;

import com.xenakis.model.Score;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ScoreUtil extends DatabaseUtil {

    private static List<Score> selectScoresSql(String sql) {
        Connection conn = DatabaseUtil.connect();
        ResultSet rs;
        List<Score> scoreList = new ArrayList<>();

        try {
            PreparedStatement statement  = conn.prepareStatement(sql);
            rs = statement.executeQuery();

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

    private static List<Score> selectScores() {
        String sql = "SELECT * FROM scores";
        return selectScoresSql(sql);
    }

    private static List<Score> selectScores(int chapterId) {
        String sql = "SELECT * FROM scores WHERE chapterId = " + chapterId;
        return selectScoresSql(sql);
    }

    private static List<Score> selectScores(int chapterId, int categoryId) {
        String sql = "SELECT * FROM scores WHERE chapterId = " + chapterId + " AND categoryId = " + categoryId;
        return selectScoresSql(sql);
    }

    public static List<Score> getScoreList(int chapterId, int categoryId) {

        List<Score> scoreList;

        if(chapterId == 0) {
            scoreList = ScoreUtil.selectScores();
        }
        else if(categoryId == 0) {
            scoreList = ScoreUtil.selectScores(chapterId);
        }
        else {
            scoreList = ScoreUtil.selectScores(chapterId, categoryId);
        }

        return scoreList;
    }

    public static void insertScore(String username, String time, String date, int score, int chapterId, int categoryId) {
        String sql = "INSERT INTO scores(username, time, date, score, chapterId, categoryId) VALUES(?,?,?,?,?,?)";
        Connection conn = null;
        try {
            conn = DatabaseUtil.connect();
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, username);
            statement.setString(2, time);
            statement.setString(3, date);
            statement.setInt(4, score);
            statement.setInt(5, chapterId);
            statement.setInt(6, categoryId);
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
