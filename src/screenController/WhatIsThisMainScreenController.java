/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package screenController;

import application.ImagInLexis;
import application.ImageHolder;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

public class WhatIsThisMainScreenController extends ScreenController{

	@FXML
    private Text text;
    
    @FXML
    private ImageView homeImage;
	
    public void initialize(java.net.URL location, java.util.ResourceBundle resources) {    	
    	homeImage.setImage(ImageHolder.getImage("logo"));  
    }
    
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
}
