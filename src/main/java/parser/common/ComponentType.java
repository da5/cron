package parser.common;

import parser.model.Limit;

public enum ComponentType {
    minute("minute", new Limit(0,59)),
    hour("hour", new Limit(0,23)),
    dayOfMonth("day of month", new Limit(1,31)),
    month("month", new Limit(1,12)),
    dayOfWeek("day of week", new Limit(0,6));

    public final String label;
    public final Limit limit;
    private ComponentType(String label, Limit limit) {
        this.label = label;
        this.limit = limit;
    }
}
