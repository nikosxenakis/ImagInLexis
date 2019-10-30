/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xenakis.screenController;

import com.xenakis.ImagInLexis;
import com.xenakis.databaseService.CategoryUtil;
import com.xenakis.service.TestService;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Ellipse;

public class RecognitionChapterScreenController extends ScreenController{

	@FXML 
	private AnchorPane mainWindow;
		
    @FXML
    private ImageView backgroundImage;
    
    @FXML
    private ImageView homeImage;
    
    public void homeIconEntered(){
    	String enabledImageStyle = "-fx-effect: dropshadow(three-pass-box, rgba(255,100,100,0.8), 10, 0, 0, 0)";
    	homeImage.setStyle(enabledImageStyle);	
    }
    
    public void homeIconExited(){
    	homeImage.setStyle(null);
    }
    
    public void homeIconClicked(){
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
        
        new TestService("recognition",category,"Αναγνώριση", CategoryUtil.getCategoryGreekName(category),"RecognitionChapterScreen");
    }
}
