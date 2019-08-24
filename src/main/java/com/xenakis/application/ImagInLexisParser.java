package com.xenakis.application;

import com.xenakis.ImagInLexis;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import com.xenakis.screenController.ScoreTableScreenController.Score;
import com.xenakis.screenData.*;

import java.io.*;
import java.net.URLDecoder;
import java.util.*;
import java.util.Map.Entry;

public class ImagInLexisParser {
	
	JSONParser parser;

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
            	
            	for(String category : value){
            		categoryList.add(category);
            	}	
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
	
	private void createWhatIsThisQuestion(JSONObject question, String chapterName, String categoryName, Set<Integer> answersSet){
		
    	String screenId = (String)(question.get("screenId"));
    	String imageId = (String)(question.get("imageId"));
    	String soundId = (String)(question.get("soundId"));
    	String questionString = (String)(question.get("question"));
    	String questionSoundId = (String)(question.get("questionSoundId"));
    	String mainQuestion = (String)(question.get("mainQuestion"));
    	String mainQuestionSoundId = (String)(question.get("mainQuestionSoundId"));
    	
    	answersSet.add(1);
    	
    	WhatIsThisScreenData whatIsThisScreenData = new WhatIsThisScreenData(questionString,mainQuestion,mainQuestionSoundId,imageId,soundId,questionSoundId,answersSet,chapterName,categoryName);

    	ScreenDataHolder.addScreenData(screenId,whatIsThisScreenData);
	}
	
	private void createChooseInImageQuestion(JSONObject question, String chapterName, String categoryName, Set<Integer> answers){
    	String screenId = (String)(question.get("screenId"));
    	String questionString = (String)(question.get("question"));
    	String imageId = (String)(question.get("imageId"));
    	String questionSoundId = (String)(question.get("questionSoundId"));
    	String absolute = (String)(question.get("absolute"));

    	JSONArray circlesList = (JSONArray)(question.get("circlesList"));
    	ArrayList<Circle> circlesArrayList = new ArrayList<>();

        for (Object c : circlesList){
        	JSONObject c1 = (JSONObject) c;
        	String id = (String)(c1.get("id"));
        	Integer circleX = Integer.parseInt((String)(c1.get("circleX")));
        	Integer circleY = Integer.parseInt((String)(c1.get("circleY")));
        	Integer circleW = Integer.parseInt((String)(c1.get("circleW")));
        	Integer circleH = Integer.parseInt((String)(c1.get("circleH")));

        	Circle circle = new Circle(id,circleX,circleY,circleW,circleH);
        	circlesArrayList.add(circle);
        }
        
        ChooseInImageScreenData chooseInImageScreenData = new ChooseInImageScreenData(questionString,questionSoundId,imageId,circlesArrayList,absolute,answers,chapterName,categoryName);
        ScreenDataHolder.addScreenData(screenId,chooseInImageScreenData);
	}
	
	private void createChooseLabelQuestion(JSONObject question, String chapterName, String categoryName, Set<Integer> answers){
		
    	String screenId = (String)(question.get("screenId"));
    	String questionString = (String)(question.get("question"));
    	String imageId = (String)(question.get("imageId"));
    	String radioOption1 = (String)(question.get("radioOption1"));
    	String radioOption2 = (String)(question.get("radioOption2"));
    	String radioOption3 = (String)(question.get("radioOption3"));
    	String radioOption4 = (String)(question.get("radioOption4"));
    	String soundId = (String)(question.get("soundId"));
    	String sound1Id = (String)(question.get("sound1Id"));
    	String sound2Id = (String)(question.get("sound2Id"));
    	String sound3Id = (String)(question.get("sound3Id"));
    	String sound4Id = (String)(question.get("sound4Id"));

        ChooseLabelScreenData chooseLabelScreenData = new ChooseLabelScreenData(questionString,imageId,radioOption1,radioOption2,radioOption3,radioOption4,soundId,sound1Id,sound2Id,sound3Id,sound4Id,answers,chapterName,categoryName);
        ScreenDataHolder.addScreenData(screenId,chooseLabelScreenData);
	}
	
	private void createChooseLabelFromSoundQuestion(JSONObject question, String chapterName, String categoryName, Set<Integer> answers){
		
    	String screenId = (String)(question.get("screenId"));
    	String questionString = (String)(question.get("question"));
    	String imageId = (String)(question.get("imageId"));
    	String radioOption1 = (String)(question.get("radioOption1"));
    	String radioOption2 = (String)(question.get("radioOption2"));
    	String radioOption3 = (String)(question.get("radioOption3"));
    	String radioOption4 = (String)(question.get("radioOption4"));
    	String questionSoundId = (String)(question.get("questionSoundId"));
    	String soundId = (String)(question.get("soundId"));
    	String sound1Id = (String)(question.get("sound1Id"));
    	String sound2Id = (String)(question.get("sound2Id"));
    	String sound3Id = (String)(question.get("sound3Id"));
    	String sound4Id = (String)(question.get("sound4Id"));

        ChooseLabelFromSoundScreenData chooseLabelFromSoundScreenData = new ChooseLabelFromSoundScreenData(questionString,imageId,radioOption1,radioOption2,radioOption3,radioOption4,questionSoundId,soundId,sound1Id,sound2Id,sound3Id,sound4Id,answers,chapterName,categoryName);
        ScreenDataHolder.addScreenData(screenId,chooseLabelFromSoundScreenData);
	}
	
