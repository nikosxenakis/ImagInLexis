package screenController;

import application.ImageHolder;
import application.SoundHolder;
import application.Test;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import screenData.ChooseLabelFromSoundScreenData;
import screenData.QuestionScreenData;

public class ChooseLabelFromSoundScreenController extends QuestionScreenController{
  
	@FXML
	private RadioButton radioOption1;
	
	@FXML
	private RadioButton radioOption2;
	
	@FXML
	private RadioButton radioOption3;
	
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
	private HBox mainHBox;
	
	@FXML
	private VBox radioVBox;
	
	@FXML
	private HBox radioHBox3;	
	
	private String imageId = null;
	private String questionSoundId = null;
	private String soundId = null;
	private String sound1Id = null;
	private String sound2Id = null;
	private String sound3Id = null;

    public void setData(QuestionScreenData screenData, Test test){
    	
    	System.out.println("set Data in ChooseLabelFromSoundScreenController");
    	super.setData(screenData, test);

    	if(!(screenData instanceof ChooseLabelFromSoundScreenData)){
        	System.err.println("screenData is not ChooseLabelFromSoundScreenData");
    		return;
    	}

    	ChooseLabelFromSoundScreenData data = (ChooseLabelFromSoundScreenData) screenData;
    	
    	questionSoundId = data.getQuestionSoundId();

    	radioOption1.setText(data.getRadioOption1());
    	radioOption2.setText(data.getRadioOption2());
    	
    	sound1Id = data.getSound1Id();
    	sound2Id = data.getSound2Id();

    	questionSoundImage.setImage(ImageHolder.getImage("soundImage"));    	
    	sound1Image.setImage(ImageHolder.getImage("soundImage"));
    	sound2Image.setImage(ImageHolder.getImage("soundImage"));
    	
    	if(data.getRadioOption3() != null && data.getSound3Id() != null){
        	radioOption3.setText(data.getRadioOption3());
        	sound3Id = data.getSound3Id();
        	sound3Image.setImage(ImageHolder.getImage("soundImage"));
    	}
    	else{
    		System.out.println("remove radioHBox3");
        	radioVBox.getChildren().remove(radioHBox3);
    	}
    	
    	imageId = data.getImageId();
    	if(imageId == null){
        	System.out.println("image is null");
        	mainHBox.getChildren().remove(image);
    	}
    	else{
        	System.out.println("image : "+imageId);
    		image.setImage(ImageHolder.getImage(imageId));
    	}
    	
    	soundId = data.getSoundId();
    	if(soundId == null){
        	System.out.println("soundId is null");
        	mainHBox.getChildren().remove(soundImage);
    	}
    	else{
        	System.out.println("soundId : "+soundId);
        	soundImage.setImage(ImageHolder.getImage("soundImage"));
    	}

    	setIsSelection(true);
    	setAnswer(1);
    	submitButton.setDisable(false);
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
        
    }
    
    public void soundIconClicked(MouseEvent e){
    	ImageView sound = (ImageView)e.getSource();
    	
    	if(sound == soundImage){
    		SoundHolder.playSound(soundId);
    	}
    	else if(sound == sound1Image){
    		SoundHolder.playSound(sound1Id);
    	}
    	else if(sound == sound2Image){
    		SoundHolder.playSound(sound2Id);
    	}
    	else if(sound == sound3Image){
    		SoundHolder.playSound(sound3Id);
    	}
    	else{
    		System.err.println("error in soundIconClicked in ChooseLabelScreenController");
    	}
    }
  
    public void questionSoundIconClicked(){
    	SoundHolder.playSound(this.questionSoundId);
    }
}
