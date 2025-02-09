package com.example.GestionePrenotazioni.service;

import com.example.GestionePrenotazioni.service.ReservationService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class ReservationCleanupService {

    private final ReservationService reservationService;

    public ReservationCleanupService(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @Scheduled(cron = "0 0 0 * * *") // Run every day at midnight
    public void cleanUpExpiredReservations() {
        reservationService.getAllReservations().forEach(reservation -> {
            if (reservation.getCheck_out().isBefore(LocalDate.now())) {
                reservation.setActive(false);
                reservationService.updateReservation(reservation);
                System.out.println("✅ Reservation ID " + reservation.getId() + " is now inactive.");
            }
        });
    }
}
