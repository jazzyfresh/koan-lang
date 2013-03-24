package edu.lmu.cs.xlg.koan.entities;

import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Superclass for all Koan entities in the compiler.
 *
 * The front end of the compiler produces an abstract syntax tree which is then decorated (or
 * transformed) into a semantic graph. The nodes this structure are called entities. The hierarchy
 * for entities will look something like this:
 *
 * <pre>
 * Entity
 *     Block
 *         Script
 *     Statement
 *         Expression
 *             Literal
 *                 MuLiteral (To add 3/23)
 *                 Number
 *                 BooleanLiteral
 *                 StringLiteral (To add 3/23)
 *             BinaryExpression
 *             ObjectExpression     
 *         Declaration
 *             Variable
 *             Constant
 *             Type
 *                 ArrayType
 *                 ObjectType
 *             Function
 *         PrintStatement
 *         AssignmentStatement
 *             SwapStatement
 *         IfStatement
 *         LoopStatement

 * </pre>
 *
 * Each concrete entity class has a constructor to fill in the "syntactic" part of the entity. For
 * example, we know the name of a variable reference while generating the abstract syntax tree, so
 * that is filled in by the constructor. However, we don't know until semantic analysis exactly
 * which variable is being referred to, so that field is not filled in by the constructor.
 */
public abstract class Entity {

    /**
     * Collection of all entities that have ever been created, as a map with the entities as keys
     * and their ids as values.
     */
    private static Map<Entity, Integer> all = new LinkedHashMap<Entity, Integer>();

    /**
     * Creates an entity, "assigning" it a new unique id by placing it in a global map mapping the
     * entity to its id.
     */
    public Entity() {
        synchronized (all) {
            all.put(this, all.size());
        }
    }

    /**
     * Returns the integer id of this entity.
     */
    public Integer getId() {
        return all.get(this);
    }

    /**
     * Returns a short string containing this entity's id.
     */
    public String toString() {
        return "#" + getId();
    }

    /**
     * Writes a concise line with the entity's id number, class, and non-null properties.  For any
     * property that is itself an entity, or a collection of entities, only the entity id is
     * written.
     */
    private void writeDetailLine(PrintWriter writer) {
        String classname = getClass().getName();
        String kind = classname.substring(classname.lastIndexOf('.') + 1);
        writer.print(this + "\t" + kind + ":");
        List<Field> fields = relevantFields(getClass());

        for (Field field : fields) {
            field.setAccessible(true);
            if ((field.getModifiers() & Modifier.STATIC) != 0) {
                continue;
            }
            try {
                String name = field.getName();
                Object value = field.get(this);
                if (value == null) {
                    continue;
                }
                if (value.getClass().isArray()) {
                    value = Arrays.asList((Object[]) value);
                }
                writer.print(" " + name + "=" + value);
            } catch (IllegalAccessException cannotHappen) {
            }
        }
        writer.println();
    }

    /**
     * Writes a description of all entities reachable from this entity, one per line, to the
     * given writer. The description includes its id, class name, and all non-static attributes
     * with non-null values.
     */
    public void printEntityGraph(final PrintWriter writer) {
        traverse(new Visitor() {
            public void visit(Entity e) {
                e.writeDetailLine(writer);
            }
        });
    }

    /**
     * A little interface to implement to navigate the semantic graph.
     */
    public static interface Visitor {
        void visit(Entity e);
    }

    /**
     * Traverses the entity graph beginning at e, applying visitor v.
     */
    public void traverse(Visitor v) {
        Set<Entity> visited = new HashSet<Entity>();
        traverse(v, visited);
    }

    /**
     * Traverses the entity graph beginning at e, applying visitor v, skipping the ones already
     * in the visited set.
     */
    private void traverse(Visitor v, Set<Entity> visited) {
        if (visited.contains(this)) {
            return;
        }
        visited.add(this);

        v.visit(this);

        for (Field field : relevantFields(getClass())) {
            field.setAccessible(true);
            if ((field.getModifiers() & Modifier.STATIC) != 0)
                continue;
            try {
                Object value = field.get(this);
                if (value == null) {
                    continue;
                }
                if (value instanceof Entity) {
                    Entity.class.cast(value).traverse(v, visited);
                } else if (value.getClass().isArray()) {
                    traverse(Arrays.asList((Object[]) value), v, visited);
                } else if (value instanceof Collection<?>) {
                    traverse((Collection<?>) value, v, visited);
                } else if (value instanceof Map<?, ?>) {
                    traverse(((Map<?, ?>) value).values(), v, visited);
                }
            } catch (IllegalAccessException cannotHappen) {
            }
        }
    }

    /**
     * Traverses a collection of entities.
     */
    private static void traverse(Iterable<?> iterable, Visitor v, Set<Entity> used) {
        for (Object object : iterable) {
            if (object instanceof Entity) {
                Entity.class.cast(object).traverse(v, used);
            }
        }
    }

    /**
     * Returns a list of all non-private fields of class c, together with fields of its ancestor
     * classes, assuming that c is a descendant class of Entity.
     */
    public static List<Field> relevantFields(Class<?> c) {
        List<Field> attributes = new ArrayList<Field>();
        attributes.addAll(Arrays.asList(c.getDeclaredFields()));
        if (c.getSuperclass() != Entity.class) {
            attributes.addAll(relevantFields(c.getSuperclass()));
        }
        return attributes;
    }
}