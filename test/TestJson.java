import Artatawe.IO.JsonValue;
import org.junit.*;

import Artatawe.IO.JsonObject;
import Artatawe.IO.JsonParserException;
import Artatawe.IO.JsonParser;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.StringReader;

/**
 * JSON test suite
 */
public class TestJson
{
    /**
     * Test type casting
     */
    @Test
    public void test1()
    {
        System.out.println("JSON test case 1:");

        // Number | String | Boolean | Object | List | Null
        String json = "{\"a\":1,\"b\":\"some text\",\"c\":true,\"d\":{},\"e\":[],\"f\":null}";

        JsonObject object = null;

        try
        {
            object = JsonParser.readFrom(new StringReader(json)).asObject();
        }
        catch (JsonParserException e)
        {
            System.err.println(e.getMessage());
            Assert.fail();
        }

        Assert.assertTrue(object.get("a").isNumber());
        Assert.assertTrue(object.get("b").isString());
        Assert.assertTrue(object.get("c").isBoolean());
        Assert.assertTrue(object.get("d").isObject());
        Assert.assertTrue(object.get("e").isList());
        Assert.assertTrue(object.get("f").isNull());

        try
        {
            JsonParser.writeTo(new OutputStreamWriter(System.out), new JsonValue(object));
        }
        catch (IOException e)
        {
            System.err.println(e.getMessage());
            Assert.fail();
        }
    }

    /**
     * Testing nested values
     */
    @Test
    public void test2()
    {
        try
        {
            System.out.println("JSON test case 2:");

            String json = "{\"a\":{\"b\":{\"c\":[1,4],\"d\":null,\"e\":\"some text\"}}}";

            JsonObject object = JsonParser.readFrom(new StringReader(json)).asObject();

            Assert.assertTrue(object.get("a").isObject());
            Assert.assertTrue(object.getObject("a").get("b").isObject());
            Assert.assertTrue(object.getObject("a").getObject("b").get("c").isList());
            Assert.assertTrue(object.getObject("a").getObject("b").get("d").isNull());
            Assert.assertTrue(object.getObject("a").getObject("b").get("e").isString());

            JsonObject subobject = object.getObject("a").getObject("b");
            Assert.assertEquals(subobject.getList("c").get(0).asInteger(), 1);
            Assert.assertEquals(subobject.getList("c").get(1).asInteger(), 4);
            Assert.assertEquals(subobject.getString("e"), "some text");

            JsonParser.writeTo(new OutputStreamWriter(System.out), new JsonValue(object));
        }
        catch(Exception e)
        {
            System.err.println(e.getMessage());
            Assert.fail();
        }
    }

    /*
        Assert that a given JSON string couldn't be parsed
     */
    private void assertParseFailure(String jsonString)
    {
        try
        {
            JsonParser.readFrom(new StringReader(jsonString));
        }
        catch (JsonParserException e)
        {
            System.out.println("Error detected successfully:");
            System.out.println(e.getMessage());
            return;
        }

        System.err.println("Json compiled successfully when it shouldn't have");
        Assert.fail();
    }

    /**
     * Test error detecting
     */
    @Test
    public void test3()
    {
        System.out.println("JSON test case 3:");

        assertParseFailure("");
        assertParseFailure("{");
        assertParseFailure("{ \"abc");
        assertParseFailure("{ \"abc\" : ");
    }
}
