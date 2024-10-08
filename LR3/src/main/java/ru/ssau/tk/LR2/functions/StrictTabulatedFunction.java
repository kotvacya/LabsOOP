package ru.ssau.tk.LR2.functions;

public class StrictTabulatedFunction implements TabulatedFunction{

    private final TabulatedFunction function;

    public StrictTabulatedFunction(TabulatedFunction func){
        function = func;
    }

    @Override
    public int getCount() {
        return function.getCount();
    }

    @Override
    public double getX(int index) {
        return function.getX(index);
    }

    @Override
    public double getY(int index) {
        return function.getY(index);
    }

    @Override
    public void setY(int index, double value) {
        function.setY(index, value);
    }

    @Override
    public int indexOfX(double x) {
        return function.indexOfX(x);
    }

    @Override
    public int indexOfY(double y) {
        return function.indexOfY(y);
    }

    @Override
    public double leftBound() {
        return function.leftBound();
    }

    @Override
    public double rightBound() {
        return function.rightBound();
    }

    @Override
    public double apply(double x) throws UnsupportedOperationException {
        int index = function.indexOfX(x);
        if(index == -1) throw new UnsupportedOperationException();
        return function.getY(index);
    }
}
