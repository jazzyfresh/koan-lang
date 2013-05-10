package edu.lmu.cs.xlg.koan.entities;

import edu.lmu.cs.xlg.util.Log;

public class NullLiteral extends Literal {

    public static NullLiteral INSTANCE = new NullLiteral();

    // Constructor is private because this class is a singleton.
    private NullLiteral() {
        super("null");
    }
	
	@Override
	public void analyze(Log log, SymbolTable table, Function function,
			boolean inLoop) {
		// TODO Auto-generated method stub

	}

}
