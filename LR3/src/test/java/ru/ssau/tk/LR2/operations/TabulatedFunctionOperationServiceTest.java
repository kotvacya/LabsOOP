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
        ArrayTabulatedFunction zerofunc = new ArrayTabulatedFunction(new double[]{1, 2, 3}, new double[]{0, 0, 0});
        ArrayTabulatedFunction identfunc = new ArrayTabulatedFunction(new double[]{1, 2, 3}, new double[]{1, 2, 3});
        LinkedListTabulatedFunction constfunc = new LinkedListTabulatedFunction(new double[]{1, 2, 3}, new double[]{1, 1, 1});
        LinkedListTabulatedFunction somefunc = new LinkedListTabulatedFunction(new double[]{1, 2, 3}, new double[]{7, 8, 9});

        TabulatedFunctionFactory arr_tff = new ArrayTabulatedFunctionFactory();
        TabulatedFunctionFactory list_tff = new ArrayTabulatedFunctionFactory();
        TabulatedFunctionOperationService tf_os = new TabulatedFunctionOperationService(arr_tff);

        // Тесты ArrayTabulatedFunction
        {
            TabulatedFunction tf = tf_os.add(zerofunc, identfunc);
            for (int i = 0; i < 3; ++i) {
                assertEquals(identfunc.getX(i), tf.getX(i));
                assertEquals(identfunc.getY(i), tf.getY(i));
            }
        }
        {
            TabulatedFunction tf = tf_os.subtract(zerofunc, identfunc);
            for (int i = 0; i < 3; ++i) {
                assertEquals(identfunc.getX(i), tf.getX(i));
                assertEquals(-identfunc.getY(i), tf.getY(i));
            }
        }


        // Тесты LinkedListTabulatedFunction
        tf_os.setFactory(list_tff);
        {
            TabulatedFunction tf = tf_os.add(constfunc, somefunc);
            for (int i = 0; i < 3; ++i) {
                assertEquals(somefunc.getX(i), tf.getX(i));
                assertEquals(somefunc.getY(i) + 1.0, tf.getY(i));
            }
        }
        {
            TabulatedFunction tf = tf_os.subtract(constfunc, somefunc);
            for (int i = 0; i < 3; ++i) {
                assertEquals(somefunc.getX(i), tf.getX(i));
                assertEquals(-somefunc.getY(i) + 1.0, tf.getY(i));
            }
        }

        // Смешанные тесты
        {
            TabulatedFunction tf = tf_os.add(zerofunc, somefunc);
            for (int i = 0; i < 3; ++i) {
                assertEquals(somefunc.getX(i), tf.getX(i));
                assertEquals(somefunc.getY(i), tf.getY(i));
            }
        }
        tf_os.setFactory(arr_tff);
        {
            TabulatedFunction tf = tf_os.subtract(constfunc, identfunc);
            for (int i = 0; i < 3; ++i) {
                assertEquals(somefunc.getX(i), tf.getX(i));
                assertEquals(-identfunc.getY(i) + 1.0, tf.getY(i));
            }
        }
        {
            TabulatedFunction tf = tf_os.multiply(zerofunc, identfunc);
            for (int i = 0; i < 3; ++i) {
                assertEquals(identfunc.getX(i), tf.getX(i));
                assertEquals(0.0, tf.getY(i));
            }
        }
        {
            TabulatedFunction tf = tf_os.divide(identfunc, somefunc);
            for (int i = 0; i < 3; ++i) {
                assertEquals(identfunc.getX(i), tf.getX(i));
                assertEquals(identfunc.getY(i)/somefunc.getY(i), tf.getY(i));
            }
        }
        {
            TabulatedFunction tf = tf_os.multiply(identfunc, constfunc);
            for (int i = 0; i < 3; ++i) {
                assertEquals(identfunc.getX(i), tf.getX(i));
                assertEquals(identfunc.getY(i), tf.getY(i));
            }
        }
        {
            TabulatedFunction tf = tf_os.divide(identfunc, constfunc);
            for (int i = 0; i < 3; ++i) {
                assertEquals(identfunc.getX(i), tf.getX(i));
                assertEquals(identfunc.getY(i), tf.getY(i));
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