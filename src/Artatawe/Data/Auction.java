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

    // seller of the artwork
    private Profile seller;

    // list of bids placed on the auction
    private ArrayList<Bid> bidList;

    // list of comments made by profiles on this auction
    private ArrayList<AuctionComment> commentList;

    // the artwork being sold on the auction
    private Artwork artwork;

    // the number of available bids for the auction
    private int bidMax;

    /**
     * Creates an Auction object
     * @param artwork The artwork being sold
     * @param bidMax Max no. of bids on the auction
     */
    public Auction (Profile seller, Artwork artwork, int bidMax) {

        this.bidList = new ArrayList<>();
        this.commentList = new ArrayList<>();
        this.seller = seller;
        this.artwork = artwork;
        this.bidMax = bidMax;

        this.seller.getAuctions().add(this);
    }

    /**
     * @return The list of bids
     */
    public ArrayList<Bid> getBidList() {

        return bidList;
    }

    /**
     * @return The list of comments
     */
    public ArrayList<AuctionComment> getCommentList() {
        return commentList;
    }

    /**
     * @return Whether the auction is completed or not
     */
    public Boolean isCompleted() {

        return bidList.size() == bidMax;
    }

    /**
     * @return The artwork being auctioned
     */
    public Artwork getArtwork() {

        return artwork;
    }

    /**
     * @return The seller of the artwork in this auction
     */
    public Profile getSeller() {
        return seller;
    }

    /**
     * @return The max number of allowed bids
     */
    public int getBidMax() {

        return bidMax;
    }

    /**
     * Places a new bid on the auction
     * @param buyer Profile of the buyer
     * @param amount Amount of money being bidded
     * @param dateTime Date of the bid
     * @return false if the bid was unsuccessful, true if the
     * bid was successful
     */
    public Boolean placeBid(Profile buyer, int amount, Date dateTime) {

        /* Creates and adds a new bid to the bidList if the auction is
        not completed*/
        Bid newBid = new Bid(buyer, amount, dateTime, this);
        if (this.isCompleted()) {
            return false;
        }
        else {
            bidList.add(newBid);
            buyer.getBids().add(newBid);
            return true;
        }
    }

    /**
     * Make a comment on this auction
     * @param profile Commenter's profile
     * @param comment AuctionComment text
     */
    public void makeComment(Profile profile, String comment)
    {
        commentList.add(new AuctionComment(profile, comment));
    }

    /**
     * @return The latest bid
     */
    public Bid getLastBid() {

        return bidList.get(bidList.size() -1);
    }

    public String toString(){
        return getArtwork().toString();
    }
}