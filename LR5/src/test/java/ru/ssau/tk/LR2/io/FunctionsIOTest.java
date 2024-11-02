package ru.ssau.tk.LR2.io;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.junit.AfterClass;
import ru.ssau.tk.LR2.functions.*;
import ru.ssau.tk.LR2.functions.factory.ArrayTabulatedFunctionFactory;
import ru.ssau.tk.LR2.functions.factory.LinkedListTabulatedFunctionFactory;
import ru.ssau.tk.LR2.operations.TabulatedDifferentialOperator;

import java.io.*;

public class FunctionsIOTest extends TestCase {

    public void testBuffered() {
        ArrayTabulatedFunction atf = new ArrayTabulatedFunction(new double[]{1, 2, 3}, new double[]{11, 12, 13});
        LinkedListTabulatedFunction ltf = new LinkedListTabulatedFunction(new double[]{1, 2, 3}, new double[]{11, 12, 13});

        try (FileWriter fw1 = new FileWriter("temp/buffered1.txt");
             FileWriter fw2 = new FileWriter("temp/buffered2.txt");
        ) {
            BufferedWriter bf1 = new BufferedWriter(fw1);
            BufferedWriter bf2 = new BufferedWriter(fw2);

            FunctionsIO.writeTabulatedFunction(bf1, atf);
            FunctionsIO.writeTabulatedFunction(bf2, ltf);

        } catch (IOException e) {
            e.printStackTrace();
        }

        try (FileReader fr = new FileReader("temp/buffered1.txt");
             FileReader fr2 = new FileReader("temp/buffered2.txt");
        ) {
            BufferedReader br = new BufferedReader(fr);
            BufferedReader br2 = new BufferedReader(fr2);

            ArrayTabulatedFunctionFactory atf_f = new ArrayTabulatedFunctionFactory();
            LinkedListTabulatedFunctionFactory ltf_f = new LinkedListTabulatedFunctionFactory();

            TabulatedFunction atf2 = FunctionsIO.readTabulatedFunction(br, atf_f);
            TabulatedFunction ltf2 = FunctionsIO.readTabulatedFunction(br2, ltf_f);

            assertEquals(atf.getClass(), atf2.getClass());
            assertEquals(ltf.getClass(), ltf2.getClass());

            assertEquals(atf.toString(), atf2.toString());
            assertEquals(ltf.toString(), ltf2.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void testStreamBuffered() {
        ArrayTabulatedFunction func1 = new ArrayTabulatedFunction(new SqrFunction(), 0.0, 10.0, 11);
        LinkedListTabulatedFunction func2 = new LinkedListTabulatedFunction(new double[]{1.0, 2.0, 3.0}, new double[]{3.0, 2.0, 1.0});

        try (
                FileOutputStream ofstream_arr = new FileOutputStream("temp/stream1.bin");
                FileOutputStream ofstream_list = new FileOutputStream("temp/stream2.bin");
        ) {

            FunctionsIO.writeTabulatedFunction(new BufferedOutputStream(ofstream_arr), func1);
            FunctionsIO.writeTabulatedFunction(new BufferedOutputStream(ofstream_list), func2);

        } catch (IOException e) {
            e.printStackTrace();
        }

        try (
                FileInputStream ifstream1 = new FileInputStream("temp/stream1.bin");
                FileInputStream ifstream2 = new FileInputStream("temp/stream2.bin");
        ) {
            TabulatedFunction func1_new = FunctionsIO.readTabulatedFunction(new BufferedInputStream(ifstream1), new ArrayTabulatedFunctionFactory());
            TabulatedFunction func2_new = FunctionsIO.readTabulatedFunction(new BufferedInputStream(ifstream2), new LinkedListTabulatedFunctionFactory());

            assertEquals(func1.getClass(), func1_new.getClass());
            assertEquals(func2.getClass(), func2_new.getClass());

            assertEquals(func1.toString(), func1_new.toString());
            assertEquals(func2.toString(), func2_new.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void testSerialize() {
        SqrFunction sqr = new SqrFunction();
        ArrayTabulatedFunction atf = new ArrayTabulatedFunction(sqr, 1, 10, 10);
        TabulatedDifferentialOperator tdf = new TabulatedDifferentialOperator();
        TabulatedFunction first_derivative = tdf.derive(atf);
        TabulatedFunction second_derivative = tdf.derive(first_derivative);

        try (FileOutputStream fos = new FileOutputStream("temp/serialized.bin")) {
            BufferedOutputStream bos = new BufferedOutputStream(fos);

            FunctionsIO.serialize(bos, atf);
            FunctionsIO.serialize(bos, first_derivative);
            FunctionsIO.serialize(bos, second_derivative);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (FileInputStream fis = new FileInputStream("temp/serialized.bin")) {
            BufferedInputStream bis = new BufferedInputStream(fis);

            TabulatedFunction function1 = FunctionsIO.deserialize(bis);
            TabulatedFunction function2 = FunctionsIO.deserialize(bis);
            TabulatedFunction function3 = FunctionsIO.deserialize(bis);

            assertEquals(atf.toString(), function1.toString());
            assertEquals(first_derivative.toString(), function2.toString());
            assertEquals(second_derivative.toString(), function3.toString());

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void tearDown() throws Exception {
        File folder = new File("temp/");
        if (folder != null) {
            for (File file : folder.listFiles()) {
                file.delete();
            }

        }
    }

    public FunctionsIOTest(String testName) {
        super(testName);
    }

    public static Test suite() {
        return new TestSuite(FunctionsIOTest.class);
    }
}