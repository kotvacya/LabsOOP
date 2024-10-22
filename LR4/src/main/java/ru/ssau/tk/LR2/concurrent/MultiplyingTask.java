package ru.ssau.tk.LR2.concurrent;

import ru.ssau.tk.LR2.functions.TabulatedFunction;

public class MultiplyingTask implements Runnable {

    private TabulatedFunction tf;

    public MultiplyingTask(TabulatedFunction tf) {
        this.tf = tf;
    }

    @Override
    public void run() {
        for (int i = 0; i < tf.getCount(); ++i) {
            tf.setY(i, Math.pow(tf.getY(0), 2));
        }
    }
}
