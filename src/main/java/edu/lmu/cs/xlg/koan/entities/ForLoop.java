package edu.lmu.cs.xlg.koan.entities;

import edu.lmu.cs.xlg.util.Log;

public class ForLoop extends LoopStatement {

    private Expression iterator;
    private Expression function;
    private Statement statement;
    private String blockParam;

    public ForLoop(Expression iterator, Expression function, Statement statement, String blockParam) {
        super("FORLOOP");
        this.iterator = iterator;
        this.function = function;
        this.statement = statement;
        this.blockParam = blockParam;
    }

    public Expression getIterator() {
        return iterator;
    }

    public Expression getFunction() {
        return function;
    }

    public Statement getStatement() {
        return statement;
    }

    public String getBlockParam() {
        return blockParam;
    }

    @Override
    public void analyze(Log log, SymbolTable table, Function function, boolean inLoop) {
        // TODO Auto-generated method stub

    }

}
