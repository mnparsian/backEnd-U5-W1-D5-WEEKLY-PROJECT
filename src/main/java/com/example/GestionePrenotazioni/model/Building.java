package com.example.GestionePrenotazioni.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;
import org.springframework.boot.autoconfigure.web.WebProperties;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Building {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String city;
    private String address;
    @OneToMany(mappedBy = "building", cascade = CascadeType.ALL)
    private List<Post> posts;

    public Building(String name, String city, String address) {
        this.name = name;
        this.city = city;
        this.address = address;
    }

    @Override
    public String toString() {
        return "Building{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", city='" + city + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
