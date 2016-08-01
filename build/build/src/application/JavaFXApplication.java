package application;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;

public class JavaFXApplication extends Application {

	public static ScreenPane mainContainer = null;
	
	public final static String inputFilePath = "/json/input.json";

	public final static String scoresFilePath = "/json/scores.json";

	public static Parser parser;
	
	@Override
	public void start(Stage stage){
        mainContainer = new ScreenPane();
        
        stage.setMinWidth(960);
        stage.setMinHeight(570);
        
        parser = new Parser(inputFilePath,scoresFilePath);
        parser.initialize();
        
        Scene scene = new Scene(mainContainer);
        //scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());    
        
        String screenId = "homeScreen";
		JavaFXApplication.mainContainer.setScreen(screenId);

        stage.setScene(scene);
        stage.show();

	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
