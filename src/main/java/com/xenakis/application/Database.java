package com.xenakis.application;

import com.xenakis.screenController.ScoreTableScreenController;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Database {


    public static void testDB() {
//		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
//		DateFormat timeFormat = new SimpleDateFormat("HH:mm");
//
//        Date date = new Date();
//        Date time = new Date();
//		String strDate = dateFormat.format(date);
//		String strTime = timeFormat.format(time);
//
//		Database.insert(ImagInLexis.userName, strTime, strDate, "0%", "Αναγνώριση", "Φρούτα");
//		Database.insert(ImagInLexis.userName, strTime, strDate, "2%", "Αναγνώριση", "Χρώματα");
    }

    public static Connection connect() {

        String db_path = "jdbc:sqlite::resource:com/xenakis/database/database.db";
        System.out.println(db_path);


        Connection conn = null;

        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            // db parameters
            // create a connection to the database
            conn = DriverManager.getConnection(db_path);
            System.out.println("Connection to SQLite has been established.");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public static void closeConnection(Connection conn) {
        try {
            if (conn != null) {
                conn.close();
                System.out.println("Connection to SQLite has been closed.");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void insert(String username, String time, String date, int score, String chapter, String category) {
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
            System.out.println(statement.toString());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        Database.closeConnection(conn);
    }

    public static void removeAll() {
        String sql = "DELETE FROM scores;";
        Connection conn = null;
        try{
            conn = Database.connect();
            PreparedStatement statement = conn.prepareStatement(sql);
            System.out.println(statement.toString());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        Database.closeConnection(conn);
    }

//    public void getCapacityGreaterThan(double capacity){
//        String sql = "SELECT id, name, capacity "
//                + "FROM warehouses WHERE capacity > ?";
//
//        try (Connection conn = this.connect();
//             PreparedStatement pstmt  = conn.prepareStatement(sql)){
//
//            // set the value
//            pstmt.setDouble(1,capacity);
//            //
//            ResultSet rs  = pstmt.executeQuery();
//
//            // loop through the result set
//            while (rs.next()) {
//                System.out.println(rs.getInt("id") +  "\t" +
//                        rs.getString("name") + "\t" +
//                        rs.getDouble("capacity"));
//            }
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());
//        }
//    }

    public static List<ScoreTableScreenController.Score> select(String chapterName, String categoryName){
        Connection conn = Database.connect();
        ResultSet rs = null;
        String sql = null;

        if(chapterName == null) {
            sql = "SELECT * FROM scores";
        }
        else if(categoryName == null) {
            sql = "SELECT * FROM scores WHERE chapter == ?";
        }
        else {
            sql = "SELECT * FROM scores WHERE chapter == ? AND category == ?";
        }

        List<ScoreTableScreenController.Score> scoreList = new ArrayList<>();

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
                    scoreList.add( new ScoreTableScreenController.Score(username,score,date,time) );
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        Database.closeConnection(conn);
        return scoreList;
    }
}