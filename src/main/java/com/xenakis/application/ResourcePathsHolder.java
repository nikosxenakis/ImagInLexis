package com.xenakis.application;

import java.util.HashMap;

public class ResourcePathsHolder{

    private static final HashMap<String, String> resourcePaths = new HashMap<>();

    public static void addResourcePaths(String screenId, String resourcePath){
    	resourcePaths.put(screenId, resourcePath);
    }
    
    public static String getResourcePaths(String screenId){
    	String str = resourcePaths.get(screenId);
    	if(str == null)
    		System.err.println("error in getResourcePaths");
        return str;
    }
}