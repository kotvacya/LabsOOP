package ru.ssau.tk.LR2.functions;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class CompositeFunctionTest extends TestCase {

    private static class Add1 implements MathFunction {
        @Override
        public double apply(double x) {
            return x + 1;
        }
    }

    private static class Exp implements MathFunction {
        @Override
        public double apply(double x) {
            return Math.exp(x);
        }
    }

    public void testApply() {
        CompositeFunction double_ident = new CompositeFunction(new IdentityFunction(), new IdentityFunction());
        assertEquals(0.0, double_ident.apply(0.0));

        CompositeFunction exp_add1 = new CompositeFunction(new Exp(), new Add1());
        assertEquals(Math.E, exp_add1.apply(0.0));

        CompositeFunction add1_exp = new CompositeFunction(new Add1(), new Exp());
        assertEquals(2.0, add1_exp.apply(0.0));

    }

    public CompositeFunctionTest(String testName) {
        super(testName);
    }

    public static Test suite() {
        return new TestSuite(CompositeFunctionTest.class);
    }
}