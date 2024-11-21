package ru.ssau.tk.LR2.io;

import ru.ssau.tk.LR2.functions.ArrayTabulatedFunction;
import ru.ssau.tk.LR2.functions.SqrFunction;
import ru.ssau.tk.LR2.functions.TabulatedFunction;
import ru.ssau.tk.LR2.operations.TabulatedDifferentialOperator;

import java.io.*;

public class ArrayTabulatedFunctionSerialization {
    public static void main(String[] args) {
        try (FileOutputStream fos = new FileOutputStream("output/serialized array functions.bin")) {
            BufferedOutputStream bos = new BufferedOutputStream(fos);

            SqrFunction sqr = new SqrFunction();
            ArrayTabulatedFunction atf = new ArrayTabulatedFunction(sqr, 1, 10, 10);

            TabulatedDifferentialOperator tdf = new TabulatedDifferentialOperator();
            TabulatedFunction first_derivative = tdf.derive(atf);
            TabulatedFunction second_derivative = tdf.derive(first_derivative);

            FunctionsIO.serialize(bos, atf);
            FunctionsIO.serialize(bos, first_derivative);
            FunctionsIO.serialize(bos, second_derivative);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (FileInputStream fis = new FileInputStream("output/serialized array functions.bin")) {
            BufferedInputStream bis = new BufferedInputStream(fis);

            TabulatedFunction function1 = FunctionsIO.deserialize(bis);
            TabulatedFunction function2 = FunctionsIO.deserialize(bis);
            TabulatedFunction function3 = FunctionsIO.deserialize(bis);

            System.out.println(function1.toString());
            System.out.println(function2.toString());
            System.out.println(function3.toString());

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
