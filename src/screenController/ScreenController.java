package screenController;

import application.ScreenPane;
import application.Test;
import screenData.ScreenData;

public interface ScreenController {
	
	public void setScreenPane(ScreenPane screenPage);   
    
    public void setData(ScreenData screenData, Test test);
    
    public void setAnsweredQuestions(Integer answeredQuestions);
}
