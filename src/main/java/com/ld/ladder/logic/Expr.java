package com.ld.ladder.logic;

public interface Expr {
    int precedence();
    String toPrettyString(); // with symbols
    String toAsciiString();  // with ASCII operators
}
