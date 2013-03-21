package edu.lmu.cs.xlg.koan.entities;

import edu.lmu.cs.xlg.util.Log;

/**
 * A Koan expression.
 */
public abstract class Expression extends Entity {

    // As Koan is statically typed, we can compute and store the type at compile time.
    Type type;

    /**
     * Returns the type of this expression.
     */
    public Type getType() {
        return type;
    }

    /**
     * Performs semantic analysis on the expression.
     */
    public abstract void analyze(Log log, SymbolTable table, Function function, boolean inLoop);

    /**
     * Returns whether this expression is compatible with (that is, "can be assigned to an object
     * of") a given type.
     */
    //note that these search || are way sexier than if staetments
    public boolean isCompatibleWith(Type testType) {
        return this.type == testType
            || this.type == Type.INTEGER //&& testType == Type.NUMBER 
            || this.type == Type.NULL_TYPE && testType.isReference()
            || this.type == Type.ARBITRARY
            || testType == Type.ARBITRARY;
    }

    // Helpers for semantic analysis, called from the analyze methods of other expressions.  These
    // are by no means necessary, but they are very convenient.

    void assertAssignableTo(Type otherType, Log log, String errorKey) {
        if (!this.isCompatibleWith(otherType)) {
            log.error(errorKey, otherType.getName(), this.type.getName());
        }
    }

    void assertArithmetic(String context, Log log) {
        if (!(type == Type.INTEGER /*|| type == Type.NUMBR*/)) {
            log.error("non.arithmetic", context);
        }
    }

    void assertArithmeticOrChar(String context, Log log) {
        if (!(type == Type.INT /* || type == Type.NUMBR */ )) {
            log.error("non.arithmetic.or.char", context);
        }
    }

    void assertInteger(String context, Log log) {
        if (!(type == Type.INTEGER)) {
            log.error("non.integer", context);
        }
    }

    void assertBoolean(String context, Log log) {
        if (!(type == Type.B00L)) {
            log.error("non.boolean", context, type);
        }
    }

    void assertFun(String context, Log log) {
        if (!(type == Type.ANONFUN)) {
            log.error("non.anonfun", context);
        }
    }

    void assertArray(String context, Log log) {
        if (!(type instanceof ArrayType)) {
            log.error("non.array", context);
        }
    }

    void assertString(String context, Log log) {
        if (!(type == Type.STRING)) {
            log.error("non.string", context);
        }
    }

   
    void assertArrayOrString(String context, Log log) {
        if (!(type == Type.STRING || type instanceof ArrayType)) {
            log.error("non.array.or.string", context);
        }
    }
}