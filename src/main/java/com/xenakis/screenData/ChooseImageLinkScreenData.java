package com.xenakis.screenData;

import java.util.Set;

public class ChooseImageLinkScreenData extends ChooseImageScreenData {
	
	private final String basicImageId;
	
	public ChooseImageLinkScreenData(String question, String basicImageId,String image1Id, String image2Id, String image3Id, String soundId, Set<Integer> answers, String chapterName, String categoryName){		
		super(question, image1Id, image2Id, image3Id, soundId, answers, chapterName, categoryName);
		this.basicImageId = basicImageId;
	}

	public String getBasicImageId(){
		return this.basicImageId;
	}

}
