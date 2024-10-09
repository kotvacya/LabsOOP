package ru.ssau.tk.LR2.io;

import ru.ssau.tk.LR2.functions.ArrayTabulatedFunction;
import ru.ssau.tk.LR2.functions.LinkedListTabulatedFunction;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class TabulatedFunctionFileWriter {
    public static void main(String[] args) {
        try {
            FileWriter fw1 = new FileWriter("output/array function.txt");
            FileWriter fw2 = new FileWriter("output/linked list function.txt");

            BufferedWriter bf1 = new BufferedWriter(fw1);
            BufferedWriter bf2 = new BufferedWriter(fw2);

            ArrayTabulatedFunction atf = new ArrayTabulatedFunction(new double[]{1, 2, 3}, new double[]{11, 12, 13});
            LinkedListTabulatedFunction ltf = new LinkedListTabulatedFunction(new double[]{1, 2, 3}, new double[]{11, 12, 13});

            FunctionsIO.writeTabulatedFunction(bf1, atf);
            FunctionsIO.writeTabulatedFunction(bf2, ltf);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
