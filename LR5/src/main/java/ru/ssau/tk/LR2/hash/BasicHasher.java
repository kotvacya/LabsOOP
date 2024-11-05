package ru.ssau.tk.LR2.hash;

public class BasicHasher implements Hasher {
    long hash;

    @Override
    public void addInt(int i) {
        hash += 1;
    }

    @Override
    public void addDouble(double d) {
        hash += 100;
    }

    @Override
    public void addString(String s) {
        hash += 10000;
    }

    @Override
    public long getHash(Class<? extends Hashable> c) {
        addString(c.getName());
        return hash;
    }
}
