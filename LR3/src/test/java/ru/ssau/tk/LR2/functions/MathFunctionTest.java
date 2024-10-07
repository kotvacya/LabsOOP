package ru.ssau.tk.LR2.functions;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class MathFunctionTest extends TestCase {

    public void testAndThen() {
        UnitFunction uf = new UnitFunction();
        ZeroFunction zf = new ZeroFunction();
        SqrFunction sf = new SqrFunction();
        IdentityFunction idf = new IdentityFunction();

        assertEquals(0.0, zf.andThen(uf).andThen(idf).apply(5.0));
        assertEquals(1.0, uf.andThen(zf).andThen(idf).apply(5.0));
        assertEquals(16.0, sf.andThen(sf).andThen(idf).apply(2.0));

        MathFunction mf1 = new IdentityFunction();
        MathFunction mf2 = new ConstantFunction(52);
        MathFunction mf3 = new SqrFunction();

        ArrayTabulatedFunction atf1 = new ArrayTabulatedFunction(mf1, 1, 10, 10);
        ArrayTabulatedFunction atf2 = new ArrayTabulatedFunction(mf2, -1, -2, 5);
        ArrayTabulatedFunction atf3 = new ArrayTabulatedFunction(mf3, 7, 7, 7);
        LinkedListTabulatedFunction ltf1 = new LinkedListTabulatedFunction(mf1, 1, 10, 10);
        LinkedListTabulatedFunction ltf2 = new LinkedListTabulatedFunction(mf2, -1, -2, 5);
        LinkedListTabulatedFunction ltf3 = new LinkedListTabulatedFunction(mf3, 7, 7, 7);

        CompositeFunction cf1 = new CompositeFunction(atf1, ltf2);
        CompositeFunction cf2 = new CompositeFunction(atf2, ltf3);
        CompositeFunction cf3 = new CompositeFunction(ltf1, atf3);

        assertEquals(52.0, cf1.andThen(uf).andThen(sf).apply(1.0));
        assertEquals(52.0, cf1.andThen(zf).andThen(atf2).apply(200.0));
        assertEquals(52.0, cf2.andThen(sf).andThen(uf).apply(-300.0));
        assertEquals(2704.0, sf.andThen(cf2).andThen(atf1).apply(0.0));
        assertEquals(2401.0, sf.andThen(cf3).andThen(atf2).apply(7.7));
        assertEquals(49.0, cf3.andThen(sf).andThen(zf).apply(1.0));
    }

    public MathFunctionTest(String testName) {
        super(testName);
    }

    public static Test suite() {
        return new TestSuite(MathFunctionTest.class);
    }
}