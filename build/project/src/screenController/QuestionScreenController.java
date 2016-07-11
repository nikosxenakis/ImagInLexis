package screenController;

import application.Test;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import screenData.QuestionScreenData;

public abstract class QuestionScreenController extends ScreenController{
    @FXML
    private Button submitButton;
    
    @FXML
    private Button nextButton;
    
    private Test test;
    private boolean isSelection = false;
    private Integer answer = null;

	public Test getTest(){
		return test;
	}
	
	public boolean getIsSelection(){
		return isSelection;
	}
	
	public Button getSubmitButton(){
		return submitButton;
	}
	
	public Button getNextButton(){
		return nextButton;
	}
	
	public Integer getAnswer(){
		return answer;
	}
	
	public void setAnswer(Integer answer){
		this.answer = answer;
	}
	
	public void setIsSelection(boolean isSelection){
		this.isSelection = isSelection;
	}
	
    public void setData(QuestionScreenData screenData, Test test){
    	this.test = test;
    }
    
    public void setAnsweredQuestions(Integer answeredQuestions){
    	
    }
    
    public void clicked(MouseEvent e){
    	
    	//play sound
    	/*
    	String musicFile = "/sounds/horn.mp3";
   	 	Media sound= new Media(getClass().getResource(musicFile).toExternalForm());
    	MediaPlayer mediaPlayer = new MediaPlayer(sound);
    	mediaPlayer.play();
    	*/
	
        if((Button)e.getSource() == submitButton){
        	if(getIsSelection() == false)
        		return;
        	
        	System.out.println("submitButton clicked");
            
        	getTest().submitAnswer(getScreenPane(), getAnswer());
        	getTest().nextQuestion(getScreenPane());

        }
        if((Button)e.getSource() == nextButton){
        	System.out.println("nextButton clicked");

        	getTest().nextQuestion(getScreenPane());

        }
        
    }
}
