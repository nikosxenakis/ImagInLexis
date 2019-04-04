package application;

import screenController.ScoreTableScreenController;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Database {

//    private static final String url = "jdbc:sqlite:resources/database/database.db";
//    String url = "jdbc:sqlite::resource:notes_app.db";
//    private static final String url = "jdbc:sqlite::resources:database/database.db";




    public static Connection connect() {

//        String url = "jar:file:/Users/xenis656/Desktop/ImagInLexis.jar!/database/database.db";
    String url = "jdbc:sqlite:resources/database/database.db";

//        url = new URL("jar:file:/Users/xenis656/Desktop/ImagInLexis.jar!/");


        Connection conn = null;

        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            // db parameters
            // create a connection to the database
            conn = DriverManager.getConnection(url);
            System.out.println("Connection to SQLite has been established.");

        } catch (SQLException e) {
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

    public static void insert(String username, String time, String date, String score, String chapter, String category) {
        String sql = "INSERT INTO scores(username, time, date, score, chapter, category) VALUES(?,?,?,?,?,?)";
        Connection conn = null;
        try{
            conn = Database.connect();
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, username);
            statement.setString(2, time);
            statement.setString(3, date);
            statement.setString(4, score);
            statement.setString(5, chapter);
            statement.setString(6, category);
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

        List<ScoreTableScreenController.Score> scoreList = new ArrayList<ScoreTableScreenController.Score>();

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
                    String score = rs.getString("score");
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
