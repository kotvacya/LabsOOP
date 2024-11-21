package ru.ssau.tk.LR2.functions.factory;

import ru.ssau.tk.LR2.functions.MathFunction;
import ru.ssau.tk.LR2.functions.StrictTabulatedFunction;
import ru.ssau.tk.LR2.functions.TabulatedFunction;
import ru.ssau.tk.LR2.functions.UnmodifiableTabulatedFunction;

public interface TabulatedFunctionFactory {
    TabulatedFunction create(double[] xValues, double[] yValues);

    TabulatedFunction create(MathFunction source, double xFrom, double xTo, int count);

    default TabulatedFunction createStrict(double[] xValues, double[] yValues){
        return new StrictTabulatedFunction(create(xValues, yValues));
    }

    default TabulatedFunction createStrictUnmodifiable(double[] xValues, double[] yValues){
        return new UnmodifiableTabulatedFunction(new StrictTabulatedFunction(create(xValues, yValues)));
    }


}
