package Artatawe.Data;

import Artatawe.IO.JsonFormatter;
import Artatawe.IO.JsonList;
import Artatawe.IO.JsonObject;
import Artatawe.IO.JsonValue;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;

/**
 * @author Tom Street
 *
 * Auction Formatter class
 *
 * Converts Auction objects from/to JSON
 */
class AuctionFormatter implements JsonFormatter<Auction>
{
    //Data controller object - allows access to data in the system
    private DataController data;

    /*
       Helper method.
       Convert a Date to string
    */
    private String dateToString(Date date)
    {
        return DateFormat.getDateInstance().format(date);
    }

    /*
        Helper method.
        Convert a date from a string
     */
    private Date dateFromString(String text)
    {
        try
        {
            return DateFormat.getDateInstance().parse(text);
        }
        catch (ParseException e)
        {
            //We don't care too much about ParseExceptions
            System.err.println(e.getMessage());
            return new Date();
        }
    }

    /*
        Construct an Artwork object from JSON data
    */
    private Artwork loadArtwork(JsonObject object)
    {
        //Load artwork attributes
        String type = object.getString("type");
        String name = object.getString("name");
        String desc = object.getString("desc");
        String photo = object.getString("photo");
        int year = object.getInteger("year");
        int reserve = object.getInteger("reserve_price");
        Date dateTime = dateFromString(object.getString("date"));
        double width = object.getDouble("width");
        double height = object.getDouble("height");

        //Select artwork type

        if (type.equals("PAINTING"))
        {
            return new Painting(name,desc,new Picture(photo), year, reserve, dateTime, width, height);
        }
        else if (type.equals("SCULPTURE"))
        {
            double depth = object.getDouble("depth");

            return new Sculpture(name,desc,new Picture(photo), year, reserve, dateTime, width, height, depth);
        }
        else
        {
            System.err.println("Unknown artwork type");
        }

        return null;
    }

    /*
        Construct a JSON object from an Artwork
     */
    private JsonObject saveArtwork(Artwork artwork)
    {
        JsonObject jsonArt = new JsonObject();

        //Artwork properties
        jsonArt.set("name", artwork.getName());
        jsonArt.set("desc", artwork.getName());
        jsonArt.set("photo", artwork.getPhoto().getPath());
        jsonArt.set("year", artwork.getYear());
        jsonArt.set("reserve_price", artwork.getReservedPrice());
        jsonArt.set("date", dateToString(artwork.getDateTime()));
        jsonArt.set("width", artwork.getWidth());
        jsonArt.set("height", artwork.getHeight());

        //Artwork type
        if (artwork instanceof Painting)
        {
            jsonArt.set("type", "PAINTING");
        }
        else if (artwork instanceof Sculpture)
        {
            jsonArt.set("type", "SCULPTURE");
            jsonArt.set("depth", ((Sculpture)artwork).getDepth());
        }

        return jsonArt;
    }

    /**
     * Construct an Auction Formatter
     * @param data Data Controller
     */
    public AuctionFormatter(DataController data)
    {
        this.data = data;
    }

    /**
     * Convert an Auction to JSON
     * @param auction Auction object
     * @return a JSON value
     */
    public JsonValue store(Auction auction)
    {
        JsonObject jsonAuction = new JsonObject();

        //Auction info
        jsonAuction.set("seller", auction.getSeller().getUsername());
        jsonAuction.set("max_bids", auction.getBidMax());
        jsonAuction.set("artwork", saveArtwork(auction.getArtwork()));

        //Bid list
        JsonList jsonBids = new JsonList();

        for (Bid bid : auction.getBidList())
        {
            JsonObject jsonBid = new JsonObject();
            jsonBid.set("bidder", bid.getBuyer().getUsername());
            jsonBid.set("amount", bid.getAmount());
            jsonBid.set("date", dateToString(bid.getDateTime()));
            jsonBids.add(jsonBid);
        }

        //Comment list
        JsonList jsonComments = new JsonList();

        for (AuctionComment comment : auction.getCommentList())
        {
            JsonObject jsonComment = new JsonObject();
            jsonComment.set("profile", comment.getCommenter().getUsername());
            jsonAuction.set("text", comment.getText());
            jsonComments.add(jsonComment);
        }

        jsonAuction.set("bids", jsonBids);
        jsonAuction.set("comments", jsonComments);

        return new JsonValue(jsonAuction);
    }

    /**
     * Create an Auction from JSON
     * @param value a JSON value
     * @return an Auction object
     */
    public Auction load(JsonValue value)
    {
        JsonObject json = value.asObject();

        //Construct an auction
        Auction auction = data.createAuction(
                data.searchByUsername(json.getString("seller")),
                loadArtwork(json.getObject("artwork")),
                json.getInteger("max_bids")
        );

        //Construct bid list
        for (JsonValue bidValue : json.getList("bids"))
        {
            JsonObject jsonBid = bidValue.asObject();

            auction.placeBid(
                    data.searchByUsername(jsonBid.getString("bidder")),
                    jsonBid.getInteger("amount"),
                    dateFromString(jsonBid.getString("date"))
            );
        }

        //Construct comment list
        for (JsonValue cmValue : json.getList("comments"))
        {
            JsonObject jsonComment = cmValue.asObject();

            auction.makeComment(
                    data.searchByUsername(jsonComment.getString("profile")),
                    jsonComment.getString("text")
            );
        }

        return auction;
    }
}
