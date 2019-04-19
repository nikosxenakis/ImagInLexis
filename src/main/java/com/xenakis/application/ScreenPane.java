package com.xenakis.application;

import java.net.URL;
import java.util.HashMap;

import com.xenakis.ImagInLexis;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;
import com.xenakis.screenController.ChooseImageLinkScreenController;
import com.xenakis.screenController.ChooseImageScreenController;
import com.xenakis.screenController.ChooseImageScreenController2;
import com.xenakis.screenController.ChooseInImageScreenController;
import com.xenakis.screenController.ChooseLabelFromSoundScreenController;
import com.xenakis.screenController.ChooseLabelScreenController;
import com.xenakis.screenController.QuestionScreenController;
import com.xenakis.screenController.ScreenController;
import com.xenakis.screenController.WhatIsThisScreenController;
import com.xenakis.screenData.QuestionScreenData;
import com.xenakis.screenData.ScreenDataHolder;

public class ScreenPane extends StackPane{
    //Holds the screens to be displayed
    private HashMap<String, Node> screens = new HashMap<>();
    private HashMap<String, ScreenController> screenControllersList = new HashMap<>();

    public ScreenPane() {
        super();
    }
    
    //Add the screen to the collection
    public void addScreen(String name, Node screen){
        screens.put(name, screen);
    }
    
    //Returns the Node with the appropriate name
    public Node getScreen(String name){
        return screens.get(name);
    }
	
    public ScreenController getController(String screenId){
    	ScreenController sc = screenControllersList.get(screenId);
    	if(sc == null)
    		System.err.println("error in getController ScreenController is null");
    	return sc;
    }
    
    public void addController(String screenId, ScreenController screenController){
    	//System.out.println("addController: "+screenId+" "+com.xenakis.screenController);
    	screenControllersList.put(screenId, screenController);
    }
    //Loads the com.xenakis.fxml file, add the screen to the screens collection and
    //finally injects the screenPane to the controller.
    public boolean loadScreen(String screenId, Test test){
        try {
        	String resource = ResourcePathsHolder.getResourcePaths(screenId);			

            URL url = ImagInLexis.class.getResource(resource);

            FXMLLoader loader = new FXMLLoader(url);
            Parent loadScreen = loader.load();

            ScreenController screenControler = loader.getController();

            screenControler.setScreenPane(this);
            addController(screenId,screenControler);
            addScreen(screenId, loadScreen);  
            
            if(screenControler instanceof QuestionScreenController){
    			QuestionScreenData screenData = ScreenDataHolder.getScreenData(screenId);

                if(screenControler instanceof ChooseImageScreenController){
                	((ChooseImageScreenController)screenControler).setData(screenData,test);
                }
                else if(screenControler instanceof ChooseImageScreenController2){
                	((ChooseImageScreenController2)screenControler).setData(screenData,test);
                }
                else if(screenControler instanceof ChooseLabelScreenController){
                	((ChooseLabelScreenController)screenControler).setData(screenData,test);
                }
                else if(screenControler instanceof ChooseLabelFromSoundScreenController){
                	((ChooseLabelFromSoundScreenController)screenControler).setData(screenData,test);
                }
                else if(screenControler instanceof ChooseInImageScreenController){
                	((ChooseInImageScreenController)screenControler).setData(screenData,test);
                }
                else if(screenControler instanceof WhatIsThisScreenController){
                	((WhatIsThisScreenController)screenControler).setData(screenData,test);
                }
                else if(screenControler instanceof ChooseImageLinkScreenController){
                	((ChooseImageLinkScreenController)screenControler).setData(screenData,test);
                }
                else{
                	System.err.println("not implemented in loadScreen");
                }
            }
 
            return true;
            
        }catch(Exception e){
            System.err.println(e.getMessage());
            return false;
        }
    }
    
    //This method tries to displayed the screen with a predefined name.
    //First it makes sure the screen has been already loaded.  Then if there is more than
    //one screen the new screen is been added second, and then the current screen is removed.
    // If there isn't any screen being displayed, the new screen is just added to the root.
    
    public boolean setScreen(final String screenId){

    	if (screens.get(screenId) != null) {   //screen loaded
    		final DoubleProperty opacity = opacityProperty();
	
    		if (!getChildren().isEmpty()) {    //if there is more than one screen
    			Timeline fade = new Timeline(
    				new KeyFrame(Duration.ZERO, new KeyValue(opacity, 1.0)),
    				new KeyFrame(new Duration(200), new EventHandler<ActionEvent>() {
    					@Override
    					public void handle(ActionEvent t) {
    						getChildren().remove(0);                    //remove the displayed screen
    						getChildren().add(0, screens.get(screenId));     //add the screen
    						Timeline fadeIn = new Timeline(
    							new KeyFrame(Duration.ZERO, new KeyValue(opacity, 0.0)),
    							new KeyFrame(new Duration(200), new KeyValue(opacity, 1.0))
    						);
    						fadeIn.play();
    					}
    				}, new KeyValue(opacity, 0.0))
    			);
    			fade.play();
    		}
    		else{
    			setOpacity(0.0);
    			getChildren().add(screens.get(screenId));       //no one else been displayed, then just show
	
    			Timeline fadeIn = new Timeline(
    				new KeyFrame(Duration.ZERO, new KeyValue(opacity, 0.0)),
    				new KeyFrame(new Duration(200), new KeyValue(opacity, 1.0))
    			);
    			fadeIn.play();
    		}
    	return true;
	    }
    	else{
    		System.err.println("screen hasn't been loaded!!! \n");
    		return false;
	    }
    }
    
    
    //This method will remove the screen with the given name from the collection of screens
    public void unloadScreen(String name){
    	
    	if(screens.get(name) != null)
    		screens.remove(name);
 
    }
  
}
