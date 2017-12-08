package Artatawe.Data;

import java.util.Date;

/**
 * @author Charlie Daley
 */
public class Painting extends Artwork{

    public Painting(String name, String description, Image photo, int year, int reservedPrice, int allowedBids, Date dateTime, double width, double height){
        super(name, description, photo, year, reservedPrice, allowedBids, dateTime, width, height);


    }

    public String toString(){
        return (getName()+" "+ getDescription() +" "+ getPhoto() +" " + getYear() +" " + getReservedPrice()
                +" " + getAllowedBids() +" " + getDateTime() +" " + getWidth() +" " + getHeight());
    }
}
