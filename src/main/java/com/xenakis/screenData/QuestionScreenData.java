package com.xenakis.screenData;

import java.util.Set;

public abstract class QuestionScreenData{
	
	private final String question;
	private final Set<Integer> answers;
	private final String chapterName;
	private final String categoryName;
	
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
