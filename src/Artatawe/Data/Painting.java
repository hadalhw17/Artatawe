package Artatawe.Data;

import java.util.Date;
import javafx.scene.image.Image;

public class Painting extends Artwork{

    public Painting(String name, String description, Image photo, int year, int reservedPrice, int allowedBids, Date dateTime, int width, int height){
        super(name, description, photo, year, reservedPrice, allowedBids, dateTime, width, height);


    }

    public String toString(){
        return (getName()+" "+ getDescription() +" "+ getPhoto() +" " + getYear() +" " + getReservedPrice()
                +" " + getAllowedBids() +" " + getDateTime() +" " + getWidth() +" " + getHeight());
    }
}
