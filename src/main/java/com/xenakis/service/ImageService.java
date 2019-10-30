package com.xenakis.service;

import java.net.URL;
import java.util.HashMap;
import com.xenakis.ImagInLexis;
import com.xenakis.databaseService.ImageUtil;
import javafx.scene.image.Image;
import org.apache.log4j.Logger;

public class ImageService {

    private static final Logger logger = Logger.getLogger(ImageService.class);

    private static final HashMap<String, Image> images = new HashMap<>();

    public static Image getImage(String name){

        Image image = ImageService.images.get(name);

        if(image == null) {
            String path;
            try {
                path = ImageUtil.getImagePath(name);
                URL resource = ImagInLexis.class.getResource(path);
                image = new Image(resource.toString());
                ImageService.images.put(name, image);
                ImageService.logger.info("Add image with name = " + name);
            }
            catch (Exception e) {
                ImageService.logger.error(e);
            }
        }
        return image;
    }
}
