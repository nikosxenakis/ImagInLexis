/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package screenController;

import application.ImagInLexis;
import application.SoundHolder;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

public class MainScreenController extends ScreenController{

	@FXML
    private Text chapter1Button;

    @FXML
    protected Text chapter2Button;
  
    @FXML
    protected Text chapter3Button;
    
    @FXML
    protected Text whatIsThisButton;
    
    @FXML
    protected Text instructionsButton;
    
    @FXML
    protected Text scoreTableButton;
    
    public void initialize(java.net.URL location, java.util.ResourceBundle resources) {    	

    }
    
    public void start(MouseEvent e){
    	
        if((Text)e.getSource() == chapter1Button){
        	SoundHolder.playSound("chapter1Anagnorisi");
            ImagInLexis.mainContainer.setScreen("RecognitionChapterScreen");
        }
        else if((Text)e.getSource() == chapter2Button){
        	SoundHolder.playSound("chapter2Katonomasia");
            ImagInLexis.mainContainer.setScreen("KatonomasiaChapterScreen");
        } 	
        else if((Text)e.getSource() == chapter3Button){
        	SoundHolder.playSound("chapter3Sisxetizomenesennoies");
            ImagInLexis.mainContainer.setScreen("CombinationalChapterScreen");
        } 	
        else if((Text)e.getSource() == whatIsThisButton){
        } 	
        else if((Text)e.getSource() == instructionsButton){
        } 	
        else if((Text)e.getSource() == scoreTableButton){
            ImagInLexis.mainContainer.setScreen("ScoreTableScreen");
        }    
        else{
        	System.err.println("unknown source in main screen controller");
        }
    }
}
