public enum TokenType {
    ILLEGAL,
    EOF,

    // Identifiers and literals
    IDENT,
    INT,

    // Operators
    ASSIGN,
    PLUS,

    // Delimiters
    COMMA,
    SEMICOLON,

    LPAREN,
    RPAREN,
    LSQUIRLY,
    RSQUIRLY,

    // Keywords
    FUNCTION,
    LET
}
