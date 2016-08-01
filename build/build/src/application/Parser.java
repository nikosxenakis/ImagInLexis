package application;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import screenData.ChooseImageScreenData;
import screenData.ChooseInImageScreenData;
import screenData.ChooseLabelScreenData;
import screenData.ScreenDataHolder;

public class Parser {
	
	JSONObject jsonObject;
	JSONObject scoresJsonObject;

	//chapterName -> chapterTotalQuestions
	private HashMap<String,Integer> chapterTotalQuestions = new HashMap<String, Integer>();
	
	//categoryName -> chapterTotalQuestions
	private HashMap<String,Integer> categoryTotalQuestions = new HashMap<String, Integer>();

	//chapterName -> categoryName List
	private HashMap<String,List<String>> chaptersCategoryList = new HashMap<String, List<String>>();
	
	//categoryName -> screenId List
	private HashMap<String,List<String>> categoriesScreenIdList = new HashMap<String, List<String>>();
	
	Parser(String filePath, String scoresFilePath){
        
		JSONParser parser = new JSONParser();
		
		InputStream input = JavaFXApplication.class.getResourceAsStream(filePath);
		BufferedReader reader = new BufferedReader(new InputStreamReader(input));
        StringBuilder out = new StringBuilder();
        String line;
        try {
			while ((line = reader.readLine()) != null) {
			    out.append(line);
			}
			reader.close();

		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

        Object obj = null;
		try {
			obj = parser.parse(out.toString());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
        this.jsonObject =  (JSONObject) obj;       
     
        this.loadScores(scoresFilePath);

	}
	
	public void addScore(JSONObject obj, String chapter, String category){
	    JSONArray scores = (JSONArray) JavaFXApplication.parser.scoresJsonObject.get("scores");
	    
    	for (Object scoresChapter : scores){
    	   	JSONObject tmpScoresChapter = (JSONObject) scoresChapter;
        	String chapterName = (String)(tmpScoresChapter.get("chapterName"));
        	if(chapterName.equals(chapter)){
        		JSONArray chapterList = (JSONArray)(tmpScoresChapter.get("chapterList"));
            	System.out.println(chapterList);

            	/*
                System.out.println(scores);
                scores.add("{s:s}");
                
                try (FileWriter file = new FileWriter("resources"+scoresFilePath)) {
            		file.write(JavaFXApplication.parser.scoresJsonObject.toJSONString());
            		System.out.println("Successfully Copied JSON Object to File...");
            		System.out.println("\nJSON Object: " + JavaFXApplication.parser.scoresJsonObject);
            	} catch (IOException e) {
            		// TODO Auto-generated catch block
            		e.printStackTrace();
            	}
                
                */
        	}

    	}
	}
	
	private void loadScores(String scoresFilePath){

		JSONParser parser = new JSONParser();
		
		InputStream input = JavaFXApplication.class.getResourceAsStream(scoresFilePath);
		BufferedReader reader = new BufferedReader(new InputStreamReader(input));
        StringBuilder out = new StringBuilder();
        String line;
        try {
			while ((line = reader.readLine()) != null) {
			    out.append(line);
			}
			reader.close();

		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

        Object obj = null;
		try {
			obj = parser.parse(out.toString());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
        this.scoresJsonObject =  (JSONObject) obj;       
        
	}
	
	public void initialize(){
		
		parseImages();
        parseScreens();
		parseQuestions();
	}
	
	private void parseImages(){
        JSONArray images = (JSONArray) jsonObject.get("images");
        for (Object c : images){
        	JSONObject c1 = (JSONObject) c;
        	String imageId = (String)(c1.get("id"));
        	String imagePath = (String)(c1.get("path"));
        	ImageHolder.addImage(imageId, imagePath);
        }
        
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

        ChooseLabelScreenData chooseLabelScreenData = new ChooseLabelScreenData(questionString,imageId,radioOption1,radioOption2,radioOption3,answers,chapterName,categoryName);
        ScreenDataHolder.addScreenData(screenId,chooseLabelScreenData);
	}
	
	private void createChooseImageQuestion(JSONObject question, String chapterName, String categoryName, Set<Integer> answers){
    	String screenId = (String)(question.get("screenId"));
    	String questionString = (String)(question.get("question"));
    	String image1Id = (String)(question.get("image1Id"));
    	String image2Id = (String)(question.get("image2Id"));
    	String image3Id = (String)(question.get("image3Id"));

        ChooseImageScreenData chooseImageScreenData = new ChooseImageScreenData(questionString,image1Id,image2Id,image3Id,answers,chapterName,categoryName);
        ScreenDataHolder.addScreenData(screenId,chooseImageScreenData);
	}
	
	private void parseQuestion(Object question, String chapterName,String category, String categoryName){
        
    	JSONObject questionObj = (JSONObject) question;
    	String screenType = (String)(questionObj.get("screenType"));
    	String screenId = (String)(questionObj.get("screenId"));

        Set<Integer> answersSet = new HashSet<Integer>();
        JSONArray answers = (JSONArray)(questionObj.get("answers"));
        for (Object c : answers){
        	JSONObject c1 = (JSONObject) c;
        	Integer answerNo = new Integer((String)(c1.get("answerNo")));
        	answersSet.add(answerNo);

        }
        
		addtoCategoriesScreenIdList(category,screenId);

    	if(screenType.equals("chooseImage")){
        	ResourcePathsHolder.addResourcePaths(screenId, "../fxml/ChooseImageScreen.fxml");

    		createChooseImageQuestion(questionObj,chapterName,categoryName,answersSet);
    	}
    	else if(screenType.equals("chooseLabel")){
        	ResourcePathsHolder.addResourcePaths(screenId, "../fxml/ChooseLabelScreen.fxml");

    		createChooseLabelQuestion(questionObj,chapterName,categoryName,answersSet);
    	}
    	else if(screenType.equals("chooseInImage")){
        	ResourcePathsHolder.addResourcePaths(screenId, "../fxml/ChooseInImageScreen.fxml");

    		createChooseInImageQuestion(questionObj,chapterName,categoryName,answersSet);
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
        	ResourcePathsHolder.addResourcePaths(screenId, "../fxml/HomeScreen.fxml");

            JavaFXApplication.mainContainer.loadScreen(screenId, null);
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
