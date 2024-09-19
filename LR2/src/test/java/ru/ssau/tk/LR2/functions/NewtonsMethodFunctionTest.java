package ru.ssau.tk.LR2.functions;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class NewtonsMethodFunctionTest extends TestCase {

    public NewtonsMethodFunctionTest( String testName )
    {
        super( testName );
    }

    public static Test suite()
    {
        return new TestSuite( NewtonsMethodFunctionTest.class );
    }

    public void testApply()
    {
        final double PRECISION = 1e-9;

        MathFunction sin = new MathFunction() {
            @Override
            public double apply(double x) {
                return Math.sin(x);
            }
        };

        NewtonsMethodFunction sin_newton = new NewtonsMethodFunction(sin, PRECISION);

        assertTrue(Math.abs(Math.PI - sin_newton.apply(2.0)) < PRECISION);

    }
}