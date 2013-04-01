package edu.lmu.cs.xlg.koan.entities;


import java.util.Iterator;
import java.util.List;

import edu.lmu.cs.xlg.util.Log;


public class ObjectExpression extends Expression {

    private List<Expression> args = null;

    public ObjectExpression(List<Expression> args) {
        this.args = args;
    }

    public ObjectExpression() {
	}


    public List<Expression> getArgs() {
        return args;
    }

//    @Override
//    public void analyze(Log log, SymbolTable table, Function function, boolean inLoop) {
//        type = table.lookupType(typename, log);
//        if (! (type instanceof ObjectType)) {
//            log.error("not.an.object.type", type.getName());
//            return;
//        }
//
//        List<ObjectType.Property> properties = ObjectType.class.cast(type).getProperties();
//
//        if (args.size() != properties.size()) {
//            log.error("wrong.number.of.properties", type.getName(), properties.size(), args.size());
//            return;
//        }
//
//        Iterator<Expression> ai = args.iterator();
//        Iterator<ObjectType.Property> fi = properties.iterator();
//        while (ai.hasNext()) {
//            Expression a = ai.next();
//            ObjectType.Property f = fi.next();
//            a.analyze(log, table, function, inLoop);
//            a.assertAssignableTo(f.getType(), log, "property.type.error");
//        }
//    }


}
