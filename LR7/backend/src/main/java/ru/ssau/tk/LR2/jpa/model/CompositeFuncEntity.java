package ru.ssau.tk.LR2.jpa.model;

import jakarta.persistence.*;
import lombok.*;
import ru.ssau.tk.LR2.jpa.repository.EmbededUserKeyId;

@Entity
@Table(name = "composite")
@Data
@NoArgsConstructor
public class CompositeFuncEntity {

    @EmbeddedId
    private EmbededUserKeyId key;

    @Column(nullable = false)
    private String func_outer;
    @Column(nullable = false)
    private String func_inner;
}
