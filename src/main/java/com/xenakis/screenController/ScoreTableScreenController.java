package com.xenakis.screenController;

import com.xenakis.model.Category;
import com.xenakis.model.Chapter;
import com.xenakis.databaseService.CategoryUtil;
import com.xenakis.databaseService.ChapterUtil;
import com.xenakis.databaseService.ScoreUtil;
import com.xenakis.model.Score;
import com.xenakis.ImagInLexis;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import org.apache.log4j.Logger;

import java.util.List;

public class ScoreTableScreenController extends ScreenController{

	@FXML
    private TableView<Score> scoreTable;
    
    @FXML
    private ComboBox<String> chapterOptions;
 
    @FXML
    private ComboBox<String> categoryOptions;

    @FXML
    private ImageView homeImage;

    private static final Logger logger = Logger.getLogger(ScoreTableScreenController.class);

    public void homeIconClicked(){
        ImagInLexis.mainContainer.setScreen("MainScreen");
    }

    public void homeIconEntered(){
        String enabledImageStyle = "-fx-effect: dropshadow(three-pass-box, rgba(255,100,100,0.8), 10, 0, 0, 0)";
        homeImage.setStyle(enabledImageStyle);
    }

    public void homeIconExited(){
        homeImage.setStyle(null);
    }

    public void removeAll() {
        ScoreUtil.removeAllScores();
        this.refreshScores();
    }

    public void init(){

        TableColumn<Score, String> nameCol = (TableColumn<Score, String>) scoreTable.getVisibleLeafColumn(0);
        TableColumn<Score, String> scoreCol = (TableColumn<Score, String>) scoreTable.getVisibleLeafColumn(1);
        TableColumn<Score, String> dateCol = (TableColumn<Score, String>) scoreTable.getVisibleLeafColumn(2);
        TableColumn<Score, String> timeCol = (TableColumn<Score, String>) scoreTable.getVisibleLeafColumn(3);

        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        scoreCol.setCellValueFactory(new PropertyValueFactory<>("score"));
        dateCol.setCellValueFactory(new PropertyValueFactory<>("date"));
        timeCol.setCellValueFactory(new PropertyValueFactory<>("time"));

        chapterOptions.getItems().clear();
        chapterOptions.getItems().add("Όλα");

        List<Chapter> chapterList = ChapterUtil.getChapterList();

        for(Chapter chapter : chapterList) {
            chapterOptions.getItems().add(chapter.getGreekName());
        }

        chapterOptions.getSelectionModel().select(0);

        chapterOptions.valueProperty().addListener(new ChangeListener<String>() {
            @Override public void changed(ObservableValue ov, String t, String t1) {
                logger.info("Selected chapter : " + t1);
                refreshCategoriesList();
//                refreshScores();
            }
        });

//        categoryOptions.valueProperty().addListener(new ChangeListener<String>() {
//            @Override public void changed(ObservableValue ov, String t, String t1) {
//                logger.info("Selected category : " + t1);
//                refreshScores();
//            }
//        });

        refreshCategoriesList();
        refreshScores();
    }

    private void refreshCategoriesList(){
        String chapterGreekName = chapterOptions.getSelectionModel().getSelectedItem();
        List<Category> categoryList;

        if(chapterGreekName.equals("Όλα")) {
            categoryList = CategoryUtil.getCategoryList();
        }
        else {
            Chapter chapter = ChapterUtil.getChapterFromGreekName(chapterGreekName);
            categoryList = CategoryUtil.getCategoryList(chapter.getId());
        }

        categoryOptions.getItems().clear();
        categoryOptions.getItems().add("Όλα");
        for(Category category: categoryList){
            categoryOptions.getItems().add(category.getGreekName());
        }
        categoryOptions.getSelectionModel().select(0);

        logger.info("refreshCategoriesList for chapterGreekName: " + chapterGreekName + "the categories are: ");
        for(Category category: categoryList){
            logger.info(category.getGreekName());
        }

    }

    public void refreshScores() {
        Chapter chapter;
        Category category;
        int chapterId;
        int categoryId;
        String chapterGreekName = chapterOptions.getSelectionModel().getSelectedItem();
        String categoryGreekName = categoryOptions.getSelectionModel().getSelectedItem();

        if(chapterGreekName.equals("Όλα")) {
            chapterId = 0;
        }
        else {
            chapter = ChapterUtil.getChapterFromGreekName(chapterGreekName);
            chapterId = chapter.getId();
        }

        if(categoryGreekName.equals("Όλα")) {
            categoryId = 0;
        }
        else {
            category = CategoryUtil.getCategoryFromGreekName(categoryGreekName);
            categoryId = category.getId();
        }

        System.out.println();
    	List<Score> scoreList = ScoreUtil.getScoreList(chapterId, categoryId);

        scoreTable.getItems().clear();
        for(Score score : scoreList){
            scoreTable.getItems().add(score);
        }

        logger.info("Renew Scores: chapterId = " + chapterId + ", categoryId = " + categoryId);
    }
}
