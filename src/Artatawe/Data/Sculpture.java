package Artatawe.Data;

import java.util.Date;

public class Sculpture extends Artwork {

    private int depth;



    public Sculpture(String name, String description, Image photo, int year, int reservedPrice, int allowedBids, Date dateTime, int width, int height, int depth){
        super(name, description, photo, year, reservedPrice, allowedBids, dateTime, width, height);

        this.depth = depth;

    }

    public int getDepth() {
        return depth;
    }

    /*public String toString(){
        return (getName()+" "+ getDescription() +" "+ getPhoto() +" " + getYear() +" " + getReservedPrice()
                +" " + getAllowedBids() +" " + getDateTime() +" " + getWidth() +" " + getHeight() +" " + getDepth());
    }*/

}
