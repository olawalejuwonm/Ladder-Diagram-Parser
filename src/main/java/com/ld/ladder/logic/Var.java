package com.ld.ladder.logic;

public class Var implements Expr {
    private final String name;
    public Var(String name) { this.name = name; }
    public String getName() { return name; }
    @Override public int precedence() { return 3; }
    @Override public String toPrettyString() { return name; }
    @Override public String toAsciiString() { return name; }
}
