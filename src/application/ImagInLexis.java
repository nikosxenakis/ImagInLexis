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
	
	public final static String userName = "name2";
	
	@Override
	public void start(Stage stage){
        mainContainer = new ScreenPane();
        
        stage.setMinWidth(1080);
        stage.setMinHeight(720);
      		
        parser = new Parser(inputFilePath,scoresFilePath,imagesFilePath,soundsFilePath);
        parser.initialize();
            	
        Scene scene = new Scene(mainContainer);
        //scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());    
        
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
}
