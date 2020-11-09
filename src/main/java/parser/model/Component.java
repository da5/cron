package parser.model;

import parser.core.factories.UnitFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.NavigableSet;
import java.util.TreeSet;

public abstract class Component {
    private final List<Unit> units;

    public Component(String expression, Limit limit) {
        units = new ArrayList<>();
        expression = normalise(expression);
        String[] unitStrings = expression.split(",");
        for(String string: unitStrings) {
            units.add(UnitFactory.create(string, limit));
        }
    }

    public abstract String getName();

    protected abstract String normalise(String expression);

    public List<Integer> getValues() {
        NavigableSet<Integer> set = new TreeSet<>();
        for(Unit unit: units) {
            set.addAll(unit.getValues());
        }
        return new ArrayList<>(set);
    }
}
