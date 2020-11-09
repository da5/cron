package parser;

import parser.core.Expression;

public class App
{
    public static void main( String[] args )
    {
//        System.out.println( "Argument \"" + args[0] + "\"");
        Expression expression = new Expression(args[0]);
//        Expression expression = new Expression("*/15 0 1,15 * 1-5 /usr/bin/find");
        expression.print();
    }
}
