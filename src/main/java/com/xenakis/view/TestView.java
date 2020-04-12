package com.xenakis.view;

public class TestView {

    public static String getMainWindowStyle(String chapterName){
        String mainWindowStyle = "-fx-border-width: 10;";

        if ("Αναγνώριση".equals(chapterName)) {
            mainWindowStyle += "-fx-background-color:  #DDE3A8;";// -fx-border-color:  #9ED5DB";
        } else if ("Κατονομασία".equals(chapterName)) {
            mainWindowStyle += "-fx-background-color:  #FFD154;";// -fx-border-color:  #CF903B";
        } else if ("Συσχετιζόμενες Έννοιες".equals(chapterName)) {
            mainWindowStyle += "-fx-background-color:  #ED591F;";// -fx-border-color:  #E08E70";
        } else {
            System.err.println("error in TestView Screen no such a chapter");
        }
        return mainWindowStyle;
    }

    public static String getMainPaneStyle(String chapterName){
        String mainPaneStyle = "-fx-background-radius: 15;";

        if ("Αναγνώριση".equals(chapterName)) {
            mainPaneStyle += "-fx-background-color:  #7ECCC7;";
        } else if ("Κατονομασία".equals(chapterName)) {
            mainPaneStyle += "-fx-background-color:  #80DBBB;";
        } else if ("Συσχετιζόμενες Έννοιες".equals(chapterName)) {
            mainPaneStyle += "-fx-background-color:  #AE99C2;";
        } else {
            System.err.println("error in TestView Screen no such a chapter");
        }
        return mainPaneStyle;
    }

    public static String getInfoPaneStyle(String chapterName){
        String infoPaneStyle = "-fx-background-radius: 15;";

        if ("Αναγνώριση".equals(chapterName)) {
            infoPaneStyle += "-fx-background-color:  #7ECCA4;";
        } else if ("Κατονομασία".equals(chapterName)) {
            infoPaneStyle += "-fx-background-color:  #BADB80;";
        } else if ("Συσχετιζόμενες Έννοιες".equals(chapterName)) {
            infoPaneStyle += "-fx-background-color:  #E39DAD;";
        } else {
            System.err.println("error in TestView Screen no such a chapter");
        }
        return infoPaneStyle;
    }
}
