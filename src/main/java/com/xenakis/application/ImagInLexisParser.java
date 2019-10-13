package com.xenakis.application;

import com.xenakis.ImagInLexis;
import com.xenakis.model.Circle;
import com.xenakis.service.Database;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import com.xenakis.screenController.ScoreTableScreenController.Score;
import com.xenakis.screenData.*;

import java.io.*;
import java.util.*;
import java.util.Map.Entry;

public class ImagInLexisParser {
	
	private JSONParser parser;

	private JSONObject jsonObject;
	private JSONObject imagesJsonObject;
	private JSONObject soundsJsonObject;

	//chapterName -> chapterTotalQuestions
	private HashMap<String,Integer> chapterTotalQuestions = new HashMap<>();
	
	//categoryName -> chapterTotalQuestions
	private HashMap<String,Integer> categoryTotalQuestions = new HashMap<>();

	//chapterName -> categoryName List
	private HashMap<String,List<String>> chaptersCategoryList = new HashMap<>();
	
	//categoryName -> screenId List
	private HashMap<String,List<String>> categoriesScreenIdList = new HashMap<>();
	
	private HashMap<String,String> categoryNames = new HashMap<>();
	
	public ImagInLexisParser(String filePath, String imagesFilePath, String soundsFilePath){
          
		this.parser = new JSONParser();
		this.jsonObject = null;
		this.imagesJsonObject = null;

		this.jsonObject = this.loadObject(filePath);

		this.imagesJsonObject = this.loadObject(imagesFilePath);

        this.soundsJsonObject = this.loadObject(soundsFilePath);

	}

