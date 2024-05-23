package model;

import java.io.Serializable;
import java.util.ArrayList;

public class User implements Serializable
{
  private String username;
  private String fstName;
  private String lstName;
  private String phoneNumber;
  private String email;
  private String password;
  private ArrayList<Order> orders;
  public User(String username, String fstName, String lstName, String phone, String email, String password){
    this.username = username;
    this.email = email;
    this.fstName = fstName;
    this.lstName = lstName;
    this.phoneNumber = phone;
    this.password= password;
    orders = new ArrayList<>();
  }
  public String getPassword()
  {
    return password;
  }
  //Adds an order to the list
  public void addOrder(Order order){
    orders.add(order);
  }
  public Order getOrderByID(int orderID){
    for (Order order : orders){
      if (order.getOrderID() == orderID){
        return order;
      }
    }
    throw new IllegalArgumentException("No order with that id found.");
  }

  public ArrayList<Ticket> getAllTickets(){
    ArrayList<Ticket> tickets = new ArrayList<>();
    for (int i = 0; i < getOrders().size(); i++)
    {
      for (Ticket ticket : getOrders().get(i).getTickets())
      {
        if (ticket != null)
        {
          tickets.add(ticket);
        }
      }
    }
    return tickets;
  }

  public Order[] getAllOrders()
  {
    if (getOrders() != null){
      Order[] result = getOrders().toArray(new Order[0]);
      return result;
    }
    return null;
  }
//  public void cancelOrder(client_model.Order order){
//    orders.remove(order);
//  }
//  public void createOrder(){
//    client_model.Order order = new client_model.Order(orders.size() + 1);
//    orders.add(order);
//  }

  //***********************************************Getters and setters**************************************

  public ArrayList<Order> getOrders()
  {
    return orders;
  }

  public String getEmail()
  {
    return email;
  }

  public String getFstName()
  {
    return fstName;
  }

  public String getLstName()
  {
    return lstName;
  }

  public String getPhoneNumber()
  {
    return phoneNumber;
  }

  public String getUsername()
  {
    return username;
  }

  public void setUsername(String username)
  {
    if (username.equals(Admin.USERNAME)){
      throw new IllegalArgumentException("Username not available.");
    }
    this.username = username;
  }

  public void setEmail(String email)
  {
    this.email = email;
  }

  public void setFstName(String fstName)
  {
    this.fstName = fstName;
  }

  public void setLstName(String lstName)
  {
    this.lstName = lstName;
  }

  public void setOrders(ArrayList<Order> orders)
  {
    this.orders = orders;
  }

  public void setPhoneNumber(String phoneNumber)
  {
    this.phoneNumber = phoneNumber;
  }

}
