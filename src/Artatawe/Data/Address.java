import java.util.ArrayList;

public class Address {
	 
	private int houseNum;
	private String street; 
	private String county;
	private String postcode; 
	private String city;

	
	private ArrayList<Address> address = new ArrayList<Address>();
	
	
	public Address(int houseNum, String street, String city, String county, String postcode){
		this.houseNum = houseNum;
		this.street  = street;
		this.city  = city;
		this.county = county;
		this.postcode = postcode;
}
	
	
	public int getHouseNum() {
		return houseNum;
	}

	public void setHouseNum(int houseNum) {
		this.houseNum = houseNum;
	}


	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}


	public String getCounty() {
		return county;
	}

	public void setCounty(String county) {
		this.county = county;
	}


	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	
}
