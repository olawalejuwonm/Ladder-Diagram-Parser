package com.ld.ladder.logic;

public class Not implements Expr {
    private final Expr inner;
    public Not(Expr inner) { this.inner = inner; }
    @Override public int precedence() { return 2; }
    private String wrap(Expr e, boolean ascii) {
        String s = ascii ? e.toAsciiString() : e.toPrettyString();
        if (e.precedence() < precedence()) return "(" + s + ")"; else return s;
    }
    @Override public String toPrettyString() { return "¬" + wrap(inner, false); }
    @Override public String toAsciiString() { return "!" + wrap(inner, true); }
}
