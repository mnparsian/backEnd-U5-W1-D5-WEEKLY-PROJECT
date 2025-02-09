package com.example.GestionePrenotazioni.service;

import com.example.GestionePrenotazioni.model.Post;
import com.example.GestionePrenotazioni.model.Reservation;
import com.example.GestionePrenotazioni.model.User;
import com.example.GestionePrenotazioni.repository.ReservationRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;

    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    public void addReservation(Reservation reservation) {

        boolean userHasReservation = reservationRepository.findAll().stream()
                .anyMatch(r -> r.getUser().getId() == reservation.getUser().getId()
                        && (r.getCheck_in().equals(reservation.getCheck_in()) || r.getCheck_out().equals(reservation.getCheck_out())));

        if (userHasReservation) {
            throw new RuntimeException("❌ The user already has a reservation on this date.");
        }


        boolean postIsBooked = reservationRepository.findAll().stream()
                .anyMatch(r -> r.getPost().getId() == reservation.getPost().getId()
                        && (r.getCheck_in().equals(reservation.getCheck_in()) || r.getCheck_out().equals(reservation.getCheck_out())));

        if (postIsBooked) {
            throw new RuntimeException("❌ This post is already booked on this date.");
        }


        reservationRepository.save(reservation);
    }

    public Optional<Reservation> getReservationById(Long id) {
        return reservationRepository.findById(id);
    }

    public void deleteReservation(Long id) {
        reservationRepository.deleteById(id);
    }
    public List<Reservation> getReservationsByUser(User user) {
        return reservationRepository.findByUser(user);
    }

    public List<Reservation> getReservationsByPost(Post post) {
        return reservationRepository.findByPost(post);
    }
    public Reservation updateReservation(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

}
