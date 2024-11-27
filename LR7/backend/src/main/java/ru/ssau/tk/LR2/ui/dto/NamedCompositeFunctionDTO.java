package ru.ssau.tk.LR2.ui.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.ssau.tk.LR2.functions.CompositeFunction;

@Data
@AllArgsConstructor
public class NamedCompositeFunctionDTO {
    String name;
    CompositeFunction function;
}
