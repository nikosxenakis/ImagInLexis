package com.xenakis.screenData;

import java.util.Set;

public class ChooseLabelFromSoundScreenData extends QuestionScreenData{
	
	private String radioOption1 = null;
	private String radioOption2 = null;
	private String radioOption3 = null;
	private String radioOption4 = null;

	private String imageId = null;
	private String questionSoundId = null;
	private String soundId = null;
	private String sound1Id = null;
	private String sound2Id = null;
	private String sound3Id = null;
	private String sound4Id = null;

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
