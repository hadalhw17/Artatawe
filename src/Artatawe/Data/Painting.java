package Artatawe.Data;

import java.util.Date;

/**
 * @author Charlie Daley
 */
public class Painting extends Artwork{

    public Painting(String name, String description, Picture photo, int year, int reservedPrice, Date dateTime, double width, double height) {
        super(name, description, photo, year, reservedPrice, dateTime, width, height);
    }

    @Override
    public String toString(){
        return ("Name: "+getName()+ "\nYear: " + getYear() +"\nReserved price: " + getReservedPrice()
                +"\nWidth: " + getWidth() +"\nHeight: " + getHeight());
    }
}
