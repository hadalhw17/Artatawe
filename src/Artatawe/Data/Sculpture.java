package Artatawe.Data;

import java.util.Date;

/**
 * @author Charlie Daley
 *
 * Sculpture.java
 *
 * Sculpture class is inheriting from Artwork class.
 */
public class Sculpture extends Artwork {

    private double depth; // Variable used so that the sculptures will have a depth

    /**
     * Constructor for Sculpture.java
     * @param name
     * @param description
     * @param photo
     * @param year
     * @param reservedPrice
     * @param dateTime
     * @param width
     * @param height
     * @param depth
     */
    public Sculpture(String name, String description, Picture photo, int year, int reservedPrice,
                     Date dateTime, double width, double height, double depth){
        super(name, description, photo, year, reservedPrice, dateTime, width, height);

        this.depth = depth;

    }

    /**
     *
     * @return the depth for the sculptures
     */
    public double getDepth() {
        return depth;
    }

    /**
     * toString method is used to return the methods of the sculpture class.
     */
    @Override
    public String toString(){
        return ("Name: "+getName()+ "\nYear: " + getYear() +"\nReserved price: " + getReservedPrice()
                + "\nWidth: " + getWidth() +"\nHeight: " + getHeight() + "\nDepth:" + getDepth());
    }

}
