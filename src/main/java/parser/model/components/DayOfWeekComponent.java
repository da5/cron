package parser.model.components;

import parser.common.ComponentType;
import parser.model.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DayOfWeekComponent extends Component {
    public DayOfWeekComponent(String expr) {
        super(expr, ComponentType.dayOfWeek.limit);
    }

    @Override
    public String getName() {
        return ComponentType.dayOfWeek.label;
    }

    @Override
    protected String normalise(String expression) {
        Map<String, String> map = new HashMap<String, String>() {{
            put("SUN", "0");
            put("MON", "1");
            put("TUE", "2");
            put("WED", "3");
            put("THU", "4");
            put("FRI", "5");
            put("SAT", "6");
        }};
        for(String key: map.keySet()) {
            expression = expression.replaceAll(key, map.get(key));
        }
        return expression;
    }
}
