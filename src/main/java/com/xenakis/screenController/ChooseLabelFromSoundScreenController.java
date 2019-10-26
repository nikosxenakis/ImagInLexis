package com.xenakis.screenController;

import com.xenakis.service.SoundUtil;
import com.xenakis.service.ImageUtil;
import com.xenakis.application.TestUtil;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import com.xenakis.screenData.ChooseLabelFromSoundScreenData;
import com.xenakis.screenData.QuestionScreenData;

public class ChooseLabelFromSoundScreenController extends QuestionScreenController{
  
	@FXML
	private RadioButton radioOption1;
	
	@FXML
	private RadioButton radioOption2;
	
	@FXML
	private RadioButton radioOption3;
	
	@FXML
	private RadioButton radioOption4;
	
	@FXML
	private ImageView image;
	
	@FXML
	private ImageView questionSoundImage;
	
	@FXML
	private ImageView soundImage;
	
	@FXML
	private ImageView sound1Image;

	@FXML
	private ImageView sound2Image;
	
	@FXML
	private ImageView sound3Image;
	
	@FXML
	private ImageView sound4Image;
	
	@FXML
	private HBox mainHBox;
	
	@FXML
	private VBox radioVBox;
	
	@FXML
	private HBox radioHBox3;	
	
	@FXML
	private HBox radioHBox4;
	
	private String imageId = null;
	private String questionSoundId = null;
	private String soundId = null;
	private String sound1Id = null;
	private String sound2Id = null;
	private String sound3Id = null;
	private String sound4Id = null;

    public void setData(QuestionScreenData screenData, TestUtil testUtil){
    	
    	//System.out.println("set Data in ChooseLabelFromSoundScreenController");
    	super.setData(screenData, testUtil);

    	if(!(screenData instanceof ChooseLabelFromSoundScreenData)){
        	System.err.println("com.xenakis.screenData is not ChooseLabelFromSoundScreenData");
    		return;
    	}

    	ChooseLabelFromSoundScreenData data = (ChooseLabelFromSoundScreenData) screenData;
    	
    	questionSoundId = data.getQuestionSoundId();

    	radioOption1.setText(data.getRadioOption1());
    	radioOption2.setText(data.getRadioOption2());
    	
    	sound1Id = data.getSound1Id();
    	sound2Id = data.getSound2Id();

    	questionSoundImage.setImage(ImageUtil.getImage("soundImage"));
    	sound1Image.setImage(ImageUtil.getImage("soundImage"));
    	sound2Image.setImage(ImageUtil.getImage("soundImage"));
    	
    	if(data.getRadioOption3() != null && data.getSound3Id() != null){
        	radioOption3.setText(data.getRadioOption3());
        	sound3Id = data.getSound3Id();
        	sound3Image.setImage(ImageUtil.getImage("soundImage"));
    	}
    	else{
    		//System.out.println("remove radioHBox3");
        	radioVBox.getChildren().remove(radioHBox3);
    	}
  
    	if(data.getRadioOption4() != null && data.getSound4Id() != null){
        	radioOption4.setText(data.getRadioOption4());
        	sound4Id = data.getSound4Id();
        	sound4Image.setImage(ImageUtil.getImage("soundImage"));
    	}
    	else{
    		//System.out.println("remove radioHBox4");
        	radioVBox.getChildren().remove(radioHBox4);
    	}
    	
    	imageId = data.getImageId();
    	if(imageId == null){
        	//System.out.println("image is null");
        	mainHBox.getChildren().remove(image);
    	}
    	else{
        	//System.out.println("image : "+imageId);
    		image.setImage(ImageUtil.getImage(imageId));
    	}
    	
    	soundId = data.getSoundId();
    	if(soundId == null){
        	//System.out.println("soundId is null");
        	mainHBox.getChildren().remove(soundImage);
    	}
    	else{
        	//System.out.println("soundId : "+soundId);
        	soundImage.setImage(ImageUtil.getImage("soundImage"));
    	}

    	setIsSelection();
    	setAnswer(1);
    	submitButton.setDisable(false);
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
    	ImageView sound = (ImageView)e.getSource();
    	
    	if(sound == soundImage){
    		SoundUtil.playSound(soundId);
    	}
    	else if(sound == sound1Image){
    		SoundUtil.playSound(sound1Id);
    	}
    	else if(sound == sound2Image){
    		SoundUtil.playSound(sound2Id);
    	}
    	else if(sound == sound3Image){
    		SoundUtil.playSound(sound3Id);
    	}
    	else if(sound == sound4Image){
    		SoundUtil.playSound(sound4Id);
    	}
    	else{
    		System.err.println("error in soundIconClicked in ChooseLabelScreenController");
    	}
    }
  
    public void questionSoundIconClicked(){
    	SoundUtil.playSound(this.questionSoundId);
    }
}
