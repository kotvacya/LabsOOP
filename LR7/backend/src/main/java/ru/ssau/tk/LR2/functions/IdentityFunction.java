package ru.ssau.tk.LR2.functions;

import ru.ssau.tk.LR2.ui.annotations.SimpleFunction;

@SimpleFunction(canonName = "Тождественная функция")
public class IdentityFunction implements MathFunction {
    @Override
    public double apply(double x) {
        return x;
    }
}
