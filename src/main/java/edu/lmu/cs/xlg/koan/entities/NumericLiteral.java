package edu.lmu.cs.xlg.koan.entities;

import edu.lmu.cs.xlg.util.Log;

public class NumericLiteral extends Literal {

    private Double value;

    public NumericLiteral(String lexeme) {
        super(lexeme);

        // HEY HEY: THIS BELONGS IN ANALYZE BUT JASMINE AND DANNY ARE TOO BUSY
        // WHEN THE ANALYZER IS DONE PUT THIS BACK WHERE IT BELONGS WHICH IS NOT HERE.
        type = Type.NUMBER;
        try {
            value = Double.parseDouble(getLexeme());
        } catch (NumberFormatException e) {
            System.out.println("bad_number: " + getLexeme());
        }
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
//        type = Type.NUMBER;
//        try {
//            value = Double.parseDouble(getLexeme());
//        } catch (NumberFormatException e) {
//            log.error("bad_number", getLexeme());
//        }
    }
}
