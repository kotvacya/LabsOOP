package ru.ssau.tk.LR2.concurrent;

import ru.ssau.tk.LR2.functions.ConstantFunction;
import ru.ssau.tk.LR2.functions.LinkedListTabulatedFunction;

public class ReadWriteTaskExecutor {
    public static void main(String[] args) {
        LinkedListTabulatedFunction func = new LinkedListTabulatedFunction(new ConstantFunction(-1.0), 0.0, 1000.0, 1001);

        Thread read_thread = new Thread(new ReadTask(func));
        Thread write_thread = new Thread(new WriteTask(func, 0.5));

        read_thread.start();
        write_thread.start();
    }
}
