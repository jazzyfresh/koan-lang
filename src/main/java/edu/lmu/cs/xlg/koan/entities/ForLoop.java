package edu.lmu.cs.xlg.koan.entities;

import edu.lmu.cs.xlg.util.Log;

public class ForLoop extends LoopStatement {

    private Expression iterator;
    private Expression function;
    private Block body;
    private String blockParam;
    
	public ForLoop(Expression iterator, Expression function, Block body, String blockParam) {
		super("FORLOOP");
		this.iterator = iterator;
		this.function = function;
		this.body = body;
		this.blockParam = blockParam;
	}

	@Override
	public void analyze(Log log, SymbolTable table, Function function,
			boolean inLoop) {
		// TODO Auto-generated method stub
		
	}
    
}
