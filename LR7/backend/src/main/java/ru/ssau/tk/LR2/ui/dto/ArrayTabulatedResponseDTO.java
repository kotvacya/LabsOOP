package ru.ssau.tk.LR2.ui.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.ssau.tk.LR2.functions.Insertable;
import ru.ssau.tk.LR2.functions.Point;
import ru.ssau.tk.LR2.functions.Removable;
import ru.ssau.tk.LR2.functions.TabulatedFunction;

import java.util.List;
import java.util.Objects;
import java.util.stream.StreamSupport;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArrayTabulatedResponseDTO {

    List<Point> points;

    boolean is_insertable;
    boolean is_removable;

    public static ArrayTabulatedResponseDTO from(TabulatedFunction function){
        if(Objects.isNull(function)) return new ArrayTabulatedResponseDTO();
        return new ArrayTabulatedResponseDTO(
                StreamSupport.stream(function.spliterator(), false).toList(),
                function instanceof Insertable,
                function instanceof Removable);
    }
}
