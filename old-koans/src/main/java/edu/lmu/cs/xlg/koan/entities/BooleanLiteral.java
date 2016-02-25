package edu.lmu.cs.xlg.koan.entities;

import edu.lmu.cs.xlg.util.Log;
/** 
 * A list of the two boolean literals, T and F
 */

public class BooleanLiteral extends Literal {
	
	public static final BooleanLiteral T = new BooleanLiteral("T");
	public static final BooleanLiteral F = new BooleanLiteral("F");
	
	private BooleanLiteral(String lexeme) {
		super(lexeme);
		this.type = Type.BOOLEAN;
	}
	

	@Override
    public void analyze(Log log, SymbolTable table, Function function, boolean inLoop) {
//		this.type = Type.BOOL;
	}
}
