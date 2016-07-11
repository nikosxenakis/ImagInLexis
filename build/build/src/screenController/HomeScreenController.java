/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package screenController;

import application.Test;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

public class HomeScreenController extends ScreenController{

    @FXML
    private Button startProfessionButton;

    @FXML
    private Button startAnimalsButton;

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
