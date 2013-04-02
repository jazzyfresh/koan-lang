package edu.lmu.cs.xlg.koan.entities;

import edu.lmu.cs.xlg.util.Log;

/**
 * Abstract superclass for all literals.
 */

// TODO: MAKE THIS ABSTRACT AND CREATE ALL THE SUBCLASSES
public class Literal extends Expression {
    private String lexeme;

    /**
     * Creates a literal, given its lexeme.
     */
     public Literal(String lexeme) {
         this.lexeme = lexeme;
     }

     public String getLexeme() {
         return lexeme;
     }

    @Override
    public void analyze(Log log, SymbolTable table, Function function, boolean inLoop) {
        // TODO Auto-generated method stub

    }


}
