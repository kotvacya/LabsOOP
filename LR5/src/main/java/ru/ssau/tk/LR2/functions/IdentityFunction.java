package ru.ssau.tk.LR2.functions;

import ru.ssau.tk.LR2.hash.Hasher;

public class IdentityFunction implements MathFunction {
    @Override
    public double apply(double x) {
        return x;
    }

    @Override
    public long hash(Hasher h) {
        return h.getHash(IdentityFunction.class);
    }
}
