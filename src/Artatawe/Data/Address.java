/**
 * Address.java
 * @author Richard Marton
 * Written for CS-230 A3 - Artatawe
 */
package Artatawe.Data;

import java.util.ArrayList;
/**
 * Creates a template for an address.
 * 
 */
public class Address {

	private int houseNum;
	private String street; 
	private String county;
	private String postcode; 
	private String city;
	
    /**
    * Constructor for class Address
    * @param houseNum
    * @param street
    * @param city
    * @param county 
    * @param postcode 
    * <creates a template for for an address>
    */
	public Address(int houseNum, String street, String city, String county, String postcode){
		this.houseNum = houseNum;
		this.street  = street;
		this.city  = city;
		this.county = county;
		this.postcode = postcode;
    }
	
	
    /**
    * sets city of address
    * @param city
    */
	public void setCity(String city) {
		this.city = city;
	}
	
	
    /**
    * sets houseNum of address
    * @param houseNum
    */
	public void setHouseNum(int houseNum) {
		this.houseNum = houseNum;
	}
	
	
    /**
    * sets street of address
    * @param street
    */
	public void setStreet(String street) {
		this.street = street;
	}
	
	
    /**
    * sets county of address
    * @param county
    */
	public void setCounty(String county) {
		this.county = county;
	}
	
	
    /**
    * sets postcode of address
    * @param postcode
    */
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}	
	
	
    /**
    *
    * @return street of the address
    */
	public String getStreet() {
		return street;
	}
	
	
    /**
    *
    * @return county of the address
    */
	public String getCounty() {
		return county;
	}

	
    /**
    *
    * @return postcode of the address
    */
	public String getPostcode() {
		return postcode;
	}


    /**
    *
    * @return city of the address
    */
	public String getCity() {
		return city;
	}
    
	
	/**
    *
    * @return houseNum of the address
    */
	public int getHouseNum() {
		return houseNum;
	}
}
