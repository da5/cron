package parser.model.units;

import parser.model.Limit;
import parser.model.Unit;

import java.util.ArrayList;
import java.util.List;

public class Star extends Unit {

    public Star(String literal, Limit limit) {
        super(literal, limit);
    }

    @Override
    protected List<Integer> generateValues(Limit limit) {
        List<Integer> values = new ArrayList<>();
        for(int i=limit.getLow(); i<=limit.getHigh(); i++) {
            values.add(i);
        }
        return values;
    }
}
