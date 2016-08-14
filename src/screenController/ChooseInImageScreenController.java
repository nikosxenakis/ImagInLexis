/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package screenController;

import java.util.ArrayList;

import application.Circle;
import application.ImageHolder;
import application.SoundHolder;
import application.Test;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Ellipse;
import screenData.ChooseInImageScreenData;
import screenData.QuestionScreenData;

public class ChooseInImageScreenController extends QuestionScreenController{
    
	@FXML
    private ImageView image;

	@FXML
    private ImageView questionSoundImage;
	
	@FXML
	private BorderPane mainBorderPane;
	
	@FXML
	private Pane circleContainer;
	
	private ArrayList<Circle> circlesList;
    
    private String questionSoundId;
    
    public void setData(QuestionScreenData screenData, Test test){
    	
    	System.out.println("set Data in ChooseInImageScreenController");
    	super.setData(screenData, test);
    	
    	if(!(screenData instanceof ChooseInImageScreenData)){
        	System.err.println("screenData is not ChooseInImageScreenData");
    		return;
    	}
    	
    	submitButton.setDisable(false);
    	setIsSelection(true);
    	
    	ChooseInImageScreenData data = (ChooseInImageScreenData) screenData;
    	    	  	    	
    	Image imageJPG = ImageHolder.getImage(data.getImageId());
    	image.setImage(imageJPG);

    	circlesList = data.getCirclesList();

    	questionSoundImage.setImage(ImageHolder.getImage("soundImage"));

    	questionSoundId = data.getQuestionSoundId();

    	//get container
        for (Circle cl : circlesList){
        	
        	//create and add circles
        	System.out.println("new Ellipse: "+cl.x.toString()+" "+cl.y.toString()+" "+cl.w.toString()+" "+cl.h.toString());
        	Ellipse ellipse = new Ellipse(cl.x,cl.y,cl.w,cl.h);
        	
        	cl.addEllipse(ellipse);
        	
        	ellipse.setId(cl.id);
        	 
        	ellipse.setStyle("-fx-effect:  dropshadow(three-pass-box, rgba(250,250,250,0.8), 10, 0, 0, 0)");
        	ellipse.setFill(Paint.valueOf("transparent"));
        	ellipse.setStroke(Paint.valueOf("#00ff0d"));
        	ellipse.setStrokeWidth(0);

        	ellipse.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
        	     @Override
        	     public void handle(MouseEvent event) {
        	         System.out.println("ellipse pressed");
        	         Ellipse ellipse = (Ellipse) event.getSource();
        	         ellipse.setStrokeWidth(5);
        	        
        	        int i = 1;
        	        for(Circle cl : circlesList){
        	        	if(cl.ellipse.equals(ellipse)){
                	    	addAnswer(i);
        	        	}
        	        	i++;
        	        }
        	        
        	     }
        	});        	
        	
        	circleContainer.getChildren().add(ellipse);
        }
        
    }

    public void questionSoundIconClicked(){
    	SoundHolder.playSound(this.questionSoundId);
    }
}
