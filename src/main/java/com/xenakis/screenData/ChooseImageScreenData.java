package com.xenakis.screenData;

import java.util.Set;

public abstract class ChooseImageScreenData extends QuestionScreenData {

    protected final String image1Id;
    protected final String image2Id;
    protected final String image3Id;
    protected final String soundId;

    ChooseImageScreenData(String question, String image1Id, String image2Id, String image3Id, String soundId, Set<Integer> answers, String chapterName, String categoryName){
        super(question, answers, chapterName, categoryName);
        this.image1Id = image1Id;
        this.image2Id = image2Id;
        this.image3Id = image3Id;
        this.soundId = soundId;
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

    public String getSoundId(){
        return this.soundId;
    }

}
