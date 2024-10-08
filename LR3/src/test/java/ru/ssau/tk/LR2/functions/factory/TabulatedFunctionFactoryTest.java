package ru.ssau.tk.LR2.functions.factory;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.junit.jupiter.api.Assertions;
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

    public void testStrict(){
        ArrayTabulatedFunctionFactory array_factory = new ArrayTabulatedFunctionFactory();
        TabulatedFunction func1 = array_factory.createStrict(new double[]{1.0,2.0,3.0}, new double[]{1.0,2.0,3.0});

        Assertions.assertThrows(UnsupportedOperationException.class, () -> func1.apply(1.5));
        assertEquals(1.0, func1.apply(1.0));

        LinkedListTabulatedFunctionFactory linked_factory = new LinkedListTabulatedFunctionFactory();
        TabulatedFunction func2 = linked_factory.createStrict(new double[]{1.0,2.0,3.0}, new double[]{1.0,2.0,3.0});

        Assertions.assertThrows(UnsupportedOperationException.class, () -> func2.apply(1.5));
        assertEquals(1.0, func2.apply(1.0));
    }

    public void testStrictUnmodifiable(){
        ArrayTabulatedFunctionFactory array_factory = new ArrayTabulatedFunctionFactory();
        TabulatedFunction func1 = array_factory.createStrictUnmodifiable(new double[]{1.0,2.0,3.0}, new double[]{1.0,2.0,3.0});

        Assertions.assertThrows(UnsupportedOperationException.class, () -> func1.apply(1.5));
        Assertions.assertThrows(UnsupportedOperationException.class, () -> func1.setY(1, 10.0));
        assertEquals(1.0, func1.apply(1.0));

        LinkedListTabulatedFunctionFactory linked_factory = new LinkedListTabulatedFunctionFactory();
        TabulatedFunction func2 = linked_factory.createStrictUnmodifiable(new double[]{1.0,2.0,3.0}, new double[]{1.0,2.0,3.0});

        Assertions.assertThrows(UnsupportedOperationException.class, () -> func2.apply(1.5));
        Assertions.assertThrows(UnsupportedOperationException.class, () -> func2.setY(1, 10.0));
        assertEquals(1.0, func2.apply(1.0));
    }

    public TabulatedFunctionFactoryTest(String testName) {
        super(testName);
    }

    public static Test suite() {
        return new TestSuite(TabulatedFunctionFactoryTest.class);
    }
}