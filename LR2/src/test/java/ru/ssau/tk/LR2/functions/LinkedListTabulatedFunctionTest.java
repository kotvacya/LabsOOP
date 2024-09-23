package ru.ssau.tk.LR2.functions;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class LinkedListTabulatedFunctionTest extends TestCase {
    public LinkedListTabulatedFunctionTest( String testName )
    {
        super( testName );
    }

    public static Test suite()
    {
        return new TestSuite( LinkedListTabulatedFunctionTest.class );
    }

    public void testApply() {
        LinkedListTabulatedFunction func = new LinkedListTabulatedFunction(new double[]{0.0, 1.0, 2.0, 3.0}, new double[]{1.0, 0.0, 2.0, -1.0});

        assertEquals(func.apply(0.0), 1.0);
        assertEquals(func.apply(0.5), 0.5);
        assertEquals(func.apply(1.0), 0.0);
        assertEquals(func.apply(1.5), 1.0);
        assertEquals(func.apply(2.0), 2.0);
        assertEquals(func.apply(3.0), -1.0);
        assertEquals(func.apply(-1.0), 2.0);
        assertEquals(func.apply(4.0), -4.0);

        LinkedListTabulatedFunction func2 = new LinkedListTabulatedFunction(new SqrFunction(), 1.0, 4.0, 100);

        assertEquals(func2.apply(1.0), 1.0);
        assertTrue(Math.abs(func2.apply(2.0) - 4.0) < 1e-3);
        assertEquals(func2.apply(4.0), 16.0);
    }
}