package com.xenakis.screenData;

import java.util.ArrayList;
import java.util.Set;

import com.xenakis.model.Circle;

public class ChooseInImageScreenData extends QuestionScreenData{
	
	private final String imageId;
	private final ArrayList<Circle> circlesList;
	private boolean absolute = true;
	private final String questionSoundId;
	
	public ChooseInImageScreenData(String question, String questionSoundId, String imageId, ArrayList<Circle> circlesList, String absolute, Set<Integer> answers, String chapterName, String categoryName){
		super(question,answers,chapterName,categoryName);
		this.imageId = imageId;
		this.questionSoundId = questionSoundId;
		this.circlesList = circlesList;
		if(absolute != null)
			this.absolute = Boolean.parseBoolean(absolute);
	}
	
	public String getQuestionSoundId(){
		return this.questionSoundId;
	}
	
	public Boolean getAbsolute(){
		return this.absolute;
	}
	
	public String getImageId(){
		return this.imageId;
	}
	
	public ArrayList<Circle> getCirclesList(){
		return this.circlesList;
	}
	
}
