package com.xenakis.application;

import com.xenakis.ImagInLexis;
import com.xenakis.service.DatabaseUtil;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.net.URL;
import java.util.HashMap;

//TODO remove this with bellow class
public class SoundHolder{

    private static final SoundHolder instance = new SoundHolder();

    private final HashMap<String, MediaPlayer> sounds;
    private final HashMap<String, String> soundsPath;

    private SoundHolder(){
    	this.sounds = new HashMap<>();
    	this.soundsPath = new HashMap<>();
    }

    public static SoundHolder getInstance(){
        return instance;
    }

    private static MediaPlayer getSound(String id){

        String path = instance.soundsPath.get(id);
        System.out.println(path);
        URL resource = ImagInLexis.class.getResource(path);
        Media media = new Media(resource.toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        instance.sounds.put(id, mediaPlayer);

        return mediaPlayer;
    }

    public static void playSound(String id){
        getSound(id).play();
    }

    public static void stopSound(String id){

    	MediaPlayer mediaPlayer = instance.sounds.get(id);
    	if(mediaPlayer != null){
    		mediaPlayer.stop();
    		instance.sounds.remove(id);
    	}
    }

    public static void addSound(String id, String path){
    	instance.add(id, path);
    }

    private void add(String id, String path){
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