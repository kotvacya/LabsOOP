package ru.ssau.tk.LR2.io;

import ru.ssau.tk.LR2.functions.Point;
import ru.ssau.tk.LR2.functions.TabulatedFunction;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.PrintWriter;

public final class FunctionsIO {

    public FunctionsIO() {
        throw new UnsupportedOperationException();
    }

    public static void writeTabulatedFunction(BufferedWriter writer, TabulatedFunction function) {
        PrintWriter pw = new PrintWriter(writer);
        pw.println(function.getCount());
        for (Point el : function) {
            pw.printf("%f %f\n", el.x, el.y);
        }
        pw.flush();
    }
}
