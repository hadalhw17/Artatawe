package Artatawe.IO;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;

/**
 *  @author Tom Street
 *
 *  JSON List class.
 *
 *  A basic data type in JSON.
 *  Represents an array of JSON values.
 *
 */
public class JsonList implements Iterable<JsonValue>
{
    // Internal list of JSON values.
	private List<JsonValue> values = new ArrayList<>();

    /**
     * Construct an empty JSON list
     */
    public JsonList() {}

    /**
     * Construct a list a collection of JSON values
     * @param values array of values
     * @throws IllegalArgumentException if value collection contains an invalid JSON type
     */
    public JsonList(Collection<Object> values) throws IllegalArgumentException
    {
        //For each object
        for (Object v : values)
        {
            this.values.add(new JsonValue(v));
        }
    }

    /**
     * Construct a list from an array of JSON values
     * @param values array of values
     * @throws IllegalArgumentException if value array contains an invalid JSON type
     */
    public JsonList(Object[] values) throws IllegalArgumentException
    {
        //For each object
        for (Object v : values)
        {
            this.values.add(new JsonValue(v));
        }
    }

    /**
     * Get a value at a given index
     * @param index index in list
     * @return the value at that index
     * @throws ArrayIndexOutOfBoundsException
     */
    public JsonValue get(int index) throws ArrayIndexOutOfBoundsException
    {
        return this.values.get(index);
    }

    /**
     * Add a value to the list
     * @param value a JSON data type
     * @throws IllegalArgumentException
     */
    public void add(Object value) throws IllegalArgumentException
    {
        //List cannot contain itself
        if (value == this)
        {
            throw new IllegalArgumentException("JSON list cannot contain itself");
        }

        this.values.add(new JsonValue(value));
    }

    /**
     * Set a value at a given index
     * @param index index in list
     * @param value the value to set to
     * @throws ArrayIndexOutOfBoundsException
     * @throws IllegalArgumentException
     */
    public void set(int index, Object value) throws ArrayIndexOutOfBoundsException, IllegalArgumentException
    {
        //List cannot contain itself
        if (value == this)
        {
            throw new IllegalArgumentException("JSON list cannot contain itself");
        }

        this.values.set(index, new JsonValue(value));
    }

    /**
     * Get JSON list as an array of values
     * @return
     */
    public JsonValue[] toArray()
    {
        return this.values.toArray(new JsonValue[this.values.size()]);
    }

    /**
     * Get the size of the list
     * @return the size of the list
     */
    int size()
    {
        return values.size();
    }

    /**
     * Clear all values
     */
    public void clear()
    {
        this.values.clear();
    }

    /**
     * Get iterator over JSON list
     * @see Iterable#iterator()
     * @return
     */
    @Override
    public Iterator<JsonValue> iterator()
    {
        return this.values.iterator();
    }
}
