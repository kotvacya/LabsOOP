package ru.ssau.tk.LR2.functions;

import ru.ssau.tk.LR2.hash.Hasher;

public class IntegralFunction implements MathFunction {

    final private MathFunction mf;
    final private double start, end;

    IntegralFunction(MathFunction mf, double a, double b) {
        this.mf = mf;
        start = a;
        end = b;
    }

    @Override
    public double apply(double precision) {
        double result = mf.apply(start) * (end - start);
        double old;
        long n = 2;
        do {
            old = result;
            result = 0;
            for (long i = 0; i < n; i++) {
                result += mf.apply(start + i * (end - start) / n) * (end - start) / n;
            }
            n++;
        } while (Math.abs(result - old) >= precision);
        return result;
    }

    @Override
    public long hash(Hasher h) {
        mf.hash(h);
        h.addDouble(start);
        h.addDouble(end);
        return h.getHash(IntegralFunction.class);
    }
}
