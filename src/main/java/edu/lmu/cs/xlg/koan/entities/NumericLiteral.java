package edu.lmu.cs.xlg.koan.entities;

import edu.lmu.cs.xlg.util.Log;

public class NumericLiteral extends Literal {

    private Double value;

    public NumericLiteral(String lexeme) {
        super(lexeme);
    }

    public Double getValue() {
        return value;
    }

    // Back door for the optimizer to create these things.
    static NumericLiteral fromValue(double value) {
        NumericLiteral result = new NumericLiteral(Double.toString(value));
        result.value = value;
        return result;
    }

    @Override
    public void analyze(Log log, SymbolTable table, Function function, boolean inLoop) {
        type = Type.NUMBER;
        try {
            value = Double.parseDouble(getLexeme());
        } catch (NumberFormatException e) {
            log.error("bad_number", getLexeme());
        }
    }
}
