/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xenakis.screenController;

import com.xenakis.ImagInLexis;
import javafx.fxml.FXML;

public class AboutUsMainScreenController extends ScreenController {
    @FXML
    public void homeIconClicked(){
        ImagInLexis.mainContainer.setScreen("MainScreen");
    }
}
