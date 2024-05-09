package server_model;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;

public class TestMain
{
  public static void main(String[] args)
  {
    // DATABASE CONNECTIVITY TEST
    try {
      Connection connection = DataBaseHandler.getConnection();
      if (connection != null) {
        System.out.println("Database connection successful!");

        // Close the connection to release resources
        DataBaseHandler.closeConnection();
      } else {
        System.out.println("Failed to establish database connection.");
      }
    } catch (SQLException e) {
      // Handle any SQL exceptions
      System.out.println("Database connection error: " + e.getMessage());
    }
    // DATABASE CONNECTIVITY TEST

  }
}
