package ru.ssau.tk.LR2.io;

import ru.ssau.tk.LR2.functions.LinkedListTabulatedFunction;
import ru.ssau.tk.LR2.functions.SqrFunction;
import ru.ssau.tk.LR2.functions.TabulatedFunction;
import ru.ssau.tk.LR2.functions.factory.LinkedListTabulatedFunctionFactory;
import ru.ssau.tk.LR2.operations.TabulatedDifferentialOperator;

import java.io.*;

public class LinkedListTabulatedFunctionSerialization {
    public static void main(String[] args) {
        try( FileOutputStream out = new FileOutputStream("output/serialized linked list functions.bin") ){
            BufferedOutputStream buffout = new BufferedOutputStream(out);
            LinkedListTabulatedFunction func = new LinkedListTabulatedFunction(new SqrFunction(), 0.0, 10.0, 100);

            TabulatedDifferentialOperator tabdiff = new TabulatedDifferentialOperator(new LinkedListTabulatedFunctionFactory());

            TabulatedFunction derive1 = tabdiff.derive(func);
            TabulatedFunction derive2 = tabdiff.derive(func);

            FunctionsIO.serialize(buffout, func);
            FunctionsIO.serialize(buffout, derive1);
            FunctionsIO.serialize(buffout, derive2);
        } catch (IOException e){
            e.printStackTrace();
        }

        try( FileInputStream in = new FileInputStream("output/serialized linked list functions.bin") ){
            BufferedInputStream buffin = new BufferedInputStream(in);
            System.out.println(FunctionsIO.deserialize(buffin).toString());
            System.out.println(FunctionsIO.deserialize(buffin).toString());
            System.out.println(FunctionsIO.deserialize(buffin).toString());
        } catch (IOException | ClassNotFoundException e){
            e.printStackTrace();
        }

    }
}
