package ru.ssau.tk.LR2.functions;

import ru.ssau.tk.LR2.hash.Hashable;

public interface MathFunction extends Hashable {
    double apply(double x);

    default CompositeFunction andThen(MathFunction afterFunction) {
        return new CompositeFunction(this, afterFunction);
    }
}
