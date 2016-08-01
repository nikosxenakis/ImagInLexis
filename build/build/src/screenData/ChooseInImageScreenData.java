package screenData;

import java.util.ArrayList;
import java.util.Set;

import application.Circle;

public class ChooseInImageScreenData extends QuestionScreenData{
	
	private String imageId;
	private ArrayList<Circle> circlesList;

	public ChooseInImageScreenData(String question,String imageId, ArrayList<Circle> circlesList, Set<Integer> answers, String chapterName, String categoryName){
		super(question,answers,chapterName,categoryName);
		this.imageId = imageId;
		this.circlesList = circlesList;
	}
	
	public String getImageId(){
		return this.imageId;
	}
	
	public ArrayList<Circle> getCirclesList(){
		return this.circlesList;
	}
	
}
