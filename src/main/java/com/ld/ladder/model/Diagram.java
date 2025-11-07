package com.ld.ladder.model;

import java.util.ArrayList;
import java.util.List;

public class Diagram {
    private final List<Rung> rungs = new ArrayList<>();
    public void addRung(Rung r) { rungs.add(r); }
    public List<Rung> getRungs() { return rungs; }
}
