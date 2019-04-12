package com.xenakis.application;

import java.util.HashMap;

public class ResourcePathsHolder{
	
    private static ResourcePathsHolder instance = new ResourcePathsHolder();
    
    private HashMap<String, String> resourcePaths;
    
    private ResourcePathsHolder(){
    	this.resourcePaths = new HashMap<>();
    }

    public static ResourcePathsHolder getInstance(){ 
        return instance;
    }
 
    public static void addResourcePaths(String screenId, String resourcePath){
    	instance.resourcePaths.put(screenId, resourcePath);
    }
    
    public static String getResourcePaths(String screenId){
    	String str = instance.resourcePaths.get(screenId);
    	if(str == null)
    		System.err.println("error in getResourcePaths");
        return str;
    }
}