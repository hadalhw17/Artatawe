package Artatawe.Data;

import java.util.ArrayList;

/**
 * Address Class
 *
 * @author Richard Marton
 *
 * This class will be used to create an Address for a profile for Artatawe.
 */
public class Address {

	private int houseNum; // number of the house
	private String street; // name of the street
	private String county; // name of the county
	private String postcode; // users postcode
	private String city; // name of the city

    // arraylist used to store the address of the users
	private ArrayList<Address> address = new ArrayList<Address>();

    /**
     * Constructor for Address
     *
     * @param houseNum
     * @param street
     * @param city
     * @param county
     * @param postcode
     */
	public Address(int houseNum, String street, String city,
				   String county, String postcode){
		this.houseNum = houseNum;
		this.street  = street;
		this.city  = city;
		this.county = county;
		this.postcode = postcode;
    }

    /**
     *
     * @return the house name
     */
	public int getHouseNum() {
		return houseNum;
	}

    /**
     *
     * @param houseNum sets the house number
     */
	public void setHouseNum(int houseNum) {
		this.houseNum = houseNum;
	}

    /**
     *
     * @return the steet
     */
	public String getStreet() {
		return street;
	}

    /**
     *
     * @param street sets the street
     */
	public void setStreet(String street) {
		this.street = street;
	}

    /**
     *
     * @return the county of the user
     */
	public String getCounty() {
		return county;
	}

    /**
     *
     * @param county sets the county of the user
     */
	public void setCounty(String county) {
		this.county = county;
	}

    /**
     *
     * @return the postcode of the user
     */
	public String getPostcode() {
		return postcode;
	}

    /**
     *
     * @param postcode sets the postcode for the user
     */
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

    /**
     *
     * @return the city of the user
     */
	public String getCity() {
		return city;
	}

    /**
     *
     * @param city sets the city of the user
     */
	public void setCity(String city) {
		this.city = city;
	}
}
