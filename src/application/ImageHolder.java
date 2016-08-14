package application;

import java.util.HashMap;
import java.util.Map.Entry;

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
    	
    	Image image = instance.images.get(id);
    	
    	if(image == null){
    		String path = instance.imagePaths.get(id);
        	image = new Image(path);    		
    		instance.images.put(id, image);	
    	}
    	
		return image;
    }
    
    public static void addImage(String id, String path){
    	instance.imagePaths.put(id, path);
    }
    
    public static void cleanMemory(){
    	System.out.println("ImageHolder cleanMemory images size = "+instance.images.size());
    	
    	for(Entry<String, Image> entry : instance.images.entrySet()) {
    		entry.setValue(null);
    		//Image value = entry.getValue();
    		//value.
    	}

    	instance.images.clear();
    }
}