package screenData;

import java.util.Set;

public class ChooseLabelFromSoundScreenData extends QuestionScreenData{
	
	private String radioOption1;
	private String radioOption2;
	private String radioOption3;
	private String imageId;

	public ChooseLabelFromSoundScreenData(String question,String imageId, String radioOption1, String radioOption2, String radioOption3,  Set<Integer> answers, String chapterName, String categoryName){
		super(question,answers,chapterName,categoryName);
		this.radioOption1 = radioOption1;
		this.radioOption2 = radioOption2;
		this.radioOption3 = radioOption3;
		this.imageId = imageId;
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
	
}
