package com.xenakis.service;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import com.xenakis.ImagInLexis;
import javafx.scene.image.Image;

public class ImageUtil extends DatabaseUtil {

	private static final HashMap<String, Image> images = new HashMap<>();

	private static String getImagePath(String name) throws Exception {
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

	public static Image getImage(String name){

		Image image = ImageUtil.images.get(name);

		if(image == null) {
			String path;
			try {
				path = ImageUtil.getImagePath(name);
				URL resource = ImagInLexis.class.getResource(path);
				image = new Image(resource.toString());
				ImageUtil.images.put(name, image);
				ImageUtil.logger.info("Add image with name = " + name);
			}
			catch (Exception e) {
				ImageUtil.logger.error(e);
			}
		}
		return image;
	}
}
