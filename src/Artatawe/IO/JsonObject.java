package Artatawe.IO;

import java.util.HashMap;
import java.util.Set;
import java.util.Map;

/**
 * @author Tom Street
 *
 *  A JSON Object class.
 *
 *  A basic data type in JSON.
 *  Represents an associative array of JSON values.
 *
 */
public class JsonObject
{
    // Internal map of JSON values
    private Map<String,JsonValue> values = new HashMap<>();

    /**
     * Check if an entry exists in the object by name
     * @param key key name
     * @return true if exists
     */
    public boolean has(String key)
    {
        return this.values.containsKey(key);
    }

    /**
     * Get a value from the object by name
     * @param key key name
     * @return the object if it exists, null otherwise
     */
    public JsonValue get(String key)
    {
        return this.values.get(key);
    }

    /**
     * Set a value in the object by name
     * Creates a new key/value pair if it doesn't exist already
     * @param key key name
     * @param value JSON value
     * @throws IllegalArgumentException
     */
    public void set(String key, Object value) throws IllegalArgumentException
    {
        //Object cannot contain itself
        if (value == this)
        {
            throw new IllegalArgumentException("JSON object cannot contain itself");
        }

        this.values.put(key, new JsonValue(value));
    }

    /**
     * Remove an entry from the object by name
     * @param key key name
     */
    public void remove(String key)
    {
        this.values.remove(key);
    }

    /**
     * Get entry in object as integer
     * @param key key name
     * @return value as integer
     */
    public int getInteger(String key)
    {
        return this.values.get(key).asInteger();
    }

    /**
     * Get entry in object as double
     * @param key key name
     * @return value as double
     */
    public double getDouble(String key)
    {
        return this.values.get(key).asDouble();
    }

    /**
     * Get entry in object as boolean
     * @param key key name
     * @return value as boolean
     */
    public boolean getBoolean(String key)
    {
        return this.values.get(key).asBoolean();
    }

    /**
     * Get entry in object as string
     * @param key key name
     * @return value as string, null if key not present
     */
    public String getString(String key)
    {
        return this.values.get(key).asString();
    }

    /**
     * Get entry in object as object
     * @param key key name
     * @return value as object, null if key not present
     */
    public JsonObject getObject(String key)
    {
        return this.values.get(key).asObject();
    }

    /**
     * Get entry in object as list
     * @param key key name
     * @return value as list, null if key not present
     */
    public JsonList getList(String key)
    {
        return this.values.get(key).asList();
    }

    /**
     * Get a set of all the keys in this object
     * @return set of all entry keys
     */
    public Set<String> getKeys()
    {
        return this.values.keySet();
    }

    /**
     * Clear all key/value pairs
     */
    public void clear()
    {
        this.values.clear();
    }
}
