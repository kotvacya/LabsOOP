package ru.ssau.tk.LR2.functions;

import ru.ssau.tk.LR2.ui.annotations.SimpleFunction;

import java.lang.Math;

@SimpleFunction(canonName = "Квадратичная функция")
public class SqrFunction implements MathFunction {
    public double apply(double x) {
        return Math.pow(x, 2);
    }
}
