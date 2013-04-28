package edu.lmu.cs.xlg.koan.entities;

import java.util.List;

import edu.lmu.cs.xlg.util.Log;

/**
 * A Koan script. A script is really just a top-level block
 */
public class Script extends Block {

    public Script(List<Statement> statements) {
        super(statements);
    }

    /**
     * Performs semantic analysis on this script. By the time semantic analysis runs around, the
     * imports have already been resolved so the script can just be analyzed like the block that
     * it is.
     */
    public void analyze(Log log) {
//        analyze(log, null, null, false);
    }
}