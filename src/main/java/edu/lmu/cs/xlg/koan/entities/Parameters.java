package edu.lmu.cs.xlg.koan.entities;

import java.util.List;

import edu.lmu.cs.xlg.util.Log;

public class Parameters {

    private List<String> params;

    /**
     * Creates the function call expression.
     */
    public Parameters(List<String> params) {
        this.params = params;
    }

    /**
     * Returns the arguments
     */
    public List<String> getParams() {
        return params;
    }

}