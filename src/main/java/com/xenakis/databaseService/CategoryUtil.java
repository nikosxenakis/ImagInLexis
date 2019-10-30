package com.xenakis.databaseService;

import com.xenakis.model.Category;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CategoryUtil extends DatabaseUtil {

    private static Category getCategory(String name) throws Exception {
        ResultSet rs;
        String sql = "SELECT * FROM categories WHERE name='" + name + "'";
        Category category;

        Connection conn = DatabaseUtil.connect();

        try {
            PreparedStatement statement  = conn.prepareStatement(sql);
            rs = statement.executeQuery();
            rs.next();
            category = new Category(
                    rs.getInt("id"),
                    rs.getInt("chapterId"),
                    rs.getString("name"),
                    rs.getString("greekName")
            );
        } catch (Exception e) {
            throw new Exception("Category with name = " + name + " was not found");
        }
        DatabaseUtil.closeConnection(conn);
        return category;
    }

    public static String getCategoryGreekName(String name) {
        Category category;
        try {
            category = CategoryUtil.getCategory(name);
        }
        catch (Exception e) {
            return "-";
        }
        return category.getGreekName();
    }

    public static List<Category> getCategoryList() {
        List<Category> categoryList = new ArrayList<>();
        Connection conn = DatabaseUtil.connect();
        String sql = "SELECT * FROM categories";
        ResultSet rs;

        try {
            rs = DatabaseUtil.execute(conn, sql);
            while(rs.next()) {
                categoryList.add(new Category(
                        rs.getInt("id"),
                        rs.getInt("chapterId"),
                        rs.getString("name"),
                        rs.getString("greekName")
                ));
            }
        } catch (Exception e) {
        }
        DatabaseUtil.closeConnection(conn);
        return categoryList;
    }

    public static List<Category> getCategoryList(int chapterId) {
        List<Category> categoryList = new ArrayList<>();
        Connection conn = DatabaseUtil.connect();
        ResultSet rs;

        String sql = "SELECT * FROM categories WHERE chapterId = '" + chapterId + "'";

        logger.info("Get category list of chapterId : " + chapterId);
        try {
            rs = DatabaseUtil.execute(conn, sql);
            while(rs.next()) {
                categoryList.add(new Category(
                    rs.getInt("id"),
                    rs.getInt("chapterId"),
                    rs.getString("name"),
                    rs.getString("greekName")
                ));
            }
        } catch (Exception e) {
        }
        DatabaseUtil.closeConnection(conn);
        return categoryList;
    }

    public static Category getCategoryFromGreekName(String categoryGreekName) {
        ResultSet rs;
        String sql = "SELECT * FROM categories WHERE greekName='" + categoryGreekName + "'";
        Category category = null;

        Connection conn = DatabaseUtil.connect();
        logger.info("Get category with greekName: " + categoryGreekName);

        try {
            PreparedStatement statement  = conn.prepareStatement(sql);
            rs = statement.executeQuery();
            rs.next();
            category = new Category(
                    rs.getInt("id"),
                    rs.getInt("chapterId"),
                    rs.getString("name"),
                    rs.getString("greekName")
            );
        } catch (Exception e) {
        }
        DatabaseUtil.closeConnection(conn);
        return category;
    }
}
