package parser.model.components;

import parser.common.ComponentType;
import parser.model.Component;

public class HourComponent extends Component {
    public HourComponent(String expr) {
        super(expr, ComponentType.hour.limit);
    }

    @Override
    public String getName() {
        return ComponentType.hour.label;
    }

    @Override
    protected String normalise(String expression) {
        return expression;
    }
}
