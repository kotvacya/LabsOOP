package ru.ssau.tk.LR2.operations;

import ru.ssau.tk.LR2.functions.Point;
import ru.ssau.tk.LR2.functions.TabulatedFunction;

public class TabulatedFunctionOperationService {
    public static Point[] asPoints(TabulatedFunction tabulatedFunction) {
        int i = 0;
        Point[] arr = new Point[tabulatedFunction.getCount()];
        for (Point p : tabulatedFunction) {
            arr[i++] = p;
        }
        return arr;
    }
}
