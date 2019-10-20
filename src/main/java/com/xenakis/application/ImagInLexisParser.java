package com.xenakis.application;

import com.xenakis.ImagInLexis;
import com.xenakis.service.JsonParser;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.*;
import java.util.Map.Entry;

public class ImagInLexisParser {

	private static JSONObject dataJsonObject;
	private static JSONObject screensJsonObject;

	//chapterName -> chapterTotalQuestions
	private static final HashMap<String,Integer> chapterTotalQuestions = new HashMap<>();
	
	//categoryName -> chapterTotalQuestions
	private static final HashMap<String,Integer> categoryTotalQuestions = new HashMap<>();

	//chapterName -> categoryName List
	private static final HashMap<String,List<String>> chaptersCategoryList = new HashMap<>();
	
	//categoryName -> screenId List
	private static final HashMap<String,List<String>> categoriesScreenIdList = new HashMap<>();
	
	private static final HashMap<String,String> categoryNames = new HashMap<>();

	private static void parseCategoryNames(){
	    JSONArray questions = (JSONArray) dataJsonObject.get("questions");
    	for (Object questionsChapter : questions){
    	   	JSONObject tmpQuestionsChapter = (JSONObject) questionsChapter;
    		JSONArray chapterList = (JSONArray)(tmpQuestionsChapter.get("chapterList"));
        	for (Object questionsCategory : chapterList){
        	   	JSONObject tmpQuestionsCategory = (JSONObject) questionsCategory;
            	String category = (String)(tmpQuestionsCategory.get("category"));
            	String categoryName = (String)(tmpQuestionsCategory.get("categoryName"));
            	categoryNames.put(category, categoryName);
        	}
    	}
	}
	
    public static List<String> getChapterList(){
    	List<String> chapterList = new ArrayList<>();
    	
    	for (Entry<String, List<String>> entry : chaptersCategoryList.entrySet()) {
    	    String key = entry.getKey();
    	    chapterList.add(key);
    	}
    	
    	return chapterList;
    }
    
    public static String getCategoryNameFromCategory(String category){
    	return categoryNames.get(category);
    }
    
    public static List<String> getCategoryList(String chapterName){
    	List<String> categoryList = new ArrayList<>();
    
    	for (Entry<String, List<String>> entry : ImagInLexisParser.chaptersCategoryList.entrySet()) {
    	    String key = entry.getKey();
    	    
    	    if(chapterName.equals(key) || chapterName.equals("Όλα")){
        	    List<String> value = entry.getValue();

				categoryList.addAll(value);
    	    }
    	}
    	
    	return categoryList;
    }    

	public static void initialize(){

		dataJsonObject = JsonParser.loadObject("json/data.json");

		screensJsonObject = JsonParser.loadObject("json/screens.json");

		parseSounds();
		parseQuestions();
		parseMainScreens();
        parseCategoryNames();
	}

	private static void parseSounds(){
		JSONObject soundsJsonObject = JsonParser.loadObject("json/sounds.json");
		JSONArray sounds = (JSONArray) soundsJsonObject.get("sounds");

        for (Object c : sounds){
        	JSONObject c1 = (JSONObject) c;
        	String soundId = (String)(c1.get("id"));
        	String soundPath = (String)(c1.get("path"));
        	SoundHolder.addSound(soundId, soundPath);
        }
	}

	private static void parseQuestion(Object question, String chapterName, String category, String categoryName){
        
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
        
		addToCategoriesScreenIdList(category,screenId);

		JSONArray screensArr = (JSONArray) screensJsonObject.get("screens");

		String screenPath = null;
		if(screensArr != null)
			for (Object screenObj : screensArr){
				JSONObject screen = (JSONObject) screenObj;
				String screenTypeTmp = (String)screen.get("type");
				String screenPathTmp = (String)screen.get("path");
				if(screenType.equals(screenTypeTmp)) {
					screenPath = screenPathTmp;
					break;
				}

			}

		ResourcePathsHolder.addResourcePaths(screenId, screenPath);
		QuestionFactory.createQuestion(screenType,questionObj,chapterName,categoryName,answersSet);

	}
	
	private static void parseCategory(Object categoryObj, String chapterName){
		if(!(categoryObj instanceof JSONObject)){
			System.out.println("error in parseCategory");
		}
		
    	JSONObject tmpCategory = (JSONObject) categoryObj;
    	String category = (String) tmpCategory.get("category");
    	String categoryName = (String) tmpCategory.get("categoryName");
		JSONArray categoryList = (JSONArray) tmpCategory.get("categoryList");

		addToChaptersCategoryList(chapterName, categoryName);
		
		categoryTotalQuestions.put(category, categoryList.size());

    	for (Object question : categoryList){
        	parseQuestion(question,chapterName,category,categoryName);
        }	
	}
	
	private static void parseChapter(Object chapter){
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

	private static void parseQuestions(){

		JSONArray questions = (JSONArray) dataJsonObject.get("questions");
        
    	for (Object chapter : questions){
    		parseChapter(chapter);	
        }
	}

	private static void parseMainScreens(){

		JSONArray mainScreens = (JSONArray) dataJsonObject.get("mainScreens");
        
    	for (Object mainScreen : mainScreens){
    		JSONObject s = (JSONObject) mainScreen;
    		String screenId = (String) s.get("screenId");
        	ResourcePathsHolder.addResourcePaths(screenId, "fxml/" +screenId+".fxml");

            ImagInLexis.mainContainer.loadScreen(screenId, null);
        }
	}

	public static int getCategoryTotalQuestions(String category){
		return categoryTotalQuestions.get(category);
	}

	public static List<String> getCategoriesScreenIdList(String category){
		return categoriesScreenIdList.get(category);
	}
	
	private static void addToChaptersCategoryList(String chapterName, String categoryName){
		List<String> list = chaptersCategoryList.computeIfAbsent(chapterName, k -> new ArrayList<>());
		list.add(categoryName);
	}

	private static void addToCategoriesScreenIdList(String category, String screenId){
		List<String> list = categoriesScreenIdList.computeIfAbsent(category, k -> new ArrayList<>());
		list.add(screenId);
	}

}
