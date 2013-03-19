package edu.lmu.cs.xlg.koan.entities;

import edu.lmu.cs.xlg.util.Log;

/**
 * Superclass for all variable expressions.  There are several kinds of variable expressions:
 * simple variable references (a single id), subscripted variable expressions for arrays and
 * strings (v!?e?!), bukkit variable expressions (v!!!p), and function call results.
 */
public abstract class VariableReference extends Expression {

    /**
     * Returns whether or not this variable expression references a non read-only variable.
     */
    public abstract boolean isWritable();

    /**
     * Logs an error if the variable expression references a read-only variable.
     */
    public void assertWritable(Log log) {
        if (!isWritable()) {
            log.error("read.only.error");
        }
    }
}