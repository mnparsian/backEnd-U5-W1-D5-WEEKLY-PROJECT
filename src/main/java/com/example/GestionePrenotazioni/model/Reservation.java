package com.example.GestionePrenotazioni.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;
    private LocalDate check_in;
    private LocalDate check_out;
    private boolean active;

    public Reservation(User user, Post post, LocalDate check_in, LocalDate check_out, boolean isAvailable) {
        this.user = user;
        this.post = post;
        this.check_in = check_in;
        this.check_out = check_out;
        this.active = isAvailable;
    }
}
