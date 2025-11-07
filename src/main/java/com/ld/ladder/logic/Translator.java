package com.ld.ladder.logic;

import com.ld.ladder.model.*;

import java.util.*;

public class Translator {
    public Map<String, Expr> translate(Diagram diagram) {
        Map<String, Expr> result = new LinkedHashMap<>();
        for (Rung rung : diagram.getRungs()) {
            Expr expr = buildSeries(rung.getElements());
            result.put(rung.getCoil().getVar(), expr);
        }
        return result;
    }

    private Expr buildSeries(List<Element> elems) {
        List<Expr> factors = new ArrayList<>();
        for (Element e : elems) {
            if (e instanceof Contact) {
                factors.add(new Var(((Contact) e).getVar()));
            } else if (e instanceof NegatedContact) {
                factors.add(new Not(new Var(((NegatedContact) e).getVar())));
            } else if (e instanceof Branch) {
                factors.add(buildBranch((Branch) e));
            } else if (e instanceof LeftPowerRail) {
                // ignore in expression
            } else if (e instanceof HorizontalLink || e instanceof VerticalLink || e instanceof EndVerticalLink) {
                // structural only
            } else {
                throw new RuntimeException("Unhandled element in translation: " + e);
            }
        }
        if (factors.isEmpty()) return new Var("TRUE"); // degenerate
        if (factors.size() == 1) return factors.get(0);
        return new And(factors);
    }

    private Expr buildBranch(Branch b) {
        List<Expr> alts = new ArrayList<>();
        for (List<Element> altSeq : b.getAlternatives()) {
            List<Expr> seqFactors = new ArrayList<>();
            for (Element e : altSeq) {
                if (e instanceof Contact) seqFactors.add(new Var(((Contact) e).getVar()));
                else if (e instanceof NegatedContact) seqFactors.add(new Not(new Var(((NegatedContact) e).getVar())));
                else throw new RuntimeException("Branch can only contain contacts: " + e);
            }
            Expr altExpr = seqFactors.size()==1? seqFactors.get(0): new And(seqFactors);
            alts.add(altExpr);
        }
        if (alts.size()==1) return alts.get(0);
        return new Or(alts);
    }
}
