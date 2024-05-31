package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;
/**
 * Represents a user in the  system.
 * @version 3.0   may 2024
 * @author Michal Barczuk, Kasia, Sandut, Catalina
 */
public class User implements Serializable
{
  private String username;
  private String fstName;
  private String lstName;
  private String phoneNumber;
  private String email;
  private String password;
  private ArrayList<Order> orders;
  /**
   *6 argument constructor
   * initialises a User object with the given parameters.
   * @param username The username of the user.
   * @param fstName The first name of the user.
   * @param lstName The last name of the user.
   * @param phone The phone number of the user.
   * @param email The email of the user.
   * @param password The password of the user.
   */
  public User(String username, String fstName, String lstName, String phone, String email, String password){
    this.username = username;
    this.email = email;
    this.fstName = fstName;
    this.lstName = lstName;
    this.phoneNumber = phone;
    this.password= password;
    orders = new ArrayList<>();
  }

  /**
   * Retrieves the password of the user.
   * @return The password of the user.
   */
  public String getPassword() {
    return password;
  }

  /**
   * Adds an order to the list of orders associated with the user.
   * @param order The order to add.
   */
  public void addOrder(Order order){
    orders.add(order);
  }

  /**
   * Retrieves the order with the specified ID from the list of orders associated with the user.
   * @param orderID The ID of the order to retrieve.
   * @return The order with the specified ID.
   * @throws IllegalArgumentException if no order with the specified ID is found.
   */
  public Order getOrderByID(int orderID){
    for (Order order : orders){
      if (order.getOrderID() == orderID){
        return order;
      }
    }
    throw new IllegalArgumentException("No order with that id found.");
  }

  /**
   * Retrieves all tickets associated with the user's orders.
   * @return The list of all tickets associated with the user.
   */
  public ArrayList<Ticket> getAllTickets(){
    ArrayList<Ticket> tickets = new ArrayList<>();
    for (Order order : orders){
      tickets.addAll(order.getTickets());
    }
    return tickets;
  }

  /**
   * Retrieves all orders associated with the user.
   * @return An array containing all orders associated with the user.
   */
  public Order[] getAllOrders(){
    return orders.toArray(new Order[0]);
  }

  /**
   * Retrieves the list of orders associated with the user.
   * @return The list of orders associated with the user.
   */
  public ArrayList<Order> getOrders() {
    return orders;
  }

  /**
   * Retrieves the email of the user.
   * @return The email of the user.
   */
  public String getEmail() {
    return email;
  }

  /**
   * Retrieves the first name of the user.
   * @return The first name of the user.
   */
  public String getFstName() {
    return fstName;
  }

  /**
   * Retrieves the last name of the user.
   * @return The last name of the user.
   */
  public String getLstName() {
    return lstName;
  }

  /**
   * Retrieves the phone number of the user.
   * @return The phone number of the user.
   */
  public String getPhoneNumber() {
    return phoneNumber;
  }

  /**
   * Retrieves the username of the user.
   * @return The username of the user.
   */
  public String getUsername() {
    return username;
  }

  /**
   * Sets the username of the user.
   * @param username The username to set.
   * @throws IllegalArgumentException if the username is "Admin".
   */
  public void setUsername(String username) {
    if (username.equals("Admin")){
      throw new IllegalArgumentException("Username not available.");
    }
    this.username = username;
  }

  /**
   * Generates a unique order ID.
   * @return A unique order ID.
   */
  public int generateOrderID(){
    Random random = new Random();
    int randomPart = random.nextInt(401);

    int id = Integer.parseInt(orders.size() + "" + randomPart);
    return id;
  }

  /**
   * Sets the email of the user.
   * @param email The email to set.
   */
  public void setEmail(String email) {
    this.email = email;
  }

  /**
   * Sets the first name of the user.
   * @param fstName The first name to set.
   */
  public void setFstName(String fstName) {
    this.fstName = fstName;
  }

  /**
   * Sets the last name of the user.
   * @param lstName The last name to set.
   */
  public void setLstName(String lstName) {
    this.lstName = lstName;
  }

  /**
   * Sets the list of orders associated with the user.
   * @param orders The list of orders to set.
   */
  public void setOrders(ArrayList<Order> orders)
  {
    this.orders = orders;
  }

  public void setPhoneNumber(String phoneNumber)
  {
    this.phoneNumber = phoneNumber;
  }

}
