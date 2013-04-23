package edu.lmu.cs.xlg.koan.entities;

import java.util.List;

import edu.lmu.cs.xlg.util.Log;

public class FunctionCallStatement extends Statement {

    private FunctionCallExpression f;

    public FunctionCallStatement(FunctionCallExpression f) {
		this.f = f;
	}

	public FunctionCallExpression getF() {
		return f;
	}

	public void setF(FunctionCallExpression f) {
		this.f = f;
	}

	@Override
    public void analyze(Log log, SymbolTable table, Function function, boolean inLoop) {
        // TODO Auto-generated method stub

    }
}
