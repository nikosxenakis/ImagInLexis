package screenData;

public abstract class QuestionScreenData{
	
	private String question;
	private Integer answer;
	private String chapterName;
	private String categoryName;
	
	QuestionScreenData(String question, String answer2, String chapterName, String categoryName){
		this.question = question;
		this.answer = Integer.valueOf(answer2);
		this.chapterName = chapterName;
		this.categoryName = categoryName;
	}
	
	public String getQuestion(){
		return question;
	}
	
	public Integer getAnswer(){
		return this.answer;
	}
	
	public String getChapterName(){
		return this.chapterName;
	}
	
	public String getCategoryName(){
		return this.categoryName;
	}

}
