package com.xenakis.service;

import com.xenakis.ImagInLexis;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class SoundUtil extends DatabaseUtil {

    private static final HashMap<String, MediaPlayer> sounds = new HashMap<>();

    private static String getSoundPath(String name) throws Exception {
        ResultSet rs;
        String sql = "SELECT * FROM sounds WHERE name='" + name + "'";
        String path;

        Connection conn = DatabaseUtil.connect();

        try {
            PreparedStatement statement  = conn.prepareStatement(sql);
            rs = statement.executeQuery();
            rs.next();
            path = rs.getString("path");
        } catch (SQLException e) {
            throw new Exception("Sound with name = " + name + " was not found");
        }
        DatabaseUtil.closeConnection(conn);
        return path;
    }

    public static void playSound(String id) {
        String path;

        try {
            path = SoundUtil.getSoundPath(id);
            URL resource = ImagInLexis.class.getResource(path);
            Media media = new Media(resource.toString());
            MediaPlayer mediaPlayer = new MediaPlayer(media);
            SoundUtil.sounds.put(id, mediaPlayer);
            mediaPlayer.play();
            SoundUtil.logger.info("Play sound with name = " + id);
        }
        catch (Exception e) {
            SoundUtil.logger.error(e);
        }
    }

    public static void stopSound(String id){
        MediaPlayer mediaPlayer = SoundUtil.sounds.get(id);
        if(mediaPlayer != null){
            mediaPlayer.stop();
            SoundUtil.sounds.remove(id);
        }
    }
}
