package model;

import java.sql.SQLException;
import java.util.ArrayList;

public class AdminsList
{
  private ArrayList<User> admins;
  public AdminsList()
  {
    admins = new ArrayList<>();
  }
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

  public ArrayList<User> getAdmins()
  {
    return admins;
  }
}
