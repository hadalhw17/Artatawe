package Artatawe.IO;

import java.io.*;
import java.util.InputMismatchException;

/**
 * JSON parser class
 *
 * contains static methods for serializing and deserializing JSON data
 */
public class JsonParser
{
    /*
        Parse a JSON list from a sequence of tokens
     */
    private static JsonList parseList(Tokenizer tokens) throws InputMismatchException
    {
        JsonList list = new JsonList();

        //Expect start of list ([)
        tokens.next(TokenCode.LSQUARE);

        //While not end of list
        while (!tokens.isNext(TokenCode.RSQUARE))
        {
            //format: <value>, <value>, ...

            //Insert value into list
            list.add(parseValue(tokens));

            if (tokens.isNext(TokenCode.COMMA))
            {
                tokens.next();
            }
        }

        //Expect end of list (])
        tokens.next(TokenCode.RSQUARE);

        return list;
    }

    /*
        Parse a JSON object from a sequence of tokens
     */
    private static JsonObject parseObject(Tokenizer tokens) throws InputMismatchException
    {
        JsonObject object = new JsonObject();

        //Expect start of object ({)
        tokens.next(TokenCode.LBRACE);

        //While not end of object
        while (!tokens.isNext(TokenCode.RBRACE))
        {
            //format: "string" ,: <value>, ...

            String name = tokens.next(TokenCode.STRING_LITERAL).getData();
            tokens.next(TokenCode.COLON);

            //If object entry already present
            if (object.has(name))
            {
                //Stop parsing
                //todo: JsonParserException when duplicated object entries are found
            }

            //Insert the entry into the object
            object.set(name, parseValue(tokens));

            if (tokens.isNext(TokenCode.COMMA))
            {
                tokens.next();
            }
        }

        //Expect end of object (})
        tokens.next(TokenCode.RBRACE);

        return object;
    }

    /*
        Parse a JSON value from a sequence of tokens
     */
    private static Object parseValue(Tokenizer tokens) throws InputMismatchException
    {
        //String
        if (tokens.isNext(TokenCode.STRING_LITERAL))
        {
            return tokens.next().getData();
        }
        //Integer
        else if (tokens.isNext(TokenCode.INTEGER_CONSTANT))
        {
            return Integer.valueOf(tokens.next().getData());
        }
        //Double
        else if (tokens.isNext(TokenCode.FLOAT_CONSTANT))
        {
            return Double.valueOf(tokens.next().getData());
        }
        //Bool
        else if (tokens.isNext(TokenCode.BOOLEAN))
        {
            return Boolean.valueOf(tokens.next().getData());
        }
        //Object
        else if (tokens.isNext(TokenCode.LBRACE))
        {
            return parseObject(tokens);
        }
        //List
        else if (tokens.isNext(TokenCode.LSQUARE))
        {
            return parseList(tokens);
        }
        //Null object
        else if (tokens.isNext(TokenCode.NULL))
        {
            //Skip token
            tokens.next();
            return null;
        }

        //Stop parsing
        //todo: JsonParserException when JSON value couldn't be parsed
        throw new InputMismatchException(String.format("Expected <value-expression> got %s", tokens.next().getCode()));
    }

    /**
     * Parse a JSON file
     * @param filepath path to JSON document
     * @return a JSON value, null if parsing fails
     * @throws FileNotFoundException
     */
    public static JsonValue readFrom(String filepath) throws FileNotFoundException
    {
        return readFrom(new FileReader(filepath));
    }

    /**
     * Parse a JSON value from an abstract reader
     * @param reader reader containing a JSON document
     * @return a JSON value, null if parsing fails
     */
    public static JsonValue readFrom(Reader reader)
    {
        try
        {
            //Parse the root value
            return new JsonValue(parseValue(new Tokenizer(reader)));
        }
        catch (InputMismatchException e)
        {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Write a JSON value out to a given file
     * @param filepath path to file
     * @param value JSON value
     * @throws IOException
     */
    public static void writeTo(String filepath, JsonValue value) throws IOException
    {
        writeTo(new FileWriter(filepath), value);
    }

    /**
     * Write a JSON value out to an abstract writer
     * @param writer abstract writer
     * @param value JSON value
     */
    public static void writeTo(Writer writer, JsonValue value)
    {

    }
}