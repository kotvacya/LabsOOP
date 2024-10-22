package ru.ssau.tk.LR2.concurrent;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import ru.ssau.tk.LR2.functions.ArrayTabulatedFunction;
import ru.ssau.tk.LR2.functions.ArrayTabulatedFunctionTest;

import static org.junit.jupiter.api.Assertions.*;

public class SynchronizedTabulatedFunctionTest extends TestCase {

    public void testTabulatedFunc(){
        ArrayTabulatedFunction func = new ArrayTabulatedFunction(new double[]{1.0,2.0,3.0}, new double[]{3.0,2.0,1.0});

        SynchronizedTabulatedFunction sync_func = new SynchronizedTabulatedFunction(func);

        assertEquals(sync_func.getCount(), func.getCount());
        assertEquals(sync_func.leftBound(), func.leftBound());
        assertEquals(sync_func.rightBound(), func.rightBound());
        assertEquals(sync_func.apply(1.0), func.apply(1.0));
        assertEquals(sync_func.getX(1), func.getX(1));
        assertEquals(sync_func.getY(1), func.getY(1));
        assertEquals(sync_func.indexOfY(1.0), func.indexOfY(1.0));
        assertEquals(sync_func.indexOfX(1.0), func.indexOfX(1.0));

    }

    public SynchronizedTabulatedFunctionTest(String testName) {
        super(testName);
    }

    public static Test suite() {
        return new TestSuite(SynchronizedTabulatedFunctionTest.class);
    }

}