package com.example.GestionePrenotazioni.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class User {

    private long id;
    private String userName;
    private String fullName;
    private String email;

    public User(String userName, String fullName, String email) {
        this.userName = userName;
        this.fullName = fullName;
        this.email = email;
    }
}
