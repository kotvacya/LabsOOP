package ru.ssau.tk.LR2.operations;

import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.junit.jupiter.api.Test;
import ru.ssau.tk.LR2.functions.ArrayTabulatedFunction;
import ru.ssau.tk.LR2.functions.ArrayTabulatedFunctionTest;
import ru.ssau.tk.LR2.functions.LinkedListTabulatedFunction;
import ru.ssau.tk.LR2.functions.Point;

import static org.junit.jupiter.api.Assertions.*;

public class TabulatedFunctionOperationServiceTest extends TestCase {

    public void testAsPoints() {
        ArrayTabulatedFunction atf = new ArrayTabulatedFunction(new double[]{1, 2, 3}, new double[]{1, 2, 3});
        LinkedListTabulatedFunction ltf = new LinkedListTabulatedFunction(new double[]{1, 2, 3}, new double[]{1, 2, 3});
        Point[] atf_arr = TabulatedFunctionOperationService.asPoints(atf);
        Point[] ltf_arr = TabulatedFunctionOperationService.asPoints(ltf);

        Point[] arr = new Point[]{new Point(1, 1), new Point(2, 2), new Point(3, 3)};

        for (int i = 0; i < atf.getCount(); ++i) {
            assertEquals(arr[i].x, atf_arr[i].x);
            assertEquals(arr[i].y, atf_arr[i].y);
            assertEquals(arr[i].x, ltf_arr[i].x);
            assertEquals(arr[i].y, ltf_arr[i].y);
        }
    }

    public TabulatedFunctionOperationServiceTest(String testName) {
        super(testName);
    }

    public static junit.framework.Test suite() {
        return new TestSuite(TabulatedFunctionOperationServiceTest.class);
    }
}