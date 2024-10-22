package ru.ssau.tk.LR2.concurrent;

import ru.ssau.tk.LR2.functions.ArrayTabulatedFunction;
import ru.ssau.tk.LR2.functions.TabulatedFunction;

public class MultiplyingTask implements Runnable {

    private final TabulatedFunction tf;

    public MultiplyingTask(TabulatedFunction tf) {
        this.tf = tf;
    }

    @Override
    public void run() {
        for (int i = 0; i < tf.getCount(); ++i) {
            tf.setY(i, tf.getY(0) * 2);
        }
        System.out.println(Thread.currentThread().getName() + " закончил выполнение задачи");
    }
}
