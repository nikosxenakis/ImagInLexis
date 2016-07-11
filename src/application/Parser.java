package application;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import screenData.ChooseImageScreenData;
import screenData.ScreenDataHolder;

public class Parser {
	
	private JSONObject jsonObject;
	//chapterName -> chapterTotalQuestions
	private HashMap<String,Integer> chapterTotalQuestions = new HashMap<String, Integer>();
	//categoryName -> chapterTotalQuestions
	private HashMap<String,Integer> categoryTotalQuestions = new HashMap<String, Integer>();

	//chapterName -> categoryName List
	private HashMap<String,List<String>> chaptersCategoryList = new HashMap<String, List<String>>();
	
	//categoryName -> screenId List
	private HashMap<String,List<String>> categoriesScreenIdList = new HashMap<String, List<String>>();
	
	Parser(String filePath){

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

	private void createChooseLabelQuestion(JSONObject question, String chapterName, String categoryName){
		/*
    	String screenId = (String)(question.get("screenId"));
    	String questionString = (String)(question.get("question"));
    	String image1Id = (String)(question.get("image1Id"));
    	String image2Id = (String)(question.get("image2Id"));
    	String image3Id = (String)(question.get("image3Id"));
    	Integer questionNumber = Integer.parseInt( (String)(question.get("questionNumber")) );
    	Integer totalQuestions = Integer.parseInt( (String)(question.get("totalQuestions")) );
    	    	
        ChooseImageScreenData chooseImageScreenData1 = new ChooseImageScreenData(questionString,image1Id,image2Id,image3Id,questionNumber,totalQuestions,chapterName,categoryName);
        JavaFXApplication.mainContainer.loadScreen(screenId, JavaFXApplication.chooseImageScreenFXML, chooseImageScreenData1);
        */
	}
	
	private void createChooseImageQuestion(JSONObject question, String chapterName, String categoryName){
    	String screenId = (String)(question.get("screenId"));
    	String questionString = (String)(question.get("question"));
    	String image1Id = (String)(question.get("image1Id"));
    	String image2Id = (String)(question.get("image2Id"));
    	String image3Id = (String)(question.get("image3Id"));
    	String answer = (String)(question.get("answer"));

        ChooseImageScreenData chooseImageScreenData1 = new ChooseImageScreenData(questionString,image1Id,image2Id,image3Id,answer,chapterName,categoryName);
        ScreenDataHolder.addScreenData(screenId,chooseImageScreenData1);
	}
	
	private void parseQuestion(Object question, String chapterName,String category, String categoryName){
    	//System.out.println(question);
        
    	JSONObject questionObj = (JSONObject) question;
    	String screenType = (String)(questionObj.get("screenType"));
    	String screenId = (String)(questionObj.get("screenId"));

		addtoCategoriesScreenIdList(category,screenId);

    	if(screenType.equals("chooseImage")){
        	ResourcePathsHolder.addResourcePaths(screenId, "../fxml/ChooseImageScreen.fxml");

    		createChooseImageQuestion(questionObj,chapterName,categoryName);
    	}
    	else if(screenType.equals("chooseLabel")){
        	ResourcePathsHolder.addResourcePaths(screenId, "../fxml/ChooseLabelScreen.fxml");

    		createChooseLabelQuestion(questionObj,chapterName,categoryName);
    	}
    	else{
    		//System.out.println("not implemented yet");
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
