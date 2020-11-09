package parser.model.components;

import parser.common.ComponentType;
import parser.model.Component;

import java.util.HashMap;
import java.util.Map;

public class MonthComponent extends Component {
    public MonthComponent(String expr) {
        super(expr, ComponentType.month.limit);
    }

    @Override
    public String getName() {
        return ComponentType.month.label;
    }

    @Override
    protected String normalise(String expression) {
        Map<String, String> map = new HashMap<String, String>() {{
            put("JAN", "1");
            put("FEB", "2");
            put("MAR", "3");
            put("APR", "4");
            put("MAY", "5");
            put("JUN", "6");
            put("JUL", "7");
            put("AUG", "8");
            put("SEP", "9");
            put("OCT", "10");
            put("NOV", "11");
            put("DEC", "12");
        }};
        for(String key: map.keySet()) {
            expression = expression.replaceAll(key, map.get(key));
        }
        return expression;
    }
}
