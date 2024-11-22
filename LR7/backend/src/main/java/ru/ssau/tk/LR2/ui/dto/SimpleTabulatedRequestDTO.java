package ru.ssau.tk.LR2.ui.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SimpleTabulatedRequestDTO {
    @NonNull
    private String function;
    @NonNull
    private Double start;
    @NonNull
    private Double end;
    @NonNull
    private Integer count;
}
