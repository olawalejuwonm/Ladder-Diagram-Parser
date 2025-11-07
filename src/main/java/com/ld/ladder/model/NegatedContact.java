package com.ld.ladder.model;

public class NegatedContact extends Contact {
    public NegatedContact(String var) { super(var); }
    @Override public String toString() { return "NCONTACT(" + getVar() + ")"; }
}
