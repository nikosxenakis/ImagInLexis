package application;

import java.util.HashMap;

import javafx.scene.image.Image;

public class ImageHolder{
	
    private static ImageHolder instance = new ImageHolder();
    
    private HashMap<String, Image> images;
    private HashMap<String, String> imagePaths;

    private ImageHolder(){
    	this.images = new HashMap<>();
    	this.imagePaths = new HashMap<>();
    }

    public static ImageHolder getInstance(){ 
        return instance;
    }
    
    public static Image getImage(String id){
    	Image image = null;
    	
    	if(instance.images.get(id) != null){
    		image = instance.images.get(id);
    	}
    	else{
    		
    		String path = instance.imagePaths.get(id);
        	image = new Image(path);
        	instance.images.put(id, image);
    	}
		return image;
    }
    
    public static void addImage(String id, String path){
    	instance.imagePaths.put(id, path);
    }
}