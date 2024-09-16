package ru.ssau.tk.LR2.functions;

public class CompositeFunction implements MathFunction {

    private final MathFunction firstFunction;
    private final MathFunction secondFunction;

    CompositeFunction(MathFunction outer, MathFunction inner) {
        this.firstFunction = outer;
        this.secondFunction = inner;
    }

    @Override
    public double apply(double x) {
        return firstFunction.apply(secondFunction.apply(x));
    }
}
