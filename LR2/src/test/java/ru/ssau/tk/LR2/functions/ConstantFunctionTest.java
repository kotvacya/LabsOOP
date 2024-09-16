package ru.ssau.tk.LR2.functions;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class ConstantFunctionTest extends TestCase {

    public void testApply() {
        ConstantFunction func1 = new ConstantFunction(10.0);
        assertEquals(10.0, func1.apply(777));
        ConstantFunction func2 = new ConstantFunction(0.0);
        assertEquals(0.0, func2.apply(777));
        ConstantFunction func3 = new ConstantFunction(-1.5);
        assertEquals(-1.5, func3.apply(777));
    }

    public ConstantFunctionTest(String testName) {
        super(testName);
    }

    public static Test suite() {
        return new TestSuite(ConstantFunctionTest.class);
    }
}

