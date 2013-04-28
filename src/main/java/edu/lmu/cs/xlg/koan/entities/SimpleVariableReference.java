package edu.lmu.cs.xlg.koan.entities;

import edu.lmu.cs.xlg.util.Log;

public class SimpleVariableReference extends VariableReference {

	    private String name;
	    private Variable referent;

	    public SimpleVariableReference(String name) {
	        this.name = name;
	    }

	    public String getName() {
	        return name;
	    }

	    public Variable getReferent() {
	        return referent;
	    }


		@Override
		public void analyze(Log log, SymbolTable table, Function function,
				boolean inLoop) {
			// TODO Auto-generated method stub
			
		}

	    /**
//	     * Returns true, because simple variables are always writable in Carlos.
//	     */
//	    public boolean isWritable() {
//	       return true;
//	    }
	}

