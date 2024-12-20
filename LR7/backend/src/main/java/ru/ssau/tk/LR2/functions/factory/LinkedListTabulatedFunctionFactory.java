package ru.ssau.tk.LR2.functions.factory;

import ru.ssau.tk.LR2.functions.ArrayTabulatedFunction;
import ru.ssau.tk.LR2.functions.LinkedListTabulatedFunction;
import ru.ssau.tk.LR2.functions.MathFunction;
import ru.ssau.tk.LR2.functions.TabulatedFunction;
import ru.ssau.tk.LR2.ui.annotations.TabulatedFactory;

@TabulatedFactory(canonName = "Фабрика связных списков")
public class LinkedListTabulatedFunctionFactory implements TabulatedFunctionFactory {

    @Override
    public TabulatedFunction create(double[] xValues, double[] yValues) {
        return new LinkedListTabulatedFunction(xValues, yValues);
    }

    @Override
    public TabulatedFunction create(MathFunction source, double xFrom, double xTo, int count) {
        return new LinkedListTabulatedFunction(source, xFrom, xTo, count);
    }
}
