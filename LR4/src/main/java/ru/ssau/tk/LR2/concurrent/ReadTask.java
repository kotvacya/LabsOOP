package ru.ssau.tk.LR2.concurrent;

import ru.ssau.tk.LR2.functions.TabulatedFunction;

public class ReadTask implements Runnable {
    private final TabulatedFunction func;

    public ReadTask(TabulatedFunction func){
        this.func = func;
    }

    @Override
    public void run() {
        for(int i = 0; i < func.getCount(); i++){
            System.out.printf("After read: i = %d, x = %f, y = %f%n\n", i, func.getX(i), func.getY(i));
        }
    }
}
