package com.xenakis.application;

import com.xenakis.model.Score;
import com.xenakis.service.Database;

import java.util.List;

public class ScoreUtil {

    public static List<Score> getScoreList(String chapterName, String categoryName){

        List<Score> scoreList;

        if(chapterName.equals("Όλα")) {
            scoreList = Database.select(null, null);
        }
        else if(categoryName.equals("Όλα")) {
            scoreList = Database.select(chapterName, null);
        }
        else {
            scoreList = Database.select(chapterName, categoryName);
        }

        return scoreList;
    }


}
