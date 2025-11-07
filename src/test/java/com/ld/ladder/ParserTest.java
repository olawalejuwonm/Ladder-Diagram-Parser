package com.ld.ladder;

import com.ld.ladder.logic.Translator;
import com.ld.ladder.parser.Lexer;
import com.ld.ladder.parser.Parser;
import com.ld.ladder.model.Diagram;
import com.ld.ladder.logic.Expr;
import org.junit.jupiter.api.Test;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {
    private Map<String, Expr> parse(String src) {
        Lexer lexer = new Lexer(src);
        Parser parser = new Parser(lexer.lex());
        Diagram d = parser.parseDiagram();
        return new Translator().translate(d);
    }

    @Test
    void simpleSeries() {
        Map<String, Expr> m = parse("LPR CONTACT A -- CONTACT B COIL Q");
        assertEquals(1, m.size());
        String pretty = m.get("Q").toPrettyString();
        assertEquals("A ∧ B", pretty);
    }

    @Test
    void branchWithNegated() {
        Map<String, Expr> m = parse("LPR CONTACT A -- ( CONTACT B | !CONTACT C ) COIL Q");
        String pretty = m.get("Q").toPrettyString();
        assertEquals("A ∧ (B ∨ ¬C)", pretty);
    }

    @Test
    void negatedContactSeries() {
        Map<String, Expr> m = parse("LPR !CONTACT A -- CONTACT B COIL X");
        String ascii = m.get("X").toAsciiString();
        assertEquals("!A & B", ascii);
    }
}
