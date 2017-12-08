package Artatawe.Data;

import javafx.scene.image.Image;

/**
 * @author alexisvenizelos
 *
 */
public class Picture extends Image {
	
	private String path;
	
	/**
	 * @param path Path to image
	 */
	public Picture(String path) {
        super(path);
        this.path = path;
	}

	/**
	 * @return caption of the image
	 */
	public String getPath() {
		return path;
	}
	
	/* 
	 * A toString(); method to convert the image to String
	 */
	public String toString() {
		return this.getPath();
	}
}
