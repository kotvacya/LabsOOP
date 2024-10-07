package ru.ssau.tk.LR2.functions;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class NewtonsMethodFunctionTest extends TestCase {

    public NewtonsMethodFunctionTest(String testName) {
        super(testName);
    }

    public static Test suite() {
        return new TestSuite(NewtonsMethodFunctionTest.class);
    }

    public void testApply() {
        final double PRECISION = 1e-9;

        MathFunction sin = new MathFunction() {
            @Override
            public double apply(double x) {
                return Math.sin(x);
            }
        };

        NewtonsMethodFunction sin_newton = new NewtonsMethodFunction(sin, PRECISION);

        assertEquals(Math.PI, sin_newton.apply(2.0), PRECISION);

        MathFunction const_func = new ConstantFunction(1.0);

        NewtonsMethodFunction const_newton = new NewtonsMethodFunction(const_func, PRECISION);

        assertEquals(Double.NaN, const_newton.apply(0.0));

        MathFunction exp = new MathFunction() {
            @Override
            public double apply(double x) {
                return Math.exp(x)-1;
            }
        };

        NewtonsMethodFunction exp_newton = new NewtonsMethodFunction(exp, PRECISION, 1000000);

        assertEquals(0.0, exp_newton.apply(3.0), PRECISION);
        
    }
}