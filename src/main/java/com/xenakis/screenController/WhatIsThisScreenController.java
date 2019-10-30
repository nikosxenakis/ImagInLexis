package com.xenakis.screenController;

import com.xenakis.service.TestService;
import com.xenakis.service.ImageService;
import com.xenakis.service.SoundService;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import com.xenakis.screenData.QuestionScreenData;
import com.xenakis.screenData.WhatIsThisScreenData;

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
    private ImageView soundImage;
    
    @FXML
    private HBox mainQuestionHBox; 
    
    @FXML
    private Text mainQuestion;
    
    @FXML
    private ImageView mainQuestionSoundImage;
    
    private String soundId = null;    
    private String questionSoundId = null;    
    private String mainQuestionSoundId = null;

    public void setData(QuestionScreenData screenData, TestService testService){
    	
    	System.out.println("set Data in WhatIsThisScreenController");
    	super.setData(screenData, testService);

    	if(!(screenData instanceof WhatIsThisScreenData)){
        	System.err.println("com.xenakis.screenData is not WhatIsThisScreenData");
    		return;
    	}

    	WhatIsThisScreenData data = (WhatIsThisScreenData) screenData;
    	    	  	    	
    	String imageId = data.getImageId();

    	System.out.println("imageId: "+imageId);

    	if(imageId != null){
        	Image tmpImage = ImageService.getImage(imageId);
        	image.setImage(tmpImage); 		
    	}
    	else{
    		System.out.println("delete image");
    		mainBox.getChildren().remove(image);
    	}

    	soundId = data.getSoundId();
    	questionSoundId = data.getQuestionSoundId();

    	if(data.getMainQuestion() != null && data.getMainQuestionSoundId() != null){
        	mainQuestion.setText(data.getMainQuestion());
        	mainQuestionSoundId = data.getMainQuestionSoundId();
        	mainQuestionSoundImage.setImage(ImageService.getImage("soundImage"));
    	}
    	else{
    		mainPane.getChildren().remove(mainQuestionHBox);
    	}
        
    	soundImage.setImage(ImageService.getImage(soundImage.getId()));

    	setAnswer(1);

    	setIsSelection();
    	submitButton.setDisable(false);
    }
    
    public void radioClicked(MouseEvent e){
    	System.out.println("radioClicked");
    	
        if(e.getSource() == correctOption){
        	setAnswer(1);
        }
        else if(e.getSource() == wrongOption){
        	setAnswer(2);
        }
    }
    
    public void imageClicked(){
		SoundService.playSound(soundId);
    }
    
    public void soundIconClicked(){
		SoundService.playSound(questionSoundId);
    }
    
    public void mainQuestionSoundIconClicked(){
		SoundService.playSound(mainQuestionSoundId);
    }
    
}
