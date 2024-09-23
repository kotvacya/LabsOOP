package ru.ssau.tk.LR2.functions;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class ArrayTabulatedFunctionTest extends TestCase {

    MathFunction mf1 = new IdentityFunction();
    MathFunction mf2 = new ConstantFunction(52);
    MathFunction mf3 = new SqrFunction();
    ArrayTabulatedFunction atf1 = new ArrayTabulatedFunction(mf1, 1, 10, 10);
    ArrayTabulatedFunction atf2 = new ArrayTabulatedFunction(mf2, -1, -2, 5);
    ArrayTabulatedFunction atf3 = new ArrayTabulatedFunction(mf3, 7, 7, 7);

    public void testApply() {
        assertEquals(1.0, atf1.apply(1));
        assertEquals(100.0, atf1.apply(100));
        assertEquals(-100.0, atf1.apply(-100));
        assertEquals(5.5, atf1.apply(5.5));

        assertEquals(52.0, atf2.apply(-2));
        assertEquals(52.0, atf2.apply(100));
        assertEquals(52.0, atf2.apply(-100));
        assertEquals(52.0, atf2.apply(-1.52));

        assertEquals(49.0, atf3.apply(7));
        assertEquals(49.0, atf3.apply(100));
        assertEquals(49.0, atf3.apply(-100));
        assertEquals(49.0, atf3.apply(7));
    }

    public void testFloorIndexOfX() {
        assertEquals(1, atf1.floorIndexOfX(2.1));
        assertEquals(4, atf2.floorIndexOfX(100));
        assertEquals(0, atf3.floorIndexOfX(-100));
    }

    public void testGetCount() {
        assertEquals(10, atf1.getCount());
        assertEquals(5, atf2.getCount());
        assertEquals(7, atf3.getCount());
    }

    public void testGetX() {
        assertEquals(1.0, atf1.getX(0));
        assertEquals(-2.0, atf2.getX(0));
        assertEquals(7.0, atf3.getX(0));
    }

    public void testGetY() {
        assertEquals(1.0, atf1.getY(0));
        assertEquals(52.0, atf2.getY(0));
        assertEquals(49.0, atf3.getY(0));
    }

    public void testIndexOfX() {
        assertEquals(0, atf1.indexOfX(1.0));
        assertEquals(0, atf2.indexOfX(-2.0));
        assertEquals(-1, atf3.indexOfX(1.0));
    }

    public void testIndexOfY() {
        assertEquals(0, atf1.indexOfY(1.0));
        assertEquals(0, atf2.indexOfY(52.0));
        assertEquals(-1, atf3.indexOfY(-49.0));
    }

    public void testLeftBound() {
        assertEquals(1.0, atf1.leftBound());
        assertEquals(-2.0, atf2.leftBound());
        assertEquals(7.0, atf3.leftBound());
    }

    public void testRightBound() {
        assertEquals(10.0, atf1.rightBound());
        assertEquals(-1.0, atf2.rightBound());
        assertEquals(7.0, atf3.rightBound());
    }

    public void testSetY() {
        atf1.setY(0, 10.0);
        atf2.setY(1, 0.0);
        atf3.setY(2, -10.0);
        assertEquals(10.0, atf1.getY(0));
        assertEquals(0.0, atf2.getY(1));
        assertEquals(-10.0, atf3.getY(2));
    }

    public ArrayTabulatedFunctionTest(String testName) {
        super(testName);
    }

    public static Test suite() {
        return new TestSuite(ArrayTabulatedFunctionTest.class);
    }
}