package com.xenakis.screenData;

import java.util.Set;

public class ChooseImageScreenData2 extends ChooseImageScreenData {

	private final String image4Id;

	public ChooseImageScreenData2(String question,String image1Id, String image2Id, String image3Id, String image4Id, String soundId, Set<Integer> answers, String chapterName, String categoryName){
		super(question, image1Id, image2Id, image3Id, soundId, answers, chapterName, categoryName);
		this.image4Id = image4Id;
	}

	public String getImage4Id(){
		return this.image4Id;
	}
	
}
