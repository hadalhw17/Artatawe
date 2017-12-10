package Artatawe.Data;

/**
 * DataController.java
 * @author Jamie Hammond
 * Written for CS-230 A3 - Artatawe
 */

import Artatawe.IO.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

/**
 * Stores and manipulates the list of auctions and profiles. Can get profiles by
 * username, filter auctions, and create profiles and auctions.
 */
public class DataController {

    // Path to JSON document containing program data
    private final String ROOT_JSON_FILE = "data/artatawe.json";

    // List of auctions
    private ArrayList<Auction> auctionList = new ArrayList<>();

    // List of user profiles
    private ArrayList<Profile> profileList = new ArrayList<>();

    /**
     * Construct a Data Controller object and load associated data from disk
     * @throws IOException if the data could not be loaded for whatever reason
     */
    public DataController() throws IOException {
        load(); //load persistent data
    }

    /**
     * Creates a new auction and adds it to the list of auctions
     * @param seller The seller of the artwork
     * @param artwork The artwork being sold
     * @param bidCount The max no. of bids on the auction
     * @return The new auction
     */
	public Auction createAuction(Profile seller, Artwork artwork, int bidCount) {

	    Auction newAuction = new Auction(seller, artwork, bidCount);
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
    public Profile createProfile(String uName, String fName, String sName, String mobileNo, Address address, Picture profImg) {

	    Profile newProfile = new Profile(uName,fName, sName, mobileNo, address, profImg);
	    profileList.add(newProfile);
	    return newProfile;
    }

    /**
     * Filters auctions by type of artwork
     * @param key The filter key
     * @return The filtered list of auctions
     */
    public ArrayList<Auction> filterAuctions(AuctionFilterKey key) {

        ArrayList<Auction> filtered = new ArrayList<>();

        // Filters by paintings
        if (key == AuctionFilterKey.PAINTING) {
            for (Auction elem : auctionList) {
                if (elem.getArtwork() instanceof Painting) {
                    filtered.add(elem);
                }
            }
        }
        // Filters by sculptures
        else if (key == AuctionFilterKey.SCULPTURE) {
            for (Auction elem : auctionList) {
                if (elem.getArtwork() instanceof Sculpture) {
                    filtered.add(elem);
                }
            }
        }
        // No filter
        else if (key == AuctionFilterKey.ALL){
            //Return copy of all auctions
            filtered = (ArrayList<Auction>) auctionList.clone();
        }

        return filtered;
    }

    /**
     * Filters auctions by type of artwork
     * @param key The filter key
     * @param hideCompleted if true filter out completed auctions
     * @return The filtered list of auctions
     */
    public ArrayList<Auction> filterAuctions(AuctionFilterKey key, boolean hideCompleted) {

        ArrayList<Auction> auctions = filterAuctions(key);

        if (hideCompleted)
        {
            //Auction iterator
            Iterator<Auction> it =  auctions.iterator();

            //For each auction
            while (it.hasNext())
            {
                //If this auction is complete
                if (it.next().isCompleted())
                {
                    //Remove it
                    it.remove();
                }
            }
        }

        return auctions;
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

    /**
     * @return The list of auctions
     */
    public ArrayList<Auction> getAuctions() {

        return auctionList;
    }

    /**
     * @return The list of profiles
     */
    public ArrayList<Profile> getProfiles() {

        return profileList;
    }

    /**
     * Save changes made to program data to persistent storage
     */
    public void save()
    {
        JsonObject root = new JsonObject();

        //Save profile and auction info
        root.set("auctions", saveAuctions());
        root.set("profiles", saveProfiles());

        try
        {
            JsonParser.writeTo(ROOT_JSON_FILE, new JsonValue(root));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    /*
        Save profiles to JSON object
     */
    private JsonList saveProfiles()
    {
        JsonList jsonProfileList = new JsonList();

        ProfileFormatter formatter = new ProfileFormatter(this);

        //For each profile save to jsonProfileList
        for (Profile profile : this.profileList)
        {
            jsonProfileList.add(formatter.store(profile));
        }

        return jsonProfileList;
    }

    /*
        Save auctions to JSON object
     */
    private JsonList saveAuctions()
    {
        JsonList jsonAuctionList = new JsonList();

        AuctionFormatter formatter = new AuctionFormatter(this);

        //For each auction save to jsonAuctionList
        for (Auction auction : this.auctionList)
        {
            //Append auction
            jsonAuctionList.add(formatter.store(auction));
        }

        return jsonAuctionList;
    }


    /*
        Construct the profile list from a list of JSON profiles
     */
    private void loadProfiles(JsonList jsonProfiles)
    {
        //We can't add favourite users in one pass
        //All profiles must be loaded first so we cache the usernames of favourite users here
        TreeMap<Profile,List<String>> favouriteProfileMap = new TreeMap<>(new Comparator<Profile>() {
            //Custom comparator for profiles
            @Override
            public int compare(Profile o1, Profile o2) {
                return o1.getUsername().compareTo(o2.getUsername());
            }
        });

        ProfileFormatter formatter = new ProfileFormatter(this);

        //For each profile entry
        for (JsonValue value : jsonProfiles)
        {
            JsonObject jsonProfile = value.asObject();

            Profile p = formatter.load(value);

            //Cache favourite profiles
            ArrayList<String> favouriteUsernames = new ArrayList<>();

            for (JsonValue fav : jsonProfile.getList("favourite_profiles"))
            {
                favouriteUsernames.add(fav.asString());
            }

            favouriteProfileMap.put(p, favouriteUsernames);
        }

        //For each profile add their favourite users
        for (Map.Entry<Profile,List<String>> favourites : favouriteProfileMap.entrySet())
        {
            Profile p = favourites.getKey();

            //Look up profile object by name and add to favourites
            for (String favUsername : favourites.getValue())
            {
                p.addFavourite(this.searchByUsername(favUsername));
            }
        }
    }

    /*
        Construct the profile list from a list of JSON profiles
     */
    private void loadAuctions(JsonList jsonAuctions)
    {
        AuctionFormatter formatter = new AuctionFormatter(this);

        //For each auction entry
        for (JsonValue value : jsonAuctions)
        {
            Auction auction = formatter.load(value);
        }
    }

    /*
     * Load program data from persistent storage
     *
     * Called once on construction of the Data Controller
     */
    private void load() throws IOException
    {
        try
        {
            //Parse document
            JsonObject root = JsonParser.readFrom(ROOT_JSON_FILE).asObject();

            loadProfiles(root.getList("profiles"));
            loadAuctions(root.getList("auctions"));
        }
        //If file not found
        catch (FileNotFoundException e)
        {
            //Create empty JSON file
            File f = new File(ROOT_JSON_FILE);
            f.createNewFile();
        }
        //If JSON data is corrupt
        catch (JsonParserException e)
        {
            //Pass exception up the call stack
            throw new IOException(e.getMessage(), e);
        }
    }
}
