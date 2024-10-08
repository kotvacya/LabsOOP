package ru.ssau.tk.LR2.functions;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.junit.jupiter.api.Assertions;

import static org.junit.jupiter.api.Assertions.*;

public class UnmodifiableTabulatedFunctionTest extends TestCase {

    public void test() {
        ArrayTabulatedFunction atf = new ArrayTabulatedFunction(new double[]{1, 2, 3}, new double[]{1, 2, 3});
        LinkedListTabulatedFunction ltf = new LinkedListTabulatedFunction(new double[]{1, 2, 3, 4}, new double[]{1, 2, 3, 4});
        UnmodifiableTabulatedFunction utf1 = new UnmodifiableTabulatedFunction(atf);
        UnmodifiableTabulatedFunction utf2 = new UnmodifiableTabulatedFunction(ltf);

        assertEquals(3, utf1.getCount());
        assertEquals(4, utf2.getCount());

        assertEquals(1., utf1.leftBound());
        assertEquals(1., utf2.leftBound());

        Assertions.assertThrows(UnsupportedOperationException.class, () -> utf1.setY(1, 10));
        Assertions.assertThrows(UnsupportedOperationException.class, () -> utf2.setY(1, 10));
    }

    public UnmodifiableTabulatedFunctionTest(String testName) {
        super(testName);
    }

    public static Test suite() {
        return new TestSuite(UnmodifiableTabulatedFunctionTest.class);
    }
}