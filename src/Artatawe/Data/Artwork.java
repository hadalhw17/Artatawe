package Artatawe.Data;

import java.util.Date;
import javafx.scene.image.Image;

/**
 * @author Charlie Daley
 */
public class Artwork {

    private String name;
    private String description;
    private Image photo;
    private int year;
    private int reservedPrice;
    private int allowedBids;
    private Date dateTime;
    private int width;
    private int height;

    public Artwork(String name, String description, Image photo, int year, int reservedPrice, int allowedBids, Date dateTime, int width, int height) {

        this.name = name;
        this.description = description;
        this.photo = photo;
        this.year = year;
        this.reservedPrice = reservedPrice;
        this.allowedBids = allowedBids;
        this.dateTime = dateTime;
        this.width = width;
        this.height = height;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Image getPhoto() {
        return photo;
    }

    public int getYear() {
        return year;
    }

    public int getReservedPrice() {
        return reservedPrice;
    }

    public int getAllowedBids() {
        return allowedBids;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
