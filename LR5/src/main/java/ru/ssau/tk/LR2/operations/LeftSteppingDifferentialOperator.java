package ru.ssau.tk.LR2.operations;

import ru.ssau.tk.LR2.functions.MathFunction;
import ru.ssau.tk.LR2.hash.Hasher;

public class LeftSteppingDifferentialOperator extends SteppingDifferentialOperator {

    public LeftSteppingDifferentialOperator(double step) {
        super(step);
    }

    @Override
    public MathFunction derive(MathFunction function) {
        return new MathFunction() {
            @Override
            public double apply(double x) {
                return ((function.apply(x) - function.apply(x - getStep())) / getStep());
            }
        };
    }
}
