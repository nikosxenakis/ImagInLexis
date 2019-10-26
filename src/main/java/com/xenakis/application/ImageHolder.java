package com.xenakis.application;

import java.net.URL;
import java.util.HashMap;
import com.xenakis.ImagInLexis;
import com.xenakis.service.DatabaseUtil;
import javafx.scene.image.Image;
import org.apache.log4j.Logger;

public class ImageHolder {

	private static final HashMap<String, Image> images = new HashMap<>();
	private static Logger logger = Logger.getLogger(ImageHolder.class);

	public static Image getImage(String name){

		Image image = ImageHolder.images.get(name);

		if(image == null) {
			String path;
			try {
				path = DatabaseUtil.getImagePath(name);
				URL resource = ImagInLexis.class.getResource(path);
				image = new Image(resource.toString());
				ImageHolder.images.put(name, image);
				ImageHolder.logger.info("Add image with name = " + name);
			}
			catch (Exception e) {
				ImageHolder.logger.error(e);
			}
		}
		return image;
	}
}
