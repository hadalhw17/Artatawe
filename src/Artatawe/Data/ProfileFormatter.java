package Artatawe.Data;

import Artatawe.IO.JsonFormatter;
import Artatawe.IO.JsonList;
import Artatawe.IO.JsonObject;

/**
 * @author Tom Street
 *
 * Profile Formatter class
 *
 * Converts Profile objects from/to JSON
 */
public class ProfileFormatter implements JsonFormatter<Profile>
{
    //Data controller object - allows access to data in the system
    private DataController data;

    /*
        Construct an Address object from JSON data
     */
    private Address loadAddress(JsonObject object)
    {
        return new Address(
                object.getInteger("houseNo"),
                object.getString("street"),
                object.getString("city"),
                object.getString("country"),
                object.getString("postcode")
        );
    }

    /**
     * Construct an Profile Formatter
     * @param data Data Controller
     */
    public ProfileFormatter(DataController data)
    {
        this.data = data;
    }

    /**
     * Convert an Profile to JSON
     * @param profile Profile object
     * @return a JSON object
     */
    public JsonObject store(Profile profile)
    {
        JsonObject json = new JsonObject();

        //Basic profile info
        json.set("username", profile.getUsername());
        json.set("firstname", profile.getFirstname());
        json.set("surname", profile.getSurname());
        json.set("avatar", profile.getProfileImg().getCaption());
        json.set("mobileNo", profile.getMobileNo());

        //Address info
        JsonObject address = new JsonObject();
        address.set("houseNo", profile.getAddress().getHouseNum());
        address.set("street", profile.getAddress().getStreet());
        address.set("city", profile.getAddress().getCity());
        address.set("country", profile.getAddress().getCounty());
        address.set("postcode", profile.getAddress().getPostcode());

        json.set("address", address);

        //Favourite profiles
        JsonList favourites = new JsonList();

        for (Profile fav : profile.getAllFavourites())
        {
            favourites.add(fav.getUsername());
        }

        json.set("favourite_profiles", favourites);

        return json;
    }

    /**
     * Create an Profile from JSON
     * @param json a JSON object
     * @return an Profile object
     */
    public Profile load(JsonObject json)
    {
        //Construct a profile
        return data.createProfile(
                json.getString("username"),
                json.getString("firstname"),
                json.getString("surname"),
                json.getString("mobileNo"),
                loadAddress(json.getObject("address")),
                new Image(json.getString("avatar"),0,0)
        );
    }
}
