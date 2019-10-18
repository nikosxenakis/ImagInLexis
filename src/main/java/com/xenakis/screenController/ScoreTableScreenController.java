/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xenakis.screenController;

import com.xenakis.application.ImagInLexisParser;
import com.xenakis.application.ScoreUtil;
import com.xenakis.model.Score;
import com.xenakis.service.Database;
import com.xenakis.ImagInLexis;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.util.List;

public class ScoreTableScreenController extends ScreenController{

	@FXML
    private TableView<Score> scoreTable;

    @FXML
    private ScrollBar scrollBar;
    
    @FXML
    private ComboBox<String> chapterOptions;
 
    @FXML
    private ComboBox<String> categoryOptions;
    
    @FXML
    private ImageView backgroundImage;
    
    @FXML
    private ImageView homeImage;
    
	@FXML 
	private AnchorPane mainWindow;

	@FXML
    private Button removeAllButton;

    public void homeIconClicked(MouseEvent e){
        ImagInLexis.mainContainer.setScreen("MainScreen");
    }
    
    public void init(){

        chapterOptions.getItems().clear();

    	if(chapterOptions.getItems().isEmpty()){
        	List<String> chapterList = ImagInLexisParser.getChapterList();

            chapterOptions.getItems().addAll("Όλα");
            for(String chapter: chapterList){
            	chapterOptions.getItems().addAll(chapter);
            }    		
    	}
        
    	chapterOptions.getSelectionModel().select(0);

    	categoryOptions.getItems().addAll("Όλα");

    	renewComboBoxData("Όλα");

//        final TableColumn<Score, ?> nameCol = scoreTable.getVisibleLeafColumn(0);
//        final TableColumn<Score, ?> scoreCol = scoreTable.getVisibleLeafColumn(1);
//        final TableColumn<Score, ?> dateCol = scoreTable.getVisibleLeafColumn(2);
//        final TableColumn<Score, ?> timeCol = scoreTable.getVisibleLeafColumn(3);

        TableColumn<Score, String> nameCol = (TableColumn<Score, String>) scoreTable.getVisibleLeafColumn(0);
		TableColumn<Score, String> scoreCol = (TableColumn<Score, String>) scoreTable.getVisibleLeafColumn(1);
        TableColumn<Score, String> dateCol = (TableColumn<Score, String>) scoreTable.getVisibleLeafColumn(2);
        TableColumn<Score, String> timeCol = (TableColumn<Score, String>) scoreTable.getVisibleLeafColumn(3);

        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        scoreCol.setCellValueFactory(new PropertyValueFactory<>("score"));
        dateCol.setCellValueFactory(new PropertyValueFactory<>("date"));
        timeCol.setCellValueFactory(new PropertyValueFactory<>("time"));

        renewScoreInfo();

        chapterOptions.valueProperty().addListener((ov, t, t1) -> {
            if(!t.equals(t1)){
                renewComboBoxData(t1);
                renewScoreInfo();
            }
        });
       
        categoryOptions.valueProperty().addListener((ov, t, t1) -> {
            if(!t.equals(t1)){
                renewScoreInfo();
            }
        });
        /*
        scoreTable.setRowFactory(new Callback<TableView<Score>, TableRow<Score>>() {
            @Override
            public TableRow<Score> call(TableView<Score> paramP) {
                return new TableRow<Score>() {
                    @Override
                    protected void updateItem(Score paramT, boolean paramBoolean) {
                    	
                    	if(paramT == null)
                    		return;
                    	
                    	String score = paramT.getScore();
                    	String scoreNumber = score.substring(0, score.length()-1);
                    	Integer number = new Integer(scoreNumber);
                    	                    	
                    	String style = null;
                    	
                    	if(number >= 90)
                    		style = "-fx-background-color: lightgreen";
                    	else
                    		style = "-fx-background-color: lightcoral";

                        setStyle(style);
                        super.updateItem(paramT, paramBoolean);
                    }
                };
            }
        });
        */
    }

    public void homeIconEntered(){
    	String enabledImageStyle = "-fx-effect: dropshadow(three-pass-box, rgba(255,100,100,0.8), 10, 0, 0, 0)";
    	homeImage.setStyle(enabledImageStyle);	
    }
    
    public void homeIconExited(){
    	homeImage.setStyle(null);
    }

    private void renewComboBoxData(String chapterName){
    	
        List<String> categoryList = ImagInLexisParser.getCategoryList(chapterName);

    	categoryOptions.getSelectionModel().select(0);

        scoreTable.getItems().clear();

        for(String category: categoryList){
    		categoryOptions.getItems().addAll(category);
        }
    }
    
    private void renewScoreInfo(){
    	String chapter = chapterOptions.getValue();
    	String category = categoryOptions.getValue();
        List<Score> scoreList = ScoreUtil.getScoreList(chapter,category);

        scoreTable.getItems().clear();

        for(Score score : scoreList){
            scoreTable.getItems().add(score);
        }
        
        
    }

    public void removeAll() {
        Database.removeAll();
        this.renewScoreInfo();
    }

}
