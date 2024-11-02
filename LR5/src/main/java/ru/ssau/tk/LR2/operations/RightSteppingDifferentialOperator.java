package ru.ssau.tk.LR2.operations;

import ru.ssau.tk.LR2.functions.MathFunction;

public class RightSteppingDifferentialOperator extends SteppingDifferentialOperator {

    public RightSteppingDifferentialOperator(double step) {
        super(step);
    }

    @Override
    public MathFunction derive(MathFunction function) {
        return new MathFunction() {
            @Override
            public double apply(double x) {
                return ( (function.apply(x + getStep()) - function.apply(x)) / getStep() );
            }
        };
    }
}