package application;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
	private String menuScreenId;
	private String chapterName;
	private String categoryName;

	HashMap<String, Set<Integer>> answers = new HashMap<String, Set<Integer>>();
	HashMap<String, Set<Integer>> correctAnswers = new HashMap<String,Set<Integer>>();

	private Queue<String> screenList = new LinkedList<String>();
	private ArrayList<String> testScreenList = new ArrayList<String>();

	private Integer totalQuestions;
	private Integer answeredQuestions;

	public Test(String chapter, String category,String chapterName, String categoryName, String menuScreenId){
		this.chapter = chapter;
		this.category = category;
		this.chapterName = chapterName;
		this.categoryName = categoryName;		
		this.menuScreenId = menuScreenId;
		
		this.answeredQuestions = 0;
		this.totalQuestions = ImagInLexis.parser.getCategoryTotalQuestions(category);
        
    	System.out.println("new Test: "+chapter+" "+category+" "+chapterName+" "+categoryName+" "+totalQuestions);

		for(String screenId : ImagInLexis.parser.getCategoriesScreenIdList(category)){
	        ImagInLexis.mainContainer.loadScreen(screenId, this);
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
		ImagInLexis.mainContainer.setScreen(nextScreen);
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
	
	@SuppressWarnings("unchecked")
	public void finishTest(){
		if(totalQuestions != answeredQuestions){
			System.err.println("error in finishTest answeredQuestions = "+answeredQuestions);
		}

		String name = ImagInLexis.userName;
		
		String score = calculateResults();
		System.out.println("score= "+score);
		
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		DateFormat timeFormat = new SimpleDateFormat("HH:mm");

		Date date = new Date();
		Date time = new Date();
		String strDate = dateFormat.format(date);
		String strTime = timeFormat.format(time);
		System.out.println("date= "+strDate);
		System.out.println("time= "+strTime);

		JSONObject obj = new JSONObject();

		obj.put("name", name);
		obj.put("score", score);
		obj.put("date", strDate);
		obj.put("time", strTime);
		
		ImagInLexis.parser.addScore(obj,chapterName,categoryName);
		ImagInLexis.parser.submitScores();

		ImagInLexis.mainContainer.setScreen(menuScreenId);
		//Platform.exit();
	
		this.cleanMemory();
		ImagInLexis.cleanMemory();		
	}
	
	public void submitAnswer(ScreenPane myScreenPane, Set<Integer> answersNo){
    	System.out.println("screenList: "+screenList.toString());
		answeredQuestions++;
		answers.put(screenList.peek(), answersNo);	
    	removeScreen();

    	String screenId = getNextScreen();
    	if(screenId == null)
    		return;
    	
        QuestionScreenController screenController = (QuestionScreenController) ImagInLexis.mainContainer.getController(screenId);
        screenController.setAnsweredQuestions(answeredQuestions);
	}
	
    public void addToScreenList(String screenId){
    	System.out.println("addToScreenList: "+screenId);
    	screenList.add(screenId);
    	testScreenList.add(screenId);
    }
    
    public String getNextScreen(){
    	
    	if(!screenList.isEmpty()){
    		String screenId = screenList.remove();
    		screenList.add(screenId);
    		String retScreenId = screenList.peek();
            QuestionScreenController screenController = (QuestionScreenController) ImagInLexis.mainContainer.getController(retScreenId);
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
    
    private void cleanMemory(){
    	for(String screenId: testScreenList){
    		System.out.println("unload screen : "+screenId);
    		ImagInLexis.mainContainer.unloadScreen(screenId);
    	}
    	
    	this.testScreenList.clear();
    	
		this.chapter = null;
		this.category = null;
		this.chapterName = null;
		this.categoryName = null;		
		this.menuScreenId = null;
    }
}
