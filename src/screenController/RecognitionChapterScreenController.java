/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package screenController;

import application.ImagInLexis;
import application.Test;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Ellipse;

public class RecognitionChapterScreenController extends ScreenController{

    public void homeIconClicked(MouseEvent e){
        ImagInLexis.mainContainer.setScreen("MainScreen");
    }
    
    public void mouseOverCircle(MouseEvent e){
    	
    	Ellipse el = (Ellipse)e.getSource();
    	
    	if(el instanceof Ellipse){
    		el.setStyle("-fx-opacity: 1");
    	}
    	else{
        	System.err.println("error in mouseOverCircle in RecognitionChapterScreenController");
    	}
    }
   
    public void mouseOutCircle(MouseEvent e){
    	
    	Ellipse el = (Ellipse)e.getSource();
    	
    	if(el instanceof Ellipse){
    		el.setStyle("-fx-opacity: 0.7");
    	}
    	else{
        	System.err.println("error in mouseOutCircle in RecognitionChapterScreenController");
    	}	
    }
    
    public void start(MouseEvent e){

    	Ellipse el = (Ellipse)e.getSource();
    	String category = el.getId();
    	
    	System.out.println("startButton clicked for category: "+category);

        Test test = new Test("recognition",category,"RecognitionChapterScreen");
        test.startTest();

    }
}
