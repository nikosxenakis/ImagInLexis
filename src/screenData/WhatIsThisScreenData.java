package screenData;

import java.util.Set;

public class WhatIsThisScreenData extends QuestionScreenData{

	private String imageId;
	private String soundId;
	
	public WhatIsThisScreenData(String question, String imageId, String soundId, Set<Integer> answersSet, String chapterName, String categoryName){		
		super(question,answersSet,chapterName,categoryName);
		this.imageId = imageId;
		this.soundId = soundId;
	}

	public String getImageId(){
		return this.imageId;
	}
	
	public String getSoundId(){
		return this.soundId;
	}
}