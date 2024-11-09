package ru.ssau.tk.LR2.operations;

import ru.ssau.tk.LR2.functions.MathFunction;

public abstract class SteppingDifferentialOperator implements DifferentialOperator<MathFunction> {
    private double step;

    public SteppingDifferentialOperator(double step) {
        this.step = step;
    }

    public double getStep() {
        return step;
    }

    public void setStep(double step) {
        if (step <= 0 || !Double.isFinite(step)) throw new IllegalArgumentException();
        this.step = step;
    }
}
