package Artatawe.IO;

public class JsonValue
{
	private Object value;

	public JsonValue(int value)
    {

    }

    public JsonValue(double value)
    {

    }

    public JsonValue(boolean value)
    {

    }

    public JsonValue(String value)
    {

    }

    public JsonValue(JsonObject object)
    {

    }

    public JsonValue(JsonList list)
    {

    }

    public int asInteger()
    {
        return 0;
    }

    public double asDouble()
    {
        return 0;
    }

    public boolean asBoolean()
    {
        return false;
    }

    public String asString()
    {
        return null;
    }

    public JsonObject asObject()
    {
        return null;
    }

    public JsonList asList()
    {
        return null;
    }

    public boolean isInteger() { return false; }

    public boolean isDouble() { return false; }

    public boolean isBoolean() { return false; }

    public boolean isString() { return false; }

    public boolean isObject() { return false; }

    public boolean isList() { return false; }

    public boolean isNull() { return false; }

}