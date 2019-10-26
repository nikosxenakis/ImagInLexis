package com.xenakis.application;

import com.xenakis.ImagInLexis;
import com.xenakis.service.DatabaseUtil;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import org.apache.log4j.Logger;
import java.net.URL;
import java.util.HashMap;

public class SoundHolder {

    private static Logger logger = Logger.getLogger(SoundHolder.class);

    private static final HashMap<String, MediaPlayer> sounds = new HashMap<>();

    public static void playSound(String id) {
        String path;

        try {
            path = DatabaseUtil.getSoundPath(id);
            URL resource = ImagInLexis.class.getResource(path);
            Media media = new Media(resource.toString());
            MediaPlayer mediaPlayer = new MediaPlayer(media);
            SoundHolder.sounds.put(id, mediaPlayer);
            mediaPlayer.play();
            SoundHolder.logger.info("Play sound with name = " + id);
        }
        catch (Exception e) {
            SoundHolder.logger.error(e);
        }
    }

    public static void stopSound(String id){
        MediaPlayer mediaPlayer = SoundHolder.sounds.get(id);
        if(mediaPlayer != null){
            mediaPlayer.stop();
            SoundHolder.sounds.remove(id);
        }
    }
}
