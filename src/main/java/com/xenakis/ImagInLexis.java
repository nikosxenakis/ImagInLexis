package com.xenakis;

import com.xenakis.application.ImagInLexisParser;
import com.xenakis.application.ImageHolder;
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
    public void start(Stage stage){

        BasicConfigurator.configure();
        mainContainer = new ScreenPane();

        stage.setMinWidth(1130);
        stage.setMinHeight(750);
        stage.setMaxWidth(1130);
        stage.setMaxHeight(750);

        ImagInLexisParser.initialize();

        Scene scene = new Scene(mainContainer);
//        scene.getStylesheets().add("src/application.css");

        ImagInLexis.mainContainer.setScreen("MainScreen");

        stage.setScene(scene);
        stage.show();
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
