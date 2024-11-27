package ru.ssau.tk.LR2.jpa.repository;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import ru.ssau.tk.LR2.jpa.model.User;

import java.io.Serial;
import java.io.Serializable;

@Embeddable
public class EmbededUserKeyId implements Serializable {

    @Serial
    private static final long serialVersionUID = 5381539191780886198L;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    User user;

    @Column(nullable = false)
    private String name;
}
