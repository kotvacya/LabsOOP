package ru.ssau.tk.LR2.concurrent;

import ru.ssau.tk.LR2.functions.TabulatedFunction;

public class SynchronizedTabulatedFunction implements TabulatedFunction {
    private final TabulatedFunction func;

    public SynchronizedTabulatedFunction(TabulatedFunction func) {
        this.func = func;
    }

    @Override
    public int getCount() {
        return func.getCount();
    }

    @Override
    synchronized public double getX(int index) {
        return func.getX(index);
    }

    @Override
    synchronized public double getY(int index) {
        return func.getY(index);
    }

    @Override
    synchronized public void setY(int index, double value) {
        func.setY(index, value);
    }

    @Override
    synchronized public int indexOfX(double x) {
        return func.indexOfX(x);
    }

    @Override
    synchronized public int indexOfY(double y) {
        return func.indexOfY(y);
    }

    @Override
    public double leftBound() {
        return func.leftBound();
    }

    @Override
    public double rightBound() {
        return func.rightBound();
    }

    @Override
    synchronized public double apply(double x) {
        return func.apply(x);
    }
}
