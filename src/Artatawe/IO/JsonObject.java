package Artatawe.IO;

import java.util.Set;
import java.util.Map;

public class JsonObject
{
    private Map<String,JsonValue> values;

    public boolean has(String key)
    {
        return false;
    }

    public JsonValue get(String key)
    {
        return null;
    }

    public void set(String key, JsonValue value)
    {

    }

    public void remove(String key)
    {

    }

    public int getInteger(String key)
    {
        return 0;
    }

    public double getDouble(String key)
    {
        return 0;
    }

    public boolean getBoolean(String key)
    {
        return false;
    }

    public String getString(String key)
    {
        return null;
    }

    public JsonObject getObject(String key)
    {
        return null;
    }

    public JsonList getList(String key)
    {
        return null;
    }

    public Set<String> getKeys()
    {
        return null;
    }
}
