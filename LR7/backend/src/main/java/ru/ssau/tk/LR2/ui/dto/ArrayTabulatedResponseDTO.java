package ru.ssau.tk.LR2.ui.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import ru.ssau.tk.LR2.functions.Point;
import ru.ssau.tk.LR2.functions.TabulatedFunction;

import java.util.List;
import java.util.Objects;
import java.util.stream.StreamSupport;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArrayTabulatedResponseDTO {
    List<Point> points;

    public static ArrayTabulatedResponseDTO from(TabulatedFunction function){
        if(Objects.isNull(function)) return new ArrayTabulatedResponseDTO();
        return new ArrayTabulatedResponseDTO(StreamSupport.stream(function.spliterator(), false).toList());
    }
}
