package ru.ssau.tk.LR2.jdbc;

public class MathResult {
    private double x;
    private double y;
    private long hash;

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public long getHash() {
        return hash;
    }

    public void setHash(long hash) {
        this.hash = hash;
    }

    public String toString(){
        return String.format("x: %f y: %f hash: %x", x, y, hash);
    }
}
