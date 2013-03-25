package edu.lmu.cs.xlg.koan.entities;

import edu.lmu.cs.xlg.util.Log;

/**
 * A Koan numeric literal expression.
 */
public class Number extends Literal {

    public Number(String lexeme) {
        super(lexeme);
    }

    @Override
    public void analyze(Log log, SymbolTable table, Function function, boolean inLoop) {
        // TODO - Nothing for now, but we might want later to check for the literal being
        // too large.  Something to think about.
    }
}
