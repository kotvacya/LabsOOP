package ru.ssau.tk.LR2.hash;

public class BasicHaserFactory implements HasherFactory {
    @Override
    public Hasher create() {
        return new BasicHasher();
    }
}
