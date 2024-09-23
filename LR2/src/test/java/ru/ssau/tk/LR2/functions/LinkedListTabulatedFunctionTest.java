package ru.ssau.tk.LR2.functions;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class LinkedListTabulatedFunctionTest extends TestCase {

    LinkedListTabulatedFunction func = new LinkedListTabulatedFunction(
            new double[]{0.0, 1.0, 2.0, 3.0},
            new double[]{1.0, 0.0, 2.0, -1.0}
    );

    public void testApply() {
        assertEquals(4, func.getCount());
        assertEquals(0.0, func.getX(0));
        assertEquals(1.0, func.getY(0));
        assertEquals(3, func.indexOfX(3.0));
        assertEquals(2, func.indexOfY(2.0));
        assertEquals(2, func.floorIndexOfX(2.5));
        assertEquals(0.0, func.leftBound());
        assertEquals(3.0, func.rightBound());

        assertEquals(1.0, func.apply(0.0));
        assertEquals(0.5, func.apply(0.5));
        assertEquals(0.0, func.apply(1.0));
        assertEquals(1.0, func.apply(1.5));
        assertEquals(2.0, func.apply(2.0));
        assertEquals(-1.0, func.apply(3.0));
        assertEquals(2.0, func.apply(-1.0));
        assertEquals(-4.0, func.apply(4.0));

        LinkedListTabulatedFunction func2 = new LinkedListTabulatedFunction(new SqrFunction(), 1.0, 4.0, 100);

        assertEquals(101, func2.getCount());

        assertEquals(1.0, func2.apply(1.0));
        assertEquals(4.0, func2.apply(2.0), 1e-3);
        assertEquals(16.0, func2.apply(4.0));
    }

    public void testInsertRemove() {
        func.insert(-1.0, 52.0);
        assertEquals(52.0, func.getY(0));
        assertEquals(0, func.indexOfY(52.0));
        func.insert(-0.5, 53.0);
        assertEquals(53.0, func.getY(1));
        assertEquals(1, func.indexOfY(53.0));
        func.insert(5.0, 54.0);
        assertEquals(54.0, func.getY(func.getCount() - 1));
        assertEquals(func.getCount() - 1, func.indexOfY(54.0));

        func.remove(0);
        func.remove(func.getCount() - 1);
        assertEquals(5, func.getCount());
        assertEquals(-0.5, func.leftBound());
        assertEquals(-1, func.indexOfY(52.0));
    }

    public LinkedListTabulatedFunctionTest(String testName) {
        super(testName);
    }

    public static Test suite() {
        return new TestSuite(LinkedListTabulatedFunctionTest.class);
    }
}