package com.xenakis.application;

import com.xenakis.model.Score;
import com.xenakis.service.Database;
import com.xenakis.service.DatabaseUtil;

import java.util.List;

public class ScoreUtil {

    public static List<Score> getScoreList(String chapterName, String categoryName){

        List<Score> scoreList;

        if(chapterName.equals("Όλα")) {
            scoreList = DatabaseUtil.selectScores(null, null);
        }
        else if(categoryName.equals("Όλα")) {
            scoreList = DatabaseUtil.selectScores(chapterName, null);
        }
        else {
            scoreList = DatabaseUtil.selectScores(chapterName, categoryName);
        }

        return scoreList;
    }


}
