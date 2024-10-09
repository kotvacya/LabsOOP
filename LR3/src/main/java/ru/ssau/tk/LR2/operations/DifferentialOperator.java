package ru.ssau.tk.LR2.operations;


import ru.ssau.tk.LR2.functions.MathFunction;

public interface DifferentialOperator<T extends MathFunction> {
    T derive(T function);
}
