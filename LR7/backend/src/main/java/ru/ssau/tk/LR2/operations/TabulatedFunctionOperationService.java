package ru.ssau.tk.LR2.operations;

import ru.ssau.tk.LR2.exceptions.InconsistentFunctionsException;
import ru.ssau.tk.LR2.functions.Point;
import ru.ssau.tk.LR2.functions.TabulatedFunction;
import ru.ssau.tk.LR2.functions.factory.ArrayTabulatedFunctionFactory;
import ru.ssau.tk.LR2.functions.factory.TabulatedFunctionFactory;
import ru.ssau.tk.LR2.ui.annotations.TabulatedOperation;

import java.util.Objects;

public class TabulatedFunctionOperationService {

    private TabulatedFunctionFactory factory;

    public TabulatedFunctionOperationService() {
        this.factory = new ArrayTabulatedFunctionFactory();
    }

    public TabulatedFunctionOperationService(TabulatedFunctionFactory factory) {
        this.factory = factory;
    }

    public TabulatedFunctionFactory getFactory() {
        return factory;
    }

    public void setFactory(TabulatedFunctionFactory factory) {
        this.factory = factory;
    }

    public static Point[] asPoints(TabulatedFunction tabulatedFunction) {
        int i = 0;
        Point[] arr = new Point[tabulatedFunction.getCount()];
        for (Point p : tabulatedFunction) {
            arr[i++] = p;
        }
        return arr;
    }

    private interface BiOperation {
        double apply(double u, double v);
    }

    private TabulatedFunction doOperation(TabulatedFunction a, TabulatedFunction b, BiOperation operation) {
        if(Objects.isNull(a)) throw new InconsistentFunctionsException("Функция 1 не задана");
        if(Objects.isNull(b)) throw new InconsistentFunctionsException("Функция 2 не задана");

        int count = a.getCount();
        if (count != b.getCount()) throw new InconsistentFunctionsException("Точки не совпадают");

        Point[] arrA = TabulatedFunctionOperationService.asPoints(a);
        Point[] arrB = TabulatedFunctionOperationService.asPoints(b);

        double[] xValues = new double[count];
        double[] yValues = new double[count];

        for (int i = 0; i < count; i++) {
            if (arrA[i].x != arrB[i].x) throw new InconsistentFunctionsException("Точки не совпадают");
            xValues[i] = arrA[i].x;
            yValues[i] = operation.apply(arrA[i].y, arrB[i].y);
        }

        return factory.create(xValues, yValues);
    }

    @TabulatedOperation(symbol = "+")
    public TabulatedFunction add(TabulatedFunction a, TabulatedFunction b) {
        return doOperation(a, b, (u, v) -> u + v); // Double::sui
    }

    @TabulatedOperation(symbol = "-")
    public TabulatedFunction subtract(TabulatedFunction a, TabulatedFunction b) {
        return doOperation(a, b, (u, v) -> u - v);
    }

    @TabulatedOperation(symbol = "×")
    public TabulatedFunction multiply(TabulatedFunction a, TabulatedFunction b) {
        return doOperation(a, b, (u, v) -> u * v);
    }

    @TabulatedOperation(symbol = "÷")
    public TabulatedFunction divide(TabulatedFunction a, TabulatedFunction b) {
        return doOperation(a, b, (u, v) -> u / v);
    }


}
