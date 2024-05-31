package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *Handles database connection
 * @version 3.0   may 2024
 * @author Michal Barczuk, Kasia, Sandut, Catalina
 */
public class DataBaseHandler
{
  private static final String URL ="jdbc:postgresql://localhost:5432/postgres";
  private static final String USERNAME = "postgres";

  private static final String PASSWORD = "sukablyat";

//something here for checkup//
  //secondchgeck//

  private static Connection connection;

  private DataBaseHandler() {}
  /**
   * Retrieves or establishes a database connection.
   * @return A singleton instance of the database connection.
   * @throws SQLException If there is an error connecting to the database.
   */

  // Method to get the database connection
  public static Connection getConnection() throws SQLException {
    if (connection == null || connection.isClosed()) {
      // Establish a new connection if one doesn't exist or is closed
      connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }
    return connection;
  }

  /**
   * Closes the database connection if it is currently open.
   * @throws SQLException If a database access error occurs.
   */
  // Method to close the database connection
  public static void closeConnection() throws SQLException {
    if (connection != null && !connection.isClosed()) {
      connection.close();
    }

  }
}
