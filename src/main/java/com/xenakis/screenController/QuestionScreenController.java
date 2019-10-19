package com.xenakis.screenController;

import java.util.HashSet;

import com.xenakis.application.ImageHolder;
import com.xenakis.application.TestUtil;
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

public abstract class QuestionScreenController extends ScreenController{

	@FXML
	protected AnchorPane mainWindow;
	
	@FXML
    protected Button submitButton;
    
    @FXML
    protected Button nextButton;
    
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
    
    private TestUtil testUtil;
    private boolean isSelection = false;
    private final HashSet<Integer> answers = new HashSet<>();

	private TestUtil getTestUtil(){
		return testUtil;
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

	void setIsSelection(boolean isSelection){
		this.isSelection = isSelection;
	}
	
    public void setData(QuestionScreenData screenData, TestUtil testUtil){
    	//System.out.println("set Data in QuestionScreenController");

    	this.testUtil = testUtil;

    	question.setText(screenData.getQuestion());
    	
    	answeredQuestions.setText(String.valueOf(testUtil.getAnsweredQuestions()));
    	totalQuestions.setText(String.valueOf(testUtil.getTotalQuestions()));
    	chapterName.setText(screenData.getChapterName());
    	categoryName.setText(screenData.getCategoryName());
   
    	//System.out.println("loading image: "+testUtil.getChapter().toString()+"Image");
    	Image image = ImageHolder.getImage(testUtil.getChapter()+"Image");
    	chapterImage.setImage(image);

    	//System.out.println("loading image: "+testUtil.getCategory()+"Image");
    	image = ImageHolder.getImage(testUtil.getCategory()+"Image");
    	categoryImage.setImage(image);

    	progressBar.setProgress((getTestUtil().getAnsweredQuestions()/(double) getTestUtil().getTotalQuestions()));
    	
    	submitButton.setDisable(true);
	
    	if(testUtil.getTotalQuestions() == 1){
    		nextButton.setDisable(true);
    	}
    	
    	mainWindow.setStyle(testUtil.getMainWindowStyle());
    	mainPane.setStyle(testUtil.getMainPaneStyle());
    	infoPane.setStyle(testUtil.getInfoPaneStyle());
    	
    }
    
    public void setAnsweredQuestions(Integer answeredQuestions){
    	this.answeredQuestions.setText(answeredQuestions.toString());
    	this.progressBar.setProgress((getTestUtil().getAnsweredQuestions()/(double) getTestUtil().getTotalQuestions()));
    	
    	if(getTestUtil().isLastQuestion()){
    		nextButton.setDisable(true);
    	}

    }

    public void clicked(MouseEvent e){
    	
        if(e.getSource() == submitButton){
        	if(!getIsSelection()){
        		System.err.println("error in clicked there is no selection");
        		return;
        	}
        	
        	System.out.println("submitButton clicked");
            
        	getTestUtil().submitAnswer(getScreenPane(), getAnswer());

        }
        else if(e.getSource() == nextButton){
        	System.out.println("nextButton clicked");

        	getTestUtil().nextQuestion(getScreenPane());

        }
        
    }

}
