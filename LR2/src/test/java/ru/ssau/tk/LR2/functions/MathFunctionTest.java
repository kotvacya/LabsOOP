package ru.ssau.tk.LR2.functions;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class MathFunctionTest extends TestCase {

    public void testAndThen() {
        UnitFunction uf = new UnitFunction();
        ZeroFunction zf = new ZeroFunction();
        IdentityFunction idf = new IdentityFunction();
        SqrFunction sf = new SqrFunction();

        assertEquals(0.0, zf.andThen(uf).andThen(idf).apply(5.0));
        assertEquals(1.0, uf.andThen(zf).andThen(idf).apply(5.0));
        assertEquals(16.0, sf.andThen(sf).andThen(idf).apply(2.0));
    }

    public MathFunctionTest(String testName) {
        super(testName);
    }

    public static Test suite() {
        return new TestSuite(MathFunctionTest.class);
    }
}