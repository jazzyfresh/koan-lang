package edu.lmu.cs.xlg.koan.entities;

import edu.lmu.cs.xlg.util.Log;

/**
 * A Koan print statement.
 */
public class PrintStatement extends Statement {

    private Expression expression;

    public PrintStatement(Expression expression) {
        this.expression = expression;
    }

    public Expression getExpressions() {
        return expression;
    }

    /**
     * Analyzes the statement.
     */
    @Override
    public void analyze(Log log, SymbolTable table, Function function, boolean inLoop) {
        expression.analyze(log, table, function, inLoop);
    }
}