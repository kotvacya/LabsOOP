package ru.ssau.tk.LR2.io;

import ru.ssau.tk.LR2.functions.Point;
import ru.ssau.tk.LR2.functions.TabulatedFunction;

import java.io.*;

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

    public static void writeTabulatedFunction(BufferedOutputStream outputStream, TabulatedFunction function) throws IOException {
        final DataOutputStream outputdata = new DataOutputStream(outputStream);

        outputdata.writeInt(function.getCount());
        for(Point p : function){
            outputdata.writeDouble(p.x);
            outputdata.writeDouble(p.y);
        }

        outputdata.flush();

    }
}
