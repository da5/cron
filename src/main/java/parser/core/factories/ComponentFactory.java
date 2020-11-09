package parser.core.factories;

import parser.common.ComponentType;
import parser.model.Component;
import parser.model.components.*;

public class ComponentFactory {
    public static Component create(ComponentType componentType, String string) {
        Component component = null;
        switch (componentType) {
            case minute:
                component = new MinuteComponent(string);
                break;
            case hour:
                component = new HourComponent(string);
                break;
            case dayOfMonth:
                component = new DayOfMonthComponent(string);
                break;
            case month:
                component = new MonthComponent(string);
                break;
            case dayOfWeek:
                component = new DayOfWeekComponent(string);
                break;
        }
        return component;
    }
}
