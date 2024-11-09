package ru.ssau.tk.LR2.jdbc.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Objects;

@Table("logs")
public class Log {
    @Id
    private int id;

    private String text;
    private long ts;


    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Timestamp getTimestamp() {
        return new Timestamp(ts);
    }

    public void setTimestamp(Timestamp ts) {
        this.ts = ts.getTime();
    }

    public int getId(){
        return id;
    }

    public Log(){
        text = "";
        ts = Timestamp.from(Instant.EPOCH).getTime();
    }

    public Log(String text){
        this.text = text;
        ts = Timestamp.from(Instant.now()).getTime();
    }

    public Log(String text, Timestamp ts){
        this.text = text;
        this.ts = ts.getTime();
    }

    @Override
    public String toString() {
        return "Log{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", timestamp=" + ts +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Log log = (Log) o;
        return id == log.id && Objects.equals(text, log.text) && Objects.equals(ts, log.ts);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, text, ts);
    }
}
