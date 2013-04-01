package edu.lmu.cs.xlg.koan.entities;

import edu.lmu.cs.xlg.util.Log;

public class InfiniteLoop extends LoopStatement{

	private Block body;
	
	public InfiniteLoop(Block body) {
		super("INFINITELOOP");
		this.body = body;
	}

	@Override
	public void analyze(Log log, SymbolTable table, Function function,
			boolean inLoop) {
		// TODO Auto-generated method stub
		
	}
	
}
