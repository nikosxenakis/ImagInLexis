package com.xenakis.screenController;

import com.xenakis.service.TestService;
import com.xenakis.service.ImageService;
import com.xenakis.service.SoundService;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.RadioButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import com.xenakis.screenData.ChooseLabelScreenData;
import com.xenakis.screenData.QuestionScreenData;

public class ChooseLabelScreenController extends QuestionScreenController{
        
	@FXML
	private ImageView image;

	@FXML
	private RadioButton radioOption1;
	
	@FXML
	private RadioButton radioOption2;
	
	@FXML
	private RadioButton radioOption3;

	@FXML
	private RadioButton radioOption4;
	
	@FXML
	private HBox hBox4;
	
	@FXML
	private VBox vBox;

	@FXML
	private ImageView soundIcon;
	
	@FXML
	private ImageView sound1Icon;
	
	@FXML
	private ImageView sound2Icon;
	
	@FXML
	private ImageView sound3Icon;
	
	@FXML
	private ImageView sound4Icon;
	
	private String soundId;
	private String sound1Id;
	private String sound2Id;
	private String sound3Id;
	private String sound4Id;

    public void setData(QuestionScreenData screenData, TestService testService){

		logger.info("set Data in ChooseLabelScreenController");
    	super.setData(screenData, testService);

    	if(!(screenData instanceof ChooseLabelScreenData)){
			logger.error("screenData is not ChooseLabelScreenData");
    		return;
    	}

    	ChooseLabelScreenData data = (ChooseLabelScreenData) screenData;
    	    	  	    	
    	Image tmpImage = ImageService.getImage(data.getImageId());
    	image.setImage(tmpImage);
    	
    	radioOption1.setText(data.getRadioOption1());
    	radioOption2.setText(data.getRadioOption2());
    	radioOption3.setText(data.getRadioOption3());

    	setIsSelection();
    	setAnswer(1);
		enableSubmit();

    	soundId = data.getSoundId();
    	sound1Id = data.getSound1Id();
    	sound2Id = data.getSound2Id();
    	sound3Id = data.getSound3Id();
		
    	soundIcon.setImage(ImageService.getImage("soundImage"));
    	sound1Icon.setImage(ImageService.getImage("soundImage"));
    	sound2Icon.setImage(ImageService.getImage("soundImage"));
    	sound3Icon.setImage(ImageService.getImage("soundImage"));
    	
    	if(data.getRadioOption4() != null && data.getSound4Id() != null){
        	radioOption4.setText(data.getRadioOption4());
        	sound4Id = data.getSound4Id();	
        	sound4Icon.setImage(ImageService.getImage("soundImage"));
    	}
    	else{
        	vBox.getChildren().remove(hBox4);
    	}

    }
    
    public void radioClicked(MouseEvent e){
    	System.out.println("radioClicked");
    	
        if(e.getSource() == radioOption1){
        	setAnswer(1);
        }
        else if(e.getSource() == radioOption2){
        	setAnswer(2);
        }
        else if(e.getSource() == radioOption3){
        	setAnswer(3);
        }
        else if(e.getSource() == radioOption4){
        	setAnswer(4);
        }
        
    }
    
    public void soundIconClicked(MouseEvent e){
    	String id = ((Node) e.getSource()).getId();

		switch (id) {
			case "soundIcon":
				SoundService.playSound(soundId);
				break;
			case "sound1Icon":
				SoundService.playSound(sound1Id);
				break;
			case "sound2Icon":
				SoundService.playSound(sound2Id);
				break;
			case "sound3Icon":
				SoundService.playSound(sound3Id);
				break;
			case "sound4Icon":
				SoundService.playSound(sound4Id);
				break;
			default:
				logger.error("error in soundIconClicked in ChooseLabelScreenController");
				break;
		}
    }
    
}
