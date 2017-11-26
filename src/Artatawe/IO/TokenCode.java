package Artatawe.IO;

/**
 *  @author Tom Street
 *
 *  Token code
 */
enum TokenCode
{
    UNKNOWN,    //Unknown token

    //Values
    STRING_LITERAL,
    INTEGER_CONSTANT,
    FLOAT_CONSTANT,
    BOOLEAN,
    NULL,

    //Symbols
    COLON,      //:
    COMMA,      //,
    LBRACE,     //{
    RBRACE,     //}
    LSQUARE,    //[
    RSQUARE,    //]

    EOF         //end of file
}
