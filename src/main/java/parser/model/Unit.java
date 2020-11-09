package parser.model;


import java.util.List;

public abstract class Unit {
    protected String literal;
    private List<Integer> values;

    public Unit(String literal, Limit limit) {
        this.literal = literal;
        this.values = generateValues(limit);
    }

    public List<Integer> getValues() {
        return values;
    }

    protected abstract List<Integer> generateValues(Limit limit);
}
