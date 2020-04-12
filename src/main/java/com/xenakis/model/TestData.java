package com.xenakis.model;

import com.xenakis.application.ImagInLexisParser;

import java.util.HashMap;
import java.util.Set;
import java.util.TreeSet;

public class TestData {
    private final String chapter;
    private final String category;
    private final String chapterName;
    private final String categoryName;

    private final HashMap<String, Set<Integer>> answers = new HashMap<>();
    private final HashMap<String, Set<Integer>> correctAnswers = new HashMap<>();
    private final HashMap<String, Boolean> absoluteAnswers = new HashMap<>();

    private int answeredQuestions;
    private final int totalQuestions;

    public TestData(String chapter, String category, String chapterName, String categoryName) {
        this.chapter = chapter;
        this.category = category;
        this.chapterName = chapterName;
        this.categoryName = categoryName;

        this.answeredQuestions = 0;
        this.totalQuestions = ImagInLexisParser.getCategoryTotalQuestions(category);
    }

    public String getChapter(){
        return this.chapter;
    }

    public String getCategory(){
        return this.category;
    }

    public String getChapterName(){
        return this.chapterName;
    }

    public String getCategoryName(){
        return this.categoryName;
    }

    public int getAnsweredQuestions() { return this.answeredQuestions; }

    public int getTotalQuestions() { return this.totalQuestions; }

    public Set<String> getAnswersKeys() { return this.answers.keySet(); }

    public Set<Integer> getCorrectAnswers(String answersKey) { return this.correctAnswers.get(answersKey); }

    public Set<Integer> getAnswers(String answersKey) { return this.answers.get(answersKey); }

    public Boolean getAbsoluteAnswers(String answersKey) { return this.absoluteAnswers.get(answersKey); }

    public void setAnsweredQuestions(int answeredQuestions) { this.answeredQuestions = answeredQuestions; }

    public void addAbsoluteAnswers(String screenId, boolean absoluteAnswer) { this.absoluteAnswers.put(screenId, absoluteAnswer); }

    public void addCorrectAnswers(String screenId, Set<Integer> answers) { this.correctAnswers.put(screenId, answers); }

    public void addAnswer(String screenId, Set<Integer> answersNo) { answers.put(screenId, answersNo); }
}
