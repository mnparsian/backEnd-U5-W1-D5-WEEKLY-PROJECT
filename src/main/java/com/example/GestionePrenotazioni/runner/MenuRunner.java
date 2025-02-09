package com.example.GestionePrenotazioni.runner;

import com.example.GestionePrenotazioni.enumration.PostType;
import com.example.GestionePrenotazioni.model.*;
import com.example.GestionePrenotazioni.service.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Component
public class MenuRunner implements CommandLineRunner {

  private final UserService userService;
  private final BuildingService buildingService;
  private final PostService postService;
  private final ReservationService reservationService;
  private final DataLoaderService dataLoaderService; // Inject DataLoaderService

  // Constructor injection
  public MenuRunner(
      UserService userService,
      BuildingService buildingService,
      PostService postService,
      ReservationService reservationService,
      DataLoaderService dataLoaderService) {
    this.userService = userService;
    this.buildingService = buildingService;
    this.postService = postService;
    this.reservationService = reservationService;
    this.dataLoaderService = dataLoaderService; // Initialize DataLoaderService
  }

  @Override
  public void run(String... args) throws Exception {
    Scanner scanner = new Scanner(System.in);

    while (true) {
      System.out.println("\n📌 *** Reservation Management Menu ***");
      System.out.println("1️⃣ Add a new user");
      System.out.println("2️⃣ Add a new building");
      System.out.println("3️⃣ Add a new post");
      System.out.println("4️⃣ Search posts by city and type");
      System.out.println("5️⃣ Make a reservation");
      System.out.println("6️⃣ Show all users");
      System.out.println("7️⃣ Show all buildings");
      System.out.println("8️⃣ Show all posts");
      System.out.println("9️⃣ Show all reservations");
      System.out.println("🔟 Load test data");
      System.out.println("0️⃣ Exit");

      System.out.print("\n🔹 Your choice: ");
      int choice = scanner.nextInt();
      scanner.nextLine(); // Consume newline character

      switch (choice) {
        case 1:
          addUser(scanner);
          break;
        case 2:
          addBuilding(scanner);
          break;
        case 3:
          addPost(scanner);
          break;
        case 4:
          searchPostsByCityAndType(scanner);
          break;
        case 5:
          makeReservation(scanner);
          break;
        case 6:
          showUsers();
          break;
        case 7:
          showBuildings();
          break;
        case 8:
          showPosts();
          break;
        case 9:
          showReservations();
          break;
        case 10:
          dataLoaderService.loadTestData();
          break;
        case 0:
          System.out.println("👋 Program exited.");
          scanner.close();
          return;
        default:
          System.out.println("❌ Invalid option.");
      }
    }
  }

  // ✅ Add new user
  private void addUser(Scanner scanner) {
    System.out.print("👤 Username: ");
    String username = scanner.nextLine();
    System.out.print("📛 Full name: ");
    String fullName = scanner.nextLine();
    System.out.print("📧 Email: ");
    String email = scanner.nextLine();

    User user = new User(username, fullName, email);
    userService.addUser(user);
    System.out.println("✅ User added successfully.");
  }

  // ✅ Add new building
  private void addBuilding(Scanner scanner) {
    System.out.print("🏢 Building name: ");
    String name = scanner.nextLine();
    System.out.print("📍 City: ");
    String city = scanner.nextLine();
    System.out.print("🛣 Address: ");
    String address = scanner.nextLine();

    Building building = new Building(name, city, address);
    buildingService.addBuilding(building);
    System.out.println("✅ Building added successfully.");
  }

  // ✅ Add new post
  private void addPost(Scanner scanner) {
    System.out.print("🏷 Unique code: ");
    String uniqueCode = scanner.nextLine();
    System.out.print("📝 Description: ");
    String description = scanner.nextLine();
    System.out.print("🔢 Maximum occupants: ");
    int maxOccupants = scanner.nextInt();
    scanner.nextLine(); // Consume newline character

    System.out.println("📌 Choose post type (PRIVATE, OPEN_SPACE, MEETING_ROOM): ");
    String typeStr = scanner.nextLine();
    PostType type = PostType.valueOf(typeStr.toUpperCase());

    System.out.println("🏢 Available buildings:");
    showBuildings();
    System.out.print("🔹 Enter building ID: ");
    Long buildingId = scanner.nextLong();
    scanner.nextLine();

    Optional<Building> building = buildingService.getBuildingById(buildingId);
    if (building.isPresent()) {
      Post post = new Post(uniqueCode, description, type, maxOccupants, building.get());
      postService.addPost(post);
      System.out.println("✅ Post added successfully.");
    } else {
      System.out.println("❌ Building not found.");
    }
  }

