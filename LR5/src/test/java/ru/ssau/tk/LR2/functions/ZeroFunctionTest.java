package ru.ssau.tk.LR2.functions;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class ZeroFunctionTest extends TestCase {

    public void testApply() {
        ZeroFunction zero_func = new ZeroFunction();
        assertEquals(0.0, zero_func.apply(777));
    }

    public ZeroFunctionTest(String testName) {
        super(testName);
    }

    public static Test suite() {
        return new TestSuite(ZeroFunctionTest.class);
    }
}