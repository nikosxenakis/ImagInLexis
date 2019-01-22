package application;

import org.json.simple.JSONObject;
import screenController.PostTestScreenController;
import screenController.PreTestScreenController;
import screenController.QuestionScreenController;
import screenController.ScreenController;
import screenData.ChooseInImageScreenData;
import screenData.QuestionScreenData;
import screenData.ScreenDataHolder;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class Test{

	private String chapter;
	private String category;
	private String menuScreenId;
	private String chapterName;
	private String categoryName;
	private String mainWindowStyle;
	private String mainPaneStyle;
	private String infoPaneStyle;
	private Integer scoreNum;
	
	private Integer correctAnswersNum;
	private Integer wrongAnswersNum;

	HashMap<String, Set<Integer>> answers = new HashMap<String, Set<Integer>>();
	HashMap<String, Set<Integer>> correctAnswers = new HashMap<String,Set<Integer>>();

    private HashMap<String, Boolean> absoluteAnswers = new HashMap<String, Boolean>();

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
		
		this.mainWindowStyle = "-fx-border-width: 10;";
		this.mainPaneStyle = "-fx-background-radius: 15;";
    	this.infoPaneStyle = "-fx-background-radius: 15;";

    	if(chapterName.equals("Αναγνώριση")){
        	this.mainWindowStyle += "-fx-background-color:  #DDE3A8;";// -fx-border-color:  #9ED5DB";
        	this.mainPaneStyle+= "-fx-background-color:  #7ECCC7;";
        	this.infoPaneStyle+= "-fx-background-color:  #7ECCA4;";
    	}
    	else if(chapterName.equals("Κατονομασία")){
        	this.mainWindowStyle += "-fx-background-color:  #FFD154;";// -fx-border-color:  #CF903B";
        	this.mainPaneStyle+= "-fx-background-color:  #80DBBB;";
        	this.infoPaneStyle+= "-fx-background-color:  #BADB80;";
    	}
    	else if(chapterName.equals("Συσχετιζόμενες Έννοιες")){
        	this.mainWindowStyle += "-fx-background-color:  #ED591F;";// -fx-border-color:  #E08E70";
        	this.mainPaneStyle+= "-fx-background-color:  #AE99C2;";
        	this.infoPaneStyle+= "-fx-background-color:  #E39DAD;";
    	}
    	else{
    		System.err.println("error in Test Screen no such a chapter");
    		return;
    	}

		this.answeredQuestions = 0;
		this.totalQuestions = ImagInLexis.parser.getCategoryTotalQuestions(category);
                
    	System.out.println("new Test: "+chapter+" "+category+" "+chapterName+" "+categoryName+" "+totalQuestions);

    	
    	//load PreTestScreen
        ImagInLexis.mainContainer.setScreen("PreTestScreen");
        ScreenController sc = (ScreenController) ImagInLexis.mainContainer.getController("PreTestScreen");
        PreTestScreenController sc2 = (PreTestScreenController) sc;
        sc2.init(this);
            	
		for(String screenId : ImagInLexis.parser.getCategoriesScreenIdList(category)){
	        ImagInLexis.mainContainer.loadScreen(screenId, this);
            this.addToScreenList(screenId);
			QuestionScreenData screenData = ScreenDataHolder.getScreenData(screenId);
			
			if(screenData instanceof ChooseInImageScreenData){
				absoluteAnswers.put(screenId, ((ChooseInImageScreenData) screenData).getAbsolute());
			}
			
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
   
	public String getChapterName(){
		return this.chapterName;
	}
	
	public String getCategoryName(){
		return this.categoryName;
	}
	
	public String getMenuScreenId(){
		return this.menuScreenId;
	}
	
	public Integer getScoreNum(){
		return this.scoreNum;
	}
	
	public Integer getCorrectAnswers(){
		return correctAnswersNum;
	}
	
	public Integer getWrongAnswers(){
		return wrongAnswersNum;
	}
	
	public void startTest(){
		String nextScreen = screenList.peek();
		System.out.println("next screenId = "+nextScreen);
		
		SoundHolder.playSound("completeSound");
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

		for (String key : answers.keySet()) {
			
			//if it is not absolute
			if(absoluteAnswers.get(key) != null && absoluteAnswers.get(key) == false){
				if(correctAnswers.get(key).containsAll(answers.get(key)) && answers.get(key).size() > 0){
					correct++;
				}					
			}
			else{
				Set<Integer> mutualAnswers = new TreeSet<Integer>(correctAnswers.get(key));
		    	mutualAnswers.retainAll(answers.get(key));
		    	correct += mutualAnswers.size()/correctAnswers.get(key).size();
			}
			
		}

		Integer res = (int) (((correct)/(this.totalQuestions))*100);
		this.correctAnswersNum = new Integer((int) correct);
		this.wrongAnswersNum = this.totalQuestions - this.correctAnswersNum;
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

		this.scoreNum = new Integer(score.substring(0, score.length()-1));
		
    	//load PostTestScreen
        ImagInLexis.mainContainer.setScreen("PostTestScreen");
        ScreenController sc = (ScreenController) ImagInLexis.mainContainer.getController("PostTestScreen");
        PostTestScreenController sc2 = (PostTestScreenController) sc;
        sc2.init(this);
        			
		this.cleanMemory();
		ImagInLexis.cleanMemory();		
	}
	
	public void submitAnswer(ScreenPane myScreenPane, Set<Integer> answersNo){
		answeredQuestions++;
		answers.put(screenList.peek(), answersNo);	
    	removeScreen();

    	String screenId = screenList.peek();
    	if(screenId == null){
    		finishTest();
    		return;
    	}
    	
        QuestionScreenController screenController = (QuestionScreenController) ImagInLexis.mainContainer.getController(screenId);
        screenController.setAnsweredQuestions(answeredQuestions);
        
    	System.out.println("next screenId = "+screenId);
    	myScreenPane.setScreen(screenId);
	}
	
    public void addToScreenList(String screenId){
    	System.out.println("addToScreenList: "+screenId);
    	screenList.add(screenId);
    	testScreenList.add(screenId);
    }
    
    public String getNextScreen(){
    	System.out.println("screenList before: "+screenList.toString());

    	if(!screenList.isEmpty()){
    		String screenId = screenList.remove();
    		screenList.add(screenId);
    		String retScreenId = screenList.peek();
            QuestionScreenController screenController = (QuestionScreenController) ImagInLexis.mainContainer.getController(retScreenId);
            screenController.setAnsweredQuestions(answeredQuestions);
        	
            System.out.println("screenList after: "+screenList.toString());
        	
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
		
		System.gc();
    }
    
    public String getMainWindowStyle(){
    	return this.mainWindowStyle;
    }
    
    public String getMainPaneStyle(){
    	return this.mainPaneStyle;
    }
    
    public String getInfoPaneStyle(){
    	return this.infoPaneStyle;
    }
    
    
}
