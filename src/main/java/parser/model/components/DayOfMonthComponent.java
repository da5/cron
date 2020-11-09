package parser.model.components;

import parser.common.ComponentType;
import parser.model.Component;

public class DayOfMonthComponent extends Component {
    public DayOfMonthComponent(String expr) {
        super(expr, ComponentType.dayOfMonth.limit);
    }

    @Override
    public String getName() {
        return ComponentType.dayOfMonth.label;
    }

    @Override
    protected String normalise(String expression) {
        return expression;
    }
}
