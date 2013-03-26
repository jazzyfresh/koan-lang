package edu.lmu.cs.xlg.koan.entities;

import edu.lmu.cs.xlg.util.Log;

/**
 * Superclass for all variable expressions.  There are several kinds of variable expressions:
 * simple variable references (a single id), subscripted variable expressions for arrays and
 * strings (v!?e?!), bukkit variable expressions (v!!!p), and function call results.
 */
//Perhaps this should be abstract in a working parser?
public  class VariableReference extends Expression {

    /**
     * Returns whether or not this variable expression references a non read-only variable.
     */
	//again, abstract?
    public  boolean isWritable() {
		return false;
	}

    /**
     * Logs an error if the variable expression references a read-only variable.
     */
    public void assertWritable(Log log) {
        if (!isWritable()) {
            log.error("read.only.error");
        }
    }

	@Override
	public void analyze(Log log, SymbolTable table, Function function,
			boolean inLoop) {
		// TODO Auto-generated method stub
		
	}
}