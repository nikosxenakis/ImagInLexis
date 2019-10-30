package com.xenakis.databaseService;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ImageUtil extends DatabaseUtil {

	public static String getImagePath(String name) throws Exception {
		ResultSet rs;
		String sql = "SELECT * FROM images WHERE name='" + name + "'";
		String path;
		Connection conn = DatabaseUtil.connect();

		try {
			rs = DatabaseUtil.execute(conn, sql);
			rs.next();
			path = rs.getString("path");
		} catch (SQLException e) {
			throw new Exception("Image with name = " + name + " was not found");
		}
		DatabaseUtil.closeConnection(conn);
		return path;
	}
}
