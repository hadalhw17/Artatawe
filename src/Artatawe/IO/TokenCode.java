package Artatawe.IO;

/**
 *  @author Tom Street
 *
 *  Token code
 */
enum TokenCode
{
    UNKNOWN("unknown"),    //Unknown token

    //Values
    STRING_LITERAL("string"),
    INTEGER_CONSTANT("integer"),
    FLOAT_CONSTANT("float"),
    BOOLEAN("boolean"),
    NULL("null"),

    //Symbols
    COLON(":"),      //:
    COMMA(","),      //,
    LBRACE("{"),     //{
    RBRACE("}"),     //}
    LSQUARE("["),    //[
    RSQUARE("]"),    //]

    EOF("EOF"); //end of file

    private String debugStr;

    /**
     * TokenCode constructor
     * @param str debug string describing token type
     */
    TokenCode(String str)
    {
        this.debugStr = str;
    }

    /**
     * Get token type debug string
     * @return token type as string
     */
    @Override
    public String toString()
    {
        return this.debugStr;
    }
}
