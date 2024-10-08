package ru.ssau.tk.LR2.functions;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.junit.jupiter.api.Assertions;
import ru.ssau.tk.LR2.exceptions.ArrayIsNotSortedException;
import ru.ssau.tk.LR2.exceptions.DifferentLengthOfArraysException;

import java.util.Iterator;

public class LinkedListTabulatedFunctionTest extends TestCase {

    public void testConstructor() {
        MathFunction mf1 = new IdentityFunction();
        Assertions.assertThrows(IllegalArgumentException.class, () -> new ArrayTabulatedFunction(mf1, 1, 10, 1));
        Assertions.assertThrows(DifferentLengthOfArraysException.class, () -> new ArrayTabulatedFunction(new double[]{1, 2}, new double[]{1, 2, 3}));
        Assertions.assertThrows(DifferentLengthOfArraysException.class, () -> new ArrayTabulatedFunction(new double[]{0, 0}, new double[]{}));
        Assertions.assertThrows(ArrayIsNotSortedException.class, () -> new ArrayTabulatedFunction(new double[]{1, 3, 2}, new double[]{1 ,2, 3}));
        Assertions.assertThrows(ArrayIsNotSortedException.class, () -> new ArrayTabulatedFunction(new double[]{2, 1}, new double[]{1, 2}));
    }

    public void testApply() {
        LinkedListTabulatedFunction func = new LinkedListTabulatedFunction(
                new double[]{0.0, 1.0, 2.0, 3.0},
                new double[]{1.0, 0.0, 2.0, -1.0}
        );

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

        assertEquals(100, func2.getCount());

        assertEquals(1.0, func2.apply(1.0));
        assertEquals(4.0, func2.apply(2.0), 1e-3);
        assertEquals(16.0, func2.apply(4.0));
    }

    public void testInsertRemove() {
        LinkedListTabulatedFunction func = new LinkedListTabulatedFunction(
                new double[]{0.0, 1.0, 2.0, 3.0},
                new double[]{1.0, 0.0, 2.0, -1.0}
        );

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

        func.remove(0);
        func.remove(0);
        func.remove(0);
        func.remove(0);
        func.remove(0);

        assertEquals(0, func.getCount());
    }

    public void testSetYInsert() {
        LinkedListTabulatedFunction func = new LinkedListTabulatedFunction(
                new double[]{0.0, 1.0, 2.0, 3.0},
                new double[]{1.0, 0.0, 2.0, -1.0}
        );

        func.setY(0, 0.0);

        assertEquals(4, func.getCount());
        assertEquals(0.0, func.leftBound());
        assertEquals(0.0, func.apply(0.0));

        func.insert(2.0, 4.0);

        assertEquals(4.0, func.apply(2.0));
        assertEquals(2.0, func.apply(1.5));
    }

    public void testInsertOld() {
        LinkedListTabulatedFunction func = new LinkedListTabulatedFunction(
                new double[]{0.0, 1.0, 2.0, 3.0},
                new double[]{1.0, 0.0, 2.0, -1.0}
        );

        assertEquals(1.0, func.interpolate(1.5, 1));
    }

    public void testExceptions(){
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            LinkedListTabulatedFunction func = new LinkedListTabulatedFunction(
                    new double[]{0.0},
                    new double[]{1.0}
            );
        });

        Assertions.assertThrows(IllegalArgumentException.class, () -> new LinkedListTabulatedFunction(new SqrFunction(), 1.0, 4.0, 1));

        LinkedListTabulatedFunction func = new LinkedListTabulatedFunction(
                new double[]{0.0, 1.0, 2.0, 3.0},
                new double[]{1.0, 0.0, 2.0, -1.0}
        );

        Assertions.assertThrows(IllegalArgumentException.class, () -> func.getX(-1));
        Assertions.assertThrows(IllegalArgumentException.class, () -> func.getX(100));

        Assertions.assertThrows(IllegalArgumentException.class, () -> func.getY(-1));
        Assertions.assertThrows(IllegalArgumentException.class, () -> func.getY(100));

        Assertions.assertThrows(IllegalArgumentException.class, () -> func.setY(-1, 0.0));
        Assertions.assertThrows(IllegalArgumentException.class, () -> func.setY(100, 0.0));

        Assertions.assertThrows(IllegalArgumentException.class, () -> func.remove(-1));
        Assertions.assertThrows(IllegalArgumentException.class, () -> func.remove(100));

        Assertions.assertThrows(IllegalArgumentException.class, () -> func.floorIndexOfX(-100.0));
    }

    public void testIterator(){
        double[] x = new double[]{0.0, 1.0, 2.0, 3.0};
        double[] y = new double[]{1.0, 0.0, 2.0, -1.0};

        LinkedListTabulatedFunction func = new LinkedListTabulatedFunction(x, y);

        {
            Iterator<Point> iterator = func.iterator();
            int i = 0;
            while (iterator.hasNext()) {
                Point point = iterator.next();
                assertEquals(x[i], point.x);
                assertEquals(y[i], point.y);
                i++;
            }
        }

        {
            int i = 0;
            for (Point point : func) {
                assertEquals(x[i], point.x);
                assertEquals(y[i], point.y);
                i++;
            }
        }

        LinkedListTabulatedFunction func2 = new LinkedListTabulatedFunction(new IdentityFunction(), 0, 10, 11);

        {
            Iterator<Point> iterator = func2.iterator();
            double i = 0.0;
            while (iterator.hasNext()) {
                Point point = iterator.next();
                assertEquals(i, point.x);
                assertEquals(i, point.y);
                i += 1.0;
            }
        }

        {
            double i = 0.0;
            for (Point point : func) {
                assertEquals(i, point.x);
                assertEquals(i, point.x);
                i += 1.0;
            }
        }
    }

    public LinkedListTabulatedFunctionTest(String testName) {
        super(testName);
    }

    public static Test suite() {
        return new TestSuite(LinkedListTabulatedFunctionTest.class);
    }
}