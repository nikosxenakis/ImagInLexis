package com.xenakis.service;

import com.xenakis.model.Chapter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ChapterUtil extends DatabaseUtil {

    private static Chapter getChapter(String name) throws Exception {
        ResultSet rs;
        String sql = "SELECT * FROM chapters WHERE name='" + name + "'";
        Chapter chapter;

        Connection conn = DatabaseUtil.connect();

        try {
            PreparedStatement statement  = conn.prepareStatement(sql);
            rs = statement.executeQuery();
            rs.next();
            chapter = new Chapter(
                    rs.getString("name"),
                    rs.getString("greekName")
            );
        } catch (Exception e) {
            throw new Exception("Category with name = " + name + " was not found");
        }
        DatabaseUtil.closeConnection(conn);
        return chapter;
    }

    public static String getChapterGreekName(String name) {
        Chapter chapter;
        try {
            chapter = ChapterUtil.getChapter(name);
        }
        catch (Exception e) {
            return "-";
        }
        return chapter.getGreekName();
    }

    public static List<Chapter> getChapterList(){
        List<Chapter> chapterList = new ArrayList<>();
        ResultSet rs;
        String sql = "SELECT * FROM chapters";
        Connection conn = DatabaseUtil.connect();

        try {
            rs = DatabaseUtil.execute(conn, sql);
            while(rs.next()) {
                chapterList.add(new Chapter(
                        rs.getString("name"),
                        rs.getString("greekName")
                ));
            }
        } catch (Exception e) {
        }
        DatabaseUtil.closeConnection(conn);
        return chapterList;
    }
}