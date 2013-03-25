package edu.lmu.cs.xlg.koan.entities;

import edu.lmu.cs.xlg.util.Log;
/** 
 * A list of the two boolean litearls, T and F
 */

public class BooleanLiteral extends Literal {
	
	public static final BooleanLiteral T = new BooleanLiteral("T");
	public static final BooleanLiteral F = new BooleanLiteral("F");
	
	private BooleanLiteral(String lexeme) {
		super(lexeme);
	}
	
	//NB 3/20/13 analyze method is currently the huge error in our project
	@Override
    public void analyze(Log log, SymbolTable table, Function function, boolean inLoop) {
		this.type = Type.BOOL;
	}
}
