package com.xenakis.service;

import org.apache.log4j.Logger;
import java.sql.*;

public abstract class DatabaseUtil {

    static final Logger logger = Logger.getLogger(DatabaseUtil.class);

    static Connection connect() {

        String db_path = "jdbc:sqlite::resource:com/xenakis/database/database.db";
        Connection conn = null;

        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }

        try {
            conn = DriverManager.getConnection(db_path);
//            logger.info("Connection to SQLite has been established.");
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        return conn;
    }

    static void closeConnection(Connection conn) {
        try {
            if (conn != null) {
                conn.close();
//                logger.info("Connection to SQLite has been closed.");
            }
        } catch (SQLException ex) {
            logger.error(ex.getMessage());
        }
    }

}
