/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

import java.net.URL;
import java.util.ResourceBundle;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

public class HomeScreenController implements Initializable, ScreenController {
    private ScreenPane myScreenPane;
    private Test test;

    @FXML
    private Button startProfessionButton;

    @FXML
    private Button startAnimalsButton;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    

    public void setData(ScreenData screenData, Test test){
    	System.out.println("set Data Screen1Controller");
    	
    	this.test = test;
    	
    	if(!(screenData instanceof ChooseImageScreenData)){
        	System.out.println("screenData is not ChooseImageScreenData");
    		return;
    	}
    	
    	ChooseImageScreenData data = (ChooseImageScreenData) screenData;
    	    	    	
    	//choose the initialization resourses
    	
    }
    
    public void setScreenPane(ScreenPane screenPage) {
        myScreenPane = screenPage;
    }
    
    public void setAnsweredQuestions(Integer answeredQuestions){
    	
    }
    
    public void start(MouseEvent e){
    	
    	//play sound
    	/*
    	String musicFile = "sounds/trainHonk.wav";
   	 	Media sound= new Media(getClass().getResource(musicFile).toExternalForm());
    	MediaPlayer mediaPlayer = new MediaPlayer(sound);
    	mediaPlayer.play();
        */
        
        if((Button)e.getSource() == startProfessionButton){
        	System.out.println("startButton clicked");
            
            Test test = new Test("professions");
            test.startTest();

        }
        else if((Button)e.getSource() == startAnimalsButton){
        	System.out.println("startButton clicked");
            
            Test test = new Test("animals");
            test.startTest();

        }
        
    }
}
