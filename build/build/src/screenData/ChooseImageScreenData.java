package screenData;

import java.util.Set;

public class ChooseImageScreenData extends QuestionScreenData{
	
	private String image1Id;
	private String image2Id;
	private String image3Id;
	
	public ChooseImageScreenData(String question,String image1Id, String image2Id, String image3Id,  Set<Integer> answers, String chapterName, String categoryName){
		super(question,answers,chapterName,categoryName);
		this.image1Id = image1Id;
		this.image2Id = image2Id;
		this.image3Id = image3Id;
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
	
}
