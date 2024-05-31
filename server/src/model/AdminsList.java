package model;

import java.sql.SQLException;
import java.util.ArrayList;
/**
 *Class for storing the list of Admins
 * @version 3.0   may 2024
 * @author Michal Barczuk, Kasia, Sandut, Catalina
 */
public class AdminsList
{
  private ArrayList<User> admins;
  public AdminsList()
  {
    admins = new ArrayList<>();
  }
  /**
   * initialises a list of admins based on the provided list.
   *
   * @param users The list of users to initialize the admins
   */
  public AdminsList(ArrayList<User> users){
    this.admins = users;
  }
  public User getByUsername(String username){
    for (User admin : admins){
      if (admin.getUsername().equals(username)){
        return admin;
      }
    }
    return null;
  }
  /**
   * Logs in an admin with the provided credentials.
   *
   * @param username The username .
   * @param password The password
   * @return The user object representing the logged-in admin
   * @throws IllegalArgumentException If no user with matching credentials is found.
   */
  public User logIn(String username, String password){
    for (User admin : admins)
    {
      if (admin.getUsername().equals(username) && admin.getPassword()
          .equals(password))
      {
        return admin;
      }
    }
    throw new IllegalArgumentException("No user with matching credentials found.");
  }
  /**
   * Retrieves the list of adminis.
   *
   * @return The list of adminis.
   */
  public ArrayList<User> getAdmins()
  {
    return admins;
  }
}
