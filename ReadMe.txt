I have taken reference for a standard cron expression(and the subtle rules) from the following site:
         https://crontab.guru/

I have considered the following which might make my implementation a bit different from a standard cron implementation.
You could find a test case for each in the class ExpressionTest.java:
    1) Different set of ranges renders a union of both the ranges, e.g. 11-12,3-4 for months would renders {3,4,11,12}
    2) An range or single value outside the valid range renders empty list, e.g. 7-8 for days of week renders {}
    3) An invalid range renders empty list of applicable, e.g.  10-8 for Month renders {}
    4) Support for Month/Day names as both single values and ranges e.g. "* * * FEB-MAR SUN" and "* * * OCT MON-FRI"
    5) Fail expression with more than 6 components
    6) Fail expression with more than one / for intervals
    7) Fail expression with non numerical offset for intervals
    8) Fail expression with more than one - for ranges
    9) Fail expression with no */ prefix for intervals

Prerequisites to run the project:
    1) Java 8
    2) Apache Maven

To view code coverage(achieved 97%) by unit tests:

    $ mvn clean test

    and open the file "target/site/jacoco/index.html" in any browser

To run the program(in command line):

    $ mvn clean package
    $ java -cp  target/cron-1.0.0.jar parser.App "*/15 0 1,15 * 1-5 /usr/bin/find"
------------------------------------------------------------------------

Sample output with ＂*/15 0 1,15 * 1-5 /usr/bin/find＂:

    $ java -cp  target/cron-1.0.0.jar parser.App "*/15 0 1,15 * 1-5 /usr/bin/find"
    minute        0 15 30 45
    hour          0
    day of month  1 15
    month         1 2 3 4 5 6 7 8 9 10 11 12
    day of week   1 2 3 4 5
    command       /usr/bin/find
