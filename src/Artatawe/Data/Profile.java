/**
 * Profile.java
 * @author Richard Marton
 * Written for CS-230 A3 - Artatawe
 */

package Artatawe.Data;
import java.util.ArrayList;
import java.util.List;



/**
 * Creates and stores favourites, sellingAuctions and bids. Constructs a Profile.
 * 
 */
public class Profile {

	private String username;
	private String firstname; 
	private String surname; 
	private String mobileNo;
	private Address address;
	private Picture profilePicture;

	//List of favourites
	private ArrayList<Profile> favourites = new ArrayList<>();
	
	//List of selling auctions
	private ArrayList<Auction> sellingAuctions = new ArrayList<>();

	//list of bids
	private ArrayList<Bid> bids = new ArrayList<>();
	
	/**
	* Constructor for class Profile
	* @param username
    * @param firstName The user's first name
    * @param surname The user's surname
    * @param mobileNo The user's mobile phone number
    * @param address The address of the user
    * @param profilePicture The user's profile image
	*   <creates a template for a profile>
	*/
	public Profile(String username, String firstName, String surname, String mobileNo, Address address, Picture profilePicture) {
		this.username = username;
		this.firstname = firstName;
		this.surname = surname;
		this.mobileNo = mobileNo;
		this.address = address;
		this.profilePicture = profilePicture;
	}
	
	
    /**
    *
    * @return profiles username
    */
	public String getUsername() {
		return username;
	}
	
	
    /**
    * sets profile picture
    * @param path
    */
	public void setPicture(String path){
		this.profilePicture = new Picture(path);
	}
	
    /**
    * sets username
    * @param username
    */
	public void setUsername(String username) {
		this.username = username;
	}
	
	
    /**
    *
    * @return profiles firstname
    */
	public String getFirstname() {
		return firstname;
	}
	
	
    /**
    * sets profiles firstname
    * @param firstname
    */
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	
	
    /**
    *
    * @return profiles surname
    */
	public String getSurname() {
		return surname;
	}
	
	
    /**
    * sets profiles surname
    * @param surname
    */
	public void setSurname(String surname) {
		this.surname = surname;
	}
	
	
    /**
    *
    * @return profiles mobileNo
    */
	public String getMobileNo() {
		return mobileNo;
	}
	
	
	/**
	 * sets mobileNo
	 * @param mobileNo
	 */
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	
	
    /**
    * sets profile picture
    * @param profilePicture
    */
	public void setProfileImg(Picture profilePicture) {
		this.profilePicture = profilePicture;
	}

    /**
     * changes address
     * @param newAddress
     */
	public void changeAddress(Address newAddress){
	    this.address = newAddress;
    }
	
	
    /**
    *
    * @return profile picture
    */
    public Picture getProfileImg() {
		return profilePicture;
	}
    
    
    /**
    *
    * @return profiles address
    */
    public Address getAddress() {
        return this.address;
    }
    
    
    /**
    *
    * @return selling auctions
    */
    public List<Auction> getAuctions() {
	    return this.sellingAuctions;
    }
    
    
    /**
    *
    * @return bids
    */
    public List<Bid> getBids() {
	    return this.bids;
    }
    
    
    /**
    * 
    * adds favourite
    * @param favourite
    */
	public void addFavourite(Profile favourite) {
		this.favourites.add(favourite);
	}
	
	
    /**
    * 
    * removes favourite
    * @param favourite
    */
	public void removeFavourite(Profile favourite) {
        this.favourites.remove(favourite);
	}
    /**
    *
    * @return favourites
    */
	public List<Profile> getAllFavourites() {
	    return this.favourites;
	}

	// for testing
	public String toString() {
        return "";
	}
}
