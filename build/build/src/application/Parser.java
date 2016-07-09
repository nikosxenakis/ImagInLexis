package application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Parser {
	
	private JSONObject jsonObject;
	
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
    	Integer questionNumber = Integer.parseInt( (String)(question.get("questionNumber")) );
    	Integer totalQuestions = Integer.parseInt( (String)(question.get("totalQuestions")) );
    	    	
        ChooseImageScreenData chooseImageScreenData1 = new ChooseImageScreenData(questionString,image1Id,image2Id,image3Id,questionNumber,totalQuestions,chapterName,categoryName);
        JavaFXApplication.mainContainer.loadScreen(screenId, JavaFXApplication.chooseImageScreenFXML, chooseImageScreenData1);
        
	}
	
	private void parseQuestion(Object question, String chapterName, String categoryName){
    	System.out.println(question);
        
    	JSONObject questionObj = (JSONObject) question;
    	String screenType = (String)(questionObj.get("screenType"));

    	if(screenType.equals("chooseImage")){
    		createChooseImageQuestion(questionObj,chapterName,categoryName);
    	}
    	else if(screenType.equals("chooseLabel")){
    		createChooseLabelQuestion(questionObj,chapterName,categoryName);
    	}
    	else{
    		System.out.println("not implemented yet");
    	}
	}
	
	private void parseCategory(Object category, String chapterName){
		if(!(category instanceof JSONObject)){
			System.out.println("error in parseCategory");
		}
		
    	JSONObject tmpCategory = (JSONObject) category;
    	String categoryName = (String) tmpCategory.get("categoryName");
		JSONArray categoryList = (JSONArray) tmpCategory.get("categoryList");

    	for (Object question : categoryList){
        	parseQuestion(question,chapterName,categoryName);
        }	
	}
	
	private void parseChapter(Object chapter){
		if(!(chapter instanceof JSONObject)){
			System.out.println("error in parseChapter");
		}
		
    	JSONObject tmpChapter = (JSONObject) chapter;
    	String chapterName = (String) tmpChapter.get("chapterName");
		JSONArray chapterList = (JSONArray) tmpChapter.get("chapterList");

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

	

	
	
}
