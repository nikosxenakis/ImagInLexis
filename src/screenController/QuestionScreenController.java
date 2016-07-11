package screenController;

import application.ImageHolder;
import application.Test;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import screenData.QuestionScreenData;

public abstract class QuestionScreenController extends ScreenController{

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
    
    private Test test;
    private boolean isSelection = false;
    private Integer answer = null;

	public Test getTest(){
		return test;
	}
	
	public boolean getIsSelection(){
		return this.isSelection;
	}
	
	public Integer getAnswer(){
		return this.answer;
	}
	
	public void setAnswer(Integer answer){
		this.answer = answer;
	}
	
	public void setIsSelection(boolean isSelection){
		this.isSelection = isSelection;
	}
	
    public void setData(QuestionScreenData screenData, Test test){
    	System.out.println("set Data in QuestionScreenController");

    	this.test = test;

    	question.setText(screenData.getQuestion());
    	
    	answeredQuestions.setText(test.getAnsweredQuestions().toString());
    	totalQuestions.setText(test.getTotalQuestions().toString());
    	chapterName.setText(screenData.getChapterName().toString());
    	categoryName.setText(screenData.getCategoryName().toString());
    	
    	Image image = ImageHolder.getImage(test.getChapter().toString()+"Image");
    	chapterImage.setImage(image);
  
    	image = ImageHolder.getImage(test.getCategory()+"Image");
    	categoryImage.setImage(image);
    	
    	progressBar.setProgress((double)(getTest().getAnsweredQuestions()/(double)getTest().getTotalQuestions()));
    	
    	submitButton.setDisable(true);
		
    }
    
    public void setAnsweredQuestions(Integer answeredQuestions){
    	this.answeredQuestions.setText(answeredQuestions.toString());
    	this.progressBar.setProgress((double)(getTest().getAnsweredQuestions()/(double)getTest().getTotalQuestions()));
    	
    	if(getTest().isLastQuestion()){
    		nextButton.setDisable(true);
    	}
    }
    
    public void clicked(MouseEvent e){
    	
    	//play sound
    	/*
    	String musicFile = "/sounds/horn.mp3";
   	 	Media sound= new Media(getClass().getResource(musicFile).toExternalForm());
    	MediaPlayer mediaPlayer = new MediaPlayer(sound);
    	mediaPlayer.play();
    	*/
	
        if((Button)e.getSource() == submitButton){
        	if(getIsSelection() == false)
        		return;
        	
        	System.out.println("submitButton clicked");
            
        	getTest().submitAnswer(getScreenPane(), getAnswer());
        	getTest().nextQuestion(getScreenPane());

        }
        if((Button)e.getSource() == nextButton){
        	System.out.println("nextButton clicked");

        	getTest().nextQuestion(getScreenPane());

        }
        
    }
}
