package application;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

import javafx.application.Platform;

public class Test{

	HashMap<String,Integer> answers = new HashMap<String, Integer>();
	
	private Queue<String> screenList = new LinkedList<String>();

	private Integer totalQuestions;
	private Integer answeredQuestions;
	
	Test(String category){
		answeredQuestions = 0;
		totalQuestions = JavaFXApplication.parser.getCategoryTotalQuestions(category);
		System.out.println("totalQuestions = "+totalQuestions);
		System.out.println("categoriesScreenIdList = "+JavaFXApplication.parser.getCategoriesScreenIdList(category));

		for(String screenId : JavaFXApplication.parser.getCategoriesScreenIdList(category)){
	        JavaFXApplication.mainContainer.loadScreen(screenId, this);
            this.addToScreenList(screenId);

		}
	}
	
	
    
	public void startTest(){
		System.out.println("start test");
		String nextScreen = screenList.peek();
		System.out.println(nextScreen);
		JavaFXApplication.mainContainer.setScreen(nextScreen);
	}
    
	public void nextQuestion(ScreenPane myScreenPane){
    	String screenId = this.getNextScreen();
    	
    	if(screenId == null){
    		finishTest();
    		return;
    	}
    		
    	System.out.println("next screenId = "+screenId);
    	myScreenPane.setScreen(screenId);
	}
	
	public void finishTest(){
		if(totalQuestions != answeredQuestions){
			System.out.println("error in finishTest answeredQuestions = "+answeredQuestions);
		}

		System.out.println("answers: "+answers.toString());
		
		Platform.exit();
	}
	
	public void submitAnswer(ScreenPane myScreenPane, Integer answerNo){
    	System.out.println("screenList: "+screenList.toString());
		answeredQuestions++;
		answers.put(screenList.peek(), answerNo);	
    	removeScreen();

    	String screenId = getNextScreen();
    	if(screenId == null)
    		return;
    	
        ScreenController screenController = JavaFXApplication.mainContainer.getController(screenId);
        screenController.setAnsweredQuestions(answeredQuestions);
	}
	
    public void addToScreenList(String screenId){
    	System.out.println("addToScreenList: "+screenId);
    	screenList.add(screenId);
    }
    
    public String getNextScreen(){
    	
    	if(!screenList.isEmpty()){
    		String screenId = screenList.remove();
    		screenList.add(screenId);
    		return screenList.peek();
    	}
    	else
    		return null;
    	
    }
    
    public void removeScreen(){
    
    	if(!screenList.isEmpty())
    		screenList.remove();
    }
    
    public Integer getTotalQuestions(){
    	return totalQuestions;
    }
    
    public Integer getAnsweredQuestions(){
    	return answeredQuestions;
    }
}
