package Artatawe.IO;

import java.io.*;
import java.util.InputMismatchException;
import java.util.Iterator;

/**
 * @author Tom Street
 *
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
                throw new JsonParserException(String.format("Cannot have duplicate object entry \"%s\"", name));
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
        throw new JsonParserException(Token.formatError("Number|Boolean|String|Object|List|null", tokens.next()));
    }

    /*
        Indent helper method
     */
    private static void indent(Writer output, int indentLevel) throws IOException
    {
        for (int i = 0; i < indentLevel; i++)
        {
            output.write("    ");
        }
    }

    /*
        Convert a JSON object into a string
     */
    private static void stringifyObject(Writer output, JsonObject object, int indentLevel) throws IOException
    {
        //Begin object (no indent is needed)
        output.write("{");

        //Workaround for inserting the correct number of commas
        int separatorCount = object.getKeys().size() - 1;

        //For each object entry name
        for (String name : object.getKeys())
        {
            output.write("\n");
            indent(output, indentLevel + 1);

            //Write entry name and value to output
            output.write("\"" + name + "\" : ");
            stringifyValue(output, object.get(name), indentLevel + 1);

            if (separatorCount-- > 0)
            {
                //Write separator
                output.write(",");
            }
            else
            {
                //End of line
                output.write("\n");
            }
        }

        if (object.getKeys().size() > 0)
        {
            indent(output, indentLevel);
        }

        //End object
        output.write("}");
    }

    /*
        Convert a JSON list into a string
    */
    private static void stringifyList(Writer output, JsonList list, int indentLevel) throws IOException
    {
        //Begin list (no indent is needed)
        output.write("[");

        //Workaround for inserting the correct number of commas
        int separatorCount = list.size() - 1;

        //Foreach list element
        for (JsonValue value : list)
        {
            output.write("\n");
            indent(output, indentLevel + 1);

            //Write element to output
            stringifyValue(output, value, indentLevel + 1);

            if (separatorCount-- > 0)
            {
                //Write separator
                output.write(",");
            }
            else
            {
                //End of list
                output.write("\n");
            }
        }

        if (list.size() > 0)
        {
            indent(output, indentLevel);
        }

        //End list
        output.write("]");
    }

    /*
        Convert a JSON data value into a string
     */
    private static void stringifyValue(Writer output, JsonValue value, int indentLevel) throws IOException
    {
        //If JSON value needs special formatting
        if (value.isNull())
        {
            output.write("null");
        }
        else if (value.isString())
        {
            //Format string with quote marks
            output.write("\"");
            output.write(value.asString());
            output.write("\"");
        }
        else if (value.isObject())
        {
            //Format object
            stringifyObject(output, value.asObject(), indentLevel);
        }
        else if (value.isList())
        {
            //Format list
            stringifyList(output, value.asList(), indentLevel);
        }
        else
        {
            //Otherwise use default toString method
            output.write(value.get().toString());
        }
    }

    /**
     * Parse a JSON file
     * @param filepath path to JSON document
     * @return a JSON value, null if parsing fails
     * @throws FileNotFoundException if the JSON file could not be found
     * @throws JsonParserException if the JSON file could not be parsed
     */
    public static JsonValue readFrom(String filepath) throws FileNotFoundException, JsonParserException
    {
        return readFrom(new FileReader(filepath));
    }

    /**
     * Parse a JSON value from an abstract reader
     * @param reader reader containing a JSON document
     * @return a JSON value, null if parsing fails
     * @throws JsonParserException if the JSON file could not be parsed
     */
    public static JsonValue readFrom(Reader reader) throws JsonParserException
    {
        try
        {
            //Parse the root value
            return new JsonValue(parseValue(new Tokenizer(reader)));
        }
        //If there was a parser error
        catch (InputMismatchException e)
        {
            //rethrow exception
            throw new JsonParserException(e.getMessage());
        }
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
     * @throws IOException
     */
    public static void writeTo(Writer writer, JsonValue value) throws IOException
    {
        //Write root value to output
        stringifyValue(writer, value, 0);
        writer.write("\n");
        //Flush output to ensure it is written properly
        writer.flush();
    }
}