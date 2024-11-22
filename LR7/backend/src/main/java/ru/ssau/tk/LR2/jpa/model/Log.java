package ru.ssau.tk.LR2.jpa.model;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
import java.time.Instant;

@Entity
@Table(name="log")
@Data
@NoArgsConstructor
public class Log {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.PROTECTED)
    @Getter
    private int id;

    @Column
    private String text;

    @Column
    private Timestamp ts;

    public Log(String text) {
        this.text = text;
        ts = Timestamp.from(Instant.now());
    }

    public Log(String text, Timestamp ts) {
        this.text = text;
        this.ts = ts;
    }
}
