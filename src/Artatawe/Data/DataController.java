package Artatawe.Data;

/**
 * DataController.java
 * @author Jamie Hammond
 * Written for CS-230 A3 - Artatawe
 */

import java.util.ArrayList;

/**
 * Stores and manipulates the list of auctions and profiles. Can get profiles by
 * username, filter auctions, and create profiles and auctions.
 */
public class DataController {

    // List of auctions
    private ArrayList<Auction> auctionList;

    // List of user profiles
    private ArrayList<Profile> profileList;

    /**
     * Creates a new auction and adds it to the list of auctions
     * @param artwork The artwork being sold
     * @param bidCount The max no. of bids on the auction
     * @return The new auction
     */
	public Auction createAuction(Artwork artwork, int bidCount) {

	    Auction newAuction = new Auction(artwork, bidCount);
	    auctionList.add(newAuction);
	    return newAuction;
    }

    /**
     * Creates a new user profile and adds it to the list of profiles
     * @param uName The username
     * @param fName The user's first name
     * @param sName The user's surname
     * @param mobileNo The user's mobile phone number
     * @param address The address of the user
     * @param profImg The user's profile image
     * @return The new profile
     */
    public Profile createProfile(String uName, String fName, String sName, String mobileNo, Address address, Image profImg) {

	    Profile newProfile = new Profile(uName,fName, sName, mobileNo, address, profImg);
	    profileList.add(newProfile);
	    return newProfile;
    }

    /**
     * Filters auctions by type of artwork
     * @param key The filter key
     * @return The filtered list of auctions
     */
    public ArrayList<Auction> filterAuctions(int key) {

        ArrayList<Auction> filtered = new ArrayList<>();

        // Filters by paintings
        if (key == 0) {
            for (Auction elem : auctionList) {
                if (elem.getArtwork() instanceof Painting) {
                    filtered.add(elem);
                }
            }
        }
        // Filters by sculptures
        else if (key == 1) {
            for (Auction elem : auctionList) {
                if (elem.getArtwork() instanceof Sculpture) {
                    filtered.add(elem);
                }
            }
        }
        // No filter
        else {
            return auctionList;
        }
        return filtered;
    }

    /**
     * Searches for a profile by username
     * @param username The username to search for
     * @return The profile of the user if it exists
     */
    public Profile searchByUsername(String username) {
        for (Profile elem : profileList) {
            if (elem.getUsername().equals(username)) {
                return elem;
            }
        }
        return null;
    }
}