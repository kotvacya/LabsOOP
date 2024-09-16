package ru.ssau.tk.LR2.functions;

public class CompositeFunction implements MathFunction {

    private final MathFunction outer;
    private final MathFunction inner;

    CompositeFunction(MathFunction outer, MathFunction inner) {
        this.outer = outer;
        this.inner = inner;
    }

    @Override
    public double apply(double x) {
        return outer.apply(inner.apply(x));
    }
}
