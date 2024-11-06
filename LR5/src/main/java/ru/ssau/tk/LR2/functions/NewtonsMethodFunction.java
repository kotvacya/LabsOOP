package ru.ssau.tk.LR2.functions;

import ru.ssau.tk.LR2.hash.Hasher;

public class NewtonsMethodFunction implements MathFunction {

    private final static double DERIVATIVE_EPS = 1e-6;
    private final MathFunction func;
    private final double tolerance;
    private final double max_iterations;

    public NewtonsMethodFunction(MathFunction func, double tolerance) {
        this.func = func;
        this.tolerance = tolerance;
        this.max_iterations = 100000000;
    }

    public NewtonsMethodFunction(MathFunction func, double tolerance, int max_iterations) {
        this.func = func;
        this.tolerance = tolerance;
        this.max_iterations = max_iterations;
    }

    private double derivative(double x) {
        // double precision hack
        return func.apply(x + DERIVATIVE_EPS) / DERIVATIVE_EPS / 2 - func.apply(x - DERIVATIVE_EPS) / DERIVATIVE_EPS / 2;
    }

    @Override
    public double apply(double x) {
        for (int i = 0; i < 100000000; i++) {
            double y = func.apply(x);
            double y_prime = derivative(x);

            if (Math.abs(y_prime) < tolerance) break;

            double new_x = x - y / y_prime;

            if (Math.abs(new_x - x) < tolerance) {
                return new_x;
            }

            x = new_x;
        }

        return Double.NaN; // function does not converge to zero
    }

    @Override
    public long hash(Hasher h) {
        h.addDouble(DERIVATIVE_EPS);
        func.hash(h);
        h.addDouble(tolerance);
        h.addDouble(max_iterations);
        return h.getHash(NewtonsMethodFunction.class);
    }
}
