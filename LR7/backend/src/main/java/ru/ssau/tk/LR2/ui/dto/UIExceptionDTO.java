package ru.ssau.tk.LR2.ui.dto;

import lombok.*;

import java.sql.Timestamp;
import java.time.Instant;

@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class UIExceptionDTO {
    @NonNull
    private Integer status;
    @NonNull
    private String type;
    @NonNull
    private String error;
    private Timestamp timestamp = Timestamp.from(Instant.now());
}
