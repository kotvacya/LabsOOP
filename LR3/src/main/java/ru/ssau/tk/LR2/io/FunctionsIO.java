package ru.ssau.tk.LR2.io;

import ru.ssau.tk.LR2.functions.Point;
import ru.ssau.tk.LR2.functions.TabulatedFunction;
import ru.ssau.tk.LR2.functions.factory.TabulatedFunctionFactory;

<<<<<<< Updated upstream
import java.io.*;
=======
<<<<<<< HEAD
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;
=======
import java.io.*;
>>>>>>> 5e98e041ea90ffc8123181dc6a9dcae71f7243cc
>>>>>>> Stashed changes

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

<<<<<<< Updated upstream
=======
<<<<<<< HEAD
    public static TabulatedFunction readTabulatedFunction(BufferedReader reader, TabulatedFunctionFactory factory) throws IOException {
        int count = Integer.parseInt(reader.readLine());

        NumberFormat nf = NumberFormat.getInstance(Locale.forLanguageTag("ru"));
        double[] xValues = new double[count];
        double[] yValues = new double[count];

        for (int i = 0; i < count; i++) {
            String[] xy = reader.readLine().split(" ");
            try {
                xValues[i] = nf.parse(xy[0]).doubleValue();
                yValues[i] = nf.parse(xy[1]).doubleValue();
            } catch (ParseException e) {
                throw new IOException(e);
            }
        }
        return factory.create(xValues, yValues);
=======
>>>>>>> Stashed changes
    public static void writeTabulatedFunction(BufferedOutputStream outputStream, TabulatedFunction function) throws IOException {
        final DataOutputStream outputdata = new DataOutputStream(outputStream);

        outputdata.writeInt(function.getCount());
        for(Point p : function){
            outputdata.writeDouble(p.x);
            outputdata.writeDouble(p.y);
        }

        outputdata.flush();

<<<<<<< Updated upstream
=======
>>>>>>> 5e98e041ea90ffc8123181dc6a9dcae71f7243cc
>>>>>>> Stashed changes
    }
}
