package application;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;

public class ImagInLexis extends Application {

	public static ScreenPane mainContainer = null;
	
	public final static String inputFilePath = "/json/input.json";

	public final static String imagesFilePath = "/json/images.json";

	public final static String soundsFilePath = "/json/sounds.json";

	public final static String scoresFilePath = "files/scores.json";

	public static Parser parser;
	
	public static String userName = "unknown user";
	
	public static Stage mainStage = null;
	
	@Override
	public void start(Stage stage){
		mainStage = stage;
        mainContainer = new ScreenPane();
        
        stage.setMinWidth(1130);
        stage.setMinHeight(750);
        stage.setMaxWidth(1130);
        stage.setMaxHeight(750);
        
        parser = new Parser(inputFilePath,scoresFilePath,imagesFilePath,soundsFilePath);
        parser.initialize();
            	
        Scene scene = new Scene(mainContainer);
        //scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());    
        
		SoundHolder.playSound("startProgramSound");

        ImagInLexis.mainContainer.setScreen("MainScreen");
        
        stage.setScene(scene);
        stage.show();
	}
	
	@Override
	public void stop(){
	    System.out.println("Stage is closing");
	    // Save file
	    parser.submitScores();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public static void cleanMemory(){
		SoundHolder.cleanMemory();
		ImageHolder.cleanMemory();
	}

	public static void setUserName(String userName) {
		ImagInLexis.userName = userName;
	}
}
