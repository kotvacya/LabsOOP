package ru.ssau.tk.LR2.functions;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;


public class SqrFunctionTest extends TestCase {
    public void testApply() {
        SqrFunction func = new SqrFunction();
        assertEquals(0.0, func.apply(0.0));
        assertEquals(2.25, func.apply(-1.5));
        assertEquals(100.0, func.apply(10.0));
    }

    public SqrFunctionTest(String testName) {
        super(testName);
    }

    public static Test suite() {
        return new TestSuite(SqrFunctionTest.class);
    }
}