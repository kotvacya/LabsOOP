package ru.ssau.tk.LR2.functions;
import java.lang.Math;


public class SqrFunction implements MathFunction {
    public double apply(double x) {
        return Math.pow(x, 2);
    }
}
