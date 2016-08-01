package screenController;

import application.ImageHolder;
import application.Test;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import screenData.ChooseLabelScreenData;
import screenData.QuestionScreenData;

public class ChooseLabelScreenController extends QuestionScreenController{
        
	@FXML
	private ImageView image;

	@FXML
	private RadioButton radioOption1;
	
	@FXML
	private RadioButton radioOption2;
	
	@FXML
	private RadioButton radioOption3;
	
    public void setData(QuestionScreenData screenData, Test test){
    	
    	System.out.println("set Data in ChooseLabelScreenController");
    	super.setData(screenData, test);

    	if(!(screenData instanceof ChooseLabelScreenData)){
        	System.err.println("screenData is not ChooseLabelScreenData");
    		return;
    	}

    	ChooseLabelScreenData data = (ChooseLabelScreenData) screenData;
    	    	  	    	
    	Image tmpImage = ImageHolder.getImage(data.getImageId());
    	image.setImage(tmpImage);
    	
    	radioOption1.setText(data.getRadioOption1());
    	radioOption2.setText(data.getRadioOption2());
    	radioOption3.setText(data.getRadioOption3());

    	setIsSelection(true);
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
    
}
