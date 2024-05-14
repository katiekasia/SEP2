package model;

import model.Order;

import java.util.ArrayList;

public class User
{
  private String username;
  private String fstName;
  private String lstName;
  private String phoneNumber;
  private String email;
  private ArrayList<Order> orders;
  public User(String username, String fstName, String lstName, String phone, String email){
    this.username = username;
    this.email = email;
    this.fstName = fstName;
    this.lstName = lstName;
    this.phoneNumber = phone;
    orders = new ArrayList<>();
  }
  //Adds an order to the list
  public void addOrder(Order order){
    orders.add(order);
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
