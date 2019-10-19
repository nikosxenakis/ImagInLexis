package com.xenakis.screenController;

import com.xenakis.application.ImageHolder;
import com.xenakis.application.SoundHolder;
import com.xenakis.application.TestUtil;
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

    public void setData(QuestionScreenData screenData, TestUtil testUtil){
    	
    	System.out.println("set Data in ChooseLabelScreenController");
    	super.setData(screenData, testUtil);

    	if(!(screenData instanceof ChooseLabelScreenData)){
        	System.err.println("com.xenakis.screenData is not ChooseLabelScreenData");
    		return;
    	}

    	ChooseLabelScreenData data = (ChooseLabelScreenData) screenData;
    	    	  	    	
    	Image tmpImage = ImageHolder.getImage(data.getImageId());
    	image.setImage(tmpImage);
    	
    	radioOption1.setText(data.getRadioOption1());
    	radioOption2.setText(data.getRadioOption2());
    	radioOption3.setText(data.getRadioOption3());

    	setIsSelection(true);
    	setAnswer(1);
    	submitButton.setDisable(false);
    	
    	soundId = data.getSoundId();
    	sound1Id = data.getSound1Id();
    	sound2Id = data.getSound2Id();
    	sound3Id = data.getSound3Id();
		
    	soundIcon.setImage(ImageHolder.getImage("soundImage"));
    	sound1Icon.setImage(ImageHolder.getImage("soundImage"));
    	sound2Icon.setImage(ImageHolder.getImage("soundImage"));
    	sound3Icon.setImage(ImageHolder.getImage("soundImage"));
    	
    	if(data.getRadioOption4() != null && data.getSound4Id() != null){
        	radioOption4.setText(data.getRadioOption4());
        	sound4Id = data.getSound4Id();	
        	sound4Icon.setImage(ImageHolder.getImage("soundImage"));
    	}
    	else{
        	vBox.getChildren().remove(hBox4);
    	}

    }
    
    public void radioClicked(MouseEvent e){
    	System.out.println("radioClicked");
    	
        if((RadioButton)e.getSource() == radioOption1){
        	setAnswer(1);
        }
        else if((RadioButton)e.getSource() == radioOption2){
        	setAnswer(2);
        }
        else if((RadioButton)e.getSource() == radioOption3){
        	setAnswer(3);
        }
        else if((RadioButton)e.getSource() == radioOption4){
        	setAnswer(4);
        }
        
    }
    
    public void soundIconClicked(MouseEvent e){
    	String id = ((Node) e.getSource()).getId();

    	if(id.equals("soundIcon")){
    		SoundHolder.playSound(soundId);
    	}
    	else if(id.equals("sound1Icon")){
    		SoundHolder.playSound(sound1Id);
    	}
    	else if(id.equals("sound2Icon")){
    		SoundHolder.playSound(sound2Id);
    	}
    	else if(id.equals("sound3Icon")){
    		SoundHolder.playSound(sound3Id);
    	}
    	else if(id.equals("sound4Icon")){
    		SoundHolder.playSound(sound4Id);
    	}
    	else{
    		System.err.println("error in soundIconClicked in ChooseLabelScreenController");
    	}
    }
    
}
