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

public class KatonomasiaChapterScreenController extends ScreenController{

	@FXML 
	private AnchorPane mainWindow;
		
    @FXML
    private ImageView backgroundImage;
    
    @FXML
    private ImageView homeImage;
    
    public void initialize(java.net.URL location, java.util.ResourceBundle resources) {   
    	
    	homeImage.setImage(ImageHolder.getImage("logo"));
    	backgroundImage.setImage(ImageHolder.getImage("background"));
    	
        for (Node node : mainWindow.getChildrenUnmodifiable()) {
        	if(node instanceof ImageView){
        		ImageView image = (ImageView) node;
        		String id = image.getId();
        		if(id != null)
        			image.setImage(ImageHolder.getImage(id));
        	}
        }  
    }
    
    public void homeIconEntered(){
    	String enabledImageStyle = "-fx-effect: dropshadow(three-pass-box, rgba(255,100,100,0.8), 10, 0, 0, 0)";
    	homeImage.setStyle(enabledImageStyle);	
    }
    
    public void homeIconExited(){
    	homeImage.setStyle(null);
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
        	System.err.println("error in mouseOverCircle in CombinationalChapterScreenController");
    	}
    }
   
    public void mouseOutCircle(MouseEvent e){
    	
    	Ellipse el = (Ellipse)e.getSource();
    	
    	if(el instanceof Ellipse){
    		el.setStyle("-fx-opacity: 0.7");
    	}
    	else{
        	System.err.println("error in mouseOutCircle in CombinationalChapterScreenController");
    	}	
    }
    
    public void start(MouseEvent e){

    	Ellipse el = (Ellipse)e.getSource();
    	String category = el.getId();

        new Test("katonomasia",category,"Κατονομασία",ImagInLexis.parser.getCategoryNameFromCategory(category),"KatonomasiaChapterScreen");
    }
}
