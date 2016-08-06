package screenData;

import java.util.Set;

public class ChooseLabelScreenData extends QuestionScreenData{
	
	private String radioOption1;
	private String radioOption2;
	private String radioOption3;
	private String radioOption4;
	private String imageId;
	private String soundId;
	private String sound1Id;
	private String sound2Id;
	private String sound3Id;
	private String sound4Id;

	public ChooseLabelScreenData(String question,String imageId, String radioOption1, String radioOption2, String radioOption3, String radioOption4, String soundId, String sound1Id, String sound2Id, String sound3Id, String sound4Id, Set<Integer> answers, String chapterName, String categoryName){
		super(question,answers,chapterName,categoryName);
		this.radioOption1 = radioOption1;
		this.radioOption2 = radioOption2;
		this.radioOption3 = radioOption3;
		this.radioOption4 = radioOption4;
		this.imageId = imageId;
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
