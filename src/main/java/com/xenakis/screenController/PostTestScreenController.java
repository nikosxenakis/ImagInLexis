/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xenakis.screenController;

import com.xenakis.ImagInLexis;
import com.xenakis.application.ImageHolder;
import com.xenakis.application.SoundHolder;
import com.xenakis.application.TestUtil;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
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
	
	TestUtil testUtil = null;

    public void init(TestUtil testUtil){
    	this.testUtil = testUtil;
		
    	mainWindow.setStyle(testUtil.getMainWindowStyle());
    	score.setText( testUtil.getScoreNum()+"%");
    	correctAnswers.setText(String.valueOf(testUtil.getCorrectAnswers()));
    	wrongAnswers.setText(String.valueOf(testUtil.getWrongAnswers()));

    	backgroundImage.setImage(ImageHolder.getImage("background"));

    	boolean passed = false;
    	
    	if(
    			(testUtil.getChapterName().equals("Αναγνώριση") && testUtil.getScoreNum() >= 80) ||
    			(testUtil.getChapterName().equals("Κατονομασία") && testUtil.getScoreNum() >= 90) ||
    			(testUtil.getChapterName().equals("Συσχετιζόμενες Έννοιες") && testUtil.getScoreNum() >= 90)
    	){
    		passed = true;
    	}
    	
		if(passed == true){
			SoundHolder.playSound("correctSound");
			resultText.setText("Επιτυχία");
	    	infoPane.setStyle("-fx-background-radius: 15; -fx-background-color: lightgreen; -fx-opacity: 0.9;");
	    	emotionImage.setImage(ImageHolder.getImage("happyIcon"));

		}
		else{
			resultText.setText("Αποτυχία");
			SoundHolder.playSound("wrongSound");
	    	infoPane.setStyle("-fx-background-radius: 15; -fx-background-color: lightcoral; -fx-opacity: 0.9;");
	    	emotionImage.setImage(ImageHolder.getImage("sadIcon"));
		}
		
    	infoPane.setMaxWidth(500);

    }
    
    public void endTest(MouseEvent e){
    	SoundHolder.playSound("endProgramSound");
    	ImagInLexis.mainContainer.setScreen(testUtil.getMenuScreenId());
    }
}
