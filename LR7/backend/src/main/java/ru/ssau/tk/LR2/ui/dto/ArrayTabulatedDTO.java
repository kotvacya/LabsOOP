package ru.ssau.tk.LR2.ui.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import ru.ssau.tk.LR2.functions.Point;
import ru.ssau.tk.LR2.functions.TabulatedFunction;

import java.util.List;
import java.util.stream.StreamSupport;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArrayTabulatedDTO {
    @NonNull
    List<Point> points;

    public static ArrayTabulatedDTO from(TabulatedFunction function){
        return new ArrayTabulatedDTO(StreamSupport.stream(function.spliterator(), false).toList());
    }
}
