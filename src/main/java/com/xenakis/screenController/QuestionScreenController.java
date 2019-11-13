package com.xenakis.screenController;

import java.net.URL;
import java.util.HashSet;
import java.util.ResourceBundle;

import com.xenakis.service.TestService;
import com.xenakis.service.ImageService;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import com.xenakis.screenData.QuestionScreenData;
import org.apache.log4j.Logger;

public abstract class QuestionScreenController extends ScreenController {

	@FXML
	protected AnchorPane mainWindow;
	
	@FXML
	private Button submitButton;

    @FXML
    private Button nextButton;

    @FXML
    protected Text question;
    
    @FXML
    protected Text answeredQuestions;
    
    @FXML
    protected Text totalQuestions;
    
    @FXML
    protected ProgressBar progressBar;

    @FXML
    protected Text chapterName;
  
    @FXML
    protected Text categoryName;
    
    @FXML
    protected ImageView chapterImage;
    
    @FXML
    protected ImageView categoryImage;
    
    @FXML
    protected VBox infoPane;
    
    @FXML
    protected BorderPane mainPane;

//	@FXML
//	private TestActionsController testActionsController;

	private static final Logger logger = Logger.getLogger(QuestionScreenController.class);

	@Override
	public void initialize(URL url, ResourceBundle rb) {
	}

    private TestService testService;
    private boolean isSelection;
    private final HashSet<Integer> answers = new HashSet<>();

    QuestionScreenController() {
    	super();
		isSelection = false;
	}
	private TestService getTestService(){
		return testService;
	}

	private boolean getIsSelection(){
		return this.isSelection;
	}

	private HashSet<Integer> getAnswer(){
		return this.answers;
	}

	void setAnswer(int answer){
		this.answers.clear();
		this.answers.add(answer);
	}

	void addAnswer(int answer){
		this.answers.add(answer);
	}

	void setIsSelection(){
		this.isSelection = true;
	}
	
    public void setData(QuestionScreenData screenData, TestService testService){
    	//System.out.println("set Data in QuestionScreenController");

    	this.testService = testService;

    	question.setText(screenData.getQuestion());
    	
    	answeredQuestions.setText(String.valueOf(testService.getAnsweredQuestions()));
    	totalQuestions.setText(String.valueOf(testService.getTotalQuestions()));
    	chapterName.setText(screenData.getChapterName());
    	categoryName.setText(screenData.getCategoryName());
   
    	//System.out.println("loading image: "+testUtil.getChapter().toString()+"Image");
    	Image image = ImageService.getImage(testService.getChapter()+"Image");
    	chapterImage.setImage(image);

    	//System.out.println("loading image: "+testUtil.getCategory()+"Image");
    	image = ImageService.getImage(testService.getCategory()+"Image");
    	categoryImage.setImage(image);

    	progressBar.setProgress((getTestService().getAnsweredQuestions()/(double) getTestService().getTotalQuestions()));
    	
    	submitButton.setDisable(true);
	
    	if(testService.getTotalQuestions() == 1){
    		nextButton.setDisable(true);
    	}
    	
    	mainWindow.setStyle(testService.getMainWindowStyle());
    	mainPane.setStyle(testService.getMainPaneStyle());
    	infoPane.setStyle(testService.getInfoPaneStyle());
    	
    }
    
    public void setAnsweredQuestions(Integer answeredQuestions){
    	this.answeredQuestions.setText(answeredQuestions.toString());
    	this.progressBar.setProgress((getTestService().getAnsweredQuestions()/(double) getTestService().getTotalQuestions()));
    	
    	if(getTestService().isLastQuestion()){
    		nextButton.setDisable(true);
    	}

    }

	public void submitButtonPressed(MouseEvent e) {
		if(!getIsSelection()){
			logger.error("error in clicked there is no selection");
			return;
		}
		logger.info("submitButton clicked");
		getTestService().submitAnswer(getScreenPane(), getAnswer());
    }

	public void nextButtonPressed(MouseEvent e) {
		logger.info("nextButton clicked");
		getTestService().nextQuestion(getScreenPane());
	}

    protected void enableSubmit() {
		submitButton.setDisable(false);
	}
}
