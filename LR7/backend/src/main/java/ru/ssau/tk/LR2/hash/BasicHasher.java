package ru.ssau.tk.LR2.hash;

import java.util.Objects;

public class BasicHasher implements Hasher {

    private long hash;

    @Override
    public void addInt(int i) {
        hash = Objects.hash(hash, Integer.hashCode(i));
    }

    @Override
    public void addDouble(double d) {
        hash = Objects.hash(hash, Double.hashCode(d));
    }

    @Override
    public void addString(String s) {
        hash = Objects.hash(hash, s.hashCode());
    }

    @Override
    public long getHash(Class<? extends Hashable> c) {
        addString(c.getName());
        return hash;
    }
}
