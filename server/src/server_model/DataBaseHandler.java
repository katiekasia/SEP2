package server_model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.sql.*;

public class DataBaseHandler
{
  private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
  private static final String USERNAME = "postgres";
  private static final String PASSWORD = "papiezpolak";

  private static Connection connection;

  private DataBaseHandler()
  {
  }

  // Method to get the database connection
  public static Connection getConnection() throws SQLException
  {
    if (connection == null || connection.isClosed())
    {
      // Establish a new connection if one doesn't exist or is closed
      connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
      // Set the schema after establishing the connection
      try (Statement statement = connection.createStatement())
      {
        statement.execute("SET SEARCH_PATH TO SEP2_Cinema");
      }
    }
    return connection;
  }
  // Method to get the database connection

  // Method to close the database connection
  public static void closeConnection() throws SQLException
  {
    if (connection != null && !connection.isClosed())
    {
      connection.close();
    }
  }
  // Method to close the database connection

  // METHOD TO GET ALL THE CUSTOMERS ( USERS ) FROM DATABASE
  public static ArrayList<User> getAllCustomers() throws SQLException
  {
    ArrayList<User> customers = new ArrayList<>();
    try (Connection connection = getConnection())
    {
      String query = "SELECT * FROM Customer";
      try (Statement statement = connection.createStatement();
          ResultSet resultSet = statement.executeQuery(query))
      {
        while (resultSet.next())
        {
          String username = resultSet.getString("username");
          String fstName = resultSet.getString("name");
          String lstName = resultSet.getString("surname");
          String phoneNumber = resultSet.getString("phone_number");
          String email = resultSet.getString("email");
          User user = new User(username, fstName, lstName, phoneNumber, email);
          customers.add(user);
        }
      }
    }
    return customers;
  }
  // METHOD TO GET ALL THE CUSTOMERS ( USERS ) FROM DATABASE

  //TEST CLASS TO RETRIEVE CUSTOMERS //
  public static void main(String[] args)
  {
    try
    {
      // Establishing a connection to the database
      Connection connection = getConnection();

      if (connection != null)
      {
        System.out.println("Database connection successful!");

        try (Statement statement = connection.createStatement())
        {
          statement.execute("SET SEARCH_PATH TO SEP2_Cinema");
        }

        ArrayList<User> customers = getAllCustomers();

        // Printing details of each customer
        for (User customer : customers)
        {
          System.out.println("Username: " + customer.getUsername());
          System.out.println("First Name: " + customer.getFstName());
          System.out.println("Last Name: " + customer.getLstName());
          System.out.println("Phone Number: " + customer.getPhoneNumber());
          System.out.println("Email: " + customer.getEmail());
          System.out.println("---------------------------");
        }

        // Closing the database connection
        closeConnection();
      }
      else
      {
        System.out.println("Failed to establish database connection.");
      }
    }
    catch (SQLException e)
    {
      // Handling any SQL exceptions
      e.printStackTrace();
    }
  }
  // TEST CLASS TO RETRIEVE CUSTOMERS //

  // METHOD TO GET ALL THE ROOMS FROM DATABASE
  public static ArrayList<Room> getAllRooms() throws SQLException
  {
    ArrayList<Room> rooms = new ArrayList<>();
    try (Connection connection = getConnection())
    {
      String query = "SELECT * FROM room";
      try (Statement statement = connection.createStatement();
          ResultSet resultSet = statement.executeQuery(query))
      {
        while (resultSet.next())
        {
          int roomID = resultSet.getInt("roomID");
          int numberOfSeats = resultSet.getInt("numberOfSeats");
          Room room = new Room(roomID, numberOfSeats);
          rooms.add(room);
        }
      }
    }
    return rooms;
  }

  // method to get all the movies //
  public static ArrayList<Movie> getAllMovies() throws SQLException
  {
    ArrayList<Movie> movies = new ArrayList<>();
    try (Connection connection = getConnection())
    {
      String query = "SELECT * FROM movie";
      try (Statement statement = connection.createStatement();
          ResultSet resultSet = statement.executeQuery(query))
      {
        while (resultSet.next())
        {
          String name = resultSet.getString("name");
          String length = resultSet.getString("length");
          String description = resultSet.getString("description");
          String genre = resultSet.getString("genre");
          LocalDate releaseDate = resultSet.getDate("releasedate")
              .toLocalDate();
          Movie movie = new Movie(name, length, description, genre,
              releaseDate);
          movies.add(movie);
        }
      }
    }
    return movies;
  }

