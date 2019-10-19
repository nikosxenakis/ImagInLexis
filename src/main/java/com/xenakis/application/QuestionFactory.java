package com.xenakis.application;

import com.xenakis.model.Circle;
import com.xenakis.screenData.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.Set;

public class QuestionFactory {

    public static void createQuestion(String screenType, JSONObject questionObj, String chapterName, String categoryName, Set<Integer> answersSet){
        switch (screenType) {
            case "chooseImage":
                QuestionFactory.createChooseImageQuestion(questionObj, chapterName, categoryName, answersSet);
                break;
            case "chooseImage2":
                QuestionFactory.createChooseImage2Question(questionObj, chapterName, categoryName, answersSet);
                break;
            case "chooseLabel":
                QuestionFactory.createChooseLabelQuestion(questionObj, chapterName, categoryName, answersSet);
                break;
            case "chooseInImage":
                QuestionFactory.createChooseInImageQuestion(questionObj, chapterName, categoryName, answersSet);
                break;
            case "whatIsThis":
                QuestionFactory.createWhatIsThisQuestion(questionObj, chapterName, categoryName, answersSet);
                break;
            case "chooseImageLink":
                QuestionFactory.createChooseImageLinkQuestion(questionObj, chapterName, categoryName, answersSet);
                break;
            case "chooseLabelFromSound":
                QuestionFactory.createChooseLabelFromSoundQuestion(questionObj, chapterName, categoryName, answersSet);
                break;
            default:
                System.err.println("error in parseQuestion not implemented yet");
                break;
        }
    }

    private static void createWhatIsThisQuestion(JSONObject question, String chapterName, String categoryName, Set<Integer> answersSet){

        String screenId = (String)(question.get("screenId"));
        String imageId = (String)(question.get("imageId"));
        String soundId = (String)(question.get("soundId"));
        String questionString = (String)(question.get("question"));
        String questionSoundId = (String)(question.get("questionSoundId"));
        String mainQuestion = (String)(question.get("mainQuestion"));
        String mainQuestionSoundId = (String)(question.get("mainQuestionSoundId"));

        answersSet.add(1);

        WhatIsThisScreenData whatIsThisScreenData = new WhatIsThisScreenData(questionString,mainQuestion,mainQuestionSoundId,imageId,soundId,questionSoundId,answersSet,chapterName,categoryName);

        ScreenDataHolder.addScreenData(screenId,whatIsThisScreenData);
    }

    private static void createChooseInImageQuestion(JSONObject question, String chapterName, String categoryName, Set<Integer> answers){
        String screenId = (String)(question.get("screenId"));
        String questionString = (String)(question.get("question"));
        String imageId = (String)(question.get("imageId"));
        String questionSoundId = (String)(question.get("questionSoundId"));
        String absolute = (String)(question.get("absolute"));

        JSONArray circlesList = (JSONArray)(question.get("circlesList"));
        ArrayList<Circle> circlesArrayList = new ArrayList<>();

        for (Object c : circlesList){
            JSONObject c1 = (JSONObject) c;
            String id = (String)(c1.get("id"));
            Integer circleX = Integer.parseInt((String)(c1.get("circleX")));
            Integer circleY = Integer.parseInt((String)(c1.get("circleY")));
            Integer circleW = Integer.parseInt((String)(c1.get("circleW")));
            Integer circleH = Integer.parseInt((String)(c1.get("circleH")));

            Circle circle = new Circle(id,circleX,circleY,circleW,circleH);
            circlesArrayList.add(circle);
        }

        ChooseInImageScreenData chooseInImageScreenData = new ChooseInImageScreenData(questionString,questionSoundId,imageId,circlesArrayList,absolute,answers,chapterName,categoryName);
        ScreenDataHolder.addScreenData(screenId,chooseInImageScreenData);
    }

