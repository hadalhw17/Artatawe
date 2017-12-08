package Artatawe.Data;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.image.Image;

/**
 * Profile class for Artatawe
 *
 * @author Richard Marton
 *
 * This creates a profile for the system
 */
public class Profile {

	private String username; // users username
	private String firstname; // users firstname
	private String surname; // users surname
	private String mobileNo; // users mobile number
	private Address address;// users address
	private Image profileImage; // users profile image

    /**
     * Arraylist which creates a list of favourite users for Artatawe.
     */
	private ArrayList<Profile> favourites = new ArrayList<Profile>();

    /**
     * arraylist that creates a list of artworks for the users.
     */
	private ArrayList<Artwork> artwork = new ArrayList<Artwork>();

	public Profile(){

    }
	public Profile(String username, String firstName, String surname,
                   String mobileNo, Address address, Image profileImage) {
		this.username = username;
		this.firstname = firstName;
		this.surname = surname;
		this.mobileNo = mobileNo;
		this.address = address;
		this.profileImage = profileImage;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	
	public void setProfileImg(Image profileImage) {
		this.profileImage = profileImage;
	}
	
	public Image getProfileImg() {
		return profileImage;
		
	}
	
	public Artwork getArtworks(Artwork artwork) {
		return artwork;
	}
	
	public Artwork addArt(Artwork art) {
		return art;
	}
	
	public Artwork removeArt(Artwork art) {
		return art;
	}
	
	public void addFavourite(Profile favourite) {
		this.favourites.add(favourite);
	}
	
	public void removeFavourite(Profile favourite) {
        this.favourites.remove(favourite);
	}

	public List<Profile> getAllFavourites() {
	    return this.favourites;
	}

	public Address getAddress() {
        return this.address;
    }

	public String toString() {
        return "";
	}
}

