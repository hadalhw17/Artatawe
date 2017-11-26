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
}
