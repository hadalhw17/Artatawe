package Artatawe.Data;
import java.util.Date;
/**
 * @author alexisvenizelos
 *
 */
public class Bid {
	
	private Profile buyer;
	private int amount;
	private Date dateTime;
	private int auction;
	
	public Bid(Profile buyer, int amount, Date dateTime, int auction) {
		
		this.buyer = buyer;
		this.amount = amount;
		this.dateTime = dateTime;
		this.auction = auction;
		
	}

	public int getAuction() {
		return auction;
	}

	public void setAuction(int auction) {
		this.auction = auction;
	}

	public String getBuyer() {
		return buyer;
	}

	public void setBuyer(String buyer) {
		this.buyer = buyer;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public Date getDateTime() {
		return dateTime;
	}

	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}
}
