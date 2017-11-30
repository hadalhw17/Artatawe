package Artatawe.Data;
import java.util.Date;
/**
 * @author alexisvenizelos
 *
 */
public class Bid {
	
	private Profile buyer; // Profile of the buyer
	private int amount; // Amount spend on bid
	private Date dateTime; // Date and time of the bid
	private int auction; 
	
	/**
	 * @param buyer
	 * @param amount
	 * @param dateTime
	 * @param auction
	 */
	public Bid(Profile buyer, int amount, Date dateTime, int auction) {
		
		this.buyer = buyer;
		this.amount = amount;
		this.dateTime = dateTime;
		this.auction = auction;
		
	}

	/**
	 * @return
	 */
	public int getAuction() {
		return auction;
	}

	/**
	 * @param auction 
	 */
	public void setAuction(int auction) {
		this.auction = auction;
	}

	/**
	 * @return profile of the buyer
	 */
	public Profile getBuyer() {
		return buyer;
	}

	/**
	 * @param buyer
	 */
	public void setBuyer(Profile buyer) {
		this.buyer = buyer;
	}

	/**
	 * @return amount spend on bid
	 */
	public int getAmount() {
		return amount;
	}

	/**
	 * @param amount
	 */
	public void setAmount(int amount) {
		this.amount = amount;
	}

	/**
	 * @return date and time of the each bid
	 */
	public Date getDateTime() {
		return dateTime;
	}

	/**
	 * @param dateTime
	 */
	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}
}
