package ru.ssau.tk.LR2.concurrent;

import ru.ssau.tk.LR2.functions.Point;
import ru.ssau.tk.LR2.functions.TabulatedFunction;
import ru.ssau.tk.LR2.operations.TabulatedFunctionOperationService;

import java.util.Iterator;

public class SynchronizedTabulatedFunction implements TabulatedFunction {
    private final TabulatedFunction func;

    public interface Operation<T> {
        public T apply(SynchronizedTabulatedFunction x);
    }

    public SynchronizedTabulatedFunction(TabulatedFunction func) {
        this.func = func;
    }

    @Override
    public int getCount() {
        synchronized (func) {
            return func.getCount();
        }
    }

    @Override
    public double getX(int index) {
        synchronized (func) {
            return func.getX(index);
        }
    }

    @Override
    public double getY(int index) {
        synchronized (func) {
            return func.getY(index);
        }
    }

    @Override
    public void setY(int index, double value) {
        synchronized (func) {
            func.setY(index, value);
        }
    }

    @Override
    public int indexOfX(double x) {
        synchronized (func) {
            return func.indexOfX(x);
        }
    }

    @Override
    public int indexOfY(double y) {
        synchronized (func) {
            return func.indexOfY(y);
        }
    }

    @Override
    public double leftBound() {
        synchronized (func) {
            return func.leftBound();
        }
    }

    @Override
    public double rightBound() {
        synchronized (func) {
            return func.rightBound();
        }
    }

    @Override
    public double apply(double x) {
        synchronized (func) {
            return func.apply(x);
        }
    }

    @Override
    public Iterator<Point> iterator() {
        Point[] points;

        synchronized (func) {
            points = TabulatedFunctionOperationService.asPoints(this.func);
        }

        return new Iterator<Point>() {
            private int i = 0;
            private final Point[] points_arr = points;

            @Override
            public boolean hasNext() {
                return i <= points_arr.length-1;
            }

            @Override
            public Point next() {
                return points_arr[i++];
            }
        };

    }

    synchronized public <T> T doSynchronously(Operation<T> op) {
        return op.apply(this);
    }

}
