package com.xenakis.screenData;

import com.xenakis.screenController.QuestionScreenController;
import org.apache.log4j.Logger;

import java.util.HashMap;

public class ScreenDataHolder {

    private static final Logger logger = Logger.getLogger(QuestionScreenController.class);

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
            logger.error("error in getScreenData");
    	return screenData;
    }
    
    public static void addScreenData(String id, QuestionScreenData screenData){
    	instance.screenData.put(id, screenData);
    }
    
}