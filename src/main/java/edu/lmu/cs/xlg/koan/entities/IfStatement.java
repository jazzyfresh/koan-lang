package edu.lmu.cs.xlg.koan.entities;

import java.util.List;

import edu.lmu.cs.xlg.util.Log;

/**
 * A koan if statement.  Like most languages, it contains a list of one or more arms
 * and an optional else part.
 */
public class IfStatement extends Statement {

    public static class Arm extends Entity {
        private Expression guard;
        private Statement statement;

        public Arm(Expression guard, Statement statement) {
            this.guard = guard;
            this.statement = statement;
        }

        public Expression getGuard() {
            return guard;
        }

        public Statement getStatement() {
            return statement;
        }

    }

    private List<Arm> arms;
    private Statement elsePart;

    public IfStatement(List<Arm> arms, Statement statement) {
        this.arms = arms;
        this.elsePart = statement;
    }

    public List<Arm> getArms() {
        return arms;
    }

    public Statement getElsePart() {
        return elsePart;
    }

    @Override
    public void analyze(Log log, SymbolTable table, Function function, boolean inLoop) {
//        for (Arm arm: arms) {
//            arm.guard.analyze(log, table, function, inLoop);
//            arm.guard.assertBoolean("condition", log);
//            arm.statement.analyze(log, table, function, inLoop);
//        }
//        if (elsePart != null) {
//            elsePart.analyze(log, table, function, inLoop);
//        }
    }
}