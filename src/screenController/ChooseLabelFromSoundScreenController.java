package screenController;

import application.Test;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.input.MouseEvent;
import screenData.ChooseLabelFromSoundScreenData;
import screenData.QuestionScreenData;

public class ChooseLabelFromSoundScreenController extends QuestionScreenController{
  
	@FXML
	private RadioButton radioOption1;
	
	@FXML
	private RadioButton radioOption2;
	
	@FXML
	private RadioButton radioOption3;
	
    public void setData(QuestionScreenData screenData, Test test){
    	
    	System.out.println("set Data in ChooseLabelFromSoundScreenController");
    	super.setData(screenData, test);

    	if(!(screenData instanceof ChooseLabelFromSoundScreenData)){
        	System.err.println("screenData is not ChooseLabelFromSoundScreenData");
    		return;
    	}

    	ChooseLabelFromSoundScreenData data = (ChooseLabelFromSoundScreenData) screenData;
    	
    	radioOption1.setText(data.getRadioOption1());
    	radioOption2.setText(data.getRadioOption2());
    	radioOption3.setText(data.getRadioOption3());

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
    
}
