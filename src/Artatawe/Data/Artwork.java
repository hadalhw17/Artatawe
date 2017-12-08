package Artatawe.Data;

import java.util.Date;



/**
 * @author Charlie Daley
 */
public class Artwork {
    private String name; // Name of the artwork
    private String description; // Description of the artwork
    private Image photo; // Photo of the artwork
    private int year; // Year the artwork was made
    private int reservedPrice; // Price of artwork before bids are placed
    private int allowedBids; // How many allowed bids for the artwork
    private Date dateTime; // Date and time of the artwork
    private double width; // Width of the artwork
    private double height; // Height of the artwork
    /**
     * @param name
     * @param description
     * @param photo
     * @param year
     * @param reservedPrice
     * @param allowedBids
     * @param dateTime
     * @param width
     * @param height
     */
    public Artwork(String name, String description, Image photo, int year,
                   int reservedPrice, int allowedBids, Date dateTime,
                   double width, double height) {

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

    /**
     * @return name of artwork
     */
    public String getName() {
        return name;
    }

    /**
     * @return description of artwork
     */
    public String getDescription() {
        return description;
    }

    /**
     * @return photo of artwork
     */
    public Image getPhoto() {
        return photo;
    }

    /**
     * @return year of artwork
     */
    public int getYear() {
        return year;
    }

    /**
     * @return reservedPrice of artwork
     */
    public int getReservedPrice() {
        return reservedPrice;
    }

    /**
     * @return allowedBids for the artwork
     */
    public int getAllowedBids() {
        return allowedBids;
    }

    /**
     * @return dateTime of artwork
     */
    public Date getDateTime() {
        return dateTime;
    }

    /**
     * @return width of the artwork
     */
    public double getWidth() {
        return width;
    }

    /**
     * @return height of the artwork
     */
    public double getHeight() {
        return height;
    }
}
