package com.xenakis.view;

import com.xenakis.model.Score;
import javafx.beans.property.SimpleStringProperty;

public class ScoreView {
    private final SimpleStringProperty name;
    private final SimpleStringProperty score;
    private final SimpleStringProperty date;
    private final SimpleStringProperty time;

    public ScoreView(Score score){
        this.name = new SimpleStringProperty(score.getName());
        this.score = new SimpleStringProperty(Integer.toString(score.getScore()));
        this.date = new SimpleStringProperty(score.getDate());
        this.time = new SimpleStringProperty(score.getTime());
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
