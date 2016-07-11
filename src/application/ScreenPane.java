package application;

import java.util.HashMap;

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
import screenController.ChooseImageScreenController;
import screenController.QuestionScreenController;
import screenController.ScreenController;
import screenData.QuestionScreenData;
import screenData.ScreenDataHolder;

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
    	return screenControllersList.get(screenId);
    }
    
    public void addController(String screenId, ScreenController screenController){
    	screenControllersList.put(screenId, screenController);
    }
    //Loads the fxml file, add the screen to the screens collection and
    //finally injects the screenPane to the controller.
    public boolean loadScreen(String screenId,Test test){
        try{
        	String resource = ResourcePathsHolder.getResourcePaths(screenId);			
        	
            FXMLLoader loader = new FXMLLoader(getClass().getResource(resource));
            Parent loadScreen = (Parent) loader.load();
            
            ScreenController screenControler = ((ScreenController)loader.getController());
            screenControler.setScreenPane(this);
            addController(screenId,screenControler);
            addScreen(screenId, loadScreen);  
            
            if(screenControler instanceof QuestionScreenController){
    			QuestionScreenData screenData = ScreenDataHolder.getScreenData(screenId);

                if(screenControler instanceof ChooseImageScreenController){
                	((ChooseImageScreenController)screenControler).setData(screenData,test);
                }        	
                else{
                	System.err.println("not implemented");
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
                  new KeyFrame(new Duration(200), new KeyValue(opacity, 1.0)));
                fadeIn.play();
              }
            }, new KeyValue(opacity, 0.0)));
        fade.play();
        
      } else {
        setOpacity(0.0);
        getChildren().add(screens.get(screenId));       //no one else been displayed, then just show

        Timeline fadeIn = new Timeline(
            new KeyFrame(Duration.ZERO, new KeyValue(opacity, 0.0)),
            new KeyFrame(new Duration(200), new KeyValue(opacity, 1.0)));
        fadeIn.play();
      }
      return true;
    } else {
      System.err.println("screen hasn't been loaded!!! \n");
      return false;
    }

        
        /*Node screenToRemove;
        if(screens.get(name) != null){   //screen loaded
            if(!getChildren().isEmpty()){    //if there is more than one screen
                getChildren().add(0, screens.get(name));     //add the screen
                screenToRemove = getChildren().get(1);
                getChildren().remove(1);                    //remove the displayed screen
            }else{
             getChildren().add(screens.get(name));       //no one else been displayed, then just show
            }
            return true;
        }else {
            System.out.println("screen hasn't been loaded!!! \n");
            return false;
        }*/
    }
    
    
    //This method will remove the screen with the given name from the collection of screens
    public boolean unloadScreen(String name){
        if(screens.remove(name) == null)
        {    
            System.err.println("Screen didn't exist");
            return false;
        }else{return true;}
    }
  
}