  // ✅ Make a reservation
  private void makeReservation(Scanner scanner) {
    System.out.println("👤 Available users:");
    showUsers();
    System.out.print("🔹 Enter user ID: ");
    Long userId = scanner.nextLong();
    scanner.nextLine();

    System.out.println("📌 Available posts:");
    showPosts();
    System.out.print("🔹 Enter post ID: ");
    Long postId = scanner.nextLong();
    scanner.nextLine();

    System.out.print("📅 Reservation date (YYYY-MM-DD): ");
    LocalDate reservationDate = LocalDate.parse(scanner.nextLine());

    Optional<User> user = userService.getUserById(userId);
    Optional<Post> post = postService.getPostById(postId);

    if (user.isPresent() && post.isPresent()) {
      // Check if the user already has a reservation on this date
      boolean userHasReservation =
          reservationService.getReservationsByUser(user.get()).stream()
              .anyMatch(reservation -> reservation.getCheck_in().equals(reservationDate));

      if (userHasReservation) {
        System.out.println("❌ The user already has a reservation on this date.");
        return;
      }

      // Check if the post is already booked on this date
      boolean postIsBooked =
          reservationService.getReservationsByPost(post.get()).stream()
              .anyMatch(reservation -> reservation.getCheck_in().equals(reservationDate));

      if (postIsBooked) {
        System.out.println("❌ The post is already booked on this date.");
        return;
      }

      // Make the reservation
      Reservation reservation =
          new Reservation(user.get(), post.get(), reservationDate, reservationDate, true);
      reservationService.addReservation(reservation);
      System.out.println("✅ Reservation completed successfully.");
    } else {
      System.out.println("❌ User or post not found.");
    }
  }

  // ✅ Show users
  private void showUsers() {
    List<User> users = userService.getAllUsers();
    if (users.isEmpty()) {
      System.out.println("🚫 No users registered.");
    } else {
      users.forEach(
          user ->
              System.out.println(
                  "ID: "
                      + user.getId()
                      + ", Username: "
                      + user.getUserName()
                      + ", Full Name: "
                      + user.getFullName()));
    }
  }

  // ✅ Show buildings
  private void showBuildings() {
    List<Building> buildings = buildingService.getAllBuildings();
    if (buildings.isEmpty()) {
      System.out.println("🚫 No buildings registered.");
    } else {
      buildings.forEach(
          building -> System.out.println(building.getId() + " - " + building.getName() + " - City:  " + building.getCity() + " - Address: " + building.getAddress()));
    }
  }

  // ✅ Show posts
  private void showPosts() {
    List<Post> posts = postService.getAllPosts();
    if (posts.isEmpty()) {
      System.out.println("🚫 No posts registered.");
    } else {
      posts.forEach(
          post ->
              System.out.println(
                  "ID: "
                      + post.getId()
                      + ", Code: "
                      + post.getUniqueCode()
                      +", Building: "
                      +post.getBuilding()
                      + ", Type: "
                      + post.getType()
                      + ", Description: "
                      + post.getDescription()));
    }
  }

  // ✅ Show reservations
  private void showReservations() {
    List<Reservation> reservations = reservationService.getAllReservations();
    if (reservations.isEmpty()) {
      System.out.println("🚫 No reservations found.");
    } else {
      reservations.forEach(
          reservation ->
              System.out.println(
                  "📝 Reservation "
                      + reservation.getId()
                      + ": "
                      + reservation.getUser().getUserName()
                      + " -> "
                      + reservation.getPost().getUniqueCode()
                      + " ("
                      + reservation.getCheck_in()
                      + " to "
                      + reservation.getCheck_out()
                      + ")"));
    }
  }

  private void searchPostsByCityAndType(Scanner scanner) {
    System.out.print("🌆 Enter city: ");
    String city = scanner.nextLine();

    System.out.print("📌 Enter post type (PRIVATE, OPEN_SPACE, MEETING_ROOM): ");
    String typeStr = scanner.nextLine();
    PostType type = PostType.valueOf(typeStr.toUpperCase());

    List<Post> posts = postService.getPostsByCityAndType(city, type);
    if (posts.isEmpty()) {
      System.out.println("🚫 No posts found for the specified city and type.");
    } else {
      posts.forEach(
          post ->
              System.out.println(
                  "ID: "
                      + post.getId()
                      + ", Code: "
                      + post.getUniqueCode()
                      + ", Type: "
                      + post.getType()
                      + ", Description: "
                      + post.getDescription()));
    }
  }
}
