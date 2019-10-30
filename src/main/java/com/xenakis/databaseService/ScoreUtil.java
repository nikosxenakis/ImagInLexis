package com.xenakis.databaseService;

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

    public static List<Score> getScoreList(int chapterId, int categoryId) {
        String sql;

        if(categoryId != 0) {
            if(chapterId != 0) {
                sql = "SELECT * FROM scores WHERE chapterId = " + chapterId + " AND categoryId = " + categoryId;
            }
            else {
                sql = "SELECT * FROM scores WHERE categoryId = " + categoryId;
            }
        }
        else {
            if(chapterId != 0) {
                sql = "SELECT * FROM scores WHERE chapterId = " + chapterId;
            }
            else {
                sql = "SELECT * FROM scores";
            }
        }
        return selectScoresSql(sql);
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
