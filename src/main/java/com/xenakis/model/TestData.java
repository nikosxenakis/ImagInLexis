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
    private final int totalQuestions;

    private int correctAnswersNum;
    private int answeredQuestions;

    public TestData(String chapter, String category, String chapterName, String categoryName) {
        this.chapter = chapter;
        this.category = category;
        this.chapterName = chapterName;
        this.categoryName = categoryName;

        this.answeredQuestions = 0;
        this.totalQuestions = ImagInLexisParser.getCategoryTotalQuestions(category);

        System.out.println("new TestUtil.java: "+chapter+" "+category+" "+chapterName+" "+categoryName+" "+totalQuestions);
    }

    public void addAbsoluteAnswers(String screenId, boolean absoluteAnswer) {
        this.absoluteAnswers.put(screenId, absoluteAnswer);
    }

    public void addCorrectAnswers(String screenId, Set<Integer> answers) {
        this.correctAnswers.put(screenId, answers);
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

    public int getCorrectAnswers(){
        return correctAnswersNum;
    }

    public int getWrongAnswers(){
        return this.totalQuestions - this.correctAnswersNum;
    }

    public int getAnsweredQuestions() { return this.answeredQuestions; }

    public int getTotalQuestions() { return this.totalQuestions; }

    public HashMap<String, Set<Integer>> getAnswers() { return this.answers; }

    public void submitAnswer(String screenId, Set<Integer> answersNo) {
        answeredQuestions++;
        answers.put(screenId, answersNo);
    }

    public int getScore() {

        System.out.println("answers: " + answers.toString());
        System.out.println("correctAnswers: "+ correctAnswers);

        this.correctAnswersNum = 0;

        for (String key : answers.keySet()) {

            //if it is not absolute
            if(absoluteAnswers.get(key) != null && !absoluteAnswers.get(key)){
                if(correctAnswers.get(key).containsAll(answers.get(key)) && answers.get(key).size() > 0){
                    this.correctAnswersNum++;
                }
            }
            else{
                Set<Integer> mutualAnswers = new TreeSet<>(correctAnswers.get(key));
                mutualAnswers.retainAll(answers.get(key));
                this.correctAnswersNum += mutualAnswers.size()/correctAnswers.get(key).size();
            }
        }

        int score = (this.correctAnswersNum * 100) / this.totalQuestions;
        System.out.println("Correct: " + correctAnswersNum + ", Total: " + totalQuestions + ", Score: " + score);
        return score;
    }
}
