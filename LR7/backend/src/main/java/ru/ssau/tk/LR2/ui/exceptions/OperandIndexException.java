package ru.ssau.tk.LR2.ui.exceptions;

public class OperandIndexException extends BaseUIException{
    public OperandIndexException() {
        super("This operand doesn't exist");
    }
}
