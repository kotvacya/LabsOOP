package ru.ssau.tk.LR2.jdbc.model;

import jakarta.annotation.Generated;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

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

    public long getId() {
        return id;
    }
}
