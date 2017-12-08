package Artatawe.Data;

import java.util.Date;
import javafx.scene.image.Image;

/**
 * @author Charlie Daley
 */
public class Painting extends Artwork{

    public Painting(String name, String description, Image photo, int year, int reservedPrice, int allowedBids, Date dateTime, double width, double height){
        super(name, description, photo, year, reservedPrice, allowedBids, dateTime, width, height);


    }

    @Override
    public String toString(){
        return ("Name: "+getName()+ "\nYear: " + getYear() +"\nReserved price: " + getReservedPrice()
                +"\nAllowed bids " + getAllowedBids()+"\nWidth: " + getWidth() +"\nHeight: " + getHeight());
    }
}
