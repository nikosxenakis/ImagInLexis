package application;

public class ChooseImageScreenData extends ScreenData{
	
	private String question;
	private String image1Id;
	private String image2Id;
	private String image3Id;
	private Integer questionNumber;
	private Integer totalQuestions;
	private String chapterName;
	private String categoryName;

	ChooseImageScreenData(String question,String image1Id, String image2Id, String image3Id, Integer questionNumber, Integer totalQuestions, String chapterName, String categoryName){
		this.question = question;
		this.image1Id = image1Id;
		this.image2Id = image2Id;
		this.image3Id = image3Id;
		this.questionNumber = questionNumber;
		this.totalQuestions = totalQuestions;
		this.chapterName = chapterName;
		this.categoryName = categoryName;
	}
	
	public String getQuestion(){
		return this.question;
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
	
	public Integer getQuestionNumber(){
		return this.questionNumber;
	}
	
	public Integer getTotalQuestions(){
		return this.totalQuestions;
	}
	
	public String getChapterName(){
		return this.chapterName;
	}
	
	public String getCategoryName(){
		return this.categoryName;
	}
}
