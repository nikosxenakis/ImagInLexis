package screenController;

import java.net.URL;
import java.util.ResourceBundle;

import application.ScreenPane;
import javafx.fxml.Initializable;

public abstract class ScreenController implements Initializable{
    private ScreenPane myScreenPane;
 
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    
    
	public void setScreenPane(ScreenPane screenPage){
		myScreenPane = screenPage;
	}
	
	public ScreenPane getScreenPane(){
		return myScreenPane;
	}
}
