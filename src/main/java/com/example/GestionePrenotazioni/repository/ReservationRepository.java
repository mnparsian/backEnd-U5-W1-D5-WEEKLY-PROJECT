package com.example.GestionePrenotazioni.repository;

import com.example.GestionePrenotazioni.model.Post;
import com.example.GestionePrenotazioni.model.Reservation;
import com.example.GestionePrenotazioni.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findByUser(User user);
    List<Reservation> findByPost(Post post);
}
