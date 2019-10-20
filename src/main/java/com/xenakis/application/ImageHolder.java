package com.xenakis.application;

import java.net.URL;
import java.util.HashMap;
import com.xenakis.ImagInLexis;
import com.xenakis.service.DatabaseUtil;
import javafx.scene.image.Image;

public class ImageHolder {

	private static final HashMap<String, Image> images = new HashMap<>();

	public static Image getImage(String id){

		Image image = ImageHolder.images.get(id);
		if(image == null) {
			image = ImageHolder.addImage(id);
		}
		return image;
	}

	private static Image addImage(String id) {
		String path = DatabaseUtil.getImagePath(id);
		assert(path != null);
		System.out.println(path);
		URL resource = ImagInLexis.class.getResource(path);
		assert(resource != null);
		Image image = new Image(resource.toString());
		assert(image != null);
		ImageHolder.images.put(id, image);
		return image;
	}
}