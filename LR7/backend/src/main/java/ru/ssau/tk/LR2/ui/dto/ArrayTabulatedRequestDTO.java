package ru.ssau.tk.LR2.ui.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import ru.ssau.tk.LR2.functions.Point;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArrayTabulatedRequestDTO {
    @NonNull
    List<Point> points;
}
