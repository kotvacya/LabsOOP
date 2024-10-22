package ru.ssau.tk.LR2.io;

import ru.ssau.tk.LR2.functions.TabulatedFunction;
import ru.ssau.tk.LR2.functions.factory.ArrayTabulatedFunctionFactory;
import ru.ssau.tk.LR2.functions.factory.LinkedListTabulatedFunctionFactory;
import ru.ssau.tk.LR2.operations.TabulatedDifferentialOperator;

import java.io.*;

public class TabulatedFunctionFileInputStream {
    public static void main(String[] args) {
        try (FileInputStream ifstream = new FileInputStream("input/binary function.bin")) {
            TabulatedFunction func = FunctionsIO.readTabulatedFunction(new BufferedInputStream(ifstream), new ArrayTabulatedFunctionFactory());
            System.out.println(func.toString());
        } catch (IOException e){
            e.printStackTrace();
        }

        InputStreamReader rd = new InputStreamReader(System.in);

        System.out.println("Введите размер и значения функции");

        try {
            TabulatedFunction func = FunctionsIO.readTabulatedFunction(new BufferedReader(rd), new LinkedListTabulatedFunctionFactory());

            TabulatedDifferentialOperator diff_op = new TabulatedDifferentialOperator(new LinkedListTabulatedFunctionFactory());

            TabulatedFunction derived = diff_op.derive(func);

            System.out.println(derived.toString());
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
