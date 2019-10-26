/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xenakis.screenController;

import com.xenakis.service.ImageUtil;
import com.xenakis.service.SoundUtil;
import com.xenakis.application.TestUtil;
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

	private TestUtil testUtil = null;

	@Override
	public void initialize(java.net.URL location, java.util.ResourceBundle resources) {
	}

    public void init(TestUtil testUtil){
    	this.testUtil = testUtil;
    	
    	chapterName.setText(testUtil.getChapterName());
    	chapterImage.setImage(ImageUtil.getImage(testUtil.getChapter()+"Image"));
    	categoryName.setText(testUtil.getCategoryName());
    	categoryImage.setImage(ImageUtil.getImage(testUtil.getCategory()+"Image"));
    	totalQuestions.setText(String.valueOf(testUtil.getTotalQuestions()));

    	backgroundImage.setImage(ImageUtil.getImage("background"));
    	
    	SoundUtil.stopSound("startProgramSound");
    	SoundUtil.playSound(testUtil.getCategory()+"Sound");
    	
    	mainWindow.setStyle(testUtil.getMainWindowStyle());
    	infoPane.setStyle(testUtil.getInfoPaneStyle()+" -fx-opacity: 0.9;");
    	
    	infoPane.setMaxWidth(500);
    }
    
    public void startTest(){
    	this.testUtil.startTest();
    }
}
