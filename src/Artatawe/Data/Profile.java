package Artatawe.Data;
import java.util.ArrayList;
import java.util.List;

public class Profile {

	private String username;
	private String firstname; 
	private String surname; 
	private String mobileNo;
	private Address address;
	private Picture profilePicture;

	private ArrayList<Profile> favourites = new ArrayList<Profile>(); 
	
	private ArrayList<Artwork> artwork = new ArrayList<Artwork>();

	public Profile(){

    }
	public Profile(String username, String firstName, String surname, String mobileNo, Address address, Picture profilePicture) {
		this.username = username;
		this.firstname = firstName;
		this.surname = surname;
		this.mobileNo = mobileNo;
		this.address = address;
		this.profilePicture = profilePicture;
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
	
	public void setProfileImg(Picture profilePicture) {
		this.profilePicture = profilePicture;
	}
	
	public Picture getProfileImg() {
		return profilePicture;
		
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

