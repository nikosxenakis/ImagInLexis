/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package screenController;

import java.net.URL;
import java.util.ResourceBundle;

import application.ImageHolder;
import application.ScreenPane;
import application.Test;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import screenData.ChooseImageScreenData;
import screenData.ScreenData;

public class ChooseLabelScreenController implements Initializable, ScreenController {
    private ScreenPane myScreenPane;
    private Test test;

    @FXML
    private Button submitButton;
    
    @FXML
    private Button nextButton;
    
    @FXML
    private ImageView image1;

    @FXML
    private ImageView image2;
    
    @FXML
    private ImageView image3;
    
    @FXML
    private Text question;
  
    @FXML
    private Text answeredQuestions;
    
    @FXML
    private Text totalQuestions;
    
    @FXML
    private ProgressBar progressBar;

    @FXML
    private Text chapterName;
  
    @FXML
    private Text categoryName;
    
    @FXML
    private ImageView chapterImage;
    
    @FXML
    private ImageView categoryImage;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    

    public void setData(ScreenData screenData,Test test){
    	System.out.println("set Data Screen1Controller");
    	
    	this.test = test;
    	
    	if(!(screenData instanceof ChooseImageScreenData)){
        	System.out.println("screenData is not ChooseImageScreenData");
    		return;
    	}
    	
    	ChooseImageScreenData data = (ChooseImageScreenData) screenData;
    	    	    	
    	//choose the initialization resourses
    	
    	Image image = ImageHolder.getImage(data.getImage1Id());
    	image1.setImage(image);

    	image = ImageHolder.getImage(data.getImage2Id());
    	image2.setImage(image);
    	
    	image = ImageHolder.getImage(data.getImage3Id());
    	image3.setImage(image);
    	
    	question.setText(data.getQuestion());
    	
    	answeredQuestions.setText(test.getAnsweredQuestions().toString());
    	totalQuestions.setText(test.getTotalQuestions().toString());
    	chapterName.setText(data.getChapterName().toString());
    	categoryName.setText(data.getCategoryName().toString());

    	image = ImageHolder.getImage(data.getChapterName().toString()+"Image");
    	chapterImage.setImage(image);
  
    	image = ImageHolder.getImage(data.getCategoryName().toString()+"Image");
    	categoryImage.setImage(image);
    	
    	progressBar.setProgress((double)(test.getAnsweredQuestions()/(double)test.getTotalQuestions()));
    }
    
    public void setScreenPane(ScreenPane screenPage) {
        myScreenPane = screenPage;
    }

    public void setAnsweredQuestions(Integer answeredQuestions){
    	this.answeredQuestions.setText(answeredQuestions.toString());
    	this.progressBar.setProgress((double)(test.getAnsweredQuestions()/(double)test.getTotalQuestions()));
    }
    
    public void disableImages(){
    	String disabledImageStyle = "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.8), 10, 0, 0, 0)";
    	image1.setStyle(disabledImageStyle);
    	image2.setStyle(disabledImageStyle);
    	image3.setStyle(disabledImageStyle);
    }
   
    public void enableImage(ImageView image){
    	disableImages();
    	String enabledImageStyle = "-fx-effect: dropshadow(three-pass-box, rgba(255,100,100,0.8), 10, 0, 0, 0)";
    	image.setStyle(enabledImageStyle);
    }
    
    public void clicked(MouseEvent e){
    	System.out.println("clicked");
    	
    	//play sound
    	/*
    	String musicFile = "sounds/trainHonk.wav";
   	 	Media sound= new Media(getClass().getResource(musicFile).toExternalForm());
    	MediaPlayer mediaPlayer = new MediaPlayer(sound);
    	mediaPlayer.play();
        */
        
        if((Button)e.getSource() == submitButton){
        	System.out.println("submitButton");
            
        	String nextScreen = test.getNextScreen();
        	System.out.println("next screen = "+nextScreen);
        	myScreenPane.setScreen(nextScreen);

        }
        if((Button)e.getSource() == nextButton){
        	System.out.println("nextButton");

        	String nextScreen = test.getNextScreen();
        	System.out.println("next screen = "+nextScreen);
        	myScreenPane.setScreen(nextScreen);
        	
        }
        
    }
    
    public void imageClicked(MouseEvent e){
    	System.out.println("imageClicked");

        if((ImageView)e.getSource() == image1){
        	System.out.println("image1");
        	enableImage(image1);
        }
        else if((ImageView)e.getSource() == image2){
        	System.out.println("image2");
        	enableImage(image2);
        }
        else if((ImageView)e.getSource() == image3){
        	System.out.println("image3");
        	enableImage(image3);
        }
    }
    
}
