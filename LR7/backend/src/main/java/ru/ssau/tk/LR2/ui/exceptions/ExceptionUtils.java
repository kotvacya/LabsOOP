package ru.ssau.tk.LR2.ui.exceptions;

import java.util.Objects;

public abstract class ExceptionUtils {
    public static String exceptionMessageWithClass(Exception e){
        return e.getClass().getSimpleName() + (Objects.nonNull(e.getMessage()) ? ": " + e.getMessage() : "");
    }
}
