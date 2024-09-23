package ru.ssau.tk.LR2.functions;

public abstract class AbstractTabulatedFunction implements TabulatedFunction {
    protected abstract int floorIndexOfX(double x);

    protected abstract double extrapolateLeft(double x);

    protected abstract double extrapolateRight(double x);

    protected abstract double interpolate(double x, int floorIndex);

    protected double interpolate(double x, double leftX, double rightX, double leftY, double rightY) {
        return (rightY - leftY) / (rightX - leftX) * (x - leftX) + leftY;
    }

    @Override
    public double apply(double x) {
        if (x < leftBound()) return extrapolateLeft(x);
        else if (x > leftBound()) return extrapolateRight(x);
        else if (indexOfX(x) == -1) return interpolate(x, floorIndexOfX(x));
        return getY(indexOfX(x));
    }
}
