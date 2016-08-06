package application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Map.Entry;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import screenController.ScoreTableScreenController.Score;
import screenData.ChooseImageLinkScreenData;
import screenData.ChooseImageScreenData;
import screenData.ChooseImageScreenData2;
import screenData.ChooseInImageScreenData;
import screenData.ChooseLabelFromSoundScreenData;
import screenData.ChooseLabelScreenData;
import screenData.ScreenDataHolder;
import screenData.WhatIsThisScreenData;

public class Parser {
	
	JSONParser parser;

	private JSONObject jsonObject;
	private JSONObject scoresJsonObject;
	private JSONObject imagesJsonObject;
	private JSONObject soundsJsonObject;

	//chapterName -> chapterTotalQuestions
	private HashMap<String,Integer> chapterTotalQuestions = new HashMap<String, Integer>();
	
	//categoryName -> chapterTotalQuestions
	private HashMap<String,Integer> categoryTotalQuestions = new HashMap<String, Integer>();

	//chapterName -> categoryName List
	private HashMap<String,List<String>> chaptersCategoryList = new HashMap<String, List<String>>();
	
	//categoryName -> screenId List
	private HashMap<String,List<String>> categoriesScreenIdList = new HashMap<String, List<String>>();
	
	private HashMap<String,String> categoryNames = new HashMap<String, String>(); 
	
