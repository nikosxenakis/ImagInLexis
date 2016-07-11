package application;

import java.util.HashMap;

import javafx.scene.image.Image;

public class SoundHolder{
	
    private static SoundHolder instance = new SoundHolder();
    
    private HashMap<String, Image> sounds;
    
    private SoundHolder(){
    	this.sounds = new HashMap<>();
    }

    public static SoundHolder getInstance(){ 
        return instance;
    }
    
    public static Image getImage(String id){
    	Image image = instance.sounds.get(id);
    	if(image == null)
    		System.err.println("error in getImage");
    	return image;
    }
    
    public static void addImage(String id, String path){
    	Image image = new Image(path);
    	instance.sounds.put(id, image);
    }
    
}