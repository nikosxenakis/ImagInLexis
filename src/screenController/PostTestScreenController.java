/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package screenController;

import application.ImagInLexis;
import application.ImageHolder;
import application.SoundHolder;
import application.Test;
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
	
	Test test = null;

    public void init(Test test){
    	this.test = test;
		
    	mainWindow.setStyle(test.getMainWindowStyle());	
    	score.setText(test.getScoreNum().toString()+"%");
    	correctAnswers.setText(test.getCorrectAnswers().toString());
    	wrongAnswers.setText(test.getWrongAnswers().toString());

    	backgroundImage.setImage(ImageHolder.getImage("background"));

    	boolean passed = false;
    	
    	if(
    			(test.getChapterName().equals("Αναγνώριση") && test.getScoreNum() >= 80) ||
    			(test.getChapterName().equals("Κατονομασία") && test.getScoreNum() >= 90) ||
    			(test.getChapterName().equals("Συσχετιζόμενες Έννοιες") && test.getScoreNum() >= 90)
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
    	SoundHolder.playSound("startProgramSound");
    	ImagInLexis.mainContainer.setScreen(test.getMenuScreenId());
    }
}
