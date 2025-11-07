package com.ld.ladder.model;

public class Contact implements Element {
    private final String var;

    public Contact(String var) { this.var = var; }
    public String getVar() { return var; }
    @Override public String toString() { return "CONTACT(" + var + ")"; }
}
