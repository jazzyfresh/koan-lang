package edu.lmu.cs.xlg.koan.entities;

import java.util.List;

import edu.lmu.cs.xlg.util.Log;

public class FunctionCallExpression extends Expression {

    private String functionName;
    private List<Expression> args;

    public FunctionCallExpression(String functionName, List<Expression> args) {
        this.functionName = functionName;
        this.args = args;
    }

    @Override
    public void analyze(Log log, SymbolTable table, Function function, boolean inLoop) {
        // TODO Auto-generated method stub

    }

    public String getFunctionName() {
        return functionName;
    }

    public List<Expression> getArgs() {
        return args;
    }
}