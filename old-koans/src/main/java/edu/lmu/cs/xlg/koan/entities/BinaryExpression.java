package edu.lmu.cs.xlg.koan.entities;

import edu.lmu.cs.xlg.util.Log;

/**
 * A Koan expression made up of a binary operator and two operands.
 */
public class BinaryExpression extends Expression {

    private String op;
    private Expression left;
    private Expression right;


    public BinaryExpression(Expression left, String op, Expression right) {
        this.left = left;
        this.op = op;
        this.right = right;
    }


    public Expression getLeft() {
        return left;
    }


    public String getOp() {
        return op;
    }


    public Expression getRight() {
        return right;
    }

//checks for certain types depending on the op.. we don't assign types semantic analysis
    // so for each binary operation we need to define the cases of what types might be at stake
    @Override
    public void analyze(Log log, SymbolTable table, Function function, boolean inLoop) {
//        left.analyze(log, table, function, inLoop);
//        right.analyze(log, table, function, inLoop);
//
//        // integer op integer (for exponential)
//        if (op.matches("**")) {
//            left.assertArithmetic("**", log);
//            right.assertArithmetic("**", log);
//            type = (left.type == Type.INTEGER || right.type == Type.INTEGER)
//                ? Type.INTEGER : Type.INTEGER;
//
//        // integer op integer (for arithmetic op)
//        } else if (op.matches("[-+*/]")) {
//            left.assertArithmetic(op, log);
//            right.assertArithmetic(op, log);
//            type = (left.type == Type.INTEGER || right.type == Type.INTEGER)
//                ? Type.INTEGER : Type.INTEGER;
//
//        // int op int returning int (mod)
//        } else if (op.matches("%")) {
//            left.assertInteger(op, log);
//            right.assertInteger(op, log);
//            type = Type.INTEGER;
//
//
//        // num/str op num/str (for greater/less inequalities)
//        } else if (op.matches("<|<=|>|>=")) {
//            if (left.type == Type.STRING) {
//                right.assertString(op, log);
//            } else if (left.type.isArithmetic()){
//                left.assertArithmetic(op, log);
//                right.assertArithmetic(op, log);
//            }
//            type = Type.BOOL;
//
//        // string concat
//        } else if (op.matches("+")) {
//            left.assertString("+", log);
//            right.assertString("+", log);
//            type = Type.STRING;
//
//        // any SAME AS any
//        } else if (op.matches("==")) {
//            if (!(left.isCompatibleWith(right.type) || right.isCompatibleWith(left.type))) {
//                log.error("eq.type.error", op, left.type.getName(), right.type.getName());
//            }
//            type = Type.BOOL;
//
//        // bool ANALSO bool
//        // bool ORELSE bool
//        } else if (op.matches("ANALSO|ORELSE")) {
//            left.assertBoolean(op, log);
//            right.assertBoolean(op, log);
//            type = Type.BOOL;
//        }
    }
}