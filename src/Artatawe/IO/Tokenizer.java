package Artatawe.IO;

import java.io.IOException;
import java.io.PushbackReader;
import java.io.Reader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.InputMismatchException;

/**
 *  @author Tom Street
 *
 *  Tokenizer helper class
 *
 *  Breaks down a stream of text into a sequence of tokens for parsing.
 */
class Tokenizer
{
    private Token nextToken;        // Tokenizer always stores one token ahead
    private PushbackReader reader;
    private int lineNumber;         // current line number

    /**
     * Construct a Tokenizer from an abstract Reader object
     * @param reader reader object
     */
    public Tokenizer(Reader reader)
    {
        this.reader = new PushbackReader(reader);
        this.nextToken = fetch();
        this.lineNumber = 1;
    }

    /**
     * Check if the next token is of a given type
     * @param code token type
     * @return true if the next token has that code
     */
    public boolean isNext(TokenCode code)
    {
        return nextToken.getCode() == code;
    }

    /**
     * Check if there are more tokens remaining
     * @return true if there are more tokens
     */
    public boolean hasNext()
    {
        return !isNext(TokenCode.EOF);
    }

    /**
     * Get the next token of a given type, throw an exception if the type does not match
     * @param code token type
     * @return the next token
     * @throws InputMismatchException if the next token is not the correct type
     */
    public Token next(TokenCode code) throws InputMismatchException
    {
        //Assert that the next token is the expected type
        if (nextToken.getCode() != code)
        {
            throw new InputMismatchException(String.format("Expected token %s got %s", code.toString(), nextToken.getCode().toString()));
        }

        //Return next token
        return next();
    }

    /**
     * Get the next token
     * @return the next token
     */
    public Token next()
    {
        //Next token is current token
        Token curToken = nextToken;
        //Increment next token
        nextToken = fetch();
        //Return current token
        return curToken;
    }

    /*
        Peek the next character without incrementing the read pointer
     */
    private char peekChar() throws IOException
    {
        int c = this.reader.read();
        this.reader.unread(c);

        if (c == -1)
        {
            throw new IOException("EOF");
        }

        return (char)c;
    }

    /*
        Get the next character and increment the read pointer
     */
    private char nextChar() throws IOException
    {
        int c = this.reader.read();

        if (c == -1)
        {
            throw new IOException("EOF");
        }

        return (char)c;
    }

    /*
        Fetch and return the next token in the stream
     */
    private Token fetch()
    {
        try
        {
            //Skip whitespace
            while (Character.isWhitespace(peekChar()))
            {
                char cur = nextChar();

                if (cur == '\n')
                {
                    lineNumber++;
                }
            }

            //Peek next character
            char cur = peekChar();

            //Expect number
            if (Character.isDigit(cur))
            {
                String text = "";
                TokenCode code = TokenCode.INTEGER_CONSTANT;

                //Matches integer sequence
                while (Character.isDigit(peekChar()))
                {
                    text += nextChar();
                }

                //Matches float sequence
                if (peekChar() == '.')
                {
                    code = TokenCode.FLOAT_CONSTANT;
                    text += nextChar();

                    while (Character.isDigit(peekChar()))
                    {
                        text += nextChar();
                    }
                }

                return new Token(code, text, lineNumber);
            }
            //Expect identifier
            else if (Character.isLetter(cur) || (cur == '_'))
            {
                String text = "";
                TokenCode code = TokenCode.UNKNOWN;

                //While characters match identifier
                while (
                        Character.isLetter(peekChar()) ||
                                Character.isDigit(peekChar()) ||
                                (peekChar() == '_'))
                {
                    text += nextChar();
                }

                //If identifier equals true or false
                if (text.equals("true") || text.equals("false"))
                {
                    code = TokenCode.BOOLEAN;
                }
                //If identifier equals null
                else if (text.equals("null"))
                {
                    code = TokenCode.NULL;
                }

                //Return identifier
                return new Token(code, text, lineNumber);
            }
            //Expect string literal
            else if (cur == '\"')
            {
                String text = "";

                //Skip first quote mark "
                nextChar();

                //Accumulate string content between quotes
                while (peekChar() != '\"')
                {
                    cur = nextChar();

                    text += cur;

                    //If current char marks an escape sequence
                    //And the next char is a quote then include nested string
                    if ((cur == '\\') && (peekChar() == '\"'))
                    {
                        text += nextChar();
                    }
                }

                //Skip last quote mark "
                nextChar();

                //Return string token
                return new Token(TokenCode.STRING_LITERAL, text, lineNumber);
            }
            else
            {
                cur = nextChar();

                String text = "" + cur;

                //Symbol table
                HashMap<Character, TokenCode> symbolMap = new HashMap<>();
                symbolMap.put(':', TokenCode.COLON);
                symbolMap.put(',', TokenCode.COMMA);
                symbolMap.put('[', TokenCode.LSQUARE);
                symbolMap.put(']', TokenCode.RSQUARE);
                symbolMap.put('{', TokenCode.LBRACE);
                symbolMap.put('}', TokenCode.RBRACE);

                //If current char exists in symbol table
                //Return corresponding code
                //Otherwise return unknown token
                TokenCode code = symbolMap.getOrDefault(cur, TokenCode.UNKNOWN);

                return new Token(code, text, lineNumber);
            }
        }
        //If end of stream is reached or there is an IO error then return EOF token
        catch (IOException e)
        {
            return new Token(TokenCode.EOF, "", lineNumber);
        }
    }
}
