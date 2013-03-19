package edu.lmu.cs.xlg.koan.entities;

import edu.lmu.cs.xlg.util.Log;

/**
 * A Koan type.
 */
public class Type extends Declaration {

    public static final Type INTEGER = new Type("#");
    public static final Type B00L = new Type("^");
    public static final Type OBJECT = new Type("o");
    public static final Type STRING = new Type("$");
    public static final Type ANONFUN = new Type("f");

    /**
     * The type whose sole member is the null value.
     */
    public static final Type NULL_TYPE = new Type("<type_of_null>");

    /**
     * A type representing the union of all types.  It is assigned to an entity whose typename is
     * not in scope to allow compilation to proceed without generating too many spurious errors.
     * It is compatible with all other types.
     */
    public static final Type ARBITRARY = new Type("<arbitrary>");
    public static final Type ARBITRARY_ARRAY = new Type("<arbitrary_array>");
    public static final Type ARBITRARY_ARRAY_OF_REFERENCES = new Type("<arbitrary_array_of_references>");

    // The type of arrays of this type.  Created only if needed.
    private ArrayType arrayOfThisType = null;

    /**
     * Constructs a type with the given name.
     */
    Type(String name) {
        super(name);
    }

    /**
     * Returns whether this type is a reference type.
     */
    public boolean isReference() {
        return this == STRING
            || this instanceof ArrayType
            || this == ARBITRARY;
    }

    /**
     * Returns whether this type is an arithmetic type.
     */
    public boolean isArithmetic() {
        return this == INTEGER;
    }

    /**
     * Returns the type that is an array of this type, lazily creating it.
     */
    public Type array() {
        if (arrayOfThisType == null) {
            arrayOfThisType = new ArrayType(this);
        }
        return arrayOfThisType;
    }

    /**
     * A default implementation that does nothing, since many type subclasses need no analysis.
     */
    public void analyze(Log log, SymbolTable table, Function function, boolean inLoop) {
        // Intentionally empty.
    }
}