package com.ld.ladder;

import com.ld.ladder.logic.Translator;
import com.ld.ladder.parser.Lexer;
import com.ld.ladder.parser.Parser;
import com.ld.ladder.model.Diagram;
import com.ld.ladder.logic.Expr;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Map;

public class TranslatorTest {
    private String pretty(String src) {
        Lexer lexer = new Lexer(src);
        Parser parser = new Parser(lexer.lex());
        Diagram d = parser.parseDiagram();
        Map<String, Expr> m = new Translator().translate(d);
        return m.values().iterator().next().toPrettyString();
    }

    @Test
    void singleBranchAlternative() {
        String p = pretty("LPR ( CONTACT A ) COIL R");
        assertEquals("A", p);
    }

    @Test
    void multiBranchAltAndInside() {
        String p = pretty("LPR ( CONTACT A -- CONTACT B | !CONTACT C ) COIL R");
        assertEquals("(A ∧ B) ∨ ¬C", p);
    }
}
