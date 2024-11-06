package ru.ssau.tk.LR2.concurrent;

import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.junit.jupiter.api.Test;
import ru.ssau.tk.LR2.exceptions.ExceptionsTest;
import ru.ssau.tk.LR2.functions.ArrayTabulatedFunction;
import ru.ssau.tk.LR2.functions.ConstantFunction;
import ru.ssau.tk.LR2.functions.SqrFunction;
import ru.ssau.tk.LR2.functions.UnitFunction;

import static org.junit.jupiter.api.Assertions.*;

public class IntegralTaskTest extends TestCase {

    public void testCall() {
        ArrayTabulatedFunction func1 = new ArrayTabulatedFunction(new SqrFunction(), 0.0, 3.0, 100000);
        ArrayTabulatedFunction func2 = new ArrayTabulatedFunction(new UnitFunction(), 1.0, 5.0, 200000);
        ArrayTabulatedFunction func3 = new ArrayTabulatedFunction(new ConstantFunction(5), -19.0, 27.0, 300000);
        IntegralTask it1 = new IntegralTask(func1);
        IntegralTask it2 = new IntegralTask(func2);
        IntegralTask it3 = new IntegralTask(func3);
        assertEquals(9., it1.call(), 1e-6);
        assertEquals(4., it2.call(), 1e-6);
        assertEquals(230., it3.call(), 1e-6);
    }

    public IntegralTaskTest(String testName) {
        super(testName);
    }

    public static junit.framework.Test suite() {
        return new TestSuite(IntegralTaskTest.class);
    }
}