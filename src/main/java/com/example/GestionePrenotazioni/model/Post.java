package com.example.GestionePrenotazioni.model;

import com.example.GestionePrenotazioni.enumration.PostType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(unique = true)
    private String UniqueCode;
    private String description;
    @Enumerated(EnumType.STRING)
    private PostType type;
    private int MaxNumber;
    @ManyToOne
    @JoinColumn(name = "building_id")
    private Building building;
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<Reservation> resevationList;

    public Post(String uniqueCode, String description, PostType type, int maxNumber, Building building) {
        UniqueCode = uniqueCode;
        this.description = description;
        this.type = type;
        MaxNumber = maxNumber;
        this.building = building;
    }
}
