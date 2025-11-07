package com.ld.ladder.parser;

import com.ld.ladder.model.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Parser {
    private final List<Token> tokens;
    private final Iterator<Token> iter;
    private Token current;

    public Parser(List<Token> tokens) {
        this.tokens = tokens;
        this.iter = tokens.iterator();
        advance();
    }

    private void advance() { current = iter.hasNext() ? iter.next() : new Token(TokenType.EOF, null); }
    private boolean check(TokenType type) { return current.type == type; }
    private Token consume(TokenType type, String msg) {
        if (!check(type)) throw new RuntimeException(msg + " Found: " + current);
        Token t = current; advance(); return t;
    }

    public Diagram parseDiagram() {
        Diagram diagram = new Diagram();
        while (!check(TokenType.EOF)) {
            diagram.addRung(parseRung());
        }
        return diagram;
    }

    private Rung parseRung() {
        Rung rung = new Rung();
        consume(TokenType.LPR, "Rung must start with LPR");
        rung.add(new LeftPowerRail());
        // parse sequence until COIL
        List<Element> sequence = parseSequenceUntilCoil();
        for (Element e : sequence) rung.add(e);
        consume(TokenType.COIL, "Expected COIL keyword terminating rung");
        String coilVar = consume(TokenType.IDENT, "Expected coil identifier").text;
        rung.setCoil(new Coil(coilVar));
        return rung;
    }

    private List<Element> parseSequenceUntilCoil() {
        List<Element> elems = new ArrayList<>();
        boolean expectElement = true;
        while (!check(TokenType.COIL)) {
            if (check(TokenType.HL)) { // skip explicit horizontal link token; semantics handled by translator
                advance();
                expectElement = true;
                continue;
            }
            if (check(TokenType.LPAREN)) {
                elems.add(parseBranch());
                expectElement = false;
                continue;
            }
            if (check(TokenType.CONTACT) || check(TokenType.NCONTACT) || check(TokenType.BANG)) {
                elems.add(parseContactLike());
                expectElement = false;
                continue;
            }
            if (check(TokenType.EOF)) throw new RuntimeException("Unexpected EOF before COIL");
            throw new RuntimeException("Unexpected token in sequence: " + current);
        }
        return elems;
    }

    private Element parseContactLike() {
        if (check(TokenType.CONTACT)) {
            advance();
            String var = consume(TokenType.IDENT, "CONTACT requires identifier").text;
            return new Contact(var);
        }
        if (check(TokenType.NCONTACT)) {
            advance();
            String var = consume(TokenType.IDENT, "NCONTACT requires identifier").text;
            return new NegatedContact(var);
        }
        if (check(TokenType.BANG)) { // !CONTACT X form
            advance();
            consume(TokenType.CONTACT, "Expect CONTACT after '!'");
            String var = consume(TokenType.IDENT, "CONTACT requires identifier").text;
            return new NegatedContact(var);
        }
        throw new RuntimeException("Not a contact token: " + current);
    }

    private Branch parseBranch() {
        consume(TokenType.LPAREN, "Branch must start with '('");
        List<List<Element>> alts = new ArrayList<>();
        alts.add(parseBranchAlternative());
        while (check(TokenType.PIPE)) {
            advance();
            alts.add(parseBranchAlternative());
        }
        consume(TokenType.RPAREN, "Branch must end with ')'");
        return new Branch(alts);
    }

    private List<Element> parseBranchAlternative() {
        List<Element> seq = new ArrayList<>();
        // simple contacts possibly separated by '--'
        seq.add(parseContactLike());
        while (check(TokenType.HL)) {
            advance();
            seq.add(parseContactLike());
        }
        return seq;
    }
}
