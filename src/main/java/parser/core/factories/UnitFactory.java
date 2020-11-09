package parser.core.factories;

import parser.exceptions.UnitParseException;
import parser.model.Limit;
import parser.model.Unit;
import parser.model.units.Interval;
import parser.model.units.Range;
import parser.model.units.SingleValue;
import parser.model.units.Star;

public class UnitFactory {
    public static Unit create(String literal, Limit limit) {
        literal = literal.trim();
        Unit unit = null;
        try {
            if ("*".equals(literal)) {
                unit = new Star(literal, limit);
            } else if (literal.indexOf('-')>=0) {
                if(literal.indexOf('-')==literal.lastIndexOf('-')) {
                    unit = new Range(literal, limit);
                }
            } else if (literal.indexOf('/')>=0) {
                if(literal.indexOf('/')==literal.lastIndexOf('/') &&
                        "*".equals(literal.substring(0, literal.indexOf('/')))) {
                    unit = new Interval(literal, limit);
                }
            } else {
                unit = new SingleValue(literal, limit);
            }
        } catch (Exception e) {
            throw new UnitParseException(e);
        }
        if (unit==null) {
            throw new UnitParseException(String.format("Could not match any valid patterns for %s!", literal));
        }
        return unit;
    }
}
