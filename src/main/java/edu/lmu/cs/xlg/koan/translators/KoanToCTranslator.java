package src.main.java.edu.lmu.cs.xlg.koan.translators;

import java.io.PrintWriter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import edu.lmu.cs.xlg.koan.entities.ArrayExpression;
import edu.lmu.cs.xlg.koan.entities.ArrayType;
import edu.lmu.cs.xlg.koan.entities.AssignmentStatement;
import edu.lmu.cs.xlg.koan.entities.BinaryExpression;
import edu.lmu.cs.xlg.koan.entities.Block;
import edu.lmu.cs.xlg.koan.entities.BooleanLiteral;
import edu.lmu.cs.xlg.koan.entities.ObjectExpression;
import edu.lmu.cs.xlg.koan.entities.ObjectType;
import edu.lmu.cs.xlg.koan.entities.ObjectType.Property;
import edu.lmu.cs.xlg.koan.entities.CallStatement;
import edu.lmu.cs.xlg.koan.entities.IfStatement;
import edu.lmu.cs.xlg.koan.entities.Entity;
import edu.lmu.cs.xlg.koan.entities.Entity.Visitor;
import edu.lmu.cs.xlg.koan.entities.Expression;
import edu.lmu.cs.xlg.koan.entities.Function;
import edu.lmu.cs.xlg.koan.entities.ReturnStatement;
import edu.lmu.cs.xlg.koan.entities.BreakStatement;
import edu.lmu.cs.xlg.koan.entities.IndexVariableExpression;
import edu.lmu.cs.xlg.koan.entities.IntegerLiteral;
import edu.lmu.cs.xlg.koan.entities.Literal;
import edu.lmu.cs.xlg.koan.entities.LoopStatement;
import edu.lmu.cs.xlg.koan.entities.MuLiteral;
import edu.lmu.cs.xlg.koan.entities.Script;
import edu.lmu.cs.xlg.koan.entities.Statement;
import edu.lmu.cs.xlg.koan.entities.StringLiteral;
import edu.lmu.cs.xlg.koan.entities.Type;
import edu.lmu.cs.xlg.koan.entities.Variable;
import edu.lmu.cs.xlg.koan.entities.VariableReference;
import edu.lmu.cs.xlg.koan.entities.PrintStatement;

/**
 * A Translator that produces a C program for a given Koan script.  Translator objects are
 * <strong>NOT THREAD SAFE</strong>, but that is not a big deal because this class has a private
 * constructor and a new Translator is created every time you call the static translate method.
 */
public class KoanToCTranslator {

 /*   @SuppressWarnings("serial")
    private static final Map<String, String> C_BINARY_OPERATORS = new HashMap<String, String>() {{
        put("OR", "||");
        put("AND", "&&");
    }};
*/
    /**
     * Translates the given script, writing the target C program to the given writer.
     */
    public static void translate(Script script, PrintWriter writer) {
//        new KoanToCTranslator(writer).translateScript(script);
    }

/*    private PrintWriter writer;
    private List<Function> functions = new ArrayList<Function>();
    private List<ObjectType> ObjectTypes = new ArrayList<ObjectType>();
    private Set<Variable> nonLocallyAccessedVariables = new HashSet<Variable>();
*/
    private KoanToCTranslator(PrintWriter writer) {
//        this.writer = writer;
    }

    /**
     * Writes a C representation of the given Koan script to the Translator's writer.
     * Precondition: The script has already been semantically analyzed and is error-free.
     */
    private void translateScript(Script script) {
/*
        writer.println("#include <stdio.h>");
        writer.println("#include <stdlib.h>");
        writer.println("#include <math.h>");

        // Get all the object types, functions, and non-locally accessed variables.
        preprocess(script);

        for (Variable v :  nonLocallyAccessedVariables) {
            writer.println("int __offset_" + v.getId() + ";");
        }

        // Emit type declarations then definitions, to handle mutual recursion.
        for (ObjectType o : ObjectTypes) {
            translateStructTypeDeclaration(b);
        }

        for (ObjectType o : ObjectTypes) {
            translateStructTypeDefinition(b);
        }

        // Emit function sigs THEN whole bodies, to handle mutual recursion.
        for (Function f: functions) {
            translateFunctionSignature(f);
            writer.println(";");
        }

        for (Function f: functions) {
            translateWholeFunction(f);
        }

        // Finally put the statements of the top-level statements in the main() function.
        writer.println("int main() {");
        translateBlock(script, "    ");
        writer.println("    return 0;");
        writer.println("}");
*/    }


