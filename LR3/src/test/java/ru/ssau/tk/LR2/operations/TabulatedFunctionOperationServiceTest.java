package ru.ssau.tk.LR2.operations;

import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.junit.jupiter.api.Test;
import ru.ssau.tk.LR2.functions.*;
import ru.ssau.tk.LR2.functions.factory.ArrayTabulatedFunctionFactory;
import ru.ssau.tk.LR2.functions.factory.TabulatedFunctionFactory;

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

    public void testOperation() {
        ArrayTabulatedFunction atf1 = new ArrayTabulatedFunction(new double[]{1, 2, 3}, new double[]{0, 0, 0});
        ArrayTabulatedFunction atf2 = new ArrayTabulatedFunction(new double[]{1, 2, 3}, new double[]{1, 2, 3});
        LinkedListTabulatedFunction ltf1 = new LinkedListTabulatedFunction(new double[]{1, 2, 3}, new double[]{1, 1, 1});
        LinkedListTabulatedFunction ltf2 = new LinkedListTabulatedFunction(new double[]{1, 2, 3}, new double[]{7, 8, 9});

        TabulatedFunctionFactory arr_tff = new ArrayTabulatedFunctionFactory();
        TabulatedFunctionFactory list_tff = new ArrayTabulatedFunctionFactory();
        TabulatedFunctionOperationService tf_os = new TabulatedFunctionOperationService(arr_tff);

        // Тесты ArrayTabulatedFunction
        {
            TabulatedFunction tf = tf_os.add(atf1, atf2);
            for (int i = 0; i < 3; ++i) {
                assertEquals(tf.getX(i), atf2.getX(i));
                assertEquals(tf.getY(i), atf2.getY(i));
            }
        }
        {
            TabulatedFunction tf = tf_os.subtract(atf1, atf2);
            for (int i = 0; i < 3; ++i) {
                assertEquals(tf.getX(i), atf2.getX(i));
                assertEquals(tf.getY(i), -atf2.getY(i));
            }
        }

        // Тесты LinkedListTabulatedFunction
        tf_os.setFactory(list_tff);
        {
            TabulatedFunction tf = tf_os.add(ltf1, ltf2);
            for (int i = 0; i < 3; ++i) {
                assertEquals(tf.getX(i), ltf2.getX(i));
                assertEquals(tf.getY(i), ltf2.getY(i) + 1);
            }
        }
        {
            TabulatedFunction tf = tf_os.subtract(ltf1, ltf2);
            for (int i = 0; i < 3; ++i) {
                assertEquals(tf.getX(i), ltf2.getX(i));
                assertEquals(tf.getY(i), -ltf2.getY(i) + 1);
            }
        }
        // Смешанные тесты
        {
            TabulatedFunction tf = tf_os.add(atf1, ltf2);
            for (int i = 0; i < 3; ++i) {
                assertEquals(tf.getX(i), ltf2.getX(i));
                assertEquals(tf.getY(i), ltf2.getY(i));
            }
        }
        tf_os.setFactory(arr_tff);
        {
            TabulatedFunction tf = tf_os.subtract(ltf1, atf2);
            for (int i = 0; i < 3; ++i) {
                assertEquals(tf.getX(i), ltf2.getX(i));
                assertEquals(tf.getY(i), -atf2.getY(i) + 1);
            }
        }
    }


    public TabulatedFunctionOperationServiceTest(String testName) {
        super(testName);
    }

    public static junit.framework.Test suite() {
        return new TestSuite(TabulatedFunctionOperationServiceTest.class);
    }
}