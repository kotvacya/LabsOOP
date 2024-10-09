package ru.ssau.tk.LR2.functions;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayTabulatedFunction extends AbstractTabulatedFunction implements Insertable, Removable, Serializable {

    private static final long serialVersionUID = -8279954938813966470L;
    private double[] xValues;
    private double[] yValues;
    private int count;

    public ArrayTabulatedFunction(double[] xValues, double[] yValues) throws IllegalArgumentException {
        if (xValues.length < 2) throw new IllegalArgumentException("Array length <2");
        checkLengthIsTheSame(xValues, yValues);
        checkSorted(xValues);

        this.count = xValues.length;
        this.xValues = Arrays.copyOf(xValues, count);
        this.yValues = Arrays.copyOf(yValues, count);
    }

    public ArrayTabulatedFunction(MathFunction source, double xFrom, double xTo, int count) throws IllegalArgumentException {
        if (count < 2) throw new IllegalArgumentException("Array length <2");

        this.count = count;
        this.xValues = new double[count];
        this.yValues = new double[count];

        if (xFrom > xTo) {
            double temp = xFrom;
            xFrom = xTo;
            xTo = temp;
        }

        int j = 0;
        double step = (xTo - xFrom) / (count - 1);
        for (double i = xFrom; i <= xTo && j < (count - 1); i += step, ++j) {
            xValues[j] = i;
            yValues[j] = source.apply(i);
        }
        xValues[j] = xTo;
        yValues[j] = source.apply(xTo);
    }

    @Override
    public int getCount() {
        return count;
    }

    @Override
    public double getX(int index) throws ArrayIndexOutOfBoundsException {
        return xValues[index];
    }

    @Override
    public double getY(int index) throws ArrayIndexOutOfBoundsException {
        return yValues[index];
    }

    @Override
    public void setY(int index, double value) throws ArrayIndexOutOfBoundsException {
        yValues[index] = value;
    }

    @Override
    public int indexOfX(double x) {
        for (int i = 0; i < count; ++i) {
            if (xValues[i] == x)
                return i;
        }
        return -1;
    }

    @Override
    public int indexOfY(double y) {
        for (int i = 0; i < count; ++i) {
            if (yValues[i] == y)
                return i;
        }
        return -1;
    }

    @Override
    public double leftBound() {
        return xValues[0];
    }

    @Override
    public double rightBound() {
        return xValues[count - 1];
    }

    @Override
    protected int floorIndexOfX(double x) throws IllegalArgumentException {
        if (x <= leftBound()) throw new IllegalArgumentException();
        if (x >= rightBound()) return count - 1;
        for (int i = 0; i < count; ++i) {
            if (xValues[i] > x) return i - 1;
            if (xValues[i] == x) return i;
        }
        return 0;
    }

    @Override
    protected double extrapolateLeft(double x) {
        double rightY = yValues[1];
        double leftY = yValues[0];
        double rightX = xValues[1];
        double leftX = xValues[0];
        return interpolate(x, leftX, rightX, leftY, rightY);
    }

    @Override
    protected double extrapolateRight(double x) {
        double rightY = yValues[count - 1];
        double leftY = yValues[count - 2];
        double rightX = xValues[count - 1];
        double leftX = xValues[count - 2];
        return interpolate(x, leftX, rightX, leftY, rightY);
    }

    @Override
    protected double interpolate(double x, int floorIndex) {
        if (!(x >= xValues[floorIndex] && x <= xValues[floorIndex + 1])) {
            throw new IllegalArgumentException();
        }
        double rightY = yValues[floorIndex + 1];
        double leftY = yValues[floorIndex];
        double rightX = xValues[floorIndex + 1];
        double leftX = xValues[floorIndex];
        return interpolate(x, leftX, rightX, leftY, rightY);
    }

    @Override
    public void insert(double x, double y) {
        int ix = indexOfX(x);

        if (indexOfX(x) != -1) {
            setY(ix, y);

        } else {
            double[] new_xvalues = new double[count + 1];
            double[] new_yvalues = new double[count + 1];

            if (x < leftBound()) {
                new_xvalues[0] = x;
                new_yvalues[0] = y;

                System.arraycopy(xValues, 0, new_xvalues, 1, count);
                System.arraycopy(yValues, 0, new_yvalues, 1, count);

            } else {
                int fix = floorIndexOfX(x);
                System.arraycopy(xValues, 0, new_xvalues, 0, fix + 1);
                System.arraycopy(yValues, 0, new_yvalues, 0, fix + 1);

                new_xvalues[fix + 1] = x;
                new_yvalues[fix + 1] = y;

                System.arraycopy(xValues, fix + 1, new_xvalues, fix + 2, count - fix - 1);
                System.arraycopy(yValues, fix + 1, new_yvalues, fix + 2, count - fix - 1);
            }

            xValues = new_xvalues;
            yValues = new_yvalues;

            count++;
        }
    }

    @Override
    public void remove(int index) throws ArrayIndexOutOfBoundsException {
        if (index < 0 || index >= getCount()) throw new ArrayIndexOutOfBoundsException();

        if (count == 1) {
            xValues = null;
            yValues = null;
        } else {

            double[] new_xvalues = new double[count - 1];
            double[] new_yvalues = new double[count - 1];

            System.arraycopy(xValues, 0, new_xvalues, 0, index);
            System.arraycopy(xValues, index + 1, new_xvalues, index, count - index - 1);
            System.arraycopy(yValues, 0, new_yvalues, 0, index);
            System.arraycopy(yValues, index + 1, new_yvalues, index, count - index - 1);

            xValues = new_xvalues;
            yValues = new_yvalues;
        }
        count--;
    }

    @Override
    public Iterator<Point> iterator() {
        return new Iterator<Point>() {
            int index = -1;

            @Override
            public boolean hasNext() {
                return index + 1 < getCount();
            }

            @Override
            public Point next() {
                if (!hasNext()) throw new NoSuchElementException();
                index++;
                return new Point(xValues[index], yValues[index]);
            }
        };
    }
}
