import java.util.HashMap;
import java.util.Iterator;

public class Lexer implements Iterator<Token>, Iterable<Token> {
    private final String input;
    private int position;
    private int readPosition = 0;
    private char ch;

    public Lexer(String input) {
        this.input = input;
        this.readChar();
    }

    private void readChar() {
        if (this.readPosition >= this.input.length()) {
            this.ch = 0;
        } else {
            this.ch = input.charAt(this.readPosition);
        }

        this.position = this.readPosition;
        ++this.readPosition;
    }

    private String readIdentifier() {
        int beginning = this.position;

        while (Character.isLetter(this.ch)) {
            this.readChar();
        }

        return this.input.substring(beginning, this.position);
    }

    private String readNumber() {
        int beginning = this.position;

        while (Character.isDigit(this.ch)) {
            this.readChar();
        }
        return input.substring(beginning, this.position);
    }

    private Token detectKeyword(String identifier) {
        var keywords = new HashMap<String, TokenType>();

        // Keyword to token mapping
        keywords.put("fn", TokenType.FUNCTION);
        keywords.put("let", TokenType.LET);

        return new Token(
                // If the identifier is not a keyword, it's token type is ident
                keywords.getOrDefault(identifier, TokenType.IDENT),
                identifier);
    }

    private void skipWhitespace() {
        while (this.ch == ' ' || this.ch == '\t' || this.ch == '\n' || this.ch == '\r') {
            this.readChar();
        }
    }

    @Override
    public boolean hasNext() {
        return this.position < this.input.length();
    }

    @Override
    public Token next() {
        this.skipWhitespace();

        Token token;
        // Simple tokens
        switch (this.ch) {
            case '=':
                token = new Token(TokenType.ASSIGN, "=");
                break;
            case ';':
                token = new Token(TokenType.SEMICOLON, ";");
                break;
            case '(':
                token = new Token(TokenType.LPAREN, "(");
                break;
            case ')':
                token = new Token(TokenType.RPAREN, ")");
                break;
            case '{':
                token = new Token(TokenType.LSQUIRLY, "{");
                break;
            case '}':
                token = new Token(TokenType.RSQUIRLY, "}");
                break;
            case ',':
                token = new Token(TokenType.COMMA, ",");
                break;
            case '+':
                token = new Token(TokenType.PLUS, "+");
                break;
            case 0:
                token = new Token(TokenType.EOF, "");
                break;
            default:
                token = null;
        }

        // Token with more elaborate logic
        if (token == null) {
            if (Character.isLetter(ch)){
                return detectKeyword(readIdentifier());
            } else if (Character.isDigit(ch)) {
                return new Token(TokenType.INT, readNumber());
            } else {
                token = new Token(TokenType.ILLEGAL, Character.toString(this.ch));
            }
        }

        readChar();
        return token;
    }

    @Override
    public Iterator<Token> iterator() {
        return this;
    }
}
