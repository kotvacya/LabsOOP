package ru.ssau.tk.LR2.hash;

public interface Hasher {
    void addInt(int i);

    void addDouble(double d);

    void addString(String s);

    long getHash(Class<? extends Hashable> c);
}
