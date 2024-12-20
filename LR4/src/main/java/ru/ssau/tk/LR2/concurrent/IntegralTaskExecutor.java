package ru.ssau.tk.LR2.concurrent;

import ru.ssau.tk.LR2.functions.ArrayTabulatedFunction;
import ru.ssau.tk.LR2.functions.SqrFunction;

public class IntegralTaskExecutor {
    public static void main(String[] args) {
        ArrayTabulatedFunction func = new ArrayTabulatedFunction(new SqrFunction(), 0.0, 3.0, 1000000);

        IntegralTask it = new IntegralTask(func);
        System.out.println(it.call());
    }
}
