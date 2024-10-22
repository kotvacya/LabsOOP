package ru.ssau.tk.LR2.concurrent;

import ru.ssau.tk.LR2.functions.LinkedListTabulatedFunction;
import ru.ssau.tk.LR2.functions.UnitFunction;

import java.util.ArrayList;
import java.util.List;

public class MultiplyingTaskExecutor {
    public static void main(String[] args) throws InterruptedException {
        UnitFunction uf = new UnitFunction();
        LinkedListTabulatedFunction ltf = new LinkedListTabulatedFunction(uf, 1, 10000, 10000);

        List<Thread> threadList = new ArrayList<>();

        for (int i = 1; i <= 10; i++) {
            Thread thread = new Thread(new MultiplyingTask(ltf), "Thread-" + i);
            threadList.add(thread);
        }

        for (Thread el : threadList) {
            el.start();
        }

        for (Thread el : threadList) {
            el.join();
        }

        System.out.println(ltf);
    }
}
