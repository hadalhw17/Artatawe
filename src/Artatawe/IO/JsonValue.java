package Artatawe.IO;

/**
 *  @author Tom Street
 *
 *  JSON value wrapper class.
 *
 *  Value can one of 5 data types:
 *  - Number
 *  - Boolean
 *  - String
 *  - JsonObject
 *  - JsonList
 */
public class JsonValue
{
    // Wrapped value
	private Object value;

    /**
     * Construct a null value
     */
	public JsonValue()
    {
        this.value = null;
    }

    /**
     * Construct a type wrapper around a given value.
     * Asserts that the underlying type of the given value is a legal JSON type.
     * @param value unknown value
     * @throws IllegalArgumentException if the value is an illegal type
     */
    public JsonValue(Object value) throws IllegalArgumentException
    {
        //Check if input value is a legal type
        if (!(
                value instanceof Number ||
                value instanceof Boolean ||
                value instanceof String ||
                value instanceof JsonObject ||
                value instanceof JsonList ||
                value == null
            ))
        {
            throw new IllegalArgumentException("parameter is not a Number, Boolean, String, JsonObject or JsonList");
        }

        this.value = value;
    }

    /**
     * Get underlying value
     * @return generic Object value
     */
    public Object get()
    {
        return this.value;
    }

    /**
     * Cast to an integer
     * @return wrapped value as integer
     * @throws ClassCastException if wrapped value is not a number
     */
    public int asInteger() throws ClassCastException
    {
        return ((Number)this.value).intValue();
    }

    /**
     * Cast to a double
     * @return wrapped value as double
     * @throws ClassCastException if wrapped value is not a number
     */
    public double asDouble() throws ClassCastException
    {
        return ((Number)this.value).doubleValue();
    }

    /**
     * Cast to a boolean
     * @return wrapped value as boolean
     * @throws ClassCastException if wrapped type is not a boolean
     */
    public boolean asBoolean() throws ClassCastException
    {
        return (boolean)this.value;
    }

    /**
     * Cast to a string
     * @return wrapped value as string
     * @throws ClassCastException if wrapped type is not a string
     */
    public String asString() throws ClassCastException
    {
        return (String)this.value;
    }

    /**
     * Cast to an object
     * @return wrapped value as an object
     * @throws ClassCastException if wrapped type is not an object
     */
    public JsonObject asObject() throws ClassCastException
    {
        return (JsonObject)this.value;
    }

    /**
     * Cast to a list
     * @return wrapped value as a list
     * @throws ClassCastException if wrapped type is not a list
     */
    public JsonList asList() throws ClassCastException
    {
        return (JsonList)this.value;
    }


    /**
     * Test if value is a number (integer/double)
     * @return true if value is a number
     */
    public boolean isNumber() { return this.value instanceof Number; }

    /**
     * Test if value is a boolean
     * @return true if value is a boolean
     */
    public boolean isBoolean() { return this.value instanceof Boolean; }

    /**
     * Test if value is a string
     * @return true if value is a string
     */
    public boolean isString() { return this.value instanceof String; }

    /**
     * Test if value is an object
     * @return true if value is an object
     */
    public boolean isObject() { return this.value instanceof JsonObject; }

    /**
     * Test if value is a list
     * @return true if value is a list
     */
    public boolean isList() { return this.value instanceof JsonList; }

    /**
     * Test if value has not been set
     * @return true if value is null
     */
    public boolean isNull() { return (this.value == null); }
}
