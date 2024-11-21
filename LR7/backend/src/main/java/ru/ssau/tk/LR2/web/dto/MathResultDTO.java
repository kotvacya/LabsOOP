package ru.ssau.tk.LR2.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MathResultDTO {
    private double x;
    private double y;
    private long hash;
}
