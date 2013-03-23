package edu.lmu.cs.xlg.koan.entities;

/**
 * A Koan declaration.
 */
public abstract class Declaration extends Statement {

    private String name;

    public Declaration(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
