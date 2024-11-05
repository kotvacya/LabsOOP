package ru.ssau.tk.LR2.functions;


import ru.ssau.tk.LR2.hash.BasicHasher;
import ru.ssau.tk.LR2.hash.Hasher;

public class ConstantFunction implements MathFunction {

    private final double x;

    public ConstantFunction(double x) {
        this.x = x;
    }

    public double apply(double x) {
        return this.x;
    }

    @Override
    public long hash(Hasher h) {
        h.addDouble(x);
        return h.getHash(this.getClass());
    }
}
