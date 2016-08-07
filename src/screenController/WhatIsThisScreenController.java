package screenController;

import application.ImageHolder;
import application.SoundHolder;
import application.Test;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import screenData.QuestionScreenData;
import screenData.WhatIsThisScreenData;

public class WhatIsThisScreenController extends QuestionScreenController{
        
	@FXML
	private ImageView image;
	
	@FXML
	private BorderPane mainBorderPane;
	
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
    	questionSoundId = data.getQuestionSoundId();

    	if(data.getMainQuestion() != null && data.getMainQuestionSoundId() != null){
        	mainQuestion.setText(data.getMainQuestion());
        	mainQuestionSoundId = data.getMainQuestionSoundId();
        	mainQuestionSoundImage.setImage(ImageHolder.getImage("soundImage"));
    	}
    	else{
    		mainBorderPane.getChildren().remove(mainQuestionHBox);
    	}
        
    	soundImage.setImage(ImageHolder.getImage(soundImage.getId()));

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
    
    public void imageClicked(){
    	System.out.println(soundId);
    	if(soundId != null)
    		SoundHolder.playSound(soundId);
    }
    
    public void soundIconClicked(){
    	System.out.println(questionSoundId);

    	SoundHolder.playSound(questionSoundId);
    }
    
    public void mainQuestionSoundIconClicked(){
    	System.out.println(mainQuestionSoundId);

    	SoundHolder.playSound(mainQuestionSoundId);
    }
    
}
