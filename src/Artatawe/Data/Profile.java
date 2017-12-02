import java.util.ArrayList;

public class Profile {

	private String username; 
	private String firstname; 
	private String surname; 
	private String mobileNo;
	private Address address;
	private Image profileImage ; 
	
	
	private ArrayList<Profile> favourites = new ArrayList<Profile>(); 
	
	private ArrayList<Artwork> artwork = new ArrayList<Artwork>(); 
	

	Profile(String username, String firstName, String surname, String mobileNo, Address address, Image profileImage){
		
		this.username = username;
		this.firstname = firstName;
		this.surname = surname;
		this.mobileNo = mobileNo;
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
	
	public Image getProfileImg(Image profileImage) {
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
	
	public Profile addFav(Profile favorites) {
		return favorites;
	}
	
	public Profile removeFav(Profile favorites) {
		return favorites;
	}
	
	public String toString(){
		
    return // (getName()+" "+ getDescription() +" "+ getPhoto() +" " + getYear() +" " + getReservedPrice()
            //+" " + getAllowedBids() +" " + getDateTime() +" " + getWidth() +" " + getHeight() +" " + getDepth());
}
		
	}
	
}

