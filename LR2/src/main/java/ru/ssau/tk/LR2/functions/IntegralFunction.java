package ru.ssau.tk.LR2.functions;

public class IntegralFunction implements MathFunction {

    private double result;

    IntegralFunction(MathFunction mf, double a, double b, double precision) {
        result = mf.apply(a) * (b - a);
        double old;
        long n = 2;
        do {
            old = result;
            result = 0;
            for (long i = 0; i < n; i++) {
                result += mf.apply(a + i * (b - a) / n) * (b - a) / n;
            }
            n++;
        } while (Math.abs(result - old) >= precision);
    }

    @Override
    public double apply(double x) {
        return result;
    }
}
