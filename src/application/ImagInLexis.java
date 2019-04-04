package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

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

//		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
//		DateFormat timeFormat = new SimpleDateFormat("HH:mm");
//
//		Date date = new Date();
//		Date time = new Date();
//		String strDate = dateFormat.format(date);
//		String strTime = timeFormat.format(time);
//
//		Database.insert(ImagInLexis.userName, strTime, strDate, "0%", "Αναγνώριση", "Φρούτα");
//		Database.insert(ImagInLexis.userName, strTime, strDate, "2%", "Αναγνώριση", "Χρώματα");

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
