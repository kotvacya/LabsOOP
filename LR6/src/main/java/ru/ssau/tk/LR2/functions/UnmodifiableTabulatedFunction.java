package ru.ssau.tk.LR2.functions;

public class UnmodifiableTabulatedFunction implements TabulatedFunction {

    private final TabulatedFunction tf;

    public UnmodifiableTabulatedFunction(TabulatedFunction tf){
        this.tf = tf;
    }

    @Override
    public int getCount() {
        return tf.getCount();
    }

    @Override
    public double getX(int index) {
        return tf.getX(index);
    }

    @Override
    public double getY(int index) {
        return tf.getY(index);
    }

    @Override
    public void setY(int index, double value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int indexOfX(double x) {
        return tf.indexOfX(x);
    }

    @Override
    public int indexOfY(double y) {
        return tf.indexOfY(y);
    }

    @Override
    public double leftBound() {
        return tf.leftBound();
    }

    @Override
    public double rightBound() {
        return tf.rightBound();
    }

    @Override
    public double apply(double x) {
        return tf.apply(x);
    }
}
