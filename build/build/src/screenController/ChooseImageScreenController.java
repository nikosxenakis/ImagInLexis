/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package screenController;

import java.applet.Applet;
import java.applet.AudioClip;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import com.sun.media.jfxmedia.effects.AudioEqualizer;
import com.sun.media.jfxmedia.effects.AudioSpectrum;

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
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Text;
import screenData.ChooseImageScreenData;
import screenData.QuestionScreenData;

public class ChooseImageScreenController extends QuestionScreenController{
    
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
    
    private ImageView selectedImage = null;
            
    /**
     * Initializes the controller class.
     */

    public void setData(QuestionScreenData screenData, Test test){
    	System.out.println("set Data Screen1Controller");
    	super.setData(screenData, test);
    	
    	if(!(screenData instanceof ChooseImageScreenData)){
        	System.out.println("screenData is not ChooseImageScreenData");
    		return;
    	}
    	
    	ChooseImageScreenData data = (ChooseImageScreenData) screenData;
    	    	    	    	
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
    	
    	progressBar.setProgress((double)(getTest().getAnsweredQuestions()/(double)getTest().getTotalQuestions()));
    	
    	getSubmitButton().setDisable(true);
    	
    }

    public void setAnsweredQuestions(Integer answeredQuestions){
    	this.answeredQuestions.setText(answeredQuestions.toString());
    	this.progressBar.setProgress((double)(getTest().getAnsweredQuestions()/(double)getTest().getTotalQuestions()));
    	
    	if(getTest().isLastQuestion()){
    		getNextButton().setDisable(true);
    	}
    }
    
    public void disableImages(){
    	String disabledImageStyle = "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.8), 10, 0, 0, 0)";
    	if(image1 != selectedImage )
    		image1.setStyle(disabledImageStyle);
    	if(image2 != selectedImage )
    		image2.setStyle(disabledImageStyle);
    	if(image3 != selectedImage )
    		image3.setStyle(disabledImageStyle);
    }
   
    public void enableImage(ImageView image){
    	disableImages();
    	if(image == selectedImage )
    		return;
    	
    	String enabledImageStyle = "-fx-effect: dropshadow(three-pass-box, rgba(255,100,100,0.8), 10, 0, 0, 0)";
    	image.setStyle(enabledImageStyle);
    }
    
    public void selectImage(ImageView image){
    	if(image == selectedImage)
    		return;
    	
    	setIsSelection(true);
    	getSubmitButton().setDisable(false);
    	selectedImage = image;
    	if(image == image1)
    		setAnswer(1);
    	else if(image == image2)
    		setAnswer(2);
    	else
    		setAnswer(3);
    	
    	disableImages();
    	String enabledImageStyle = "-fx-effect: dropshadow(three-pass-box, rgb(247,218,49), 30, 0, 0, 0)";
    	image.setStyle(enabledImageStyle);
    }
    
    public void overImage(MouseEvent e){
    	
        if((ImageView)e.getSource() == image1){
        	System.out.println("hover image1");
        	enableImage(image1);
        }
        else if((ImageView)e.getSource() == image2){
        	System.out.println("hover image2");
        	enableImage(image2);
        }
        else if((ImageView)e.getSource() == image3){
        	System.out.println("hover image3");
        	enableImage(image3);
        }
        else{
        	System.out.println("hover null");

        }
    }
    
    public void imageClicked(MouseEvent e){
    	System.out.println("imageClicked");

        if((ImageView)e.getSource() == image1){
        	System.out.println("image1");
        	selectImage(image1);
        }
        else if((ImageView)e.getSource() == image2){
        	System.out.println("image2");
        	selectImage(image2);
        }
        else if((ImageView)e.getSource() == image3){
        	System.out.println("image3");
        	selectImage(image3);
        }
    }
    

}
