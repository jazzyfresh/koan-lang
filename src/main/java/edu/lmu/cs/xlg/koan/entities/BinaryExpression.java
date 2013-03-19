package edu.lmu.cs.xlg.koan.entities;

import edu.lmu.cs.xlg.util.Log;

/**
 * A Roflkode expression made up of a binary operator and two operands.
 */
public class BinaryExpression extends Expression {

    private String op;
    private Expression left;
    private Expression right;

    /**
     * Creates a binary expression for a given operator and operands.
     */
    public BinaryExpression(Expression left, String op, Expression right) {
        this.left = left;
        this.op = op;
        this.right = right;
    }

    /**
     * Returns the left operand.
     */
    public Expression getLeft() {
        return left;
    }

    /**
     * Returns the operator as a string.
     */
    public String getOp() {
        return op;
    }

    /**
     * Returns the right operand.
     */
    public Expression getRight() {
        return right;
    }

    /**
     * Analyzes the expression.
     */
    @Override
    public void analyze(Log log, SymbolTable table, Function function, boolean inLoop) {
        left.analyze(log, table, function, inLoop);
        right.analyze(log, table, function, inLoop);

        // num op num (for arithmetic op)
        if (op.matches("[-+*/]")) {
            left.assertArithmetic(op, log);
            right.assertArithmetic(op, log);
            type = (left.type == Type.NUMBR || right.type == Type.NUMBR)
                ? Type.NUMBR : Type.INT;

        // int op int returning int (for shifts and mod)
        } else if (op.matches("%|<<|>>")) {
            left.assertInteger(op, log);
            right.assertInteger(op, log);
            type = Type.INT;

        // int DIVIDZ int
        } else if (op.matches("\\\\")) {
            left.assertInteger(op, log);
            right.assertInteger(op, log);
            type = Type.B00L;

        // int bit operator int
        } else if (op.matches("BIT(?:AND|X?OR)")) {
            left.assertInteger(op, log);
            right.assertInteger(op, log);
            type = Type.INT;

        // char/num/str op char/num/str (for greater/less inequalities)
        } else if (op.matches("<|<=|>|>=")) {
            if (left.type == Type.KAR) {
                right.assertChar(op, log);
            } else if (left.type == Type.YARN) {
                right.assertString(op, log);
            } else if (left.type.isArithmetic()){
                left.assertArithmetic(op, log);
                right.assertArithmetic(op, log);
            }
            type = Type.B00L;

        // str ~~ str
        } else if (op.matches("~~")) {
            left.assertString("~~", log);
            right.assertString("~~", log);
            type = Type.YARN;

        // any SAME AS any
        } else if (op.matches("==")) {
            if (!(left.isCompatibleWith(right.type) || right.isCompatibleWith(left.type))) {
                log.error("eq.type.error", op, left.type.getName(), right.type.getName());
            }
            type = Type.B00L;

        // bool ANALSO bool
        // bool ORELSE bool
        } else if (op.matches("ANALSO|ORELSE")) {
            left.assertBoolean(op, log);
            right.assertBoolean(op, log);
            type = Type.B00L;
        }
    }
}