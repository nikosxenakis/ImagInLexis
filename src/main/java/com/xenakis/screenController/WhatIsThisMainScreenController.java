package com.xenakis.screenController;

import com.xenakis.ImagInLexis;

public class WhatIsThisMainScreenController extends ScreenController{
    
    public void homeIconClicked(){
        ImagInLexis.mainContainer.setScreen("MainScreen");
    }
}
