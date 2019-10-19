/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xenakis.screenController;

import com.xenakis.ImagInLexis;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;

public class WhatIsThisMainScreenController extends ScreenController{
    
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
}
