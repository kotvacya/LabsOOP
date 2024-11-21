package ru.ssau.tk.LR2.exceptions;

public class ArrayIsNotSortedException extends RuntimeException {
    public ArrayIsNotSortedException() {}

    public ArrayIsNotSortedException(String str) {
        super(str);
    }
}
