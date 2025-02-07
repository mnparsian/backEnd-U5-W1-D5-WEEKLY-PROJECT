package com.example.GestionePrenotazioni.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Reservation {
    private long id;
    private User user;
    private Post post;
    private LocalDate check_in;
    private LocalDate check_out;
    private boolean isAvailable;

    public Reservation(User user, Post post, LocalDate check_in, LocalDate check_out, boolean isAvailable) {
        this.user = user;
        this.post = post;
        this.check_in = check_in;
        this.check_out = check_out;
        this.isAvailable = isAvailable;
    }
}