	private void createChooseImageQuestion(JSONObject question, String chapterName, String categoryName, Set<Integer> answers){
    	String screenId = (String)(question.get("screenId"));
    	String questionString = (String)(question.get("question"));
    	String image1Id = (String)(question.get("image1Id"));
    	String image2Id = (String)(question.get("image2Id"));
    	String image3Id = (String)(question.get("image3Id"));
    	String soundId = (String)(question.get("soundId"));

        ChooseImageScreenData chooseImageScreenData = new ChooseImageScreenData(questionString,image1Id,image2Id,image3Id,soundId,answers,chapterName,categoryName);
        ScreenDataHolder.addScreenData(screenId,chooseImageScreenData);
	}
	
	private void createChooseImage2Question(JSONObject question, String chapterName, String categoryName, Set<Integer> answers){
    	String screenId = (String)(question.get("screenId"));
    	String questionString = (String)(question.get("question"));
    	String image1Id = (String)(question.get("image1Id"));
    	String image2Id = (String)(question.get("image2Id"));
    	String image3Id = (String)(question.get("image3Id"));
    	String image4Id = (String)(question.get("image4Id"));
    	String soundId = (String)(question.get("soundId"));

        ChooseImageScreenData2 chooseImageScreenData = new ChooseImageScreenData2(questionString,image1Id,image2Id,image3Id,image4Id,soundId,answers,chapterName,categoryName);
        ScreenDataHolder.addScreenData(screenId,chooseImageScreenData);
	}
	
	private void createChooseImageLinkQuestion(JSONObject question, String chapterName, String categoryName, Set<Integer> answers){
    	String screenId = (String)(question.get("screenId"));
    	String questionString = (String)(question.get("question"));
    	String basicImageId = (String)(question.get("bacisImageId"));
    	String image1Id = (String)(question.get("image1Id"));
    	String image2Id = (String)(question.get("image2Id"));
    	String image3Id = (String)(question.get("image3Id"));
    	String soundId = (String)(question.get("soundId"));

    	ChooseImageLinkScreenData chooseImageLinkScreenData = new ChooseImageLinkScreenData(questionString,basicImageId,image1Id,image2Id,image3Id,soundId,answers,chapterName,categoryName);
        ScreenDataHolder.addScreenData(screenId,chooseImageLinkScreenData);
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

    		createChooseImageQuestion(questionObj,chapterName,categoryName,answersSet);
    	}
    	else if(screenType.equals("chooseImage2")){
        	ResourcePathsHolder.addResourcePaths(screenId, "fxml/ChooseImageScreen2.fxml");

    		createChooseImage2Question(questionObj,chapterName,categoryName,answersSet);
    	}
    	else if(screenType.equals("chooseLabel")){
        	ResourcePathsHolder.addResourcePaths(screenId, "fxml/ChooseLabelScreen.fxml");

    		createChooseLabelQuestion(questionObj,chapterName,categoryName,answersSet);
    	}
    	else if(screenType.equals("chooseInImage")){
        	ResourcePathsHolder.addResourcePaths(screenId, "fxml/ChooseInImageScreen.fxml");

    		createChooseInImageQuestion(questionObj,chapterName,categoryName,answersSet);
    	}
    	else if(screenType.equals("whatIsThis")){
        	ResourcePathsHolder.addResourcePaths(screenId, "fxml/WhatIsThisScreen.fxml");

    		createWhatIsThisQuestion(questionObj,chapterName,categoryName,answersSet);
    	}
    	else if(screenType.equals("chooseImageLink")){
        	ResourcePathsHolder.addResourcePaths(screenId, "fxml/ChooseImageLinkScreen.fxml");

    		createChooseImageLinkQuestion(questionObj,chapterName,categoryName,answersSet);
    	}
    	else if(screenType.equals("chooseLabelFromSound")){
        	ResourcePathsHolder.addResourcePaths(screenId, "fxml/ChooseLabelFromSoundScreen.fxml");

    		createChooseLabelFromSoundQuestion(questionObj,chapterName,categoryName,answersSet);
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

        // loop array
		JSONArray screens = (JSONArray) jsonObject.get("screens");
        
    	for (Object screen : screens){
    		JSONObject s = (JSONObject) screen;
    		String screenId = (String) s.get("screenId");
        	ResourcePathsHolder.addResourcePaths(screenId, "fxml/" +screenId+".fxml");

            ImagInLexis.mainContainer.loadScreen(screenId, null);
        }
	}
	
	public int getChapterTotalQuestions(String chapterName){
		return chapterTotalQuestions.get(chapterName);
	}
	
	public Integer getCategoryTotalQuestions(String category){
		return categoryTotalQuestions.get(category);
	}

	public List<String> getChaptersCategoryList(String chapterName){
		return chaptersCategoryList.get(chapterName);
	}
	
	public List<String> getCategoriesScreenIdList(String category){
		return categoriesScreenIdList.get(category);
	}
	
	public void addtoChaptersCategoryList(String chapterName, String categoryName){
		List<String> list = chaptersCategoryList.get(chapterName);
		
		if(list == null){
			list = new ArrayList<String>();
			chaptersCategoryList.put(chapterName,list);
		}
		
		list.add(categoryName);
		//System.out.println(chaptersCategoryList.toString());
	}

	public void addtoCategoriesScreenIdList(String category, String screenId){
		List<String> list = categoriesScreenIdList.get(category);
		
		if(list == null){
			list = new ArrayList<String>();
			categoriesScreenIdList.put(category,list);

		}
		
		list.add(screenId);
		//System.out.println(categoriesScreenIdList.toString());
	}

}
