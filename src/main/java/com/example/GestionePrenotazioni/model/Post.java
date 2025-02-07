package com.example.GestionePrenotazioni.model;

import com.example.GestionePrenotazioni.enumration.PostType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Post {
    private long id;
    private String UniqueCode;
    private String description;
    private PostType type;
    private int MaxNumber;
    private Building building;

    public Post(String uniqueCode, String description, PostType type, int maxNumber, Building building) {
        UniqueCode = uniqueCode;
        this.description = description;
        this.type = type;
        MaxNumber = maxNumber;
        this.building = building;
    }
}
