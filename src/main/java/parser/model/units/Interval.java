package parser.model.units;

import parser.model.Limit;
import parser.model.Unit;

import java.util.ArrayList;
import java.util.List;

public class Interval extends Unit {
    public Interval(String literal, Limit limit) {
        super(literal, limit);
    }

    @Override
    protected List<Integer> generateValues(Limit limit) {
        List<Integer> values = new ArrayList<>();
        String[] strings = literal.split("/");
        int interval = Integer.parseInt(strings[1]);

        for(int i=limit.getLow(); i<=limit.getHigh(); i+=interval) {
            values.add(i);
        }
        return values;
    }
}
