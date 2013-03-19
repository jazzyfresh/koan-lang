package edu.lmu.cs.xlg.koan.generators;

import java.io.PrintWriter;

import edu.lmu.cs.xlg.koan.entities.AssignmentStatement;
import edu.lmu.cs.xlg.koan.entities.BinaryExpression;
import edu.lmu.cs.xlg.koan.entities.Block;
import edu.lmu.cs.xlg.koan.entities.Declaration;
import edu.lmu.cs.xlg.koan.entities.Expression;
import edu.lmu.cs.xlg.koan.entities.Number;
import edu.lmu.cs.xlg.koan.entities.Program;
import edu.lmu.cs.xlg.koan.entities.ReadStatement;
import edu.lmu.cs.xlg.koan.entities.Statement;
import edu.lmu.cs.xlg.koan.entities.VariableReference;
import edu.lmu.cs.xlg.koan.entities.WhileStatement;
import edu.lmu.cs.xlg.koan.entities.WriteStatement;

/**
 * A generator that translates a Koan program into C.
 */
public class KoanToCGenerator extends Generator {

    @Override
    public void generate(Program program, PrintWriter writer) {
        this.writer = writer;

        emit("#include <stdio.h>");
        emit("int main() {");
        generateBlock(program.getBlock());
        indentLevel++;
        emit("return 0;");
        indentLevel--;
        emit("}");
    }

    /**
     * Emits C code for the given Koan block.
     */
    private void generateBlock(Block block) {
        indentLevel++;
        for (Declaration d: block.getDeclarations()) {
            generateDeclaration(d);
        }
        for (Statement s: block.getStatements()) {
            generateStatement(s);
        }
        indentLevel--;
    }

    /**
     * Emits C code for the given Koan declaration.
     */
    private void generateDeclaration(Declaration d) {
        // The only kind of declaration there is in Koan is the variable.
        emit("int " + id(d) + " = 0;");
    }

    /**
     * Emits C code for the given Koan statement.
     */
    private void generateStatement(Statement s) {
        if (s instanceof AssignmentStatement) {
            AssignmentStatement a = AssignmentStatement.class.cast(s);
            emit(String.format("%s = %s;", generateExpression(a.getVariableReference()),
                    generateExpression(a.getExpression())));
        } else if (s instanceof ReadStatement) {
            for (VariableReference v: ReadStatement.class.cast(s).getReferences()) {
                emit("scanf(\"%d \", &" + id(v.getReferent()) + ");");
            }
        } else if (s instanceof WriteStatement) {
            for (Expression e: WriteStatement.class.cast(s).getExpressions()) {
                emit("printf(\"%d \", " + generateExpression(e) + ");");
            }
        } else if (s instanceof WhileStatement) {
            WhileStatement w = WhileStatement.class.cast(s);
            emit("while (" + generateExpression(w.getCondition()) + ") {");
            generateBlock(w.getBody());
            emit("}");
        }
    }

    /**
     * Produces the C string for the given expression.  All expressions in Koan can fit on one
     * line when translated to C.
     */
    private String generateExpression(Expression e) {
        if (e instanceof Number) {
            return Number.class.cast(e).getValue() + "";
        } else if (e instanceof VariableReference) {
            return id(VariableReference.class.cast(e).getReferent());
        } else if (e instanceof BinaryExpression) {
            BinaryExpression b = BinaryExpression.class.cast(e);
            return String.format("(%s %s %s)", generateExpression(b.getLeft()), b.getOperator(),
                    generateExpression(b.getRight()));
        } else {
            throw new RuntimeException("Internal Compiler Error");
        }
    }
}