    private void translateStructTypeDeclaration(ObjectType type) {
//        writer.println("struct _Object" + type.getId() + ";");
    }

    private void translateStructTypeDefinition(ObjectType type) {
/*        writer.println("struct _Object" + type.getId() + " {");
        for (Property p: type.getProperties()) {
            writer.print("    ");
            translateType(p.getType());
            writer.print(" ");
            translatePropertyName(p);
            writer.println(";");
        }
        writer.println("};");
*/    }

    /**
     * Writes the C string representation of a Koan type.  Notice that Strings, Objects, and
     * arrays are all reference types in Koan, so these are all represented as pointer types
     * in C.
     */
    private void translateType(Type type) {
/*        if (type == Type.INT) {
            writer.print("int");
        } else if (type == Type.INTEGER) {
            writer.print("double");
        } else if (type == Type.STRING) {
            writer.print("wchar_t*");
        } else if (type == Type.B00L) {
            writer.print("int");
        } else if (type instanceof ObjectType) {
            writer.print("struct _Object" + type.getId() + "*");
        } else if (type instanceof ArrayType) {
            translateType(ArrayType.class.cast(type).getBaseType());
            writer.print("*");
        } else {
            // Ww should not have any real types here, but just in case...
            writer.print("int");
        }
*/    }

    /**
     * Writes the C string representation of a Koan variable.
     */
    private void translateVariableName(Variable variable) {
//        writer.print("_v" + variable.getId());
    }

    /**
     * Writes the C string representation of a Koan object property.
     */
    private void translatePropertyName(Property property) {
//        writer.print("_p" + property.getId());
    }

    /**
     * Writes the C string representation of a object function name.
     */
    private void translateFunctionName(Function function) {
/*        if (function.getBody() != null) {
            writer.print("_f" + function.getId());
        } else {
            writer.print("__koan__" + function.getName());
        }
*/    }

    /**
     * Writes the C label for the top of the given loop.
     */
    private void translateLoopTop(LoopStatement s) {
//        writer.print("top" + s.getId());
    }

    /**
     * Writes the C label for the top of the given loop.
     */
    private void translateLoopBottom(LoopStatement s) {
//        writer.print("bottom" + s.getId());
    }

    /**
     * Writes the function signature, in C, only.
     */
    private void translateFunctionSignature(Function function) {
/*        if (function.getReturnType() == null) {
            writer.print("void");
        } else {
            translateType(function.getReturnType());
        }
        writer.print(" ");
        translateFunctionName(function);
        translateParameters(function.getParameters());
*/    }

    /**
     * Writes the given function in C, with the signature and the body, if applicable.  If there
     * is no body, the function is external and nothing at all is written.
     */
    private void translateWholeFunction(Function function) {
/*        if (function.getBody() == null) {
            // Do nothing, the signature was already done and there is not body
            return;
        }
        translateFunctionSignature(function);
        writer.println(" {");
        translateBlock(function.getBody(), "    ");
        writer.println("}");
*/    }

    /**
     * Writes the given parameter list in C, with parentheses.
     */
    private void translateParameters(List<Variable> parameters) {
/*        writer.print("(");
        boolean first = true;
        for (Variable v: parameters) {
            if (!first) {
                writer.print(", ");
            }
            translateType(v.getType());
            writer.print(" ");
            translateVariableName(v);
            first = false;
        }
        writer.print(")");
*/    }

    private void translateBlock(Block b, String indent) {
/*        for (Statement s: b.getStatements()) {
            translateStatement(s, indent);
        }
*/    }

