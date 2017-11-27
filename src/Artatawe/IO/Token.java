package Artatawe.IO;

/**
 *  @author Tom Street
 *
 *  Token data class
 */
class Token
{
    private TokenCode code;
    private String data;
    private int lineNumber;

    /**
     * Construct a token
     * @param code token type code
     * @param data token data
     * @param lineNumber token line number (for debugging)
     */
    public Token(TokenCode code, String data, int lineNumber)
    {
        this.code = code;
        this.data = data;
        this.lineNumber = lineNumber;
    }

    /**
     * Get the token code
     * @return token code
     */
    TokenCode getCode() { return code; }

    /**
     * Get the token data
     * @return token data
     */
    String getData() { return data; }

    /**
     * Get the token line number
     * @return the line number
     */
    int getLine() { return this.lineNumber; }

    /**
     * Helper method for formatting error messages for tokens.
     * @see Token#formatError(String, Token)
     */
    public static String formatError(TokenCode expected, Token unexpected)
    {
        return formatError(expected.toString(), unexpected);
    }

    /**
     * Helper method for formatting error messages for tokens.
     * @param expected expected token type
     * @param unexpected actual token
     * @return a formatted error message as a string
     */
    public static String formatError(String expected, Token unexpected)
    {
        return String.format(
                "Unexpected token %s\nexpected %s\nline: %d\ndata: \"%s\"\n",
                unexpected.getCode().toString(),
                expected,
                unexpected.getLine(),
                unexpected.getData()
        );
    }
}
