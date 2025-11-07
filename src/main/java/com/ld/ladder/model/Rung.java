package com.ld.ladder.model;

import java.util.ArrayList;
import java.util.List;

public class Rung {
    private final List<Element> elements = new ArrayList<>();
    private Coil coil; // terminating coil
    public void add(Element e) { elements.add(e); }
    public List<Element> getElements() { return elements; }
    public void setCoil(Coil c) { this.coil = c; }
    public Coil getCoil() { return coil; }
}
