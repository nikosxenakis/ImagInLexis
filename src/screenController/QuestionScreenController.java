package screenController;

import java.util.HashSet;
import java.util.Set;

import application.ImageHolder;
import application.Test;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import screenData.QuestionScreenData;

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
    	System.out.println("set Data in QuestionScreenController");

    	this.test = test;

    	question.setText(screenData.getQuestion());
    	
    	answeredQuestions.setText(test.getAnsweredQuestions().toString());
    	totalQuestions.setText(test.getTotalQuestions().toString());
    	chapterName.setText(screenData.getChapterName().toString());
    	categoryName.setText(screenData.getCategoryName().toString());
   
    	System.out.println("loading image: "+test.getChapter().toString()+"Image");
    	Image image = ImageHolder.getImage(test.getChapter().toString()+"Image");
    	chapterImage.setImage(image);

    	System.out.println("loading image: "+test.getCategory()+"Image");
    	image = ImageHolder.getImage(test.getCategory()+"Image");
    	categoryImage.setImage(image);
    

    	progressBar.setProgress((double)(getTest().getAnsweredQuestions()/(double)getTest().getTotalQuestions()));
    	
    	submitButton.setDisable(true);
	
    	if(test.getTotalQuestions().equals(1)){
    		nextButton.setDisable(true);
    	}
    	
    	String style = "-fx-border-width: 10;";
    	if(chapterName.getText().equals("Αναγνώριση")){
        	style += "-fx-background-color:  #9ED5DB; -fx-border-color:  #DDE3A8";
        	mainWindow.setStyle(style);	
    	}
    	else if(chapterName.getText().equals("Κατονομασία")){
        	style += "-fx-background-color:  #8CDB80; -fx-border-color:  #CF903B";
        	mainWindow.setStyle(style);	
    	}
    	else if(chapterName.getText().equals("Συνδιαστικό")){
        	style += "-fx-background-color:  #E39DCC; -fx-border-color:  #E08E70";
        	mainWindow.setStyle(style);	
    	}
    	else{
    		System.err.println("error in setData in Question Screen no such a chapter");
    	}

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
        	if(getIsSelection() == false){
        		System.err.println("error in clicked there is no selection");
        		return;
        	}
        	
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
