package ru.ssau.tk.LR2.jdbc.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Objects;

@Table("MathResult")
public class MathResult {

    @Id
    private int id;

    private long hash;
    private double x;
    private double y;

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

    @Override
    public String toString(){
        return String.format("x: %f y: %f hash: %x", x, y, hash);
    }

    public MathResult(){
        x = 0;
        y = 0;
        hash = 0;
    }

    public MathResult(double x, double y, long hash){
        this.x = x;
        this.y = y;
        this.hash = hash;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MathResult that = (MathResult) o;
        return id == that.id && hash == that.hash && Double.compare(x, that.x) == 0 && Double.compare(y, that.y) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, hash, x, y);
    }

    public long getId() {
        return id;
    }
}
