package edu.lmu.cs.xlg.koan.entities;

/**
 * A type which is a range. Range types objects are never constructed during syntax analysis.
 */
public class Range extends Entity {

	private Expression low;
	private Expression high;
	
	public Range(Expression low, Expression high) {
		this.low = low;
		this.high = high;
	}

}