package Artatawe.Data;

import java.util.Date;

/**
 * @author Charlie Daley
 *
 * Painting.java
 *
 * Painting class is inheriting from Artwork class.
 */
public class Painting extends Artwork{

    /**
     *Constructor for Painting.java
     *
     * @param name
     * @param description
     * @param photo
     * @param year
     * @param reservedPrice
     * @param dateTime
     * @param width
     * @param height
     */
    public Painting(String name, String description, Picture photo, int year,
                    int reservedPrice, Date dateTime, double width, double height) {
        super(name, description, photo, year, reservedPrice, dateTime, width, height);
    }

    /**
     * toString method is used to return the methods of the painting class
     */
    @Override
    public String toString(){
        return ("Name: "+getName()+ "\nYear: " + getYear() +"\nReserved price: " + getReservedPrice()
                +"\nWidth: " + getWidth() +"\nHeight: " + getHeight());
    }
}
