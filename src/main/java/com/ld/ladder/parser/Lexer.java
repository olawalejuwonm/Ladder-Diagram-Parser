package com.ld.ladder.parser;

import java.util.ArrayList;
import java.util.List;

public class Lexer {
    private final String input;
    private int pos = 0;

    public Lexer(String input) { this.input = input; }

    private boolean isAtEnd() { return pos >= input.length(); }
    private char peek() { return isAtEnd() ? '\0' : input.charAt(pos); }
    private char advance() { return isAtEnd() ? '\0' : input.charAt(pos++); }

    private void skipWhitespace() {
        while (!isAtEnd() && Character.isWhitespace(peek())) advance();
    }

    public List<Token> lex() {
        List<Token> tokens = new ArrayList<>();
        while (!isAtEnd()) {
            skipWhitespace();
            if (isAtEnd()) break;
            char c = peek();
            if (c == '(') { advance(); tokens.add(new Token(TokenType.LPAREN, "(")); continue; }
            if (c == ')') { advance(); tokens.add(new Token(TokenType.RPAREN, ")")); continue; }
            if (c == '|') { advance(); tokens.add(new Token(TokenType.PIPE, "|")); continue; }
            if (c == '!') { advance(); tokens.add(new Token(TokenType.BANG, "!")); continue; }
            if (c == '-') {
                // expect second '-'
                advance();
                if (peek() == '-') { advance(); tokens.add(new Token(TokenType.HL, "--")); continue; }
                throw new RuntimeException("Single '-' unexpected; use '--' for horizontal link");
            }
            if (Character.isLetter(c)) {
                String ident = readIdent();
                switch (ident) {
                    case "LPR": tokens.add(new Token(TokenType.LPR, ident)); break;
                    case "CONTACT": tokens.add(new Token(TokenType.CONTACT, ident)); break;
                    case "NCONTACT": tokens.add(new Token(TokenType.NCONTACT, ident)); break;
                    case "COIL": tokens.add(new Token(TokenType.COIL, ident)); break;
                    default: tokens.add(new Token(TokenType.IDENT, ident)); break;
                }
                continue;
            }
            throw new RuntimeException("Unexpected character: " + c);
        }
        tokens.add(new Token(TokenType.EOF, null));
        return tokens;
    }

    private String readIdent() {
        StringBuilder sb = new StringBuilder();
        while (!isAtEnd() && (Character.isLetterOrDigit(peek()) || peek()=='_')) {
            sb.append(advance());
        }
        return sb.toString();
    }
}
