package Artatawe.Data;

import Artatawe.IO.JsonFormatter;
import Artatawe.IO.JsonList;
import Artatawe.IO.JsonObject;
import Artatawe.IO.JsonValue;

/**
 * @author Tom Street
 *
 * Profile Formatter class
 *
 * Converts Profile objects from/to JSON
 */
class ProfileFormatter implements JsonFormatter<Profile>
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
    @Override
    public JsonValue store(Profile profile)
    {
        JsonObject json = new JsonObject();

        //Basic profile info
        json.set("username", profile.getUsername());
        json.set("firstname", profile.getFirstname());
        json.set("surname", profile.getSurname());
        json.set("avatar", profile.getProfileImg().getPath());
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

        return new JsonValue(json);
    }

    /**
     * Create an Profile from JSON
     * @param value a JSON value
     * @return an Profile object
     */
    @Override
    public Profile load(JsonValue value)
    {
        JsonObject json = value.asObject();

        //Construct a profile
        return data.createProfile(
                json.getString("username"),
                json.getString("firstname"),
                json.getString("surname"),
                json.getString("mobileNo"),
                loadAddress(json.getObject("address")),
                new Picture(json.getString("avatar"))
        );
    }
}
