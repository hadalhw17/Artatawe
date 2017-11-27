package Artatawe.IO;

import java.util.InputMismatchException;

/**
 * @author Tom Street
 *
 * A JSON Parser Exception is thrown when there is an error during the parsing of JSON data
 *
 * @see Artatawe.IO.JsonParser
 */
public class JsonParserException extends InputMismatchException
{
    /**
     * Constructs a JSON Parser Exception
     */
    public JsonParserException()
    {
        super();
    }

    /**
     * Constructs a JSON Parser Exception and assigns a message
     * @param msg exception message
     */
    public JsonParserException(String msg)
    {
        super(msg);
    }
}
