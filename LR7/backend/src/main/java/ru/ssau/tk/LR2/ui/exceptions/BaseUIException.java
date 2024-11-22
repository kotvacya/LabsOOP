package ru.ssau.tk.LR2.ui.exceptions;

public abstract class BaseUIException extends Exception {
    private static final long serialVersionUID = -9153551285435855918L;

    public BaseUIException(String message){
        super(message);
    }
}
