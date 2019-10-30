/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xenakis.screenController;

import com.xenakis.ImagInLexis;
import com.xenakis.service.TestService;
import com.xenakis.service.ImageService;
import com.xenakis.service.SoundService;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class PostTestScreenController extends ScreenController{

	@FXML
	private AnchorPane mainWindow;
	
	@FXML
	private VBox infoPane;
	
	@FXML
	private Text resultText;

	@FXML
	private ImageView emotionImage;
	
	@FXML
	private Text score;
	
	@FXML
	private Text correctAnswers;

	@FXML
	private Text wrongAnswers;
    
	@FXML
	private ImageView backgroundImage;

	private TestService testService = null;

    public void init(TestService testService){
    	this.testService = testService;
		
    	mainWindow.setStyle(testService.getMainWindowStyle());
    	score.setText( testService.getScoreNum()+"%");
    	correctAnswers.setText(String.valueOf(testService.getCorrectAnswers()));
    	wrongAnswers.setText(String.valueOf(testService.getWrongAnswers()));

    	backgroundImage.setImage(ImageService.getImage("background"));

    	boolean passed = false;
    	
    	if(
    			(testService.getChapterName().equals("Αναγνώριση") && testService.getScoreNum() >= 80) ||
    			(testService.getChapterName().equals("Κατονομασία") && testService.getScoreNum() >= 90) ||
    			(testService.getChapterName().equals("Συσχετιζόμενες Έννοιες") && testService.getScoreNum() >= 90)
    	){
    		passed = true;
    	}
    	
		if(passed){
			SoundService.playSound("correctSound");
			resultText.setText("Επιτυχία");
	    	infoPane.setStyle("-fx-background-radius: 15; -fx-background-color: lightgreen; -fx-opacity: 0.9;");
	    	emotionImage.setImage(ImageService.getImage("happyIcon"));

		}
		else{
			resultText.setText("Αποτυχία");
			SoundService.playSound("wrongSound");
	    	infoPane.setStyle("-fx-background-radius: 15; -fx-background-color: lightcoral; -fx-opacity: 0.9;");
	    	emotionImage.setImage(ImageService.getImage("sadIcon"));
		}
		
    	infoPane.setMaxWidth(500);

    }
    
    public void endTest(){
		SoundService.playSound("endProgramSound");
    	ImagInLexis.mainContainer.setScreen(testService.getMenuScreenId());
    }
}
