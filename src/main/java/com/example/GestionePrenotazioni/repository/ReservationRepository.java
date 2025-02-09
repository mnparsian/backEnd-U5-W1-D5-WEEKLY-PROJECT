package com.example.GestionePrenotazioni.repository;

import com.example.GestionePrenotazioni.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

}
