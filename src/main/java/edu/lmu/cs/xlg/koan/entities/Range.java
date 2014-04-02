package edu.lmu.cs.xlg.koan.entities;

import edu.lmu.cs.xlg.util.Log;

/**
 * A Koan expression that contains a starting expression ../... end expression.
 */
public class Range extends Expression {

    private String mid;
    private NumericLiteral start;
    private NumericLiteral end;


    public Range(NumericLiteral start, String mid, NumericLiteral end) {
        this.start = start;
        this.mid = mid;
        this.end = end;
    }


    public NumericLiteral getStart() {
        return start;
    }


    public String getMid() {
        return mid;
    }


    public NumericLiteral getEnd() {
        return end;
    }


    public void analyze(Log log, SymbolTable table, Function function, boolean inLoop) {

    }
}