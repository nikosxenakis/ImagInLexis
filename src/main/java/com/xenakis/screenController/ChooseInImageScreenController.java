package com.xenakis.screenController;

import java.util.ArrayList;
import com.xenakis.service.TestService;
import com.xenakis.model.Circle;
import com.xenakis.service.ImageService;
import com.xenakis.service.SoundService;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Ellipse;
import com.xenakis.screenData.ChooseInImageScreenData;
import com.xenakis.screenData.QuestionScreenData;

public class ChooseInImageScreenController extends QuestionScreenController {

	@FXML
    private ImageView image;

	@FXML
    private ImageView questionSoundImage;
	
	@FXML
	private Pane circleContainer;
	
	private ArrayList<Circle> circlesList;
    
    private String questionSoundId;

    public void setData(QuestionScreenData screenData, TestService testService){
    	
    	System.out.println("set Data in ChooseInImageScreenController");
    	super.setData(screenData, testService);
    	
    	if(!(screenData instanceof ChooseInImageScreenData)){
        	System.err.println("com.xenakis.screenData is not ChooseInImageScreenData");
    		return;
    	}

		enableSubmit();
    	setIsSelection();

    	ChooseInImageScreenData data = (ChooseInImageScreenData) screenData;
    	    	  	    	
    	Image imageJPG = ImageService.getImage(data.getImageId());
    	image.setImage(imageJPG);

    	circlesList = data.getCirclesList();

    	questionSoundImage.setImage(ImageService.getImage("soundImage"));

    	questionSoundId = data.getQuestionSoundId();

    	//get container
        for (Circle cl : circlesList){
        	
        	//create and add circles
        	System.out.println("new Ellipse: "+cl.x+" "+cl.y+" "+cl.w+" "+cl.h);
        	Ellipse ellipse = new Ellipse(cl.x,cl.y,cl.w,cl.h);
        	
        	cl.addEllipse(ellipse);
        	
        	ellipse.setId(cl.id);
        	 
        	ellipse.setStyle("-fx-effect:  dropshadow(three-pass-box, rgba(250,250,250,0.8), 10, 0, 0, 0)");
        	ellipse.setFill(Paint.valueOf("transparent"));
        	ellipse.setStroke(Paint.valueOf("#00ff0d"));
        	ellipse.setStrokeWidth(0);

        	ellipse.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
				System.out.println("ellipse pressed");
				Ellipse ellipse1 = (Ellipse) event.getSource();
				ellipse1.setStrokeWidth(5);

			   int i = 1;
			   for(Circle cl1 : circlesList){
				   if(cl1.ellipse.equals(ellipse1)){
					   addAnswer(i);
				   }
				   i++;
			   }

			});
        	
        	circleContainer.getChildren().add(ellipse);
        }
        
    }

    public void questionSoundIconClicked(){
		SoundService.playSound(this.questionSoundId);
    }
}
