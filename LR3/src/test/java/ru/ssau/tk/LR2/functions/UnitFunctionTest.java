package ru.ssau.tk.LR2.functions;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class UnitFunctionTest extends TestCase {

    public void testApply() {
        UnitFunction unit_func = new UnitFunction();
        assertEquals(1.0, unit_func.apply(777));
    }

    public UnitFunctionTest(String testName) {
        super(testName);
    }

    public static Test suite() {
        return new TestSuite(UnitFunctionTest.class);
    }
}