package com.xenakis.service;

import com.xenakis.ImagInLexis;
import com.xenakis.databaseService.SoundUtil;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import org.apache.log4j.Logger;
import java.net.URL;
import java.util.HashMap;

public class SoundService {

    private static final Logger logger = Logger.getLogger(SoundService.class);
    private static final HashMap<String, MediaPlayer> sounds = new HashMap<>();

    public static void playSound(String id) {
        String path;

        try {
            path = SoundUtil.getSoundPath(id);
            URL resource = ImagInLexis.class.getResource(path);
            Media media = new Media(resource.toString());
            MediaPlayer mediaPlayer = new MediaPlayer(media);
            SoundService.sounds.put(id, mediaPlayer);
            mediaPlayer.play();
            SoundService.logger.info("Play sound with name = " + id);
        }
        catch (Exception e) {
            SoundService.logger.error(e);
        }
    }

    public static void stopSound(String id){
        MediaPlayer mediaPlayer = SoundService.sounds.get(id);
        if(mediaPlayer != null){
            mediaPlayer.stop();
            SoundService.sounds.remove(id);
        }
    }
}
