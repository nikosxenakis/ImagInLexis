package com.xenakis;

import com.xenakis.application.ImagInLexisParser;
import com.xenakis.application.ScreenPane;
import com.xenakis.service.DatabaseUtil;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.log4j.BasicConfigurator;

public class ImagInLexis extends Application {

    public static ScreenPane mainContainer = null;
    public static String userName = DatabaseUtil.getActiveUser();

    @Override
    public void start(Stage primaryStage){

        BasicConfigurator.configure();

        primaryStage.setTitle("ImagInLexis");
        primaryStage.setMinWidth(1130);
        primaryStage.setMinHeight(750);
        primaryStage.setMaxWidth(1130);
        primaryStage.setMaxHeight(750);

        mainContainer = new ScreenPane();

        ImagInLexisParser.initialize();

        Scene scene = new Scene(mainContainer);
//        scene.getStylesheets().add("src/application.css");

        ImagInLexis.mainContainer.setScreen("MainScreen");

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @Override
    public void stop(){
        System.out.println("Stage is closing");
        // Save file
    }

    public static void main(String[] args) {
        launch(args);
    }

    public static void setUserName(String userName) {
        ImagInLexis.userName = userName;
    }
}
