package application;

import java.net.URL;
import java.util.HashMap;

import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class SoundHolder{
	
    private static SoundHolder instance = new SoundHolder();
    
    private HashMap<String, MediaPlayer> sounds;
    
    private SoundHolder(){
    	this.sounds = new HashMap<>();
    }
 
    public static SoundHolder getInstance(){ 
        return instance;
    }
    
    public static MediaPlayer getSound(String id){
    	MediaPlayer mediaPlayer = instance.sounds.get(id);
    	if(mediaPlayer == null)
    		System.err.println("error in getSound");
    	return mediaPlayer;
    }

    public static void playSound(String id){
    	SoundHolder.getSound(id).play();
    }
    
    public static void addSound(String id, String path){
    	SoundHolder.instance.add(id, path);
    }
    
    public void add(String id, String path){
    	final URL resource = getClass().getResource(path);
        final Media media = new Media(resource.toString());
        final MediaPlayer mediaPlayer = new MediaPlayer(media);
    	this.sounds.put(id, mediaPlayer);
    }
    
}