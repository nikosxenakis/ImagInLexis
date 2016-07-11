package application;

import java.util.HashMap;

import javafx.scene.image.Image;

public class ImageHolder{
	
    private static ImageHolder instance = new ImageHolder();
    
    private HashMap<String, Image> images;
    
    private ImageHolder(){
    	this.images = new HashMap<>();
    }

    public static ImageHolder getInstance(){ 
        return instance;
    }
    
    public static Image getImage(String id){
    	Image image = instance.images.get(id);
    	if(image == null)
    		System.err.println("error in getImage");
    	return image;
    }
    
    public static void addImage(String id, String path){
    	Image image = new Image(path);
    	instance.images.put(id, image);
    }
    
}