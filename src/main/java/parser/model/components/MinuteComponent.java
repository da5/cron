package parser.model.components;

import parser.common.ComponentType;
import parser.model.Component;

public class MinuteComponent extends Component {

    public MinuteComponent(String expr) {
        super(expr, ComponentType.minute.limit);
    }

    @Override
    public String getName() {
        return ComponentType.minute.label;
    }

    @Override
    protected String normalise(String expression) {
        return expression;
    }
}
