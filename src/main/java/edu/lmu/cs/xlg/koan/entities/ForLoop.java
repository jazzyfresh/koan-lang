package edu.lmu.cs.xlg.koan.entities;

import edu.lmu.cs.xlg.util.Log;

public class ForLoop extends LoopStatement {

    private Expression iterator;
    private AnonymousFunction anonymousFunction;
    private Statement statement;
    private String iteratorVariable;

    public ForLoop(Expression iterator, AnonymousFunction anonymousFunction, Statement statement, String iteratorVariable) {
        super("FORLOOP");
        this.iterator = iterator;
        this.anonymousFunction = anonymousFunction;
        this.statement = statement;
        this.iteratorVariable = iteratorVariable;
    }

    public Expression getIterator() {
        return iterator;
    }

    public AnonymousFunction getAnonymousFunction() {
        return anonymousFunction;
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
