package edu.lmu.cs.xlg.koan.entities;

import java.util.List;

import edu.lmu.cs.xlg.util.Log;

public class FunctionCallStatement extends Statement {

    private String functionName;
    private List<Variable> args;

    public FunctionCallStatement(String functionName, List<Variable> args) {
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

    public List<Variable> getArgs() {
        return args;
    }
}
