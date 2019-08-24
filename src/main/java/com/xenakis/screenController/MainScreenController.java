/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xenakis.screenController;

import com.xenakis.ImagInLexis;
import com.xenakis.application.SoundHolder;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import java.net.URL;
import java.util.ResourceBundle;

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
  
    @FXML
    private ImageView logoImage;

    @FXML
    private TextField username;

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

    @FXML
    public void initialize(URL url, ResourceBundle rb) {
        SoundHolder.playSound("endProgramSound");
//        SoundHolder.playSound("startProgramSound");
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
                ImagInLexis.mainContainer.setScreen("AboutUsMainScreen");
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
        SoundHolder.stopSound("endProgramSound");

    }

//    @FXML
    public void inputChanged(KeyEvent event) {
        System.out.println(username.getText());
        ImagInLexis.setUserName(username.getText());
    }

}
