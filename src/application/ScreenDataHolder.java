package application;

import java.util.HashMap;

public class ScreenDataHolder{
	
    private static ScreenDataHolder instance = new ScreenDataHolder();
    
    private HashMap<String, ScreenData> screenData;
    
    private ScreenDataHolder(){
    	this.screenData = new HashMap<>();
    }

    public static ScreenDataHolder getInstance(){ 
        return instance;
    }
    
    public static ScreenData getScreenData(String id){
    	ScreenData screenData = instance.screenData.get(id);
    	if(screenData == null)
    		System.out.println("error in getScreenData");
    	return screenData;
    }
    
    public static void addScreenData(String id, ScreenData screenData){
    	instance.screenData.put(id, screenData);
    }
    
}