    private static void createChooseLabelQuestion(JSONObject question, String chapterName, String categoryName, Set<Integer> answers){

        String screenId = (String)(question.get("screenId"));
        String questionString = (String)(question.get("question"));
        String imageId = (String)(question.get("imageId"));
        String radioOption1 = (String)(question.get("radioOption1"));
        String radioOption2 = (String)(question.get("radioOption2"));
        String radioOption3 = (String)(question.get("radioOption3"));
        String radioOption4 = (String)(question.get("radioOption4"));
        String soundId = (String)(question.get("soundId"));
        String sound1Id = (String)(question.get("sound1Id"));
        String sound2Id = (String)(question.get("sound2Id"));
        String sound3Id = (String)(question.get("sound3Id"));
        String sound4Id = (String)(question.get("sound4Id"));

        ChooseLabelScreenData chooseLabelScreenData = new ChooseLabelScreenData(questionString,imageId,radioOption1,radioOption2,radioOption3,radioOption4,soundId,sound1Id,sound2Id,sound3Id,sound4Id,answers,chapterName,categoryName);
        ScreenDataHolder.addScreenData(screenId,chooseLabelScreenData);
    }

    private static void createChooseLabelFromSoundQuestion(JSONObject question, String chapterName, String categoryName, Set<Integer> answers){

        String screenId = (String)(question.get("screenId"));
        String questionString = (String)(question.get("question"));
        String imageId = (String)(question.get("imageId"));
        String radioOption1 = (String)(question.get("radioOption1"));
        String radioOption2 = (String)(question.get("radioOption2"));
        String radioOption3 = (String)(question.get("radioOption3"));
        String radioOption4 = (String)(question.get("radioOption4"));
        String questionSoundId = (String)(question.get("questionSoundId"));
        String soundId = (String)(question.get("soundId"));
        String sound1Id = (String)(question.get("sound1Id"));
        String sound2Id = (String)(question.get("sound2Id"));
        String sound3Id = (String)(question.get("sound3Id"));
        String sound4Id = (String)(question.get("sound4Id"));

        ChooseLabelFromSoundScreenData chooseLabelFromSoundScreenData = new ChooseLabelFromSoundScreenData(questionString,imageId,radioOption1,radioOption2,radioOption3,radioOption4,questionSoundId,soundId,sound1Id,sound2Id,sound3Id,sound4Id,answers,chapterName,categoryName);
        ScreenDataHolder.addScreenData(screenId,chooseLabelFromSoundScreenData);
    }

    private static void createChooseImageQuestion(JSONObject question, String chapterName, String categoryName, Set<Integer> answers){
        String screenId = (String)(question.get("screenId"));
        String questionString = (String)(question.get("question"));
        String image1Id = (String)(question.get("image1Id"));
        String image2Id = (String)(question.get("image2Id"));
        String image3Id = (String)(question.get("image3Id"));
        String soundId = (String)(question.get("soundId"));

        ChooseImageScreenData chooseImageScreenData = new ChooseImageScreenData(questionString,image1Id,image2Id,image3Id,soundId,answers,chapterName,categoryName);
        ScreenDataHolder.addScreenData(screenId,chooseImageScreenData);
    }

    private static void createChooseImage2Question(JSONObject question, String chapterName, String categoryName, Set<Integer> answers){
        String screenId = (String)(question.get("screenId"));
        String questionString = (String)(question.get("question"));
        String image1Id = (String)(question.get("image1Id"));
        String image2Id = (String)(question.get("image2Id"));
        String image3Id = (String)(question.get("image3Id"));
        String image4Id = (String)(question.get("image4Id"));
        String soundId = (String)(question.get("soundId"));

        ChooseImageScreenData2 chooseImageScreenData = new ChooseImageScreenData2(questionString,image1Id,image2Id,image3Id,image4Id,soundId,answers,chapterName,categoryName);
        ScreenDataHolder.addScreenData(screenId,chooseImageScreenData);
    }

    private static void createChooseImageLinkQuestion(JSONObject question, String chapterName, String categoryName, Set<Integer> answers){
        String screenId = (String)(question.get("screenId"));
        String questionString = (String)(question.get("question"));
        String basicImageId = (String)(question.get("bacisImageId"));
        String image1Id = (String)(question.get("image1Id"));
        String image2Id = (String)(question.get("image2Id"));
        String image3Id = (String)(question.get("image3Id"));
        String soundId = (String)(question.get("soundId"));

        ChooseImageLinkScreenData chooseImageLinkScreenData = new ChooseImageLinkScreenData(questionString,basicImageId,image1Id,image2Id,image3Id,soundId,answers,chapterName,categoryName);
        ScreenDataHolder.addScreenData(screenId,chooseImageLinkScreenData);
    }

}
