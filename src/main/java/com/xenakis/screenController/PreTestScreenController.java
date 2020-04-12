/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xenakis.screenController;

import com.xenakis.service.TestService;
import com.xenakis.service.ImageService;
import com.xenakis.service.SoundService;
import com.xenakis.view.TestView;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
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

	private TestService testService = null;

	@Override
	public void initialize(java.net.URL location, java.util.ResourceBundle resources) {
	}

    public void init(TestService testService){
    	this.testService = testService;
    	
    	chapterName.setText(testService.getChapterName());
    	chapterImage.setImage(ImageService.getImage(testService.getChapter()+"Image"));
    	categoryName.setText(testService.getCategoryName());
    	categoryImage.setImage(ImageService.getImage(testService.getCategory()+"Image"));
    	totalQuestions.setText(String.valueOf(testService.getTotalQuestions()));

    	backgroundImage.setImage(ImageService.getImage("background"));

		SoundService.stopSound("endProgramSound");
		SoundService.playSound(testService.getCategory()+"Sound");

		mainWindow.setStyle(TestView.getMainWindowStyle(testService.getChapterName()));
		infoPane.setStyle(TestView.getInfoPaneStyle(testService.getChapterName()) + " -fx-opacity: 0.9;");

		infoPane.setMaxWidth(500);
    }
    
    public void startTest(){
    	this.testService.startTest();
    }
}
