package edu.lmu.cs.xlg.koan.entities;

import java.util.List;

import edu.lmu.cs.xlg.util.Log;

public class StringLiteral extends Literal {
    private List<Integer> values;

    public StringLiteral(String lexeme) {
        super(lexeme);
    }

    public List<Integer> getValues() {
        return values;
    }
    
    @Override
    public void analyze(Log log, SymbolTable table, Function function, boolean inLoop) {
        // TODO Auto-generated method stub

    }

}
