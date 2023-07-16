import java.util.Objects;

public class Token {
    private final TokenType tokenType;
    private final String literal;

    public Token(TokenType tokenType, String literal) {
        this.tokenType = tokenType;
        this.literal = literal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Token token = (Token) o;

        if (tokenType != token.tokenType) return false;
        return Objects.equals(literal, token.literal);
    }

    @Override
    public int hashCode() {
        int result = tokenType != null ? tokenType.hashCode() : 0;
        result = 31 * result + (literal != null ? literal.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Token{" +
                "tokenType=" + tokenType +
                ", literal='" + literal + '\'' +
                '}';
    }
}
