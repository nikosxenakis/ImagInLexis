/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package screenController;

import application.ImageHolder;
import application.SoundHolder;
import application.Test;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class PreTestScreenController extends ScreenController{

	@FXML
	private AnchorPane mainWindow;
	
	@FXML
	private VBox infoPane;
	
	@FXML
	private Text chapterName;

	@FXML
	private ImageView chapterImage;
	
	@FXML
	private Text categoryName;

	@FXML
	private ImageView categoryImage;
	
	@FXML
	private Text totalQuestions;
    
	@FXML
	private ImageView backgroundImage;
	
	Test test = null;
	
    public void init(Test test){
    	this.test = test;
    	
    	chapterName.setText(test.getChapterName());
    	chapterImage.setImage(ImageHolder.getImage(test.getChapter()+"Image"));
    	categoryName.setText(test.getCategoryName());
    	categoryImage.setImage(ImageHolder.getImage(test.getCategory()+"Image"));
    	totalQuestions.setText(test.getTotalQuestions().toString());

    	backgroundImage.setImage(ImageHolder.getImage("background"));
    	
    	SoundHolder.stopSound("startProgramSound");
    	SoundHolder.playSound(test.getCategory()+"Sound");
    	
    	mainWindow.setStyle(test.getMainWindowStyle());	
    	infoPane.setStyle(test.getInfoPaneStyle()+" -fx-opacity: 0.9;");
    	
    	infoPane.setMaxWidth(500);
    }
    
    public void startTest(MouseEvent e){
    	this.test.startTest();
    }
}
