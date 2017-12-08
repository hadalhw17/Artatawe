package Artatawe.Data;
import java.util.Date;
/**
 * Bid class for Artatawe
 *
 * @author alexisvenizelos
 *
 * Creates a bid for the system
 */
public class Bid {
	
	private Profile buyer; // Profile of the buyer
	private int amount; // Amount spend on bid
	private Date dateTime; // Date and time of the bid
	private Auction auction;
	
	/**
	 * constructs a bid for the user
	 * @param buyer
	 * @param amount
	 * @param dateTime
	 * @param auction
	 */
	public Bid(Profile buyer, int amount, Date dateTime, Auction auction) {
		
		this.buyer = buyer;
		this.amount = amount;
		this.dateTime = dateTime;
		this.auction = auction;
		
	}

	/**
	 * @return the auction
	 */
	public Auction getAuction() {
		return auction;
	}

	/**
	 * @param auction 
	 */
	public void setAuction(Auction auction) {
		this.auction = auction;
	}

	/**
	 * @return profile of the buyer
	 */
	public Profile getBuyer() {
		return buyer;
	}

	/**
	 * @param buyer is set.
	 */
	public void setBuyer(Profile buyer) {
		this.buyer = buyer;
	}

	/**
	 * @return amount spend on a bid
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
	 * @return date and time of each bid.
	 */
	public Date getDateTime() {
		return dateTime;
	}

	/**
	 * @param dateTime is set.
	 */
	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}
}
