package model;

import java.sql.SQLException;
import java.util.ArrayList;

public class UsersList
{
  private ArrayList<User> users;
  public UsersList() throws SQLException{
    users = new ArrayList<>();
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
  public boolean isRegistered(User user){
    for (User customer : users){
      if (user.getUsername().equals(customer.getUsername())){
        return true;
      }
    }
    return false;
  }
  public User logIn(String username, String password){
    for (User user : users)
    {

      if (user.getUsername().equals(username) && user.getPassword()
          .equals(password))
      {
        return user;
      }
    }
    throw new IllegalArgumentException("No user with matching credentials found.");
  }
  public ArrayList<Order> getOrdersForUser(String username) {
    return getByUsername(username).getOrders();
  }

  public ArrayList<User> getUsers()
  {
    return users;
  }
}
