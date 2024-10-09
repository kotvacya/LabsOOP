package ru.ssau.tk.LR2.io;

import ru.ssau.tk.LR2.functions.ArrayTabulatedFunction;
import ru.ssau.tk.LR2.functions.TabulatedFunction;
import ru.ssau.tk.LR2.functions.factory.ArrayTabulatedFunctionFactory;
import ru.ssau.tk.LR2.functions.factory.LinkedListTabulatedFunctionFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class TabulatedFunctionFileReader {

    public static void main(String[] args) {
        try (FileReader fr = new FileReader("input/function.txt");
             FileReader fr2 = new FileReader("input/function.txt");
        ) {
            BufferedReader br = new BufferedReader(fr);
            BufferedReader br2 = new BufferedReader(fr2);

            ArrayTabulatedFunctionFactory atf_f = new ArrayTabulatedFunctionFactory();
            LinkedListTabulatedFunctionFactory ltf_f = new LinkedListTabulatedFunctionFactory();

            TabulatedFunction atf = FunctionsIO.readTabulatedFunction(br, atf_f);
            TabulatedFunction ltf = FunctionsIO.readTabulatedFunction(br2, ltf_f);

            System.out.println(atf.toString());
            System.out.println(ltf.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
