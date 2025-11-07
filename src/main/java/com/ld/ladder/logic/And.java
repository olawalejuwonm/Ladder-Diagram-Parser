package com.ld.ladder.logic;

import java.util.List;
import java.util.stream.Collectors;

public class And implements Expr {
    private final List<Expr> parts;
    public And(List<Expr> parts) { this.parts = parts; }
    @Override public int precedence() { return 1; }
    private String join(boolean ascii) {
        return parts.stream().map(e -> {
            String s = ascii ? e.toAsciiString() : e.toPrettyString();
            if (e.precedence() < precedence()) s = "(" + s + ")";
            return s;
        }).collect(Collectors.joining(ascii ? " & " : " ∧ "));
    }
    @Override public String toPrettyString() { return join(false); }
    @Override public String toAsciiString() { return join(true); }
}
