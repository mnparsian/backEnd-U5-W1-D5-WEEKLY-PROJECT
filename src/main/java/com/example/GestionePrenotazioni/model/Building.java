package com.example.GestionePrenotazioni.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Building {
    private long id;
    private String name;
    private String city;
    private String address;
    private List<Post> posts;

    public Building(String name, String city, String address) {
        this.name = name;
        this.city = city;
        this.address = address;
    }
}
