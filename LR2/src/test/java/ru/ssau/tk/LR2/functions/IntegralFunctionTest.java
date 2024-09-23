package ru.ssau.tk.LR2.functions;

import junit.framework.Assert;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class IntegralFunctionTest extends TestCase {

    public void testApply() {

        ConstantFunction cf = new ConstantFunction(5);
        IntegralFunction inf = new IntegralFunction(cf, 50.0, 70.0, 1e-8);
        assertTrue(Math.abs(100.0 - inf.apply(0)) < 0.01);

        SqrFunction sr = new SqrFunction();
        IntegralFunction inf2 = new IntegralFunction(sr, 0.0, 1.0, 1e-8);
        assertTrue(Math.abs(1.0 / 3 - inf2.apply(0)) < 0.01);

    }

    public IntegralFunctionTest(String testName) {
        super(testName);
    }

    public static Test suite() {
        return new TestSuite(IntegralFunctionTest.class);
    }
}