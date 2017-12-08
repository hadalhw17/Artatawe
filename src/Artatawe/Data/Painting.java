package Artatawe.Data;

import java.util.Date;

/**
 * Painting class for Artatawe
 *
 * @author Charlie Daley
 *
 * creates a painting for the system
 *
 * inherits from the Artwork class
 */
public class Painting extends Artwork{

    /**
     * constructs a painting for the system with the following attributes
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
    public Painting(String name, String description, Image photo, int year,
                    int reservedPrice, int allowedBids, Date dateTime,
                    double width, double height){
        // super is used to get them from Artwork
        super(name, description, photo, year, reservedPrice, allowedBids,
                dateTime, width, height);


    }

    /**
     *
     * @return the following method
     */
    public String toString(){
        return (getName()+" "+ getDescription() +" "+ getPhoto()
                +" " + getYear() +" " + getReservedPrice()
                +" " + getAllowedBids() +" " + getDateTime() +" "
                + getWidth() +" " + getHeight());
    }
}
