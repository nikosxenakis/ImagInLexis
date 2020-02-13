package com.xenakis.application;

import java.net.URL;
import java.util.HashMap;

import com.xenakis.ImagInLexis;
import com.xenakis.service.TestService;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;
import com.xenakis.screenController.QuestionScreenController;
import com.xenakis.screenController.ScreenController;
import com.xenakis.screenData.QuestionScreenData;
import com.xenakis.screenData.ScreenDataHolder;
import org.apache.log4j.Logger;

public class ScreenPane extends StackPane {

	private final Logger logger;

	//Holds the screens to be displayed
    private final HashMap<String, Node> screens = new HashMap<>();
    private final HashMap<String, ScreenController> screenControllersList = new HashMap<>();

    public ScreenPane() {
		super();
		this.logger = Logger.getLogger(ScreenPane.class);
	}
    
    //Add the screen to the collection
	private void addScreen(String name, Node screen){
        screens.put(name, screen);
    }
    
    //Returns the Node with the appropriate name
    public Node getScreen(String name){
        return screens.get(name);
    }
	
    public ScreenController getController(String screenId){
    	ScreenController sc = screenControllersList.get(screenId);
    	if(sc == null)
			logger.error("Controller of screen: " + screenId + " is null");
    	return sc;
    }
    
    private void addController(String screenId, ScreenController screenController) {
		logger.info("addController: "+screenId+" " + screenController);
    	screenControllersList.put(screenId, screenController);
    }
    //Loads the com.xenakis.fxml file, add the screen to the screens collection and
    //finally injects the screenPane to the controller.
    public void loadScreen(String screenId, TestService testService){
        try {
        	String resource = ResourcePathsHolder.getResourcePaths(screenId);			

            URL url = ImagInLexis.class.getResource(resource);

            FXMLLoader loader = new FXMLLoader(url);
            Parent loadScreen = loader.load();

            ScreenController screenController = loader.getController();

			screenController.setScreenPane(this);
            addController(screenId,screenController);
            addScreen(screenId, loadScreen);  
            
            if(screenController instanceof QuestionScreenController){
    			QuestionScreenData screenData = ScreenDataHolder.getScreenData(screenId);
				((QuestionScreenController)screenController).setData(screenData, testService);
            }

        } catch(Exception e){
			logger.error(e.getMessage());
        }
    }
    
    //This method tries to displayed the screen with a predefined name.
    //First it makes sure the screen has been already loaded.  Then if there is more than
    //one screen the new screen is been added second, and then the current screen is removed.
    // If there isn't any screen being displayed, the new screen is just added to the root.
    
    public void setScreen(final String screenId){

    	if (screens.get(screenId) != null) {   //screen loaded
    		final DoubleProperty opacity = opacityProperty();
	
    		if (!getChildren().isEmpty()) {    //if there is more than one screen
    			Timeline fade = new Timeline(
    				new KeyFrame(Duration.ZERO, new KeyValue(opacity, 1.0)),
    				new KeyFrame(new Duration(100), t -> {
						getChildren().remove(0);                    //remove the displayed screen
						getChildren().add(0, screens.get(screenId));     //add the screen
						Timeline fadeIn = new Timeline(
							new KeyFrame(Duration.ZERO, new KeyValue(opacity, 0.0)),
							new KeyFrame(new Duration(100), new KeyValue(opacity, 1.0))
						);
						fadeIn.play();
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
		}
    	else {
			logger.error("setScreen with screenId = " + screenId + " failed");
		}
    }
    
    //This method will remove the screen with the given name from the collection of screens
    public void unloadScreen(String name){
    	
    	if(screens.get(name) != null)
    		screens.remove(name);
 
    }
  
}
