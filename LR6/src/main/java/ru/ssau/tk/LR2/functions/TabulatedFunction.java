package ru.ssau.tk.LR2.functions;

import ru.ssau.tk.LR2.hash.Hasher;

import java.util.Iterator;

public interface TabulatedFunction extends MathFunction, Iterable<Point> {

    int getCount();

    double getX(int index);

    double getY(int index);

    void setY(int index, double value);

    int indexOfX(double x);

    int indexOfY(double y);

    double leftBound();

    double rightBound();

    default Iterator<Point> iterator() {
        throw new UnsupportedOperationException();
    }

    @Override
    default long hash(Hasher h) {
        for (Point el : this) {
            h.addDouble(el.x);
            h.addDouble(el.y);
        }
        return h.getHash(this.getClass());
    }
}
