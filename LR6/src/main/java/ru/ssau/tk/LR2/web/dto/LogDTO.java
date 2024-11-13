package ru.ssau.tk.LR2.web.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Setter
@Getter
public class LogDTO {
    private String text;
    private Timestamp ts;
}