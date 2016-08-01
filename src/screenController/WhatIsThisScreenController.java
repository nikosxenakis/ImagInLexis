package screenController;

import application.ImageHolder;
import application.SoundHolder;
import application.Test;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import screenData.QuestionScreenData;
import screenData.WhatIsThisScreenData;

public class WhatIsThisScreenController extends QuestionScreenController{
        
	@FXML
	private ImageView image;

	@FXML
	private VBox mainBox;
	
	@FXML
	private RadioButton correctOption;
	
	@FXML
	private RadioButton wrongOption;
		
    @FXML
    private ImageView soundIcon;
    
    private String soundId = null;    
    
    public void setData(QuestionScreenData screenData, Test test){
    	
    	System.out.println("set Data in WhatIsThisScreenController");
    	super.setData(screenData, test);

    	if(!(screenData instanceof WhatIsThisScreenData)){
        	System.err.println("screenData is not WhatIsThisScreenData");
    		return;
    	}

    	WhatIsThisScreenData data = (WhatIsThisScreenData) screenData;
    	    	  	    	
    	String imageId = data.getImageId();

    	System.out.println("imageId: "+imageId);

    	if(imageId != null){
        	Image tmpImage = ImageHolder.getImage(imageId);
        	image.setImage(tmpImage); 		
    	}
    	else{
    		System.out.println("delete imagee");
    		mainBox.getChildren().remove(image);
    	}

    	soundId = data.getSoundId();

    	setAnswer(1);

    	setIsSelection(true);
    	submitButton.setDisable(false);
    }
    
    public void radioClicked(MouseEvent e){
    	System.out.println("radioClicked");
    	
        if((RadioButton)e.getSource() == correctOption){
        	setAnswer(1);
        }
        else if((RadioButton)e.getSource() == wrongOption){
        	setAnswer(2);
        }
    }
 
    public void soundIconClicked(){
    	SoundHolder.playSound(soundId);
    }
}
