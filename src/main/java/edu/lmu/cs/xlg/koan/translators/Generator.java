package edu.lmu.cs.xlg.koan.generators;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import edu.lmu.cs.xlg.koan.entities.Entity;
import edu.lmu.cs.xlg.koan.entities.Program;

/**
 * An generator that translates a Koan program into some other form.  The result of the translation
 * is written to a writer.
 */
public abstract class Generator {

    // The writer to use
    PrintWriter writer;

    // Helps to ensure all entities get a unique id if they need one.
    Map<Entity, Integer> idMap = new HashMap<Entity, Integer>();

    // The last id generated.  Updated in <code>id()</code>
    private int lastId = 0;

    // For writing clean output.  May be overridden in subclasses.
    int indentPadding = 4;

    // The current indent level
    int indentLevel = 0;

    /**
     * A factory for retrieving a specific generator based on a name.  TODO: This is ugly
     * because the generators and their names are hardcoded.  Needs to be refactored so the
     * actual generators register themselves.
     */
    public static Generator getGenerator(String name) {
        if ("c".equals(name)) {
            return new KoanToCGenerator();
        } else if ("js".equals(name)) {
            return new KoanToJavaScriptGenerator();
        } else {
            return null;
        }
    }

    /**
     * Generates a target program for the given Koan program.
     *
     * @param program
     *     The Koan program (source).
     * @param writer
     *     Writer for the target program.
     */
    public abstract void generate(Program program, PrintWriter writer);

    /**
     * Returns the id for the given entity, creating the id if the entity doesn't already
     * have one.
     */
    synchronized String id(Entity e) {
        Integer result = idMap.get(e);
        if (result == null) {
            result = ++lastId;
            idMap.put(e, result);
        }
        return "_v" + result;
    }

    /**
     * Write a line of target code to the output.
     */
    void emit(String line) {
        int pad = indentPadding * indentLevel;

        // printf does not allow "%0s" as a format specifier, darn it.
        if (pad == 0) {
            writer.println(line);
        } else {
            writer.printf("%" + pad + "s%s\n", "", line);
        }
    }
}
