package com.xenakis;

import com.xenakis.application.ImagInLexisParser;
import com.xenakis.application.ResourcePathsHolder;
import com.xenakis.application.ScreenPane;
import com.xenakis.model.ScreenData;
import com.xenakis.service.ScreenUtil;
import com.xenakis.service.UserUtil;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.log4j.BasicConfigurator;

import java.util.ArrayList;

public class ImagInLexis extends Application {

    public static ScreenPane mainContainer = null;
    public static String userName = UserUtil.getActiveUser();

    @Override
    public void start(Stage primaryStage){

        BasicConfigurator.configure();

        primaryStage.setTitle("ImagInLexis");
        primaryStage.setMinWidth(1130);
        primaryStage.setMinHeight(750);
        primaryStage.setMaxWidth(1130);
        primaryStage.setMaxHeight(750);

        mainContainer = new ScreenPane();

        ArrayList<ScreenData>  screenDataArray = ScreenUtil.getScreens(1);
        for(ScreenData screenData: screenDataArray) {
            ResourcePathsHolder.addResourcePaths(screenData.getName(), screenData.getPath());
            ImagInLexis.mainContainer.loadScreen(screenData.getName(), null);
        }

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
    }

    public static void main(String[] args) {
        launch(args);
    }

    public static void setUserName(String userName) {
        ImagInLexis.userName = userName;
    }
}