	private JSONObject loadObject(String filePath){

		InputStream input = ImagInLexis.class.getResourceAsStream(filePath);
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new InputStreamReader(input,"UTF-8"));

		} catch (UnsupportedEncodingException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
        StringBuilder out = new StringBuilder();
        String line;
        try {
			while ((line = reader.readLine()) != null) {
			    out.append(line);
			}
			reader.close();

		} catch (IOException e1) {
			e1.printStackTrace();
		}
        
        Object obj = null;
		try {

			obj = parser.parse(out.toString());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		
		return (JSONObject) obj;
	}

	public void parseCategoryNames(){
	    JSONArray scores = (JSONArray) jsonObject.get("questions");
    	for (Object scoresChapter : scores){
    	   	JSONObject tmpScoresChapter = (JSONObject) scoresChapter;
    		JSONArray chapterList = (JSONArray)(tmpScoresChapter.get("chapterList"));
        	for (Object scoresCategory : chapterList){
        	   	JSONObject tmpScoresCategory = (JSONObject) scoresCategory;
            	String category = (String)(tmpScoresCategory.get("category"));
            	String categoryName = (String)(tmpScoresCategory.get("categoryName"));
            	categoryNames.put(category, categoryName);
        	}
    	}
	}
	
	public List<Score> getScoreList(String chapterName, String categoryName){

		List<Score> scoreList;

		if(chapterName.equals("Όλα")) {
			scoreList = Database.select(null, null);
		}
		else if(categoryName.equals("Όλα")) {
			scoreList = Database.select(chapterName, null);
		}
		else {
			scoreList = Database.select(chapterName, categoryName);
		}
        
	    return scoreList;
	}
	
    public List<String> getChapterList(){
    	List<String> chapterList = new ArrayList<>();
    	
    	for (Entry<String, List<String>> entry : chaptersCategoryList.entrySet()) {
    	    String key = entry.getKey();
    	    chapterList.add(key);
    	}
    	
    	return chapterList;
    }
    
    public String getCategoryNameFromCategory(String category){
    	return categoryNames.get(category);
    }
    
    public List<String> getCategoryList(String chapterName){
    	List<String> categoryList = new ArrayList<>();
    
    	for (Entry<String, List<String>> entry : ImagInLexis.imagInLexisParser.chaptersCategoryList.entrySet()) {
    	    String key = entry.getKey();
    	    
    	    if(chapterName.equals(key) || chapterName.equals("Όλα")){
        	    List<String> value = entry.getValue();

				categoryList.addAll(value);
    	    }
    	}
    	
    	return categoryList;
    }    

	public void initialize(){
		parseSounds();
		parseImages();
		parseQuestions();
        parseScreens();

        parseCategoryNames();
	}

	private void parseSounds(){

        JSONArray sounds = (JSONArray) this.soundsJsonObject.get("sounds");
                		
        for (Object c : sounds){
        	JSONObject c1 = (JSONObject) c;
        	String soundId = (String)(c1.get("id"));
        	String soundPath = (String)(c1.get("path"));
        	SoundHolder.addSound(soundId, soundPath);
        }
	}
	
	private void parseImages(){

        JSONArray images = (JSONArray) this.imagesJsonObject.get("images");
                		
        for (Object c : images){
        	JSONObject c1 = (JSONObject) c;
        	String imageId = (String)(c1.get("id"));
        	String imagePath = (String)(c1.get("path"));
        	ImageHolder.addImage(imageId, imagePath);
        }        
	}

	private void parseQuestion(Object question, String chapterName,String category, String categoryName){
        
    	JSONObject questionObj = (JSONObject) question;
    	String screenType = (String)(questionObj.get("screenType"));
    	String screenId = (String)(questionObj.get("screenId"));

        Set<Integer> answersSet = new HashSet<>();
        JSONArray answers = (JSONArray)(questionObj.get("answers"));
        
        if(answers != null)
	        for (Object c : answers){
	        	JSONObject c1 = (JSONObject) c;
	        	Integer answerNo = Integer.parseInt((String) c1.get("answerNo"));
	        	answersSet.add(answerNo);
	        }
        
		addtoCategoriesScreenIdList(category,screenId);

    	if(screenType.equals("chooseImage")){
        	ResourcePathsHolder.addResourcePaths(screenId, "fxml/ChooseImageScreen.fxml");

			QuestionFactory.createChooseImageQuestion(questionObj,chapterName,categoryName,answersSet);
    	}
    	else if(screenType.equals("chooseImage2")){
        	ResourcePathsHolder.addResourcePaths(screenId, "fxml/ChooseImageScreen2.fxml");

			QuestionFactory.createChooseImage2Question(questionObj,chapterName,categoryName,answersSet);
    	}
    	else if(screenType.equals("chooseLabel")){
        	ResourcePathsHolder.addResourcePaths(screenId, "fxml/ChooseLabelScreen.fxml");

			QuestionFactory.createChooseLabelQuestion(questionObj,chapterName,categoryName,answersSet);
    	}
    	else if(screenType.equals("chooseInImage")){
        	ResourcePathsHolder.addResourcePaths(screenId, "fxml/ChooseInImageScreen.fxml");

			QuestionFactory.createChooseInImageQuestion(questionObj,chapterName,categoryName,answersSet);
    	}
    	else if(screenType.equals("whatIsThis")){
        	ResourcePathsHolder.addResourcePaths(screenId, "fxml/WhatIsThisScreen.fxml");

			QuestionFactory.createWhatIsThisQuestion(questionObj,chapterName,categoryName,answersSet);
    	}
    	else if(screenType.equals("chooseImageLink")){
        	ResourcePathsHolder.addResourcePaths(screenId, "fxml/ChooseImageLinkScreen.fxml");

			QuestionFactory.createChooseImageLinkQuestion(questionObj,chapterName,categoryName,answersSet);
    	}
    	else if(screenType.equals("chooseLabelFromSound")){
        	ResourcePathsHolder.addResourcePaths(screenId, "fxml/ChooseLabelFromSoundScreen.fxml");

			QuestionFactory.createChooseLabelFromSoundQuestion(questionObj,chapterName,categoryName,answersSet);
    	}
    	else{
    		System.err.println("error in parseQuestion not implemented yet");
    	}
	}
	
	private void parseCategory(Object categoryObj, String chapterName){
		if(!(categoryObj instanceof JSONObject)){
			System.out.println("error in parseCategory");
		}
		
    	JSONObject tmpCategory = (JSONObject) categoryObj;
    	String category = (String) tmpCategory.get("category");
    	String categoryName = (String) tmpCategory.get("categoryName");
		JSONArray categoryList = (JSONArray) tmpCategory.get("categoryList");

		addtoChaptersCategoryList(chapterName, categoryName);
		
		categoryTotalQuestions.put(category, categoryList.size());

    	for (Object question : categoryList){
        	parseQuestion(question,chapterName,category,categoryName);
        }	
	}
	
	private void parseChapter(Object chapter){
		if(!(chapter instanceof JSONObject)){
			System.err.println("error in parseChapter");
		}
		
    	JSONObject tmpChapter = (JSONObject) chapter;
    	String chapterName = (String) tmpChapter.get("chapterName");
		JSONArray chapterList = (JSONArray) tmpChapter.get("chapterList");

		chapterTotalQuestions.put(chapterName, chapterList.size());
		
    	for (Object category : chapterList){
    		parseCategory(category,chapterName);	
        }
	}

	private void parseQuestions(){

        // loop array
		JSONArray questions = (JSONArray) jsonObject.get("questions");
        
    	for (Object chapter : questions){
    		parseChapter(chapter);	
        }
	}

	private void parseScreens(){

		JSONArray screens = (JSONArray) jsonObject.get("screens");
        
    	for (Object screen : screens){
    		JSONObject s = (JSONObject) screen;
    		String screenId = (String) s.get("screenId");
        	ResourcePathsHolder.addResourcePaths(screenId, "fxml/" +screenId+".fxml");

            ImagInLexis.mainContainer.loadScreen(screenId, null);
        }
	}

	public Integer getCategoryTotalQuestions(String category){
		return categoryTotalQuestions.get(category);
	}

	public List<String> getCategoriesScreenIdList(String category){
		return categoriesScreenIdList.get(category);
	}
	
	private void addtoChaptersCategoryList(String chapterName, String categoryName){
		List<String> list = chaptersCategoryList.get(chapterName);
		
		if(list == null){
			list = new ArrayList<>();
			chaptersCategoryList.put(chapterName,list);
		}
		
		list.add(categoryName);
		//System.out.println(chaptersCategoryList.toString());
	}

	private void addtoCategoriesScreenIdList(String category, String screenId){
		List<String> list = categoriesScreenIdList.get(category);
		
		if(list == null){
			list = new ArrayList<>();
			categoriesScreenIdList.put(category,list);

		}
		
		list.add(screenId);
		//System.out.println(categoriesScreenIdList.toString());
	}

}
