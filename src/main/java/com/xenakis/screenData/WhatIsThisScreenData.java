package com.xenakis.screenData;

import java.util.Set;

public class WhatIsThisScreenData extends QuestionScreenData{

	private final String imageId;
	private final String soundId;
	private final String questionSoundId;
	private final String mainQuestion;
	private final String mainQuestionSoundId;

	public WhatIsThisScreenData(String question, String mainQuestion, String mainQuestionSoundId, String imageId, String soundId, String questionSoundId, Set<Integer> answersSet, String chapterName, String categoryName){		
		super(question,answersSet,chapterName,categoryName);
		this.imageId = imageId;
		this.soundId = soundId;
		this.questionSoundId = questionSoundId;
		this.mainQuestion = mainQuestion;
		this.mainQuestionSoundId = mainQuestionSoundId;

	}

	public String getMainQuestion(){
		return this.mainQuestion;
	}
	
	public String getMainQuestionSoundId(){
		return this.mainQuestionSoundId;
	}
	
	public String getImageId(){
		return this.imageId;
	}
	
	public String getQuestionSoundId(){
		return this.questionSoundId;
	}
	
	public String getSoundId(){
		return this.soundId;
	}
}