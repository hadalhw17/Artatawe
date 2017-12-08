package Artatawe.Data;

/**
 * @author alexisvenizelos
 *
 */
public class Image {
	
	private String caption; // Each image has a caption e.g: "ImageName"
	private int width; // Width of the image
	private int height; // Height of the image
	
	/**
	 * @param caption Caption of the image in String
	 * @param width Width of the image
	 * @param height Height of the image
	 */
	public Image(String caption, int width, int height) {
		this.width = width;
		this.height = height;
		this.caption = caption;
		
	}

	/**
	 * @return caption of the image
	 */
	public String getCaption() {
		return caption;
	}

	/**
	 * @param caption
	 */
	public void setCaption(String caption) {
		this.caption = caption;
	}

	/**
	 * @return width of the image
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * @param width
	 */
	public void setWidth(int width) {
		this.width = width;
	}

	/**
	 * @return height of the image
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * @param height
	 */
	public void setHeight(int height) {
		this.height = height;
	}
	
	/**
	 * @return imagePath
	 */
	public String getImagePath() {
		return caption;
	}
	
	/**
	 * @param caption
	 */
	public void setImagePath(String caption) {
		this.caption = caption;
	}
	
	
	/* 
	 * A toString(); method to convert the image to String
	 */
	public String toString() {
		return "Height: " + this.getHeight() + " | " +
				"Width: " + this.getWidth() + " | " + "Image: " + this.getImagePath();
	}
}