    private void translateStatement(Statement s, String indent) {
/*        if (s instanceof Variable) {
            translateVariableDeclaration(Variable.class.cast(s), indent);
        } else if (s instanceof ObjectType) {
            // All types already defined at the top-level of the script
            return;
        } else if (s instanceof Function) {
            // All functions already defined at the top-level of the script
            return;
        } else if (s instanceof PrintStatement) {
            translatePrintStatement(PrintStatement.class.cast(s), indent);
        } else if (s instanceof AssignmentStatement) {
            translateAssignmentStatement(AssignmentStatement.class.cast(s), indent);
        } else if (s instanceof BreakStatement) {
            translateBreakStatement(BreakStatement.class.cast(s), indent);
        } else if (s instanceof ReturnStatement) {
            translateReturnStatement(ReturnStatement.class.cast(s), indent);
        } else if (s instanceof CallStatement) {
            translateCallStatement(CallStatement.class.cast(s), indent);
        } else if (s instanceof IfStatement) {
            translateIfStatement(IfStatement.class.cast(s), indent);
        } else if (s instanceof LoopStatement) {
            translateLoopStatement(LoopStatement.class.cast(s), indent);
        } else {
            throw new RuntimeException("FATAL ERROR: Translator DOESN'T KNOW ABOUT " +
                    s.getClass().getName());
        }
*/    }

    private void translateVariableDeclaration(Variable v, String indent) {
/*        writer.print(indent);
        if (v.isConstant()) {
            writer.print("const ");
        }
        translateType(v.getType());
        writer.print(" ");
        translateVariableName(v);
        if (v.getInitializer() != null) {
            writer.print(" = ");
            translateExpression(v.getInitializer());
        }
        writer.println(";");
*/    }

    private void translatePrintStatement(PrintStatement s, String indent) {
/*        for (Expression e: s.getExpressions()) {
            writer.print(indent);
            if (e.getType() == Type.B00L) {
                writer.print("printf(");
                translateExpression(e);
                writer.println(" ? \"WIN\" : \"FAIL\");");
            } else if (e.getType() == Type.INTEGER) {
                writer.print("printf(\"%d\", ");
                translateExpression(e);
                writer.println(");");
            } else if (e.getType() == Type.STRING) {
                writer.print("printf(\"%s\", ");
                translateExpression(e);
                writer.println(");");
            } else if (e.getType() instanceof ArrayType) {
                writer.println("TODO_CODE_TO_PRINT_ARRAY");
            } else if (e.getType() instanceof ObjectType) {
                writer.println("TODO_CODE_TO_PRINT_OBJECT");
            } else {
                throw new RuntimeException("Unknown type made it to code Translator");
            }
        }
*/    }





    private void translateAssignmentStatement(AssignmentStatement s, String indent) {
/*        writer.print(indent);
        translateVariable(s.getLeft());
        writer.print(" = ");
        translateExpression(s.getRight());
        writer.println(";");
*/    }

    private void translateGtfoStatement(BreakStatement s, String indent) {
/*        if (s.getTarget() instanceof LoopStatement) {
            writer.print(indent + "goto ");
            translateLoopBottom(LoopStatement.class.cast(s.getTarget()));
            writer.println(";");
        } else {
            writer.println(indent + "return;");
        }
*/    }


    private void translateHerezStatement(ReturnStatement s, String indent) {
/*        writer.print(indent + "return ");
        translateExpression(s.getExpression());
        writer.println(";");
*/    }






    private void translateCallStatement(CallStatement s, String indent) {
//        writer.println(indent + "TODO_Call_Statement");
    }



    private void translateConditionalStatement(IfStatement s, String indent) {
//        writer.println(indent + "TODO_IF_STATEMENT");
    }

    private void translateLoopStatement(LoopStatement s, String indent) {
/*        writer.println(indent);
        translateLoopTop(s);
        writer.println(":");
        if ("WHIEL".equals(s.getLoopType())) {
            writer.print(indent + "if (!(");
            translateExpression(s.getCondition());
            writer.print(")) goto ");
            translateLoopBottom(s);
            writer.println(";");
            translateBlock(s.getBody(), indent);
            writer.print(indent + "goto ");
            translateLoopTop(s);
            writer.println(";");
        } else if ("TIL".equals(s.getLoopType())) {

        } else if (s.getCollection() != null) {
            writer.println("TODO - THRU COLLECTION");
        } else if (s.getStart() != null) {
            writer.println("TO DO - FROM/TO RANGE");
        } else {
            // Plain ol' loop
            translateBlock(s.getBody(), indent);
            writer.print(indent + "goto ");
            translateLoopTop(s);
            writer.println(";");
        }
        writer.println(indent);
        translateLoopBottom(s);
        writer.println(": ;");
*/    }


