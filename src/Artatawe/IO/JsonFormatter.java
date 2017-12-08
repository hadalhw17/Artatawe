package Artatawe.IO;

/**
 * @author Tom Street
 *
 * JSON formatter interface
 *
 * Classes that implement this must be able to convert a class E from/to JSON
 */
public interface JsonFormatter<E>
{
    /**
     * Creates an object of type E from a given JSON object
     * @param json a JSON data type
     * @return an object of type E
     */
    E load(JsonValue json);

    /**
     * Creates a JSON object from an object of type E
     * @param object an object
     * @return a JSON object
     */
    JsonValue store(E object);
}
