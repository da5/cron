package parser.model.units;

import parser.model.Limit;
import parser.model.Unit;

import java.util.ArrayList;
import java.util.List;

public class SingleValue extends Unit {
    public SingleValue(String literal, Limit limit) {
        super(literal, limit);
    }

    @Override
    protected List<Integer> generateValues(Limit limit) {
        List<Integer> values = new ArrayList<>();
        int value = Integer.parseInt(literal);
        if(value>=limit.getLow() && value<=limit.getHigh()) {
            values.add(value);
        }
        return values;
    }
}
