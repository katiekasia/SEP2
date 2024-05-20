package model;

import java.sql.SQLException;
import java.util.ArrayList;

public class UsersList
{
  private ArrayList<User> users;
  public UsersList() throws SQLException{
    users = DataBaseHandler.getAllCustomers();
  }
  public UsersList(ArrayList<User> users){
    this.users = users;
  }
  public User getByUsername(String username){
    for (User user : users){
      if (user.getUsername().equals(username)){
        return user;
      }
    }
    return null;
  }

  public ArrayList<User> getUsers()
  {
    return users;
  }
}
