package ru.ssau.tk.LR2.functions.factory;

import ru.ssau.tk.LR2.functions.StrictTabulatedFunction;
import ru.ssau.tk.LR2.functions.TabulatedFunction;

public interface TabulatedFunctionFactory {
    TabulatedFunction create(double[] xValues, double[] yValues);

    default TabulatedFunction createStrict(double[] xValues, double[] yValues){
        return new StrictTabulatedFunction(create(xValues, yValues));
    }
}
