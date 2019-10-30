/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xenakis.screenController;

import com.xenakis.service.TestService;
import com.xenakis.service.ImageService;
import com.xenakis.service.SoundService;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Line;
import com.xenakis.screenData.ChooseImageLinkScreenData;
import com.xenakis.screenData.QuestionScreenData;

public class ChooseImageLinkScreenController extends QuestionScreenController{

	@FXML
    private ImageView basicImage;
	
	@FXML
    private ImageView image1;

    @FXML
    private ImageView image2;
  
    @FXML
    private ImageView image3;
    
    @FXML
    private Line link1;
    
    @FXML
    private Line link2;
    
    @FXML
    private Line link3;
    
    @FXML
    private ImageView soundImage;
    
    private ImageView selectedImage = null;
    
    private String soundId = null;
    
    public void setData(QuestionScreenData screenData, TestService testService){
    	
    	System.out.println("set Data in ChooseImageLinkScreenController");
    	super.setData(screenData, testService);
    	
    	if(!(screenData instanceof ChooseImageLinkScreenData)){
        	System.err.println("com.xenakis.screenData is not ChooseImageLinkScreenData");
    		return;
    	}
    	
    	ChooseImageLinkScreenData data = (ChooseImageLinkScreenData) screenData;
      	
    	Image image = ImageService.getImage(data.getBasicImageId());
    	basicImage.setImage(image);
    	
    	image = ImageService.getImage(data.getImage1Id());
    	image1.setImage(image);

    	image = ImageService.getImage(data.getImage2Id());
    	image2.setImage(image);
    	
    	image = ImageService.getImage(data.getImage3Id());
    	image3.setImage(image);
    	
    	if(link1 == null || link2 == null || link3 == null){
    		System.err.println("no link in ChooseImageLinkScreenController");
    	}
    	
    	soundId = data.getSoundId();
  
    	soundImage.setImage(ImageService.getImage(soundImage.getId()));
    }
    
    public void disableImages(){
    	String disabledImageStyle = "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.8), 10, 0, 0, 0)";
    	if(image1 != selectedImage ){
    		image1.setStyle(disabledImageStyle);
    		link1.setStyle(disabledImageStyle);
    	}
    	if(image2 != selectedImage ){
    		image2.setStyle(disabledImageStyle);
    		link2.setStyle(disabledImageStyle);
    	}
    	if(image3 != selectedImage ){
    		image3.setStyle(disabledImageStyle);
    		link3.setStyle(disabledImageStyle);
    	}
    }
   
    private void enableImage(ImageView image){
    	disableImages();
    	if(image == selectedImage )
    		return;
    	
    	String enabledImageStyle = "-fx-effect: dropshadow(three-pass-box, rgba(255,100,100,0.8), 10, 0, 0, 0)";
    	
    	if(image == image1){
        	image1.setStyle(enabledImageStyle);
        	link1.setStyle(enabledImageStyle);
    	}
    	if(image == image2){
        	image2.setStyle(enabledImageStyle);
        	link2.setStyle(enabledImageStyle);
    	}    	
    	if(image == image3){
        	image3.setStyle(enabledImageStyle);
        	link3.setStyle(enabledImageStyle);
    	}  	
    }

	private void selectImage(ImageView image){
    	if(image == selectedImage)
    		return;
    	
    	setIsSelection();
    	submitButton.setDisable(false);
    	selectedImage = image;
    	if(image == image1)
    		setAnswer(1);
    	else if(image == image2)
    		setAnswer(2);
    	else
    		setAnswer(3);
    	
    	disableImages();
    	String enabledImageStyle = "-fx-effect: dropshadow(three-pass-box, rgb(247,218,49), 30, 0, 0, 0)";
    	if(image == image1){
        	image1.setStyle(enabledImageStyle);
        	link1.setStyle(enabledImageStyle);
    	}
    	if(image == image2){
        	image2.setStyle(enabledImageStyle);
        	link2.setStyle(enabledImageStyle);
    	}    	
    	if(image == image3){
        	image3.setStyle(enabledImageStyle);
        	link3.setStyle(enabledImageStyle);
    	}  
    }
    
    public void overImage(MouseEvent e){
    	
        if(e.getSource() == image1){
        	System.out.println("hover image1");
        	enableImage(image1);
        }
        else if(e.getSource() == image2){
        	System.out.println("hover image2");
        	enableImage(image2);
        }
        else if(e.getSource() == image3){
        	System.out.println("hover image3");
        	enableImage(image3);
        }
        else{
        	System.out.println("hover null");

        }
    }
    
    public void imageClicked(MouseEvent e){
    	System.out.println("imageClicked");

        if(e.getSource() == image1){
        	System.out.println("image1");
        	selectImage(image1);
        }
        else if(e.getSource() == image2){
        	System.out.println("image2");
        	selectImage(image2);
        }
        else if(e.getSource() == image3){
        	System.out.println("image3");
        	selectImage(image3);
        }
    }
    
    public void soundIconClicked(){
		SoundService.playSound(soundId);
    }
}
