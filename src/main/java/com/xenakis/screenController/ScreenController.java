package com.xenakis.screenController;

import java.net.URL;
import java.util.ResourceBundle;
import com.xenakis.application.ScreenPane;
import javafx.fxml.Initializable;
import org.apache.log4j.Logger;

public abstract class ScreenController implements Initializable {
    private ScreenPane myScreenPane;

	static final Logger logger = Logger.getLogger(ScreenController.class);

	/**
     * Initializes the controller class.
     */
	@Override
    public void initialize(URL url, ResourceBundle rb) {
    }

	public void setScreenPane(ScreenPane screenPage){
		myScreenPane = screenPage;
	}
	
	ScreenPane getScreenPane(){
		return myScreenPane;
	}

}
