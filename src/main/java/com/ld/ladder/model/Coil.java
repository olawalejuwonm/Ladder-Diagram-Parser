package com.ld.ladder.model;

public class Coil implements Element {
    private final String var;
    public Coil(String var) { this.var = var; }
    public String getVar() { return var; }
    @Override public String toString() { return "COIL(" + var + ")"; }
}
