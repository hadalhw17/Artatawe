package Artatawe.Data;

/**
 * @author Tom Street
 *
 * Class representing a comment written by a user on an auction
 */
public class AuctionComment {

    //Commenter's profile
    private Profile commenter;
    //AuctionComment itself
    private String text;

    /**
     * Construct a comment
     * @param commenter Commenter profile
     * @param text AuctionComment text
     */
    public AuctionComment(Profile commenter, String text)
    {
        this.commenter = commenter;
        this.text = text;
    }

    /**
     * @return The profile of the commmenter
     */
    Profile getCommenter()
    {
        return this.commenter;
    }

    /**
     * @return The comment text
     */
    String getText()
    {
        return this.text;
    }
}
