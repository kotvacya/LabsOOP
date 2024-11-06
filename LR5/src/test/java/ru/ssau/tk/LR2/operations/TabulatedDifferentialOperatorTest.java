package ru.ssau.tk.LR2.operations;

import junit.framework.TestCase;
import junit.framework.TestSuite;
import ru.ssau.tk.LR2.functions.*;
import ru.ssau.tk.LR2.functions.factory.ArrayTabulatedFunctionFactory;
import ru.ssau.tk.LR2.functions.factory.LinkedListTabulatedFunctionFactory;
import ru.ssau.tk.LR2.hash.Hasher;

public class TabulatedDifferentialOperatorTest extends TestCase {

    public void testGettersSetters() {
        TabulatedDifferentialOperator test = new TabulatedDifferentialOperator();
        assertEquals(ArrayTabulatedFunctionFactory.class, test.getFactory().getClass());
        test.setFactory(new LinkedListTabulatedFunctionFactory());
        assertEquals(LinkedListTabulatedFunctionFactory.class, test.getFactory().getClass());
    }

    public void testDerive() {
        TabulatedDifferentialOperator diff_array = new TabulatedDifferentialOperator(new ArrayTabulatedFunctionFactory());
        TabulatedDifferentialOperator diff_linked = new TabulatedDifferentialOperator(new LinkedListTabulatedFunctionFactory());

        TabulatedFunction derive1 = diff_array.derive(new LinkedListTabulatedFunction(new IdentityFunction(), 0, 10, 11));

        assertEquals(1.0, derive1.apply(2.0));
        assertEquals(1.0, derive1.apply(5.5));
        assertEquals(1.0, derive1.apply(9.5));

        TabulatedFunction derive2 = diff_linked.derive(new ArrayTabulatedFunction(new SqrFunction(), 0, 10, 1000));

        assertEquals(2.0, derive2.apply(1.0), 0.15);
        assertEquals(4.0, derive2.apply(2.0), 0.15);
        assertEquals(6.0, derive2.apply(3.0), 0.15);

        TabulatedFunction derive3 = diff_linked.derive(new LinkedListTabulatedFunction(new ConstantFunction(10.0), 0, 10, 11));

        assertEquals(0.0, derive3.apply(2.0));
        assertEquals(0.0, derive3.apply(5.5));
        assertEquals(0.0, derive3.apply(9.5));

        TabulatedFunction derive4 = diff_array.derive(new ArrayTabulatedFunction(new MathFunction() {
            @Override
            public long hash(Hasher h) {
                return 0;
            }

            @Override
            public double apply(double x) {
                return Math.exp(x);
            }
        }, 0, 10, 5000));

        assertEquals(Math.exp(2.0), derive4.apply(2.0), 0.1);
        assertEquals(Math.exp(3.0), derive4.apply(3.0), 0.1);
        assertEquals(Math.exp(4.0), derive4.apply(4.0), 0.1);
    }

    public void testSyncDerive() {
        TabulatedDifferentialOperator diff_array = new TabulatedDifferentialOperator(new ArrayTabulatedFunctionFactory());
        TabulatedDifferentialOperator diff_linked = new TabulatedDifferentialOperator(new LinkedListTabulatedFunctionFactory());

        TabulatedFunction derive1 = diff_array.deriveSynchronously(new LinkedListTabulatedFunction(new IdentityFunction(), 0, 10, 11));

        assertEquals(1.0, derive1.apply(2.0));
        assertEquals(1.0, derive1.apply(5.5));
        assertEquals(1.0, derive1.apply(9.5));

        TabulatedFunction derive2 = diff_linked.deriveSynchronously(new ArrayTabulatedFunction(new SqrFunction(), 0, 10, 1000));

        assertEquals(2.0, derive2.apply(1.0), 0.15);
        assertEquals(4.0, derive2.apply(2.0), 0.15);
        assertEquals(6.0, derive2.apply(3.0), 0.15);

        TabulatedFunction derive3 = diff_linked.deriveSynchronously(new LinkedListTabulatedFunction(new ConstantFunction(10.0), 0, 10, 11));

        assertEquals(0.0, derive3.apply(2.0));
        assertEquals(0.0, derive3.apply(5.5));
        assertEquals(0.0, derive3.apply(9.5));

        TabulatedFunction derive4 = diff_array.deriveSynchronously(new ArrayTabulatedFunction(new MathFunction() {
            @Override
            public long hash(Hasher h) {
                return 0;
            }

            @Override
            public double apply(double x) {
                return Math.exp(x);
            }
        }, 0, 10, 5000));

        assertEquals(Math.exp(2.0), derive4.apply(2.0), 0.1);
        assertEquals(Math.exp(3.0), derive4.apply(3.0), 0.1);
        assertEquals(Math.exp(4.0), derive4.apply(4.0), 0.1);
    }

    public TabulatedDifferentialOperatorTest(String testName) {
        super(testName);
    }

    public static junit.framework.Test suite() {
        return new TestSuite(TabulatedDifferentialOperatorTest.class);
    }
}