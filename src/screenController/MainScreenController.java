/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package screenController;

import application.ImagInLexis;
import application.ImageHolder;
import application.SoundHolder;
import javafx.animation.RotateTransition;
import javafx.fxml.FXML;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class MainScreenController extends ScreenController{

	@FXML
    private Text chapter1Button;

    @FXML
    protected Text chapter2Button;
  
    @FXML
    protected Text chapter3Button;
    
    @FXML
    private ImageView whatIsThisMainButton;
    
    @FXML
    private ImageView instructionsButton;
    
    @FXML
    private ImageView scoreTableButton;
    
    @FXML
    private Text whatIsThisMainLabel;
    
    @FXML
    private Text instructionsLabel;
    
    @FXML
    private Text scoreTableLabel;
    
    @FXML
    private ImageView backgroundImage;
    
    public void initialize(java.net.URL location, java.util.ResourceBundle resources) {    	
    	backgroundImage.setImage(ImageHolder.getImage("mainMenu"));
    }
    
    public void mouseOver(MouseEvent e){
    	Text item = (Text)e.getSource();

    	DropShadow ds = new DropShadow();
    	ds.setOffsetY(3.0f);
    	ds.setColor(Color.color(0.4f, 0.4f, 0.4f));
    	 
    	item.setEffect(ds);
    }
   
    public void mouseOut(MouseEvent e){
    	Text item = (Text)e.getSource();
    	item.setEffect(null);
    }
    
    public void start(MouseEvent e){
    	
    	if(e.getSource() instanceof Text){
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
            else if((Text)e.getSource() == whatIsThisMainLabel){
                ImagInLexis.mainContainer.setScreen("WhatIsThisMainScreen");
            } 	
            else if((Text)e.getSource() == instructionsLabel){
            } 	
            else if((Text)e.getSource() == scoreTableLabel){
            	ScoreTableScreenController ctrl = (ScoreTableScreenController) ImagInLexis.mainContainer.getController("ScoreTableScreen");
            	ctrl.init();
                ImagInLexis.mainContainer.setScreen("ScoreTableScreen");
            }    
            else{
            	System.err.println("unknown source in main screen controller");
            }
            
    	}
    	else if(e.getSource() instanceof ImageView){
            if((ImageView)e.getSource() == whatIsThisMainButton){
                ImagInLexis.mainContainer.setScreen("WhatIsThisMainScreen");
            } 	
            else if((ImageView)e.getSource() == instructionsButton){
            } 	
            else if((ImageView)e.getSource() == scoreTableButton){
            	ScoreTableScreenController ctrl = (ScoreTableScreenController) ImagInLexis.mainContainer.getController("ScoreTableScreen");
            	ctrl.init();
                ImagInLexis.mainContainer.setScreen("ScoreTableScreen");
            }    
            else{
            	System.err.println("unknown source in main screen controller");
            }
    	} 	
    	else{
        	System.err.println("unknown source in main screen controller");
    	}

    }
    
    public void rotateImage(MouseEvent e){
    	ImageView image = (ImageView)e.getSource();
    	
        RotateTransition rt = new RotateTransition(Duration.millis(10), image);
        rt.setByAngle(10);
        rt.setCycleCount(50);
        rt.setAutoReverse(true);
        rt.play();


        rt.setToAngle(0);

    }
    /*
     * 
     *      Rectangle rect = new Rectangle (100, 40, 100, 100);
     rect.setArcHeight(50);
     rect.setArcWidth(50);
     rect.setFill(Color.VIOLET);
 
*/
}
