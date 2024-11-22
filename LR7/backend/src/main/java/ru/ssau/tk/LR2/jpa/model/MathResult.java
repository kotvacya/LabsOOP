package ru.ssau.tk.LR2.jpa.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "MathResult")
@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class MathResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.PROTECTED)
    private int id = 0;

    @NonNull
    @Column(nullable = false)
    private double x;
    @NonNull
    @Column(nullable = false)
    private double y;
    @NonNull
    @Column(nullable = false)
    private long hash;
}
