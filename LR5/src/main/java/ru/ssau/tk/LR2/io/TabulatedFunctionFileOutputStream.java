package ru.ssau.tk.LR2.io;

import ru.ssau.tk.LR2.functions.ArrayTabulatedFunction;
import ru.ssau.tk.LR2.functions.LinkedListTabulatedFunction;
import ru.ssau.tk.LR2.functions.SqrFunction;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class TabulatedFunctionFileOutputStream {
    public static void main(String[] args) {

        try (
                FileOutputStream ofstream_arr = new FileOutputStream("output/array function.bin");
                FileOutputStream ofstream_list = new FileOutputStream("output/linked list function.bin");
        ) {

            FunctionsIO.writeTabulatedFunction(new BufferedOutputStream(ofstream_arr),
                    new ArrayTabulatedFunction(new SqrFunction(), 0.0, 10.0, 11));

            FunctionsIO.writeTabulatedFunction(new BufferedOutputStream(ofstream_list),
                    new LinkedListTabulatedFunction(new double[]{1.0, 2.0, 3.0}, new double[]{3.0, 2.0, 1.0}));

        } catch (IOException e){
            e.printStackTrace();
        }

    }
}
