package application;

import java.net.URL;
import java.util.HashMap;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class SoundHolder{
	
    private static SoundHolder instance = new SoundHolder();
    
    //private HashMap<String, MediaPlayer> sounds;
    private HashMap<String, String> soundsPath;

    private SoundHolder(){
    	//this.sounds = new HashMap<>();
    	this.soundsPath = new HashMap<>();
    }
 
    public static SoundHolder getInstance(){ 
        return instance;
    }
    
    public static MediaPlayer getSound(String id){
    	/*
    	MediaPlayer mediaPlayer = instance.sounds.get(id);
    	
    	if(mediaPlayer == null){
        	String path = instance.soundsPath.get(id);
        	URL resource = ImagInLexis.class.getResource(path);
            Media media = new Media(resource.toString());
            mediaPlayer = new MediaPlayer(media);
            instance.sounds.put(id, mediaPlayer);	
    	}
    	
    	return mediaPlayer;
    	*/
    	
    	String path = instance.soundsPath.get(id);
    	URL resource = ImagInLexis.class.getResource(path);
        Media media = new Media(resource.toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        return mediaPlayer;
    }

    public static void playSound(String id){
    	getSound(id).play();
    }
    
    public static void addSound(String id, String path){
    	instance.add(id, path);
    }
    
    public void add(String id, String path){
    	soundsPath.put(id, path);
    }
    
    public static void cleanMemory(){
    	
    }
    
}