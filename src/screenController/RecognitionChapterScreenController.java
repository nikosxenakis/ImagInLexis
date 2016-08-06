/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package screenController;

import application.ImagInLexis;
import application.ImageHolder;
import application.Test;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Ellipse;

public class RecognitionChapterScreenController extends ScreenController{

	@FXML 
	private AnchorPane mainWindow;
		
    public void initialize(java.net.URL location, java.util.ResourceBundle resources) {   
    	
        for (Node node : mainWindow.getChildrenUnmodifiable()) {
        	if(node instanceof ImageView){
        		ImageView image = (ImageView) node;
        		String id = image.getId();
        		if(id != null)
        			image.setImage(ImageHolder.getImage(id));
        	}
        }  
    }
    
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
    	
        Test test = new Test("recognition",category,"Αναγνώριση",ImagInLexis.parser.getCategoryNameFromCategory(category),"RecognitionChapterScreen");
        test.startTest();

    }
}
