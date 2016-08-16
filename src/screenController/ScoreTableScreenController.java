/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package screenController;

import java.util.List;
import application.ImagInLexis;
import application.ImageHolder;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;

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
		
    public void homeIconClicked(MouseEvent e){
        ImagInLexis.mainContainer.setScreen("MainScreen");
    }
    
    @SuppressWarnings("unchecked")
    public void init(){

    	if(chapterOptions.getItems().isEmpty()){
        	List<String> chapterList = ImagInLexis.parser.getChapterList();

            chapterOptions.getItems().addAll("Όλα");
            for(String chapter: chapterList){
            	chapterOptions.getItems().addAll(chapter);
            }    		
    	}
        
    	chapterOptions.getSelectionModel().select(0);

    	categoryOptions.getItems().addAll("Όλα");

    	renewComboBoxData("Όλα");
    	    	
		TableColumn<Score, String> nameCol = (TableColumn<Score, String>) scoreTable.getVisibleLeafColumn(0);
		TableColumn<Score, String> scoreCol = (TableColumn<Score, String>) scoreTable.getVisibleLeafColumn(1);
        TableColumn<Score, String> dateCol = (TableColumn<Score, String>) scoreTable.getVisibleLeafColumn(2);
        TableColumn<Score, String> timeCol = (TableColumn<Score, String>) scoreTable.getVisibleLeafColumn(3);

        nameCol.setCellValueFactory(new PropertyValueFactory<Score,String>("name"));
        scoreCol.setCellValueFactory(new PropertyValueFactory<Score,String>("score"));
        dateCol.setCellValueFactory(new PropertyValueFactory<Score,String>("date"));
        timeCol.setCellValueFactory(new PropertyValueFactory<Score,String>("time"));

        renewScoreInfo();
        
        chapterOptions.valueProperty().addListener(new ChangeListener<String>() {
            @Override public void changed(@SuppressWarnings("rawtypes") ObservableValue ov, String t, String t1) {
            	if(!t.equals(t1)){
                	renewComboBoxData(t1);                	
                    renewScoreInfo();       		
            	}
            }    
        });
       
        categoryOptions.valueProperty().addListener(new ChangeListener<String>() {
            @Override public void changed(@SuppressWarnings("rawtypes") ObservableValue ov, String t, String t1) {
            	if(!t.equals(t1)){
                    renewScoreInfo();       		
            	}            
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
    
    public void initialize(java.net.URL location, java.util.ResourceBundle resources){  
    	
    	homeImage.setImage(ImageHolder.getImage("logo"));
    	backgroundImage.setImage(ImageHolder.getImage("background"));
    	
        chapterOptions.getItems().clear();
        
    	init();
    }

    public void homeIconEntered(){
    	String enabledImageStyle = "-fx-effect: dropshadow(three-pass-box, rgba(255,100,100,0.8), 10, 0, 0, 0)";
    	homeImage.setStyle(enabledImageStyle);	
    }
    
    public void homeIconExited(){
    	homeImage.setStyle(null);
    }
    
    public void renewComboBoxData(String chapterName){
    	
        List<String> categoryList = ImagInLexis.parser.getCategoryList(chapterName);

    	categoryOptions.getSelectionModel().select(0);

    	for(int i=categoryOptions.getItems().size()-1; i>0; i--)
    		categoryOptions.getItems().remove(i);
        
        for(String category: categoryList){
    		categoryOptions.getItems().addAll(category);
        }
    }
    
    public void renewScoreInfo(){
    	String chapter = chapterOptions.getValue();
    	String category = categoryOptions.getValue();
        List<Score> scoreList = ImagInLexis.parser.getScoreList(chapter,category);

        scoreTable.getItems().clear();
        
        for(Score score : scoreList){
            scoreTable.getItems().add(score);
        }
        
        
    }
    
    public static class Score {
    	private final SimpleStringProperty name;
    	private final SimpleStringProperty score;
    	private final SimpleStringProperty date;
    	private final SimpleStringProperty time;
    	
    	public Score(String name, String score, String date, String time){
    		this.name = new SimpleStringProperty(name);
    		this.score = new SimpleStringProperty(score);
    		this.date = new SimpleStringProperty(date);
    		this.time = new SimpleStringProperty(time);
    	}

    	public String getName() {
            return name.get();
        }
    	
    	public String getScore() {
            return score.get();
        }
    	
    	public String getDate() {
            return date.get();
        }
    	
    	public String getTime() {
            return time.get();
        }
    }
    
}
