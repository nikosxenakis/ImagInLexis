package com.xenakis.screenData;

import java.util.HashMap;

public class ScreenDataHolder{
	
    private static final ScreenDataHolder instance = new ScreenDataHolder();
    
    private final HashMap<String, QuestionScreenData> screenData;
    
    private ScreenDataHolder(){
    	this.screenData = new HashMap<>();
    }

    public static ScreenDataHolder getInstance(){ 
        return instance;
    }
    
    public static QuestionScreenData getScreenData(String id){
    	QuestionScreenData screenData = instance.screenData.get(id);
    	if(screenData == null)
    		System.err.println("error in getScreenData");
    	return screenData;
    }
    
    public static void addScreenData(String id, QuestionScreenData screenData){
    	instance.screenData.put(id, screenData);
    }
    
}