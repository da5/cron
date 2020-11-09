package parser.core;

import org.junit.Before;
import org.junit.Test;
import parser.exceptions.ExpressionParseException;
import parser.exceptions.UnitParseException;

import java.util.*;

public class ExpressionTest {
    Map<String, List<List<Integer>>> testCases;

    @Before
    public void start() {
        testCases = new HashMap<>();
        testCases.put("*/15 0 1,15 * 1-5 /usr/bin/find",
                Arrays.asList(
                        Arrays.asList(0,15,30,45),
                        Collections.singletonList(0),
                        Arrays.asList(1,15),
                        Arrays.asList(1,2,3,4,5,6,7,8,9,10,11,12),
                        Arrays.asList(1,2,3,4,5)
                ));
        /*
            Different set of ranges viz. 11-12 and 3-4 renders a union of both the ranges
         */
        testCases.put("*/30 3,2,0 1-2,2-3 11-12,3-4 */3 /usr/bin/find",
                Arrays.asList(
                        Arrays.asList(0,30),
                        Arrays.asList(0,2,3),
                        Arrays.asList(1,2,3),
                        Arrays.asList(3,4,11,12),
                        Arrays.asList(0,3,6)
                ));
        /*
            An range viz. 13-15 for Month(valid range being 1-12) renders empty list of applicable months
         */
        testCases.put("*/30 3,2,0 1-2,2-3 13-15 */3 /usr/bin/find",
                Arrays.asList(
                        Arrays.asList(0,30),
                        Arrays.asList(0,2,3),
                        Arrays.asList(1,2,3),
                        Collections.emptyList(),
                        Arrays.asList(0,3,6)
                ));
        /*
            An invalid range viz. 10-8 for Month renders empty list of applicable months
         */
        testCases.put("*/30 3,2,0 1-2,2-3 10-8 */3 /usr/bin/find",
                Arrays.asList(
                        Arrays.asList(0,30),
                        Arrays.asList(0,2,3),
                        Arrays.asList(1,2,3),
                        Collections.emptyList(),
                        Arrays.asList(0,3,6)
                ));
        /*
            Support for Month/Day names as both single values and ranges
         */
            testCases.put("*/30 3,2,0 1-2,2-3 FEB-MAR SUN /usr/bin/find",
                Arrays.asList(
                        Arrays.asList(0,30),
                        Arrays.asList(0,2,3),
                        Arrays.asList(1,2,3),
                        Arrays.asList(2,3),
                        Arrays.asList(0)
                ));
        /*
            Support for Month/Day names as both single values and ranges
         */
        testCases.put("*/30 3,2,0 1-2,2-3 MAR SUN-TUE /usr/bin/find",
                Arrays.asList(
                        Arrays.asList(0,30),
                        Arrays.asList(0,2,3),
                        Arrays.asList(1,2,3),
                        Arrays.asList(3),
                        Arrays.asList(0,1,2)
                ));
    }

    @Test
    public void testSuccess() {
        for (String key: testCases.keySet()) {
            Expression expression = new Expression(key);
            List<List<Integer>> result = testCases.get(key);
            int n = result.size();
            assert expression.getComponents().size() == n;
            assert expression.getTuples().size() == n+1;
            for(int i=0; i<n; i++) {
                assert expression.getComponents().get(i).getValues().equals(result.get(i));
            }
            System.out.println(String.format("-------------------------\n%s", key));
            expression.print();
        }
    }

    @Test(expected = ExpressionParseException.class)
    public void testExpressionParseException() { //fail expression with more than 6 components
        Expression expression = new Expression("*/15 0 1,15 * 1-5 */9 /usr/bin/find");
    }

    @Test(expected = UnitParseException.class)
    public void testUnitParseException1() { //fail expression with more than one / for intervals
        Expression expression = new Expression("*/15 0 1,15 * *//9 /usr/bin/find");
    }

    @Test(expected = UnitParseException.class)
    public void testUnitParseException2() { //fail expression with non numerical offset for intervals
        Expression expression = new Expression("*/15 0 1,15 * */x /usr/bin/find");
    }

    @Test(expected = UnitParseException.class)
    public void testUnitParseException3() { //fail expression with more than one - for ranges
        Expression expression = new Expression("*/15 0 1,15 * 2--3 /usr/bin/find");
    }

    @Test(expected = UnitParseException.class)
    public void testUnitParseException4() { //fail expression with no */ prefix for intervals
        Expression expression = new Expression("*/15 0 1,15 * 2/2 /usr/bin/find");
    }
}
