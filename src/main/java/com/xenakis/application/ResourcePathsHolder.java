package com.xenakis.application;

import org.apache.log4j.Logger;
import java.util.HashMap;

public class ResourcePathsHolder {

    private static final Logger logger = Logger.getLogger(ResourcePathsHolder.class);

    private static final HashMap<String, String> resourcePaths = new HashMap<>();

    public static void addResourcePaths(String screenId, String resourcePath){
    	resourcePaths.put(screenId, resourcePath);
    }
    
    public static String getResourcePaths(String screenId){
    	String str = resourcePaths.get(screenId);
    	if(str == null)
            logger.error("getResourcePaths");
        return str;
    }
}