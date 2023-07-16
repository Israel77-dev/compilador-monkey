import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;


public class LexerTest {
    @Test
    public void givenInputString_whenTokenizing_thenReturnTokens() {
        String input = "=+(){},;";

        Lexer lexer = new Lexer(input);

        for (Token token : Arrays.asList(new Token(TokenType.ASSIGN, "="),
                new Token(TokenType.PLUS, "+"),
                new Token(TokenType.LPAREN, "("),
                new Token(TokenType.RPAREN, ")"),
                new Token(TokenType.LSQUIRLY, "{"),
                new Token(TokenType.RSQUIRLY, "}"),
                new Token(TokenType.COMMA, ","),
                new Token(TokenType.SEMICOLON, ";"),
                new Token(TokenType.EOF, ""))) {
            Assert.assertEquals(token, lexer.next());
        }
    }

    @Test
    public void givenLanguageExpr_whenTokenizing_thenReturnTokens(){
        String input = "let five = 5;\n" +
                       "let ten = 10;\n" +
                       "\n" +
                       "let add = fn(x, y) {\n" +
                       "    x + y;\n" +
                       "}\n" +
                       "\n" +
                       "let result = add(five, ten);\n";

        Lexer lexer = new Lexer(input);

        for (var token : Arrays.asList(
                new Token(TokenType.LET, "let"),
                new Token(TokenType.IDENT, "five"),
                new Token(TokenType.ASSIGN, "="),
                new Token(TokenType.INT, "5"),
                new Token(TokenType.SEMICOLON, ";"),

                new Token(TokenType.LET, "let"),
                new Token(TokenType.IDENT, "ten"),
                new Token(TokenType.ASSIGN, "="),
                new Token(TokenType.INT, "10"),
                new Token(TokenType.SEMICOLON, ";"),

                new Token(TokenType.LET, "let"),
                new Token(TokenType.IDENT, "add"),
                new Token(TokenType.ASSIGN, "="),
                new Token(TokenType.FUNCTION, "fn"),
                new Token(TokenType.LPAREN, "("),
                new Token(TokenType.IDENT, "x"),
                new Token(TokenType.COMMA, ","),
                new Token(TokenType.IDENT, "y"),
                new Token(TokenType.RPAREN, ")"),
                new Token(TokenType.LSQUIRLY, "{"),

                new Token(TokenType.IDENT, "x"),
                new Token(TokenType.PLUS, "+"),
                new Token(TokenType.IDENT, "y"),
                new Token(TokenType.SEMICOLON, ";"),

                new Token(TokenType.RSQUIRLY, "}"),

                new Token(TokenType.LET, "let"),
                new Token(TokenType.IDENT, "result"),
                new Token(TokenType.ASSIGN, "="),
                new Token(TokenType.IDENT, "add"),
                new Token(TokenType.LPAREN, "("),
                new Token(TokenType.IDENT, "five"),
                new Token(TokenType.COMMA, ","),
                new Token(TokenType.IDENT, "ten"),
                new Token(TokenType.RPAREN, ")"),
                new Token(TokenType.SEMICOLON, ";"),

                new Token(TokenType.EOF, "")
        )) {
            Assert.assertEquals(token, lexer.next());
        }
    }
}
