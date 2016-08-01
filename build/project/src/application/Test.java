package application;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

import org.json.simple.JSONObject;

import screenController.QuestionScreenController;
import screenData.QuestionScreenData;
import screenData.ScreenDataHolder;

public class Test{

	private String chapter;
	private String category;

	HashMap<String, Set<Integer>> answers = new HashMap<String, Set<Integer>>();
	HashMap<String, Set<Integer>> correctAnswers = new HashMap<String,Set<Integer>>();

	private Queue<String> screenList = new LinkedList<String>();

	private Integer totalQuestions;
	private Integer answeredQuestions;

	public Test(String chapter, String category){
		this.chapter = chapter;
		this.category = category;

		answeredQuestions = 0;
		totalQuestions = JavaFXApplication.parser.getCategoryTotalQuestions(category);
		//System.out.println("totalQuestions = "+totalQuestions);
		//System.out.println("categoriesScreenIdList = "+JavaFXApplication.parser.getCategoriesScreenIdList(category));
        
		for(String screenId : JavaFXApplication.parser.getCategoriesScreenIdList(category)){
	        JavaFXApplication.mainContainer.loadScreen(screenId, this);
            this.addToScreenList(screenId);
			QuestionScreenData screenData = ScreenDataHolder.getScreenData(screenId);
			Set<Integer> answers = screenData.getAnswers();
			correctAnswers.put(screenId, answers);	
		}

	}
	
	public String getChapter(){
		return this.chapter;
	}
	
	public String getCategory(){
		return this.category;
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
	
	private String calculateResults(){
		
		System.out.println("answers: "+answers.toString());
		System.out.println("correctAnswers: "+correctAnswers.toString());
		
			
		double correct = 0;
		double wrong = 0;

		for (String key : answers.keySet()) {
			if(answers.get(key).equals(correctAnswers.get(key)))
				correct++;
			else
				wrong++;
		}

		Integer res = (int) (((correct)/(correct+wrong))*100);
		return Integer.toString(res)+"%";
		
	}
	
	public void finishTest(){
		if(totalQuestions != answeredQuestions){
			System.err.println("error in finishTest answeredQuestions = "+answeredQuestions);
		}

		String score = calculateResults();
		System.out.println("score= "+score);
		
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		DateFormat timeFormat = new SimpleDateFormat("HH:mm");

		Date date = new Date();
		Date time = new Date();
		String strDate = dateFormat.format(date);
		String strTime = timeFormat.format(time);
		System.out.println("date= "+strDate);
		System.out.println("time= "+strTime);

		JSONObject obj = new JSONObject();
		obj.put("score", score);
		obj.put("date", date);
		obj.put("time", time);
		
		JavaFXApplication.parser.addScore(obj,chapter,category);
        
        String screenId = "homeScreen";
		JavaFXApplication.mainContainer.setScreen(screenId);
		//Platform.exit();
	}
	
	public void submitAnswer(ScreenPane myScreenPane, Set<Integer> answersNo){
    	System.out.println("screenList: "+screenList.toString());
		answeredQuestions++;
		answers.put(screenList.peek(), answersNo);	
    	removeScreen();

    	String screenId = getNextScreen();
    	if(screenId == null)
    		return;
    	
        QuestionScreenController screenController = (QuestionScreenController) JavaFXApplication.mainContainer.getController(screenId);
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
    		String retScreenId = screenList.peek();
            QuestionScreenController screenController = (QuestionScreenController) JavaFXApplication.mainContainer.getController(retScreenId);
            screenController.setAnsweredQuestions(answeredQuestions);
            
    		return retScreenId;
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
    
    public boolean isLastQuestion(){
    	System.out.println(totalQuestions.toString()+answeredQuestions.toString());
		if(totalQuestions - 1 == answeredQuestions){
			return true;
		}
		return false;
    }
}
