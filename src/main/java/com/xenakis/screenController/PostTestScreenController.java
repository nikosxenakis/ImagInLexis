/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xenakis.screenController;

import com.xenakis.ImagInLexis;
import com.xenakis.service.SoundUtil;
import com.xenakis.service.ImageUtil;
import com.xenakis.application.TestUtil;
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

	private TestUtil testUtil = null;

    public void init(TestUtil testUtil){
    	this.testUtil = testUtil;
		
    	mainWindow.setStyle(testUtil.getMainWindowStyle());
    	score.setText( testUtil.getScoreNum()+"%");
    	correctAnswers.setText(String.valueOf(testUtil.getCorrectAnswers()));
    	wrongAnswers.setText(String.valueOf(testUtil.getWrongAnswers()));

    	backgroundImage.setImage(ImageUtil.getImage("background"));

    	boolean passed = false;
    	
    	if(
    			(testUtil.getChapterName().equals("Αναγνώριση") && testUtil.getScoreNum() >= 80) ||
    			(testUtil.getChapterName().equals("Κατονομασία") && testUtil.getScoreNum() >= 90) ||
    			(testUtil.getChapterName().equals("Συσχετιζόμενες Έννοιες") && testUtil.getScoreNum() >= 90)
    	){
    		passed = true;
    	}
    	
		if(passed){
			SoundUtil.playSound("correctSound");
			resultText.setText("Επιτυχία");
	    	infoPane.setStyle("-fx-background-radius: 15; -fx-background-color: lightgreen; -fx-opacity: 0.9;");
	    	emotionImage.setImage(ImageUtil.getImage("happyIcon"));

		}
		else{
			resultText.setText("Αποτυχία");
			SoundUtil.playSound("wrongSound");
	    	infoPane.setStyle("-fx-background-radius: 15; -fx-background-color: lightcoral; -fx-opacity: 0.9;");
	    	emotionImage.setImage(ImageUtil.getImage("sadIcon"));
		}
		
    	infoPane.setMaxWidth(500);

    }
    
    public void endTest(){
    	SoundUtil.playSound("endProgramSound");
    	ImagInLexis.mainContainer.setScreen(testUtil.getMenuScreenId());
    }
}