	Parser(String filePath, String scoresFilePath, String imagesFilePath, String soundsFilePath){
          
		this.parser = new JSONParser();
		this.jsonObject = null;
		this.scoresJsonObject = null;
		this.imagesJsonObject = null;

        this.jsonObject = this.loadObject(filePath);

        this.scoresJsonObject = this.loadScores(scoresFilePath);

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
	
		
	private JSONObject loadScores(String scoresFilePath){

        Object obj = null;

        
		try {
			String path = ImagInLexis.class.getProtectionDomain().getCodeSource().getLocation().getPath();
			String decodedPath = null;
			decodedPath = URLDecoder.decode(path, "UTF-8");
			

			boolean endWithJar = false;
			if(decodedPath.endsWith(".jar")){
				endWithJar = true;
			}
			else{
				endWithJar = false;
			}
		
			int pos = decodedPath.lastIndexOf("/");
			decodedPath = decodedPath.substring(0, pos);
			System.out.println(decodedPath);
			File file = null;
			if(endWithJar == true){
				file = new File(decodedPath+"/files/scores.json");
			}
			else{
				file = new File(decodedPath+"/../files/scores.json");
			}

			InputStream input = null;
			input = new FileInputStream(file);
			BufferedReader reader = new BufferedReader(new InputStreamReader(input,"UTF-8"));

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

			try {
				obj = parser.parse(out.toString());
			} catch (ParseException e) {
				e.printStackTrace();
			}
		    

		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return (JSONObject) obj; 

		/*
		JSONParser parser = new JSONParser();
		
		InputStream input = null;
		try {
			input = new FileInputStream(new File(scoresFilePath));
		} catch (FileNotFoundException e2) {
			e2.printStackTrace();
		}
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(input));
		
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
		*/       
	}
	
	public void parseCategoryNames(){
	    JSONArray scores = (JSONArray) ((JSONObject) jsonObject).get("questions");
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
	
	@SuppressWarnings("unchecked")
	public void addScore(JSONObject obj, String chapter, String category){
		System.out.println("add score in: "+chapter+" in: "+category);
	    JSONArray scores = (JSONArray) ImagInLexis.parser.getScoresJsonObject().get("scores");
	    boolean scoreAdded = false;
    	for (Object scoresChapter : scores){
    	   	JSONObject tmpScoresChapter = (JSONObject) scoresChapter;
        	String chapterName = (String)(tmpScoresChapter.get("chapterName"));
        	if(chapterName.equals(chapter)){
        		JSONArray chapterList = (JSONArray)(tmpScoresChapter.get("chapterList"));

            	for (Object scoresCategory : chapterList){
            	   	JSONObject tmpScoresCategory = (JSONObject) scoresCategory;

                	String categoryName = (String)(tmpScoresCategory.get("categoryName"));
                	if(categoryName.equals(category)){
                		JSONArray categoryList = (JSONArray)(tmpScoresCategory.get("categoryList"));
                    	categoryList.add(obj);
                    	scoreAdded = true;
                	}
            	}
        	}

    	}
        System.out.println(scores);
        if(scoreAdded == false){
        	System.err.println("error in addScore");
        }
	}
	
	@SuppressWarnings("resource")
	public void submitScores(){

		try {
			String path = ImagInLexis.class.getProtectionDomain().getCodeSource().getLocation().getPath();
			String decodedPath = null;
			decodedPath = URLDecoder.decode(path, "UTF-8");
			
			boolean endWithJar = false;
			if(decodedPath.endsWith(".jar")){
				endWithJar = true;
			}
			else{
				endWithJar = false;
			}
		
			int pos = decodedPath.lastIndexOf("/");
			decodedPath = decodedPath.substring(0, pos);
			System.out.println(decodedPath);
			File file = null;
			if(endWithJar == true){
				file = new File(decodedPath+"/files/scores.json");
			}
			else{
				file = new File(decodedPath+"/../files/scores.json");
			}

			
			OutputStream output = new FileOutputStream(file);
			output.write(ImagInLexis.parser.getScoresJsonObject().toJSONString().getBytes());

		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		 
	}
	
	public List<Score> getScoreList(String chapterName, String categoryName){

	    List<Score> scoreList = new ArrayList<Score>();
				
        JSONArray scores = (JSONArray) scoresJsonObject.get("scores");

        for (Object c : scores){
        	JSONObject c1 = (JSONObject) c;
            JSONArray chapterList = (JSONArray) c1.get("chapterList");
        	String currChapterName = (String)(c1.get("chapterName"));
        	
        	if(currChapterName.equals(chapterName) || chapterName.equals("Όλα")){
                for (Object cc : chapterList){
                	JSONObject cc1 = (JSONObject) cc;
                    JSONArray categoryList = (JSONArray) cc1.get("categoryList");
                	String currCategoryName = (String)(cc1.get("categoryName"));

                	if(currCategoryName.equals(categoryName) || categoryName.equals("Όλα")){
                        for (Object ccc : categoryList){
                        	JSONObject ccc1 = (JSONObject) ccc;
                        	String name = (String)(ccc1.get("name"));
                        	String score = (String)(ccc1.get("score"));
                        	String date = (String)(ccc1.get("date"));
                        	String time = (String)(ccc1.get("time"));
                        	scoreList.add( new Score(name,score,date,time) );
                        }
                	}
                } 		
        	}
        }
        
	    return scoreList;
	}
	
    public List<String> getChapterList(){
    	List<String> chapterList = new ArrayList<String>();
    	
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
    	List<String> categoryList = new ArrayList<String>();
    
    	for (Entry<String, List<String>> entry : ImagInLexis.parser.chaptersCategoryList.entrySet()) {
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

    	answersSet.add(1);
    	
    	WhatIsThisScreenData whatIsThisScreenData = new WhatIsThisScreenData(questionString,imageId,soundId,questionSoundId,answersSet,chapterName,categoryName);

    	ScreenDataHolder.addScreenData(screenId,whatIsThisScreenData);
	}
	
	private void createChooseInImageQuestion(JSONObject question, String chapterName, String categoryName, Set<Integer> answers){
    	String screenId = (String)(question.get("screenId"));
    	String questionString = (String)(question.get("question"));
    	String imageId = (String)(question.get("imageId"));

    	JSONArray circlesList = (JSONArray)(question.get("circlesList"));
    	ArrayList<Circle> circlesArrayList = new ArrayList<Circle>();

        for (Object c : circlesList){
        	JSONObject c1 = (JSONObject) c;
        	String id = (String)(c1.get("id"));
        	Integer circleX = new Integer((String)(c1.get("circleX")));
        	Integer circleY = new Integer((String)(c1.get("circleY")));
        	Integer circleW = new Integer((String)(c1.get("circleW")));
        	Integer circleH = new Integer((String)(c1.get("circleH")));

        	Circle circle = new Circle(id,circleX,circleY,circleW,circleH);
        	circlesArrayList.add(circle);
        }
        
        ChooseInImageScreenData chooseInImageScreenData = new ChooseInImageScreenData(questionString,imageId,circlesArrayList,answers,chapterName,categoryName);
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
    	String questionSoundId = (String)(question.get("questionSoundId"));
    	String soundId = (String)(question.get("soundId"));
    	String sound1Id = (String)(question.get("sound1Id"));
    	String sound2Id = (String)(question.get("sound2Id"));
    	String sound3Id = (String)(question.get("sound3Id"));

        ChooseLabelFromSoundScreenData chooseLabelFromSoundScreenData = new ChooseLabelFromSoundScreenData(questionString,imageId,radioOption1,radioOption2,radioOption3,questionSoundId,soundId,sound1Id,sound2Id,sound3Id,answers,chapterName,categoryName);
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

        Set<Integer> answersSet = new HashSet<Integer>();
        JSONArray answers = (JSONArray)(questionObj.get("answers"));
        
        if(answers != null)
	        for (Object c : answers){
	        	JSONObject c1 = (JSONObject) c;
	        	Integer answerNo = new Integer((String)(c1.get("answerNo")));
	        	answersSet.add(answerNo);
	        }
        
		addtoCategoriesScreenIdList(category,screenId);

    	if(screenType.equals("chooseImage")){
        	ResourcePathsHolder.addResourcePaths(screenId, "/fxml/ChooseImageScreen.fxml");

    		createChooseImageQuestion(questionObj,chapterName,categoryName,answersSet);
    	}
    	else if(screenType.equals("chooseImage2")){
        	ResourcePathsHolder.addResourcePaths(screenId, "/fxml/ChooseImageScreen2.fxml");

    		createChooseImage2Question(questionObj,chapterName,categoryName,answersSet);
    	}
    	else if(screenType.equals("chooseLabel")){
        	ResourcePathsHolder.addResourcePaths(screenId, "/fxml/ChooseLabelScreen.fxml");

    		createChooseLabelQuestion(questionObj,chapterName,categoryName,answersSet);
    	}
    	else if(screenType.equals("chooseInImage")){
        	ResourcePathsHolder.addResourcePaths(screenId, "/fxml/ChooseInImageScreen.fxml");

    		createChooseInImageQuestion(questionObj,chapterName,categoryName,answersSet);
    	}
    	else if(screenType.equals("whatIsThis")){
        	ResourcePathsHolder.addResourcePaths(screenId, "/fxml/WhatIsThisScreen.fxml");

    		createWhatIsThisQuestion(questionObj,chapterName,categoryName,answersSet);
    	}
    	else if(screenType.equals("chooseImageLink")){
        	ResourcePathsHolder.addResourcePaths(screenId, "/fxml/ChooseImageLinkScreen.fxml");

    		createChooseImageLinkQuestion(questionObj,chapterName,categoryName,answersSet);
    	}
    	else if(screenType.equals("chooseLabelFromSound")){
        	ResourcePathsHolder.addResourcePaths(screenId, "/fxml/ChooseLabelFromSoundScreen.fxml");

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
        	ResourcePathsHolder.addResourcePaths(screenId, "/fxml/"+screenId+".fxml");

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

	public JSONObject getScoresJsonObject() {
		return scoresJsonObject;
	}
}
