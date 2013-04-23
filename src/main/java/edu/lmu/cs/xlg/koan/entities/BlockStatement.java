package edu.lmu.cs.xlg.koan.entities;


import edu.lmu.cs.xlg.util.Log;
import java.util.ArrayList;
import java.util.List;

import edu.lmu.cs.xlg.util.Log;

public class BlockStatement extends Statement {
	
    private List<Statement> statements;
    
    public BlockStatement(List<Statement> statements) {
        this.statements = statements;
    }
    
    public List<Statement> getStatements() {
        return statements;
    }
    
    
	@Override
	public void analyze(Log log, SymbolTable table, Function function,
			boolean inLoop) {
		// TODO Auto-generated method stub
		
	}

	
}
