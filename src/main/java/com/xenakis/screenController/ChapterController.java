package com.xenakis.screenController;

import com.xenakis.ImagInLexis;

abstract class ChapterController extends ScreenController {
    public void homeIconClicked(){
        ImagInLexis.mainContainer.setScreen("MainScreen");
    }
}
