package edu.lmu.cs.xlg.koan.entities;

/**
 * A type which is a range. Range types objects are never constructed during syntax analysis.
 */
public class Range extends Type {

	private Type baseType;
	private String interval;

    /**
     * Constructs an array type for arrays with the given base type.
     */
    public Range(String interval) {
        super("RANGE OF " + interval);
        this.baseType = INTEGER;
        this.interval = interval;
    }

    /**
     * Returns the type of the elements of this array type.
     */
    public Type getBaseType() {
        return baseType;
    }
    
    public String getInterval() {
    	return interval;
    }
}