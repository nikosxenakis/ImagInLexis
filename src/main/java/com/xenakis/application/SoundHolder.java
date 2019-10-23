package com.xenakis.application;

import com.xenakis.ImagInLexis;
import com.xenakis.service.DatabaseUtil;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import org.apache.log4j.Logger;

import java.net.URL;
import java.util.HashMap;

//TODO remove this with bellow class
public class SoundHolder {

    private final static HashMap<String, MediaPlayer> sounds = new HashMap<>();
    private final static HashMap<String, String> soundsPath = new HashMap<>();
    private final static Logger logger = Logger.getLogger(SoundHolder.class);

    private static MediaPlayer getSound(String id){

        String path = SoundHolder.soundsPath.get(id);
        if(path == null)
            throw new NullPointerException("There is no sound with id = " + id);


        SoundHolder.logger.info("getSound: id = " + id + ", path = " + path);

//        System.out.println(path);
        URL resource = ImagInLexis.class.getResource(path);
        Media media = new Media(resource.toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        SoundHolder.sounds.put(id, mediaPlayer);

        return mediaPlayer;
    }

    public static void playSound(String id) {
        MediaPlayer mediaPlayer;

        try {
            mediaPlayer = getSound(id);
        }
        catch (NullPointerException ex) {
            SoundHolder.logger.error(ex.getMessage());
            return;
        }

        mediaPlayer.play();
    }

    public static void stopSound(String id){

    	MediaPlayer mediaPlayer = SoundHolder.sounds.get(id);
    	if(mediaPlayer != null){
    		mediaPlayer.stop();
            SoundHolder.sounds.remove(id);
    	}
    }

    public static void addSound(String id, String path){
        SoundHolder.add(id, path);
    }

    private static void add(String id, String path){
    	soundsPath.put(id, path);
    }
}


//public class SoundHolder {
//
//    private static final HashMap<String, MediaPlayer> sounds = new HashMap<>();
//
//    private static MediaPlayer getSound(String id){
//
//        String path = DatabaseUtil.getSoundPath(id);
//        URL resource = ImagInLexis.class.getResource(path);
//        Media media = new Media(resource.toString());
//        MediaPlayer mediaPlayer = new MediaPlayer(media);
//        SoundHolder.sounds.put(id, mediaPlayer);
//        return mediaPlayer;
//    }
//
//    public static void playSound(String id){
//        getSound(id).play();
//    }
//
//    public static void stopSound(String id){
//        MediaPlayer mediaPlayer = SoundHolder.sounds.get(id);
//        if(mediaPlayer != null){
//            mediaPlayer.stop();
//            SoundHolder.sounds.remove(id);
//        }
//    }
//}