package com.example.GestionePrenotazioni.service;

import com.example.GestionePrenotazioni.enumration.PostType;
import com.example.GestionePrenotazioni.model.*;
import com.github.javafaker.Faker;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Locale;
import java.util.Random;

@Service
public class DataLoaderService {

    private final UserService userService;
    private final BuildingService buildingService;
    private final PostService postService;
    private final ReservationService reservationService;

    public DataLoaderService(UserService userService, BuildingService buildingService, PostService postService, ReservationService reservationService) {
        this.userService = userService;
        this.buildingService = buildingService;
        this.postService = postService;
        this.reservationService = reservationService;
    }

  public void loadTestData() {
    Faker faker = new Faker(new Locale("it"));
    Random random = new Random();

    // Add test users
    for (int i = 0; i < 10; i++) {
      User user =
          new User(
              faker.name().username(), faker.name().fullName(), faker.internet().emailAddress());
      userService.addUser(user);
    }
    System.out.println("✅ Added 10 test users!");

    // Add test buildings
    for (int i = 0; i < 5; i++) {
      Building building =
          new Building(
              faker.company().name(), faker.address().city(), faker.address().streetAddress());
      buildingService.addBuilding(building);
    }
    System.out.println("✅ Added 5 test buildings!");

    // Add test posts
    for (int i = 0; i < 15; i++) {
      Post post =
          new Post(
              "P" + faker.number().digits(3),
              faker.lorem().sentence(),
              PostType.values()[random.nextInt(PostType.values().length)],
              random.nextInt(10) + 1,
              buildingService.getAllBuildings().get(random.nextInt(5)));
      postService.addPost(post);
    }
    System.out.println("✅ Added 15 test posts!");

    // Add test reservations
    for (int i = 0; i < 20; i++) {
      User user = userService.getAllUsers().get(random.nextInt(10));
      Post post = postService.getAllPosts().get(random.nextInt(15));
      LocalDate today = LocalDate.now();


      Reservation reservation = new Reservation(user, post, today, today, true);
      try {
        reservationService.addReservation(reservation);
      } catch (Exception e) {
        System.out.println("❌ Error adding reservation: " + e.getMessage());
      }
    }
    System.out.println("✅ Added 20 test reservations for one-day bookings!");
        }
    }
