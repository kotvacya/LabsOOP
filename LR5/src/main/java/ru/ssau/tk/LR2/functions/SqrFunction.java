package ru.ssau.tk.LR2.functions;

import ru.ssau.tk.LR2.hash.Hasher;

import java.lang.Math;


public class SqrFunction implements MathFunction {
    public double apply(double x) {
        return Math.pow(x, 2);
    }

    @Override
    public long hash(Hasher h) {
        return h.getHash(SqrFunction.class);
    }
}
