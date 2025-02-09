package com.example.GestionePrenotazioni.repository;

import com.example.GestionePrenotazioni.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
