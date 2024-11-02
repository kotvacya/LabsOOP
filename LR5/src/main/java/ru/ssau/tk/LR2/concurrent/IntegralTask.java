package ru.ssau.tk.LR2.concurrent;

import ru.ssau.tk.LR2.functions.TabulatedFunction;

import java.util.concurrent.*;

public class IntegralTask implements Callable<Double> {
    private final TabulatedFunction func;


    public IntegralTask(TabulatedFunction func) {
        this.func = func;
    }

    private class IntegralRecursiveTask extends RecursiveTask<Double> {
        private static final long serialVersionUID = -3136839265390589299L;

        private final int lower_bound;
        private final int upper_bound;

        IntegralRecursiveTask(int lower_bound, int upper_bound) {
            this.lower_bound = lower_bound;
            this.upper_bound = upper_bound;
        }

        @Override
        protected Double compute() {
            int half = lower_bound + (upper_bound - lower_bound) / 2;

            if (lower_bound < upper_bound) {
                IntegralRecursiveTask left_sum = new IntegralRecursiveTask(lower_bound, half);
                IntegralRecursiveTask right_sum = new IntegralRecursiveTask(half + 1, upper_bound);
                left_sum.fork();
                right_sum.fork();
                return left_sum.join() + right_sum.join();
            } else {
                double halfunc = func.getY(lower_bound) + (func.getY(lower_bound + 1) - func.getY(lower_bound)) / 2;
                return halfunc * (func.getX(lower_bound + 1) - func.getX(lower_bound));
            }
        }
    }

    @Override
    public Double call() {
        IntegralRecursiveTask task = new IntegralRecursiveTask(0, func.getCount() - 2);
        ForkJoinPool forkJoinPool = new ForkJoinPool();

        return forkJoinPool.invoke(task);
    }
}
