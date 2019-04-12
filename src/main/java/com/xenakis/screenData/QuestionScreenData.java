package com.xenakis.screenData;

import java.util.Set;

public abstract class QuestionScreenData{
	
	private String question;
	private Set<Integer> answers;
	private String chapterName;
	private String categoryName;
	
	QuestionScreenData(String question, Set<Integer> answers, String chapterName, String categoryName){
		this.question = question;
		this.answers = answers;
		this.chapterName = chapterName;
		this.categoryName = categoryName;
	}
	
	public String getQuestion(){
		return question;
	}
	
	public Set<Integer> getAnswers(){
		return this.answers;
	}
	
	public String getChapterName(){
		return this.chapterName;
	}
	
	public String getCategoryName(){
		return this.categoryName;
	}
}
