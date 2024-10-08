package ru.ssau.tk.LR2.functions.factory;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import ru.ssau.tk.LR2.functions.ArrayTabulatedFunction;
import ru.ssau.tk.LR2.functions.LinkedListTabulatedFunction;
import ru.ssau.tk.LR2.functions.TabulatedFunction;

public class TabulatedFunctionFactoryTest extends TestCase {

    public void testFactory(){
        ArrayTabulatedFunctionFactory array_factory = new ArrayTabulatedFunctionFactory();
        TabulatedFunction func1 = array_factory.create(new double[]{1.0,2.0,3.0}, new double[]{1.0,2.0,3.0});

        assertEquals(ArrayTabulatedFunction.class, func1.getClass());

        LinkedListTabulatedFunctionFactory linked_factory = new LinkedListTabulatedFunctionFactory();
        TabulatedFunction func2 = linked_factory.create(new double[]{1.0,2.0,3.0}, new double[]{1.0,2.0,3.0});

        assertEquals(LinkedListTabulatedFunction.class, func2.getClass());
    }

    public TabulatedFunctionFactoryTest(String testName) {
        super(testName);
    }

    public static Test suite() {
        return new TestSuite(TabulatedFunctionFactoryTest.class);
    }
}