package ru.ssau.tk.LR2.ui.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompositeFunctionRequestDTO {
    @NonNull
    private String name;
    @NonNull
    private String outer;
    @NonNull
    private String inner;
}
