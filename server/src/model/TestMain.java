package model;

import java.sql.*;

public class TestMain {
  public static void main(String[] args) {
    try {
      // Establishing a connection to the database
      Connection connection = DataBaseHandler.getConnection();

      if (connection != null) {
        System.out.println("Database connection successful!");

        try(Statement statement= connection.createStatement()) {
          statement.execute("SET SEARCH_PATH TO SEP2_Cinema");
        }
        // Performing a SELECT query on the Customer table
        String query = "SELECT * FROM Customer";
        try (Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query)) {
          // Iterating over the results and printing them
          while (resultSet.next()) {
            String username = resultSet.getString("username");
            String name = resultSet.getString("name");
            String surname = resultSet.getString("surname");
            String phoneNumber = resultSet.getString("phone_number");
            String email = resultSet.getString("email");
            String password = resultSet.getString("password");
            int fidelityPoints = resultSet.getInt("fidelity_points");

            System.out.println("Username: " + username +
                ", Name: " + name +
                ", Surname: " + surname +
                ", Phone Number: " + phoneNumber +
                ", Email: " + email +
                ", Password: " + password +
                ", Fidelity Points: " + fidelityPoints);
          }
        }

        // Closing the database connection
        DataBaseHandler.closeConnection();
      } else {
        System.out.println("Failed to establish database connection.");
      }
    } catch (SQLException e) {
      // Handling any SQL exceptions
      e.printStackTrace();
    }
  }
}