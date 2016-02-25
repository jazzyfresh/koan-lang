package edu.lmu.cs.xlg.koan.entities;

import edu.lmu.cs.xlg.util.Log;

/**
 * A Roflkode statement.
 */
public abstract class Statement extends Entity {

    /**
     * Performs semantic analysis on this statement.
     *
     * @param log the log to use
     * @param table the current symbol table
     * @param function the innermost enclosing function containing this statement
     * @param loop whether or not this statement is in a loop
     */
    public abstract void analyze(Log log, SymbolTable table, Function function, boolean inLoop);
}