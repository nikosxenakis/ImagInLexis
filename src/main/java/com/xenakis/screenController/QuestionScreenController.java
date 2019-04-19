package com.xenakis.screenController;

import java.util.HashSet;
import java.util.Set;

import com.xenakis.application.ImageHolder;
import com.xenakis.application.Test;
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
    
    private Test test;
    private boolean isSelection = false;
    private Set<Integer> answers = new HashSet<Integer>();
    
	public Test getTest(){
		return test;
	}
	
	public boolean getIsSelection(){
		return this.isSelection;
	}
	
	public Set<Integer> getAnswer(){
		return this.answers;
	}
	
	public void setAnswer(Integer answer){
		this.answers.clear();
		this.answers.add(answer);
	}
	
	public void addAnswer(Integer answer){
		this.answers.add(answer);
	}
	
	public void setIsSelection(boolean isSelection){
		this.isSelection = isSelection;
	}
	
    public void setData(QuestionScreenData screenData, Test test){
    	//System.out.println("set Data in QuestionScreenController");

    	this.test = test;

    	question.setText(screenData.getQuestion());
    	
    	answeredQuestions.setText(test.getAnsweredQuestions().toString());
    	totalQuestions.setText(test.getTotalQuestions().toString());
    	chapterName.setText(screenData.getChapterName().toString());
    	categoryName.setText(screenData.getCategoryName().toString());
   
    	//System.out.println("loading image: "+test.getChapter().toString()+"Image");
    	Image image = ImageHolder.getImage(test.getChapter().toString()+"Image");
    	chapterImage.setImage(image);

    	//System.out.println("loading image: "+test.getCategory()+"Image");
    	image = ImageHolder.getImage(test.getCategory()+"Image");
    	categoryImage.setImage(image);

    	progressBar.setProgress((double)(getTest().getAnsweredQuestions()/(double)getTest().getTotalQuestions()));
    	
    	submitButton.setDisable(true);
	
    	if(test.getTotalQuestions().equals(1)){
    		nextButton.setDisable(true);
    	}
    	
    	mainWindow.setStyle(test.getMainWindowStyle());	
    	mainPane.setStyle(test.getMainPaneStyle());
    	infoPane.setStyle(test.getInfoPaneStyle());
    	
    }
    
    public void setAnsweredQuestions(Integer answeredQuestions){
    	this.answeredQuestions.setText(answeredQuestions.toString());
    	this.progressBar.setProgress((double)(getTest().getAnsweredQuestions()/(double)getTest().getTotalQuestions()));
    	
    	if(getTest().isLastQuestion()){
    		nextButton.setDisable(true);
    	}

    }
    
    public void clicked(MouseEvent e){
    	
        if((Button)e.getSource() == submitButton){
        	if(getIsSelection() == false){
        		System.err.println("error in clicked there is no selection");
        		return;
        	}
        	
        	System.out.println("submitButton clicked");
            
        	getTest().submitAnswer(getScreenPane(), getAnswer());

        }
        if((Button)e.getSource() == nextButton){
        	System.out.println("nextButton clicked");

        	getTest().nextQuestion(getScreenPane());

        }
        
    }

}
