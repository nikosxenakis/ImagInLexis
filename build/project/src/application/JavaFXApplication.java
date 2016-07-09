package application;

import java.nio.file.Path;
import java.nio.file.Paths;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;

public class JavaFXApplication extends Application {

	//screens declarations
	public static String chooseImageScreenFXML = "ChooseImageScreen.fxml";
	public static String chooseLabelScreenFXML = "ChooseLabelScreen.fxml";

	public static ScreenPane mainContainer = null;
	
	public final static String filePath = "/json/input.json";

	@Override
	public void start(Stage stage){
        mainContainer = new ScreenPane();
        
        stage.setMinWidth(840);
        stage.setMinHeight(550);
        
        Parser parser = new Parser(filePath);
        parser.initialize();
        
        mainContainer.setScreen(mainContainer.getNextScreen());

        Scene scene = new Scene(mainContainer);
        scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());    

        stage.setScene(scene);
        stage.show();
        
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