  // method to get all the movies //

  // METHOD TO GET ALL THE SCREENINGS FROM DATABASE
  public static ArrayList<Screening> getAllScreenings() throws SQLException
  {
    ArrayList<Screening> screenings = new ArrayList<>();
    try (Connection connection = getConnection())
    {
      // Include schema name in the query if needed
      String query =
          "SELECT s.screeningHour, s.screeningDate, m.name, m.length, m.description, m.genre, m.releaseDate, r.roomID, r.numberOfSeats "
              + "FROM SEP2_Cinema.Screening s " + "JOIN SEP2_Cinema.Movie m ON s.name = m.name "
              + "JOIN SEP2_Cinema.Room r ON s.roomID = r.roomID";

      try (Statement statement = connection.createStatement();
          ResultSet resultSet = statement.executeQuery(query))
      {
        while (resultSet.next())
        {
          Time screeningHour = resultSet.getTime("screeningHour");
          LocalDate screeningDate = resultSet.getDate("screeningDate")
              .toLocalDate();
          String movieName = resultSet.getString("name");
          String length = resultSet.getString("length");
          String description = resultSet.getString("description");
          String genre = resultSet.getString("genre");
          LocalDate releaseDate = resultSet.getDate("releasedate")
              .toLocalDate();

          Movie movie = new Movie(movieName, length, description, genre,
              releaseDate);
          int roomID = resultSet.getInt("roomID");
          int numberOfSeats = resultSet.getInt("numberOfSeats");

          Room room = new Room(roomID, numberOfSeats);
          Screening screening = new Screening(
              screeningHour.toLocalTime().getHour(),
              screeningHour.toLocalTime().getMinute(), screeningDate, movie,
              room);
          screenings.add(screening);
        }
      }
    }
    catch (SQLException e)
    {
      e.printStackTrace();
      throw e; // Re-throw to handle higher up
    }
    return screenings;
  }
}

  // METHOD TO GET ALL THE SCREENINGS FROM DATABASE
//  public static void main(String[] args)
//  {
//    try
//    {
//      // Establishing a connection to the database
//      Connection connection = getConnection();
//
//      if (connection != null)
//      {
//        System.out.println("Database connection successful!");
//
//        ArrayList<Screening> screenings = getAllScreenings();
//
//        // Printing details of each screening
//        for (Screening screening : screenings)
//        {
//          System.out.println("Screening Date: " + screening.getDate());
//          System.out.println("Screening Time: " + screening.getTime());
//          System.out.println("Room ID: " + screening.getRoom().getRoomID());
//          System.out.println("Movie Title: " + screening.getMovie().getName());
//          System.out.println(
//              "Movie Length: " + screening.getMovie().getLenghth());
//          System.out.println(
//              "Movie Description: " + screening.getMovie().getDescription());
//          System.out.println("Movie Genre: " + screening.getMovie().getGenre());
//          System.out.println("---------------------------");
//        }
//
//        // Closing the database connection
//        closeConnection();
//      }
//      else
//      {
//        System.out.println("Failed to establish database connection.");
//      }
//    }
//    catch (SQLException e)
//    {
//      // Handling any SQL exceptions
//      e.printStackTrace();
//    }
//  }
//}

// TEST CLASS TO RETRIEVE ROOMS
//  public static void main(String[] args)
//  {
//    try
//    {
//      // Establishing a connection to the database
//      Connection connection = getConnection();
//
//      if (connection != null)
//      {
//        System.out.println("Database connection successful!");
//
//        try (Statement statement = connection.createStatement())
//        {
//          statement.execute("SET SEARCH_PATH TO SEP2_Cinema");
//        }
//
//        ArrayList<Room> rooms = getAllRooms();
//
//        // Printing details of each customer
//        for (Room room : rooms)
//        {
//          System.out.println("Room id: " + room.getRoomID());
//          System.out.println("Number of seats: " + room.getNbSeats());
//          System.out.println("---------------------------");
//        }
//
//        // Closing the database connection
//        closeConnection();
//      }
//      else
//      {
//        System.out.println("Failed to establish database connection.");
//      }
//    }
//    catch (SQLException e)
//    {
//      // Handling any SQL exceptions
//      e.printStackTrace();
//    }
// TEST CLASS TO RETRIEVE ROOMS //





