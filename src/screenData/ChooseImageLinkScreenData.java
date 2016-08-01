package screenData;

import java.util.Set;

public class ChooseImageLinkScreenData extends QuestionScreenData{
	
	private String basicImageId;
	private String image1Id;
	private String image2Id;
	private String image3Id;
	private String soundId;
	
	public ChooseImageLinkScreenData(String question, String basicImageId,String image1Id, String image2Id, String image3Id, String soundId, Set<Integer> answers, String chapterName, String categoryName){		
		super(question,answers,chapterName,categoryName);
		this.basicImageId = basicImageId;
		this.image1Id = image1Id;
		this.image2Id = image2Id;
		this.image3Id = image3Id;
		this.soundId = soundId;
	}

	public String getBasicImageId(){
		return this.basicImageId;
	}
	
	public String getImage1Id(){
		return this.image1Id;
	}
	
	public String getImage2Id(){
		return this.image2Id;
	}
	
	public String getImage3Id(){
		return this.image3Id;
	}
	
	public String getSoundId(){
		return this.soundId;
	}
}
