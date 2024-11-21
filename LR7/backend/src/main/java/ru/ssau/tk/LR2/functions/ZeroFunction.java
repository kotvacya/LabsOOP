package ru.ssau.tk.LR2.functions;

import ru.ssau.tk.LR2.ui.annotations.SimpleFunction;

@SimpleFunction(canonName = "Нулевая функция")
public class ZeroFunction extends ConstantFunction {
    public ZeroFunction() {
        super(0);
    }
}