    private void translateExpression(Expression e) {
/*        if (e instanceof Literal) {
            translateLiteral(Literal.class.cast(e));
        } else if (e instanceof Variable) {
            translateVariableExpression(Variable.class.cast(e));
        } else if (e instanceof UnaryExpression) {
            translateUnaryExpression(UnaryExpression.class.cast(e));
        } else if (e instanceof BinaryExpression) {
            translateBinaryExpression(BinaryExpression.class.cast(e));
        } else if (e instanceof ArrayExpression) {
            translateArrayExpression(ArrayExpression.class.cast(e));
        } else /* BukkitExpression */ /*{
            translateBukkitExpression(ObjectExpression.class.cast(e));
        }
 */   }

    private void translateLiteral(Literal e) {
/*        if (e instanceof MuLiteral) {
            writer.print("0");
        } else if (e instanceof BooleanLiteral) {
            writer.print(e == BooleanLiteral.WIN ? "1" : "0");
        } else if (e instanceof IntegerLiteral) {
            writer.print(IntegerLiteral.class.cast(e).getValue());
        } else /* StringLiteral */ /*{
            writer.print("L\"");
            for (Integer codepoint: StringLiteral.class.cast(e).getCodepoints()) {
                writer.print(character(codepoint));
            }
            writer.print("\"");
        }
*/    }

    private void translateVariableExpression(VariableExpression e) {
/*        if (e instanceof SimpleVariableExpression) {
            SimpleVariableExpression v = SimpleVariableExpression.class.cast(e);
            translateVariableName(v.getReferent());
        } else if (e instanceof PropertyVariableExpression) {
            PropertyVariableExpression v = PropertyVariableExpression.class.cast(e);
            translateVariableExpression(v.getObject());
            writer.print(".");
            translatePropertyName(v.getProperty());
        } else if (e instanceof IndexVariableExpression) {
            IndexVariableExpression v = IndexVariableExpression.class.cast(e);
            translateVariableExpression(v.getArray());
            writer.print("[");
            translateExpression(v.getIndex());
            writer.print("]");
        } else {
            writer.print("TODO_FUNCTION_CALL");
        }
*/    }


    private void translateBinaryExpression(BinaryExpression e) {
/*        writer.print("(");
        if ("~~".equals(e.getOp())) {
            writer.print("TODO_CONCAT_EXPRESSION");
        } else if ("\\".equals(e.getOp())) {
            translateExpression(e.getLeft());
            writer.print("%");
            translateExpression(e.getRight());
            writer.print("!=0");
        } else {
            translateExpression(e.getLeft());
            String op = C_BINARY_OPERATORS.get(e.getOp());
            writer.print(op == null ? e.getOp() : op);
            translateExpression(e.getRight());
        }
        writer.print(")");
*/    }

    private void translateArrayExpression(ArrayExpression e) {
//        writer.print("TODO_ARRAY_EXPRESSION");
    }

    private void translateBukkitExpression(ObjectExpression e) {
//        writer.print("TODO_OBJECT_EXPRESSION");
    }

    private void preprocess(Script script) {
/*        script.traverse(new Visitor() {
            public void visit(Entity e) {
                if (e.getClass() == ObjectType.class) {
                    bukkitTypes.add(ObjectType.class.cast(e));
                } else if (e.getClass() == Function.class) {
                    functions.add(Function.class.cast(e));
                } else if (e.getClass() == Variable.class) {
                    Variable v = Variable.class.cast(e);
                    if (v.isNonLocal()) {
                        nonLocallyAccessedVariables.add(v.getReferent());
                    }
                }
            }
        });
*/    }

    /**
     * Returns a representation of a character for use in a C character or string literal.
     * Printable ASCII characters are returned as is; all other characters are returned in the
     * most suitable form from \xhh, \ uhhhh, or \Uhhhhhhhh.
     */
    String character(int codepoint) {
/*        if (0x20 <= codepoint && codepoint <= 0x7f) {
            return Character.toString((char)codepoint);
        } else if (codepoint <= 0xff) {
            return String.format("\\x%02x", codepoint);
        } else if (codepoint <= 0xffff) {
            return String.format("\\u%04x", codepoint);
        } else {
            return String.format("\\U%08x", codepoint);
        }
*/    }
}
