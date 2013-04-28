package edu.lmu.cs.xlg.koan.entities;

import java.util.List;

import edu.lmu.cs.xlg.util.Log;

public class AnonymousFunction extends Expression {

    private List<Variable> parameters;
    private Block body;
	
	public AnonymousFunction(List<Variable> parameters, Block body) {
		this.parameters = parameters;
		this.body = body;
	}



	public List<Variable> getParameters() {
		return parameters;
	}



	public void setParameters(List<Variable> parameters) {
		this.parameters = parameters;
	}



	public Block getBody() {
		return body;
	}



	public void setBody(Block body) {
		this.body = body;
	}



	@Override
	public void analyze(Log log, SymbolTable table, Function function,
			boolean inLoop) {
		// TODO Auto-generated method stub

	}

}
