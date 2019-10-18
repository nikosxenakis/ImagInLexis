package com.xenakis.model;

import javafx.beans.property.SimpleStringProperty;

public class Score {
    private final SimpleStringProperty name;
    private final SimpleStringProperty score;
    private final SimpleStringProperty date;
    private final SimpleStringProperty time;

    public Score(String name, int score, String date, String time){
        this.name = new SimpleStringProperty(name);
        this.score = new SimpleStringProperty(Integer.toString(score));
        this.date = new SimpleStringProperty(date);
        this.time = new SimpleStringProperty(time);
    }

    public String getName() {
        return name.get();
    }

    public String getScore() {
        return score.get();
    }

    public String getDate() {
        return date.get();
    }

    public String getTime() {
        return time.get();
    }
}