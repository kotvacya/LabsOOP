package ru.ssau.tk.LR2.functions;

import ru.ssau.tk.LR2.hash.Hasher;

public class CompositeFunction implements MathFunction {

    private final MathFunction firstFunction;
    private final MathFunction secondFunction;

    public CompositeFunction(MathFunction outer, MathFunction inner) {
        this.firstFunction = outer;
        this.secondFunction = inner;
    }

    @Override
    public double apply(double x) {
        return firstFunction.apply(secondFunction.apply(x));
    }

    @Override
    public long hash(Hasher h) {
        firstFunction.hash(h);
        secondFunction.hash(h);
        return h.getHash(ConstantFunction.class);
    }
}
