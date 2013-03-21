package edu.lmu.cs.xlg.koan.entities;

import java.util.Iterator;
import java.util.List;

import edu.lmu.cs.xlg.util.Log;

/**
 * A Koan function.  Functions whose body is null are "external" functions, whose signature
 * is defined in Koan but whose body is implemented elsewhere and brought in by a linker.
 */
public class Function extends Declaration {

    private List<Variable> parameters;
    private Block body;
    private Integer level;

    /**
     * Creates a function object.
     */
    public Function(List<Variable> parameters, Block body) {
    	super("ANONFUN");
        this.parameters = parameters;
        this.body = body;
    }

    public List<Variable> getParameters() {
        return parameters;
    }

    public Block getBody() {
        return body;
    }

    public Integer getLevel() {
        return level == null ? 0 : level;
    }

    public boolean isExternal() {
        return body == null;
    }

    /**
     * Performs semantic analysis on the function's signature and return type, but not the body.
     */
    public void analyzeSignature(Log log, SymbolTable table, Function owner, boolean inLoop) {
        level = owner == null ? 1 : owner.getLevel() + 1;
        SymbolTable tableForParameters;
        if (isExternal()) {
            // It's an external function.  The parameters can go in a temporary scratch table.
            tableForParameters = new SymbolTable(null);
        } else {
            // There is a body; the parameters share scope with the body.
            body.createTable(table);
            tableForParameters = body.getTable();
        }
        for (Variable parameter: parameters) {
            tableForParameters.insert(parameter, log);
            parameter.analyze(log, tableForParameters, owner, inLoop);
        }
    }

    /**
     * Performs semantics analysis on the body.  This is done after the signature has been analyzed,
     * so the body's symbol table has already been constructed and the parameters have already been
     * loaded.
     */
    public void analyze(Log log, SymbolTable table, Function owner, boolean inLoop) {

        // Some functions have no body, but analyze the bodies of those that do.
        if (body != null) {
            body.analyze(log, table, this, false);
        }
    }

    /**
     * Asserts that this function can be called with the given list of arguments. There have to be
     * the same number of arguments as parameters, and each argument must be type-compatible with
     * the type of the corresponding parameter.
     */
    public void assertCanBeCalledWith(List<Expression> args, Log log) {

        if (args.size() != parameters.size()) {
            log.error("argument.count.mismatch", args.size(), parameters.size());
        }

        // Check each parameter against the corresponding argument.
        Iterator<Expression> ai = args.iterator();
        Iterator<Variable> pi = parameters.iterator();
        while (pi.hasNext()) {
            Expression arg = ai.next();
            Variable parameter = pi.next();
            if (!arg.isCompatibleWith(parameter.getType())) {
                log.error("parameter.type.mismatch", parameter.getName());
            }
        }
    }

	public Type getReturnType() {
		// TODO Auto-generated method stub
		return null;
	}

}