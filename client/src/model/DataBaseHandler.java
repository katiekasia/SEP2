package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DataBaseHandler
{
  private static final String URL ="jdbc:postgresql://localhost:5432/postgres";
  private static final String USERNAME = "postgres";
  private static final String PASSWORD = "papiezpolak";
//something here for checkup//
  //secondchgeck//
  private static Connection connection;

  private DataBaseHandler() {}

  // Method to get the database connection
  public static Connection getConnection() throws SQLException {
    if (connection == null || connection.isClosed()) {
      // Establish a new connection if one doesn't exist or is closed
      connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }
    return connection;
  }

  // Method to close the database connection
  public static void closeConnection() throws SQLException {
    if (connection != null && !connection.isClosed()) {
      connection.close();
    }

  }
}
