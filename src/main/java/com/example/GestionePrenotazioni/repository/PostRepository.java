package com.example.GestionePrenotazioni.repository;

import com.example.GestionePrenotazioni.enumration.PostType;
import com.example.GestionePrenotazioni.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    @Query("SELECT p FROM Post p WHERE p.building.city = :city AND p.type = :type")
    List<Post> findByCityAndType(String city, PostType type);
}
