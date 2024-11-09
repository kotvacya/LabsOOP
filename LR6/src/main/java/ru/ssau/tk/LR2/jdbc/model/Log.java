package ru.ssau.tk.LR2.jdbc.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.sql.Timestamp;
import java.time.Instant;

@Table("logs")
@Data
@NoArgsConstructor
public class Log {
    @Id
    @Setter(AccessLevel.PROTECTED)
    private int id;

    private String text;
    @Setter(AccessLevel.NONE)
    @Getter(AccessLevel.NONE)
    private long ts;

    public Timestamp getTs() {
        return new Timestamp(ts);
    }

    public void setTs(Timestamp ts) {
        this.ts = ts.getTime();
    }

    public Log(String text){
        this.text = text;
        ts = Timestamp.from(Instant.now()).getTime();
    }

    public Log(String text, Timestamp ts){
        this.text = text;
        this.ts = ts.getTime();
    }
}
