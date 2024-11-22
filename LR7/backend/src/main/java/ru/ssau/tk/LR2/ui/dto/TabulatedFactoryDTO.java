package ru.ssau.tk.LR2.ui.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.ssau.tk.LR2.functions.factory.TabulatedFunctionFactory;
import ru.ssau.tk.LR2.ui.annotations.TabulatedFactory;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TabulatedFactoryDTO {
    private String type;
    private String canonical_name;

    public static TabulatedFactoryDTO from(TabulatedFunctionFactory factory){
        return new TabulatedFactoryDTO(factory.getClass().getSimpleName(), factory.getClass().getAnnotation(TabulatedFactory.class).canonName());
    }
}
