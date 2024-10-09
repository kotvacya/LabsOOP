package ru.ssau.tk.LR2.operations;

import ru.ssau.tk.LR2.functions.Point;
import ru.ssau.tk.LR2.functions.TabulatedFunction;
import ru.ssau.tk.LR2.functions.factory.ArrayTabulatedFunctionFactory;
import ru.ssau.tk.LR2.functions.factory.TabulatedFunctionFactory;

public class TabulatedDifferentialOperator implements DifferentialOperator<TabulatedFunction> {
    private TabulatedFunctionFactory factory;

    public TabulatedDifferentialOperator(TabulatedFunctionFactory factory) {
        this.factory = factory;
    }

    public TabulatedDifferentialOperator() {
        factory = new ArrayTabulatedFunctionFactory();
    }

    public TabulatedFunctionFactory getFactory() {
        return factory;
    }

    public void setFactory(TabulatedFunctionFactory factory) {
        this.factory = factory;
    }

    @Override
    public TabulatedFunction derive(TabulatedFunction function) {
        Point[] points = TabulatedFunctionOperationService.asPoints(function);

        double[] xValues = new double[points.length];
        double[] yValues = new double[points.length];

        for (int i = 0; i < points.length - 1; i++) {
            xValues[i] = points[i].x;
            yValues[i] = (points[i + 1].y - points[i].y) / (points[i + 1].x - points[i].x);
        }

        xValues[xValues.length - 1] = points[xValues.length - 1].x;
        yValues[xValues.length - 1] = yValues[xValues.length - 2];

        return factory.create(xValues, yValues);
    }
}
