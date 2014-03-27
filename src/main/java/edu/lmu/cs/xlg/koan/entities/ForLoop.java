package edu.lmu.cs.xlg.koan.entities;

import edu.lmu.cs.xlg.util.Log;

public class ForLoop extends LoopStatement {

    private Expression arrayIterator;
    private BinaryExpression binaryIterator;
    private Function function;
    private Statement statement;
    private String iteratorVariable;

    public ForLoop(Expression arrayIterator, BinaryExpression binaryIterator, Function function, Statement statement, String iteratorVariable) {
        super("FORLOOP");
        this.arrayIterator = arrayIterator;
        this.binaryIterator = binaryIterator;
        this.function = function;
        this.statement = statement;
        this.iteratorVariable = iteratorVariable;
    }

    public BinaryExpression getBinaryIterator() {
        return binaryIterator;
    }

    public Expression getArrayIterator() {
        return arrayIterator;
    }

    public Function getFunction() {
        return function;
    }

    public Statement getStatement() {
        return statement;
    }

    public String getIteratorVariable() {
        return iteratorVariable;
    }

    @Override
    public void analyze(Log log, SymbolTable table, Function function, boolean inLoop) {
        // TODO Auto-generated method stub

    }

}
