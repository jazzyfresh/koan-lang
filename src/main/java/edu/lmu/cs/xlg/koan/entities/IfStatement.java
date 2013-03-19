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
        private Block block;

        public Arm(Expression guard, Block block) {
            this.guard = guard;
            this.block = block;
        }
    }

    private List<Arm> arms;
    private Block elsePart;

    public IfStatement(List<Arm> arms, Block elsePart) {
        this.arms = arms;
        this.elsePart = elsePart;
    }

    public List<Arm> getArms() {
        return arms;
    }

    public Block getElsePart() {
        return elsePart;
    }

    @Override
    public void analyze(Log log, SymbolTable table, Function function, boolean inLoop) {
        for (Arm arm: arms) {
            arm.guard.analyze(log, table, function, inLoop);
            arm.guard.assertBoolean("condition", log);
            arm.block.analyze(log, table, function, inLoop);
        }
        if (elsePart != null) {
            elsePart.analyze(log, table, function, inLoop);
        }
    }
}