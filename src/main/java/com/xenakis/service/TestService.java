package com.xenakis.service;

import com.xenakis.ImagInLexis;
import com.xenakis.application.ImagInLexisParser;
import com.xenakis.application.ScreenPane;
import com.xenakis.model.Category;
import com.xenakis.model.Chapter;
import com.xenakis.model.TestData;
import com.xenakis.screenController.PostTestScreenController;
import com.xenakis.screenController.PreTestScreenController;
import com.xenakis.screenController.QuestionScreenController;
import com.xenakis.screenController.ScreenController;
import com.xenakis.screenData.ChooseInImageScreenData;
import com.xenakis.screenData.QuestionScreenData;
import com.xenakis.screenData.ScreenDataHolder;
import com.xenakis.databaseService.CategoryUtil;
import com.xenakis.databaseService.ChapterUtil;
import com.xenakis.databaseService.ScoreUtil;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class TestService {

	private final String menuScreenId;


	private final Queue<String> screenList = new LinkedList<>();
	private final ArrayList<String> testScreenList = new ArrayList<>();

	private final TestData testData;
	private int answeredQuestions = 0;

	public TestService(String chapter, String category, String chapterName, String categoryName, String menuScreenId){

		this.testData = new TestData(chapter, category, chapterName, categoryName);

		this.menuScreenId = menuScreenId;

		//load PreTestScreen
		ImagInLexis.mainContainer.setScreen("PreTestScreen");
		ScreenController sc = ImagInLexis.mainContainer.getController("PreTestScreen");
		PreTestScreenController sc2 = (PreTestScreenController) sc;
		sc2.init(this);

		for(String screenId : ImagInLexisParser.getCategoriesScreenIdList(category)){
			ImagInLexis.mainContainer.loadScreen(screenId, this);
			this.addToScreenList(screenId);
			QuestionScreenData screenData = ScreenDataHolder.getScreenData(screenId);

			if(screenData instanceof ChooseInImageScreenData){
				this.testData.addAbsoluteAnswers(screenId, ((ChooseInImageScreenData) screenData).getAbsolute());
			}

			Set<Integer> answers = screenData.getAnswers();
			this.testData.addCorrectAnswers(screenId, answers);
		}
	}

	public String getChapter(){
		return testData.getChapter();
	}

	public String getCategory(){
		return testData.getCategory();
	}

	public String getChapterName(){
		return testData.getChapterName();
	}

	public String getCategoryName(){
		return testData.getCategoryName();
	}

	public int calculateCorrectAnswers() {
		int correctAnswersNum = 0;

		for (String answersKey : testData.getAnswersKeys()) {
			Set<Integer> correctAnswers = testData.getCorrectAnswers(answersKey);
			Set<Integer> answers = testData.getAnswers(answersKey);
			Boolean absoluteAnswerExist = testData.getAbsoluteAnswers(answersKey);

			//if it is not absolute
			if(absoluteAnswerExist != null && !absoluteAnswerExist){
				if(correctAnswers.containsAll(answers) && answers.size() > 0){
					correctAnswersNum++;
				}
			}
			else {
				Set<Integer> mutualAnswers = new TreeSet<>(correctAnswers);
				mutualAnswers.retainAll(answers);
				correctAnswersNum += mutualAnswers.size()/correctAnswers.size();
			}
		}

		return correctAnswersNum;
	}

	public int calculateScore() {
		int correctAnswersNum = this.calculateCorrectAnswers();
		int score = (correctAnswersNum * 100) / this.getTotalQuestions();
		System.out.println("Correct: " + correctAnswersNum + ", Total: " + this.getTotalQuestions() + ", Score: " + score);
		return score;
	}

	public int getCorrectAnswers(){ return this.calculateCorrectAnswers(); }

	public int getWrongAnswers(){
		return this.getTotalQuestions() - this.calculateCorrectAnswers();
	}

	public int getAnsweredQuestions() { return this.answeredQuestions; }

	public void setAnsweredQuestions(int answeredQuestions) { this.answeredQuestions = answeredQuestions; }

	public int getTotalQuestions() { return ImagInLexisParser.getCategoryTotalQuestions(this.testData.getCategory()); }

	public String getMenuScreenId(){
		return this.menuScreenId;
	}

	public void startTest(){
		this.nextQuestion(ImagInLexis.mainContainer);
		SoundService.playSound("completeSound");
	}

	public void nextQuestion(ScreenPane myScreenPane){
		String screenId = this.getNextScreen();
		System.out.println("next screenId = "+screenId);
		myScreenPane.setScreen(screenId);
	}

	public void submitAnswer(ScreenPane myScreenPane, Set<Integer> answersNo){
		this.setAnsweredQuestions(this.getAnsweredQuestions() + 1);
		this.testData.addAnswer(screenList.peek(), answersNo);

		removeScreen();

		if(screenList.size() == 0) {
			finishTest();
			return;
		}

		String screenId = screenList.peek();
		QuestionScreenController screenController = (QuestionScreenController) ImagInLexis.mainContainer.getController(screenId);
		screenController.setAnsweredQuestions(this.getAnsweredQuestions());

		this.nextQuestion(myScreenPane);
	}

	private void finishTest(){
		if(this.getTotalQuestions() != this.getAnsweredQuestions()){
			System.err.println("error in finishTest answeredQuestions = " + this.getAnsweredQuestions());
		}

		int score = this.calculateScore();
		System.out.println("score= "+score);

		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		DateFormat timeFormat = new SimpleDateFormat("HH:mm");

		Date date = new Date();
		Date time = new Date();
		String strDate = dateFormat.format(date);
		String strTime = timeFormat.format(time);
		Chapter chapter = ChapterUtil.getChapterFromGreekName(this.testData.getChapterName());
		int chapterId = chapter.getId();
		Category category = CategoryUtil.getCategoryFromGreekName(this.testData.getCategoryName());
		int categoryId = category.getId();

		System.out.println("date= "+strDate);
		System.out.println("time= "+strTime);

		ScoreUtil.insertScore(ImagInLexis.userName, strTime, strDate, score, chapterId, categoryId);

		//load PostTestScreen
		ImagInLexis.mainContainer.setScreen("PostTestScreen");
		ScreenController sc = ImagInLexis.mainContainer.getController("PostTestScreen");
		PostTestScreenController sc2 = (PostTestScreenController) sc;
		sc2.init(this);

		this.cleanMemory();
	}

	private void addToScreenList(String screenId){
		System.out.println("addToScreenList: "+screenId);
		screenList.add(screenId);
		testScreenList.add(screenId);
	}

	private String getNextScreen(){
		System.out.println("screenList before: "+screenList.toString());

		if(!screenList.isEmpty()){
			String screenId = screenList.remove();
			screenList.add(screenId);
			String retScreenId = screenList.peek();
			QuestionScreenController screenController = (QuestionScreenController) ImagInLexis.mainContainer.getController(retScreenId);
			screenController.setAnsweredQuestions(this.getAnsweredQuestions());

			System.out.println("screenList after: "+screenList.toString());

			return retScreenId;
		}
		else
			return null;
	}

	private void removeScreen(){

		if(!screenList.isEmpty())
			screenList.remove();
	}

	public boolean isLastQuestion(){
		return this.getTotalQuestions() - 1 == this.getAnsweredQuestions();
	}

	private void cleanMemory(){
		for(String screenId: testScreenList){
			System.out.println("unload screen : "+screenId);
			ImagInLexis.mainContainer.unloadScreen(screenId);
		}

		this.testScreenList.clear();

		System.gc();
	}
}
