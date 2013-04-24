package edu.lmu.cs.xlg.koan.entities;

/**
 * A Koan assignment statement.
 */
public class SwapStatement extends AssignmentStatement {

    private VariableReference left;
    private VariableReference right;

    public SwapStatement(VariableReference left, VariableReference right) {
        super(left, right);
    }

    public VariableReference getLeft() {
        return left;
    }

    public VariableReference getRight() {
        return right;
    }

    /**
     * Analyzes the statement, ensuring that the target variable is writable and the right hand side
     * is type compatible with the type of the variable.
     */
//    @Override
//    public void analyze(Log log, SymbolTable table, Function function, boolean inLoop) {
//        left.analyze(log, table, function, inLoop);
//        right.analyze(log, table, function, inLoop);
//        left.assertWritable(log);
//        right.assertAssignableTo(left.type, log, "assignment.type.error");
//    }
}