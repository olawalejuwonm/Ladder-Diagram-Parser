package com.ld.ladder.model;

import java.util.List;

// Represents a vertical branch group of alternatives.
public class Branch implements Element {
    private final List<List<Element>> alternatives; // each alternative is a sequence of contacts/negated contacts
    public Branch(List<List<Element>> alternatives) { this.alternatives = alternatives; }
    public List<List<Element>> getAlternatives() { return alternatives; }
    @Override public String toString() { return "Branch" + alternatives; }
}
