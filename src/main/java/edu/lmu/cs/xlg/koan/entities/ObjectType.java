package edu.lmu.cs.xlg.koan.entities;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import edu.lmu.cs.xlg.util.Log;

/**
 * The Roflkode Bukkit type.  A bukkit type has a name and a list of properties, where properties
 * are name/type pairs.
 */
public class ObjectType extends Type {

    public static class Property extends Entity {
        private String name;
        private String typename;
        private Type type;

        /**
         * A property used in semantic analysis to take the place of a property that is being
         * referenced but that has not been declared.  This property is type-compatible with
         * everything, so its use serves to prevent a flood of spurious error messages.
         */
        public static final Property ARBITRARY = new Property(
                "<unknown>", Type.ARBITRARY.getName());

        static {ARBITRARY.type = Type.ARBITRARY;}

        public Property(String name, String typename) {
            this.name = name;
            this.typename = typename;
        }

        public String getName() {
            return name;
        }

        public String getTypename() {
            return typename;
        }

        public Type getType() {
            return type;
        }

        public void analyze(Log log, SymbolTable table) {
            type = table.lookupType(typename, log);
        }
    }

    private List<Property> properties;

    /**
     * Creates a bukkit type.
     */
    public ObjectType(String name, List<Property> properties) {
        super(name);
        this.properties = properties;
    }

    /**
     * Returns the list of properties for this bukkit type.
     */
    public List<Property> getProperties() {
        return properties;
    }

    /**
     * Analyzes this type.  First check that all property names are unique. Then check that every
     * property declaration has a valid type.
     */
    @Override
    public void analyze(Log log, SymbolTable table, Function function, boolean inLoop) {
//        Set<String> propertyNames = new HashSet<String>();
//        for (Property property: properties) {
//            if (! propertyNames.add(property.getName())) {
//                log.error("duplicate.property", property.getName(), this.getName());
//            }
//        }
//
//        for (Property property: properties) {
//            property.analyze(log, table);
//        }
    }

    /**
     * Returns the property in this type with the given name.  If no such property exists, log an
     * error and return the "arbitrary" field.
     */
    public Property getProperty(String name, Log log) {
        for (Property property: properties) {
            if (property.getName().equals(name)) {
                return property;
            }
        }

        // Didn't find it, use the placeholder.
        log.error("no.such.property", this.getName(), name);
        return Property.ARBITRARY;
    }
}
