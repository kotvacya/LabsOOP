package ru.ssau.tk.LR2.functions;

import ru.ssau.tk.LR2.ui.annotations.SimpleFunction;

@SimpleFunction(canonName = "Единичная функция")
public class UnitFunction extends ConstantFunction {
    public UnitFunction() {
        super(1);
    }
}
