package com.xenakis.application;

import com.xenakis.databaseService.ScreenUtil;
import com.xenakis.model.ScreenData;
import com.xenakis.service.JsonParser;
import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import java.util.*;

public class ImagInLexisParser {

	static final Logger logger = Logger.getLogger(ImagInLexisParser.class);

	private static JSONObject dataJsonObject;

	//categoryName -> chapterTotalQuestions
	private static final HashMap<String, Integer> categoryTotalQuestions = new HashMap<>();

	//categoryName -> screenId List
	private static final HashMap<String, List<String>> categoriesScreenIdList = new HashMap<>();

	public static void initialize() {
		dataJsonObject = JsonParser.loadObject("json/data.json");
		parseQuestions();
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

		ScreenData screenData = ScreenUtil.getScreen(2, screenType);
		String screenPath = (screenData != null) ? screenData.getPath() : null;
		ResourcePathsHolder.addResourcePaths(screenId, screenPath);
		QuestionFactory.createQuestion(screenType,questionObj,chapterName,categoryName,answersSet);
	}
	
	private static void parseCategory(Object categoryObj, String chapterName){
		if(!(categoryObj instanceof JSONObject)){
			logger.error("parseCategory");

		}

    	JSONObject tmpCategory = (JSONObject) categoryObj;
    	String category = (String) tmpCategory.get("category");
    	String categoryName = (String) tmpCategory.get("categoryName");
		JSONArray categoryList = (JSONArray) tmpCategory.get("categoryList");

		categoryTotalQuestions.put(category, categoryList.size());

    	for (Object question : categoryList){
        	parseQuestion(question,chapterName,category,categoryName);
        }
	}

	private static void parseChapter(Object chapter){
		if(!(chapter instanceof JSONObject)){
			logger.error("parseChapter");
		}
		
    	JSONObject tmpChapter = (JSONObject) chapter;
    	String chapterName = (String) tmpChapter.get("chapterName");
		JSONArray chapterList = (JSONArray) tmpChapter.get("chapterList");

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

	public static int getCategoryTotalQuestions(String category){
		return categoryTotalQuestions.get(category);
	}

	public static List<String> getCategoriesScreenIdList(String category){
		return categoriesScreenIdList.get(category);
	}

	private static void addToCategoriesScreenIdList(String category, String screenId){
		List<String> list = categoriesScreenIdList.computeIfAbsent(category, k -> new ArrayList<>());
		list.add(screenId);
	}

}
