package edu.lmu.cs.xlg.koan.entities;

import edu.lmu.cs.xlg.koan.entities.Function;
import edu.lmu.cs.xlg.koan.entities.SymbolTable;
import edu.lmu.cs.xlg.util.Log;


public class SubscriptedVariable extends VariableReference {

    private VariableReference sequence;
    private Expression index;

    public SubscriptedVariable(VariableReference v, Expression e) {
        this.sequence = v;
        this.index = e;
    }

    public VariableReference getSequence() {
        return sequence;
    }

    public Expression getIndex() {
        return index;
    }
	@Override
    public void analyze(Log log, SymbolTable table, Function function, boolean inLoop) {
//		this.type = Type.BOOL;
	}
    

//    public boolean isWritable() {
//        // It's writable if an array, but not writable if it is a string
//        return sequence.type.isArray();
//    }
}
