package ru.ssau.tk.LR2.functions;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.junit.jupiter.api.Assertions;
import ru.ssau.tk.LR2.exceptions.ArrayIsNotSortedException;
import ru.ssau.tk.LR2.exceptions.DifferentLengthOfArraysException;

import java.util.Iterator;

class MockTabulatedFunction extends AbstractTabulatedFunction {

    private final double x0, x1;
    private final double y0, y1;

    public MockTabulatedFunction(double x0, double x1, double y0, double y1) {
        assert (x0 < x1);
        this.x0 = x0;
        this.x1 = x1;
        this.y0 = y0;
        this.y1 = y1;
    }

    @Override
    protected int floorIndexOfX(double x) {
        if (x >= x1) return 1;
        return 0;
    }

    // y = (x-x0)*(y1-y0)/(x1-x0) + y0
    @Override
    protected double extrapolateLeft(double x) {
        return interpolate(x, x0, x1, y0, y1);
    }

    @Override
    protected double extrapolateRight(double x) {
        return interpolate(x, x0, x1, y0, y1);
    }

    @Override
    protected double interpolate(double x, int floorIndex) {
        return interpolate(x, x0, x1, y0, y1);
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public double getX(int index) {
        return index == 1 ? x1 : x0;
    }

    @Override
    public double getY(int index) {
        return index == 1 ? y1 : y0;
    }

    @Override
    public void setY(int index, double value) {

    }

    @Override
    public int indexOfX(double x) {
        if (x == x0) return 0;
        if (x == x1) return 1;
        return -1;
    }

    @Override
    public int indexOfY(double y) {
        return 0;
    }

    @Override
    public double leftBound() {
        return x0;
    }

    @Override
    public double rightBound() {
        return x1;
    }
}

public class AbstractTabulatedFunctionTest extends TestCase {

    public AbstractTabulatedFunctionTest(String testName) {
        super(testName);
    }

    public static Test suite() {
        return new TestSuite(AbstractTabulatedFunctionTest.class);
    }

    MockTabulatedFunction mocker = new MockTabulatedFunction(1.0, 2.0, 2.0, 4.0);

    public void testApply() {

        assertEquals(mocker.apply(0.0), 0.0);
        assertEquals(mocker.apply(1.0), 2.0);
        assertEquals(mocker.apply(2.0), 4.0);
        assertEquals(mocker.apply(3.0), 6.0);
    }

    public void testCheckLengthIsTheSame() {
        Assertions.assertThrows(DifferentLengthOfArraysException.class, () ->
                mocker.checkLengthIsTheSame(new double[]{}, new double[]{0}));
        Assertions.assertThrows(DifferentLengthOfArraysException.class, () ->
                mocker.checkLengthIsTheSame(new double[]{1, 2}, new double[]{1, 2, 3}));
        Assertions.assertThrows(DifferentLengthOfArraysException.class, () ->
                mocker.checkLengthIsTheSame(new double[]{1}, new double[]{1, 1}));
        Assertions.assertDoesNotThrow(() -> mocker.checkLengthIsTheSame(new double[]{1}, new double[]{2}));
    }

    public void testCheckSorted() {
        Assertions.assertThrows(ArrayIsNotSortedException.class, () ->
                mocker.checkSorted(new double[]{2, 1}));
        Assertions.assertThrows(ArrayIsNotSortedException.class, () ->
                mocker.checkSorted(new double[]{3, 2, 1}));
        Assertions.assertThrows(ArrayIsNotSortedException.class, () ->
                mocker.checkSorted(new double[]{0, 0, 1}));
        Assertions.assertDoesNotThrow(() -> mocker.checkSorted(new double[]{1, 2, 3}));
    }

    public void testToString(){
        LinkedListTabulatedFunction func = new LinkedListTabulatedFunction(new double[]{0.0,0.5,1.0}, new double[]{0.0,0.25,1.0});

        assertEquals("LinkedListTabulatedFunction size = 3\n[0.0; 0.0]\n[0.5; 0.25]\n[1.0; 1.0]", func.toString());

        ArrayTabulatedFunction func2 = new ArrayTabulatedFunction(new SqrFunction(), 0.0, 1.5, 4);

        assertEquals("ArrayTabulatedFunction size = 3\n[0.0; 0.0]\n[0.5; 0.25]\n[1.0; 1.0]\n[1.5,2.25]", func2.toString());
    }
}