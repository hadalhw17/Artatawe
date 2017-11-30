package Artatawe.Data;

/**
 * Auction.java
 * @author Jamie Hammond
 * Written for CS230 A3 - Artatawe
 */

import java.util.ArrayList;
import java.util.Date;

/**
 * Tracks and shows the status of a current auction. Deals with placing
 * bids and detecting when an auction has finished.
 */
public class Auction {

    // list of bids placed on the auction
    private ArrayList<Bid> bidList;

    // the artwork being sold on the auction
    private Artwork artwork;

    // the number of available bids remaining for the auction
    private int bidCount;

    /**
     * Creates an Auction object
     * @param artwork The artwork being sold
     * @param bidCount Max no. of bids on the auction
     */
    public Auction (Artwork artwork, int bidCount) {

        this.bidList = new ArrayList<>();
        this.artwork = artwork;
        this.bidCount = bidCount;
    }

    /**
     * @return The list of bids
     */
    public ArrayList<Bid> getBidList() {

        return bidList;
    }

    /**
     * @return Whether the auction is completed or not
     */
    public Boolean isCompleted() {

        if (bidCount <= 0) {
            return true;
        }
        return false;
    }

    /**
     * @return The artwork being auctioned
     */
    public Artwork getArtwork() {

        return artwork;
    }

    /**
     * @return The number of bids remaining
     */
    public int getBidCount() {

        return bidCount;
    }

    /**
     * Places a new bid on the auction
     * @param buyer Profile of the buyer
     * @param amount Amount of money being bidded
     * @param dateTime Time of the bid
     * @param auction Auction being bidded on
     * @return false if the bid was unsuccessful, true if the bid was successful
     */
    public Boolean placeBid(Profile buyer, int amount, Date dateTime, Auction auction) {

        // Creates and adds a new bid to the bidList if the auction is not completed
        Bid newBid = new Bid(buyer, amount, dateTime, this);
        if (this.isCompleted()) {
            return false;
        }
        else {
            bidCount--;     // Decrements no. of bids remaining
            bidList.add(newBid);
            return true;
        }
    }

    /**
     * @return The latest bid
     */
    public Bid getLastBid() {

        return bidList.get(bidList.size() -1);
    }

}