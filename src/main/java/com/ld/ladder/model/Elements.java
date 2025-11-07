package com.ld.ladder.model;

import java.util.ArrayList;
import java.util.List;

public final class Elements {

    private Elements() {}

    // Marker interface
    public interface Element { }

    public static class Contact implements Element {
        private final String var;
        public Contact(String var) { this.var = var; }
        public String getVar() { return var; }
        @Override public String toString() { return "CONTACT(" + var + ")"; }
    }

    public static class NegatedContact extends Contact {
        public NegatedContact(String var) { super(var); }
        @Override public String toString() { return "NCONTACT(" + getVar() + ")"; }
    }

    public static class Coil implements Element {
        private final String var;
        public Coil(String var) { this.var = var; }
        public String getVar() { return var; }
        @Override public String toString() { return "COIL(" + var + ")"; }
    }

    public static class HorizontalLink implements Element {
        @Override public String toString() { return "--"; }
    }

    public static class VerticalLink implements Element {
        @Override public String toString() { return "("; }
    }

    public static class EndVerticalLink implements Element {
        @Override public String toString() { return ")"; }
    }

    public static class LeftPowerRail implements Element {
        @Override public String toString() { return "LPR"; }
    }

    // Branch: list of alternative sequences
    public static class Branch implements Element {
        private final List<List<Element>> alternatives;
        public Branch(List<List<Element>> alternatives) { this.alternatives = alternatives; }
        public List<List<Element>> getAlternatives() { return alternatives; }
        @Override public String toString() { return "Branch" + alternatives; }
    }

    public static class Rung {
        private final List<Element> elements = new ArrayList<>();
        private Coil coil;
        public void add(Element e) { elements.add(e); }
        public List<Element> getElements() { return elements; }
        public void setCoil(Coil c) { this.coil = c; }
        public Coil getCoil() { return coil; }
    }

    public static class Diagram {
        private final List<Rung> rungs = new ArrayList<>();
        public void addRung(Rung r) { rungs.add(r); }
        public List<Rung> getRungs() { return rungs; }
    }
}
