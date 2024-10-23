package ru.ssau.tk.LR2.concurrent;

import ru.ssau.tk.LR2.functions.TabulatedFunction;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveAction;

public class IntegralTask implements Callable<Double> {
    private final TabulatedFunction func;
    private final double PRECISION;
    private Double result = 0.0;
    ForkJoinPool forkJoinPool = new ForkJoinPool();

    public IntegralTask(TabulatedFunction func) {
        this.func = func;
        PRECISION = 0.1;
    }

    public IntegralTask(TabulatedFunction func, double precision) {
        this.func = func;
        PRECISION = precision;
    }

    private class IntegralRecursiveAction extends RecursiveAction {
        private static final long serialVersionUID = -3136839265390589299L;

        private final double lower_bound;
        private final double upper_bound;

        IntegralRecursiveAction(double lower_bound, double upper_bound){
            this.lower_bound = lower_bound;
            this.upper_bound = upper_bound;
        }

        @Override
        protected void compute() {
            if(upper_bound - lower_bound < PRECISION){
                run();
            }else{
                ForkJoinTask.invokeAll(createSubtasks());
            }
        }

        private List<IntegralRecursiveAction> createSubtasks(){
            List<IntegralRecursiveAction> subtasks = new ArrayList<>();

            double half = lower_bound+(upper_bound-lower_bound)/2;

            subtasks.add(new IntegralRecursiveAction(lower_bound, half));
            subtasks.add(new IntegralRecursiveAction(half, upper_bound));

            return subtasks;
        }

        private void run(){
            double half = lower_bound+(upper_bound-lower_bound)/2;

            synchronized (func) {
                result += func.apply(half) * (upper_bound - lower_bound);
            }
        }
    }

    @Override
    public Double call() {
        IntegralRecursiveAction task = new IntegralRecursiveAction(func.leftBound(), func.rightBound());
        ForkJoinTask.invokeAll(task);

        return result;
    }
}
