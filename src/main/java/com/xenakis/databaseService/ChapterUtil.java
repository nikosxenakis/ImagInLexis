package com.xenakis.databaseService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import com.xenakis.model.Chapter;

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
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("greekName")
            );
        } catch (Exception e) {
            logger.error("Chapter with name = " + name + " was not found");
            throw new Exception("Chapter with name = " + name + " was not found");
        }
        DatabaseUtil.closeConnection(conn);
        return chapter;
    }

    public static int getChapterId(String name) {
        Chapter chapter;
        try {
            chapter = ChapterUtil.getChapter(name);
        }
        catch (Exception e) {
            return 0;
        }
        return chapter.getId();
    }

    public static Chapter getChapterFromGreekName(String chapterGreekName) {
        ResultSet rs;
        String sql = "SELECT * FROM chapters WHERE greekName='" + chapterGreekName + "'";
        Chapter chapter = null;

        Connection conn = DatabaseUtil.connect();
        logger.info("Get chapter with greekName: " + chapterGreekName);

        try {
            PreparedStatement statement  = conn.prepareStatement(sql);
            rs = statement.executeQuery();
            rs.next();
            chapter = new Chapter(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getString("greekName")
            );
        } catch (Exception e) {
            logger.error("getChapterFromGreekName");
        }
        DatabaseUtil.closeConnection(conn);
        return chapter;
    }

    public static String getChapterNameFromGreekName(String chapterGreekName) {
        ResultSet rs;
        String sql = "SELECT * FROM chapters WHERE greekName='" + chapterGreekName + "'";
        String chapterName = null;

        Connection conn = DatabaseUtil.connect();
        logger.info("Get chapter with greekName: " + chapterGreekName);

        try {
            PreparedStatement statement  = conn.prepareStatement(sql);
            rs = statement.executeQuery();
            rs.next();
            chapterName = rs.getString("name");
        } catch (Exception e) {
            logger.error("getChapterNameFromGreekName");
        }
        DatabaseUtil.closeConnection(conn);
        return chapterName;
    }

    public static String getChapterGreekName(String name) {
        Chapter chapter;
        try {
            chapter = ChapterUtil.getChapter(name);
        }
        catch (Exception e) {
            logger.error("getChapterGreekName");
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
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("greekName")
                ));
            }
        } catch (Exception e) {
            logger.error("getChapterList");
        }
        DatabaseUtil.closeConnection(conn);
        return chapterList;
    }
}
