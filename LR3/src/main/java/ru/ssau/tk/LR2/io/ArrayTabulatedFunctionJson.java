package ru.ssau.tk.LR2.io;

import ru.ssau.tk.LR2.functions.ArrayTabulatedFunction;
import ru.ssau.tk.LR2.functions.SqrFunction;

import java.io.*;

public class ArrayTabulatedFunctionJson {
    public static void main(String[] args) {
        try( FileWriter out = new FileWriter("output/array.json") ){
            BufferedWriter bf = new BufferedWriter(out);

            ArrayTabulatedFunction func = new ArrayTabulatedFunction(new SqrFunction(), 0.0, 10.0, 11);

            FunctionsIO.serializeJson(bf, func);

            bf.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try( FileReader in = new FileReader("output/array.json") ){
            BufferedReader rd = new BufferedReader(in);

            ArrayTabulatedFunction func = FunctionsIO.deserializeJson(rd);

            System.out.println(func.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
