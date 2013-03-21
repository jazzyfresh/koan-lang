package edu.lmu.cs.xlg.koan.entities;

import edu.lmu.cs.xlg.util.Log;

/**
 * A Roflkode loop statement.
 */
public abstract class LoopStatement extends Statement {

    private String loopType;
    private Expression condition;
    private String iterator;
    private Expression start;
    private Expression end;
    private Expression collection;
    private Block body;

    public LoopStatement(String name, String loopType, Expression condition, String iterator,
            Expression start, Expression end, Expression collection, Block body) {
        super(name);
        this.loopType = loopType;
        this.condition = condition;
        this.iterator = iterator;
        this.start = start;
        this.end = end;
        this.collection = collection;
        this.body = body;
    }

    public String getLoopType() {
        return loopType;
    }

    public Expression getCondition() {
        return condition;
    }

    public String getIterator() {
        return iterator;
    }

    public Expression getStart() {
        return start;
    }

    public Expression getEnd() {
        return end;
    }

    public Expression getCollection() {
        return collection;
    }

    public Block getBody() {
        return body;
    }

//    @Override
//    public void analyze(Log log, SymbolTable table, Function function, boolean inLoop) {
//
//        // An indefinite (WHIEL or TIL) loop.
//        if (condition != null) {
//            condition.analyze(log, table, function, inLoop);
//            condition.assertBoolean(loopType, log);
//        }
//
//        // A loop iterating through a collection - iteration variable has same type as collection
//        // base type.
//        if (collection != null) {
//            collection.analyze(log, table, function, inLoop);
//            collection.assertArray("loop", log);
//            body.createTable(table);
//            body.getTable().insert(new Variable(iterator, collection.getType().array()), log);
//        }
//
//        // A loop iterating through a range - bounds must be INTs, and declaration the iteration
//        // variable as an INT also.
//        if (start != null && end != null) {
//            start.analyze(log, table, function, inLoop);
//            end.analyze(log, table, function, inLoop);
//            start.assertInteger("loop", log);
//            end.assertInteger("loop", log);
//            body.createTable(table);
//            body.getTable().insert(new Variable(iterator, Type.INT), log);
//        }
//
//        // Analyze the body last, as it may depend on other things.
//        body.analyze(log, table, function, true);
//    }
}