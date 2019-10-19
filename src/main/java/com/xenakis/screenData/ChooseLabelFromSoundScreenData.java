package com.xenakis.screenData;

import java.util.Set;

public class ChooseLabelFromSoundScreenData extends QuestionScreenData{
	
	private final String radioOption1;
	private final String radioOption2;
	private final String radioOption3;
	private final String radioOption4;

	private final String imageId;
	private final String questionSoundId;
	private final String soundId;
	private final String sound1Id;
	private final String sound2Id;
	private final String sound3Id;
	private final String sound4Id;

	public ChooseLabelFromSoundScreenData(String question,String imageId, String radioOption1, String radioOption2, String radioOption3, String radioOption4, String questionSoundId, String soundId, String sound1Id, String sound2Id, String sound3Id, String sound4Id, Set<Integer> answers, String chapterName, String categoryName){
		super(question,answers,chapterName,categoryName);
		this.radioOption1 = radioOption1;
		this.radioOption2 = radioOption2;
		this.radioOption3 = radioOption3;
		this.radioOption4 = radioOption4;
		this.imageId = imageId;
		this.questionSoundId = questionSoundId;
		this.soundId = soundId;
		this.sound1Id = sound1Id;
		this.sound2Id = sound2Id;
		this.sound3Id = sound3Id;
		this.sound4Id = sound4Id;

	}

	public String getImageId(){
		return this.imageId;
	}
	
	public String getRadioOption1(){
		return this.radioOption1;
	}
	
	public String getRadioOption2(){
		return this.radioOption2;
	}
	
	public String getRadioOption3(){
		return this.radioOption3;
	}
	
	public String getRadioOption4(){
		return this.radioOption4;
	}
	
	public String getQuestionSoundId(){
		return this.questionSoundId;
	}
	
	public String getSoundId(){
		return this.soundId;
	}
	
	public String getSound1Id(){
		return this.sound1Id;
	}
	
	public String getSound2Id(){
		return this.sound2Id;
	}
	
	public String getSound3Id(){
		return this.sound3Id;
	}
	
	public String getSound4Id(){
		return this.sound4Id;
	}
}
