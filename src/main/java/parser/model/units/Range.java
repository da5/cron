package parser.model.units;

import parser.model.Limit;
import parser.model.Unit;

import java.util.ArrayList;
import java.util.List;

public class Range extends Unit {
    public Range(String literal, Limit limit) {
        super(literal, limit);
    }

    @Override
    protected List<Integer> generateValues(Limit limit) {
        List<Integer> values = new ArrayList<>();
        String[] strings = literal.split("-");
        int low = Math.max(Integer.parseInt(strings[0]), limit.getLow());
        int high = Math.min(Integer.parseInt(strings[1]), limit.getHigh());

        for(int i=low; i<=high; i++) {
            values.add(i);
        }
        return values;
    }
}
