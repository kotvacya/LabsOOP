package ru.ssau.tk.LR2.functions;

import junit.framework.Assert;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class IntegralFunctionTest extends TestCase {

    public void testApply() {

        ConstantFunction cf = new ConstantFunction(5);
        IntegralFunction inf = new IntegralFunction(cf, 50.0, 70.0);
        assertTrue(Math.abs(100.0 - inf.apply(1e-5)) < 0.1);

        SqrFunction sr = new SqrFunction();
        IntegralFunction inf2 = new IntegralFunction(sr, 0.0, 1.0);
        assertTrue(Math.abs(1.0 / 3 - inf2.apply(1e-5)) < 0.1);

        ArrayTabulatedFunction tf = new ArrayTabulatedFunction(new double[]{1, 2, 3}, new double[]{10, 5, 30});
        IntegralFunction inf3 = new IntegralFunction(tf, -10, -20);
        assertTrue(Math.abs(-900.0 - inf3.apply(1e-5)) < 0.1);

    }

    public IntegralFunctionTest(String testName) {
        super(testName);
    }

    public static Test suite() {
        return new TestSuite(IntegralFunctionTest.class);
    }
}