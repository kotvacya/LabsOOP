package ru.ssau.tk.LR2.jdbc.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Objects;

@Table("MathResult")
@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class MathResult {

    @Id
    @Setter(AccessLevel.PROTECTED)
    private int id = 0;

    @NonNull
    private double x;
    @NonNull
    private double y;
    @NonNull
    private long hash;
}
