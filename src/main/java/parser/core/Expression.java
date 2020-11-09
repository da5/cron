package parser.core;

import lombok.Getter;
import parser.common.ComponentType;
import parser.core.factories.ComponentFactory;
import parser.exceptions.ExpressionParseException;
import parser.model.Component;
import parser.model.Tuple;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Expression {
    private List<Component> components;
    private List<Tuple> tuples;

    public Expression(String expressionStr) {
        setComponents(expressionStr);
    }

    private void setComponents(String expressionStr) {
        String[] strings = expressionStr.split("\\s+");
        if(strings.length!= ComponentType.values().length+1) {
            throw new ExpressionParseException(
                    String.format("Number of arguments in expression should be %d", ComponentType.values().length+1)
            );
        }
        components = new ArrayList<>();
        tuples = new ArrayList<>();
        for (ComponentType componentType: ComponentType.values()) {
            Component component = ComponentFactory.create(componentType, strings[componentType.ordinal()]);
            components.add(component);
            tuples.add(new Tuple(component.getName(), printList(component.getValues())));
        }
        tuples.add(new Tuple("command", strings[strings.length-1]));
    }

    public void print() {
        int maxLength = 0;
        for(Tuple tuple: tuples) {
            if(maxLength < tuple.getKey().length()) {
                maxLength = tuple.getKey().length();
            }
        }
        maxLength++;
        for(Tuple tuple: tuples) {
            System.out.println(
                    String.format("%" + (-maxLength) + "s %s", tuple.getKey(), tuple.getValue())
            );
        }
    }

    private String printList(List<Integer> list) {
        int n = list.size();
        if(n==0) {
            return "";
        }
        StringBuilder builder = new StringBuilder();
        for(int i=0; i<n-1; i++) {
            builder.append(list.get(i));
            builder.append(' ');
        }
        builder.append(list.get(n-1));
        return builder.toString();
    }
}
