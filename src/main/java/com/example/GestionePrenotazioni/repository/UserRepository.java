package com.example.GestionePrenotazioni.repository;

import com.example.GestionePrenotazioni.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}