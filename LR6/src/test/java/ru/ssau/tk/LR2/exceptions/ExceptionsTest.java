package ru.ssau.tk.LR2.exceptions;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.junit.jupiter.api.Assertions;
import ru.ssau.tk.LR2.concurrent.SynchronizedTabulatedFunction;
import ru.ssau.tk.LR2.functions.*;
import ru.ssau.tk.LR2.operations.TabulatedFunctionOperationService;

import java.util.Iterator;

public class ExceptionsTest extends TestCase {
    public void testArrayIsNotSortedException() {
        ArrayIsNotSortedException exc = new ArrayIsNotSortedException("txt");
        assertEquals("txt", exc.getMessage());
        Assertions.assertInstanceOf(RuntimeException.class, new ArrayIsNotSortedException());
    }

    public void testDifferentLengthOfArraysException() {
        DifferentLengthOfArraysException exc = new DifferentLengthOfArraysException("txt");
        assertEquals("txt", exc.getMessage());
        Assertions.assertInstanceOf(RuntimeException.class, new DifferentLengthOfArraysException());
    }

    public void testInconsistentFunctionsException() {
        InconsistentFunctionsException exc = new InconsistentFunctionsException("txt");
        assertEquals("txt", exc.getMessage());
        Assertions.assertInstanceOf(RuntimeException.class, new InconsistentFunctionsException());
    }

    public void testInterpolationException() {
        InterpolationException exc = new InterpolationException("txt");
        assertEquals("txt", exc.getMessage());
        Assertions.assertInstanceOf(RuntimeException.class, new InterpolationException());
    }

    public ExceptionsTest(String testName) {
        super(testName);
    }

    public static Test suite() {
        return new TestSuite(ExceptionsTest.class);
    }

}