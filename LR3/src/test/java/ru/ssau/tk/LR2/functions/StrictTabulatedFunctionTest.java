package ru.ssau.tk.LR2.functions;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.junit.jupiter.api.Assertions;

public class StrictTabulatedFunctionTest extends TestCase {

    public void testApply() {
        LinkedListTabulatedFunction func = new LinkedListTabulatedFunction(
                new double[]{0.0, 1.0, 2.0, 3.0},
                new double[]{1.0, 0.0, 2.0, -1.0}
        );

        StrictTabulatedFunction strict_func = new StrictTabulatedFunction(func);

        assertEquals(func.getCount(), strict_func.getCount());
        assertEquals(func.getX(1), strict_func.getX(1));
        assertEquals(func.getY(1), strict_func.getY(1));
        assertEquals(func.indexOfX(1.0), strict_func.indexOfX(1.0));
        assertEquals(func.indexOfY(1.0), strict_func.indexOfY(1.0));
        assertEquals(func.leftBound(), strict_func.leftBound());
        assertEquals(func.rightBound(), strict_func.rightBound());

        Assertions.assertThrows(UnsupportedOperationException.class, () -> strict_func.apply(0.5));
        Assertions.assertThrows(UnsupportedOperationException.class, () -> strict_func.apply(-0.5));

        assertEquals(0.0, strict_func.apply(1.0));
        assertEquals(2.0, strict_func.apply(2.0));

        ArrayTabulatedFunction func2 = new ArrayTabulatedFunction(new SqrFunction(), 0, 10, 11);

        StrictTabulatedFunction strict_func2 = new StrictTabulatedFunction(func2);

        Assertions.assertThrows(UnsupportedOperationException.class, () -> strict_func2.apply(0.5));
        Assertions.assertThrows(UnsupportedOperationException.class, () -> strict_func2.apply(9.5));

        assertEquals(1.0, strict_func2.apply(1.0));
        assertEquals(4.0, strict_func2.apply(2.0));
        assertEquals(9.0, strict_func2.apply(3.0));

    }

    public StrictTabulatedFunctionTest(String testName) {
        super(testName);
    }

    public static Test suite() {
        return new TestSuite(StrictTabulatedFunctionTest.class);
    }
}