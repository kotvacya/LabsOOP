package ru.ssau.tk.LR2.functions;


public class ConstantFunction implements MathFunction {

    private final double x;

    public ConstantFunction(double x) {
        this.x = x;
    }

    public double apply(double x) {
        return this.x;
    }
}
