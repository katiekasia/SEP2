package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;
/**
 *  Represents an order made by a customer for movie tickets and snacks.
 *
 * @version 3.0   may 2024
 * @author Michal Barczuk, Kasia, Sandut, Catalina
 */
public class Order implements Serializable
{
  private int orderID;
  private double orderPrice;
  private SimpleDate orderDate;
  private ArrayList<Ticket> tickets;
  private ArrayList<Snack> snacks;
  private OrderState orderState;
  /**
   * 1 argument constructor
   * initialises a new Order object with the specified order ID.
   *
   * @param orderID The ID of the order
   */
  public Order(int orderID){
    this.orderID = orderID;
    this.orderDate = null;
    this.orderPrice = 0;
    this.tickets = new ArrayList<>();
    this.snacks = new ArrayList<>();
    this.orderState = new PendingOrder(this);
  }
  /**
   * Sets the state of the order and expires the order accordingly.
   *
   * @param orderState The state to set for the order
   */
  public void setOrderState(OrderState orderState)
  {
    this.orderState = orderState;
    orderState.expire(this);

  }
  /**
   * Upgrades a ticket in the order to vip ticket with the vip  price.
   *
   * @param ticket The ticket to be upgraded
   * @param price The price of the upgraded ticket
   * @throws IllegalStateException If the order state is not pending or if the ticket cannot be upgraded
   */
  public void upgrade(Ticket ticket, double price){
    // Check if the order state is pending
    if (orderState instanceof PendingOrder)
    {
      if (ticket instanceof StandardTicket)
      {
        // Get the ticket to be upgraded
        Ticket tick = getTicketForView(ticket.getTicketID());
        Seat seat = ticket.getSeat();
        Screening screening = ticket.getScreening();
        String id = ticket.getTicketID();
        tick.cancelTicket();
        Ticket temp = new VIPTicket(id, price, seat, screening);
        screening.getRoom().getSeatByID(seat.getID()).book(ticket);
        tickets.add(temp);
        // Add the new ticket to the order and remove the old one
        tickets.remove(tick);
      }
      else
      {
        throw new IllegalStateException("Ticket cannot be upgraded.");
      }
    }else {
      throw new IllegalStateException("Order cannot be modified because it is: " + orderState.status());
    }
  }
  /**
   * Checks if the order is expired based on its order date.
   *
   * @return True if the order is expired, false otherwise
   */
  public boolean isExpired(){
    return getOrderDate().isAfter(new SimpleDate(LocalDate.now()));
  }
  /**
   * method to generate a unique ticket ID for the order.
   *
   * @return A unique ticket ID as a string
   */
    public String generateTicketID(){
      Random random = new Random();
      int randomPart = random.nextInt(7001);
      // Concatenate the size of the tickets list and the random number to form the ticket ID
      String  id = tickets.size() + "" + randomPart;
      return id;
  }
  /**
   * Downgrades a ticket in the order from VIP to standard, if the order is in the pending state.
   *
   * @param ticket The ticket to be downgraded
   * @param price The new price for the ticket after downgrade
   * @throws IllegalStateException if the order state is not pending or if the ticket cannot be downgraded
   */
  public void downgrade(Ticket ticket, double price){
    if (orderState instanceof PendingOrder)
    {
      if (ticket instanceof VIPTicket)
      {
        Ticket tick = getTicketForView(ticket.getTicketID());
    Seat seat = ticket.getSeat();
    Screening screening = ticket.getScreening();
    String id = ticket.getTicketID();
    tick.cancelTicket();
    Ticket temp = new StandardTicket(id,price,seat,screening);
    screening.getRoom().getSeatByID(seat.getID()).book(ticket);
    tickets.remove(tick);
    tickets.add(temp);
      }
      else
      {
        throw new IllegalStateException("Ticket cannot be downgraded.");
      }
    }else {
      throw new IllegalStateException("Order cannot be modified because it is: " + orderState.status());
    }
  }
  /**
   * Retrieves the current state of the order.
   *
   * @return The current state of the order
   */
  public OrderState getOrderState()
  {
    return orderState;
  }
  /**
   * Retrieves the ticket with the specified ID.
   *
   * @param ID The ID of the ticket to retrieve
   * @return The ticket with the specified ID
   * @throws RuntimeException if no ticket with the specified ID is found
   */
  public Ticket getTicketForView(String ID){
    for (Ticket ticket : tickets){
      if (ticket.getTicketID().equals(ID)){
        return ticket;
      }
    }
    throw new RuntimeException("No such ticket found");
  }
  /**
   * Retrieves an array of snacks associated with the order.
   *
   * @return An array containing all snacks associated with the order, or null if there are no snacks
   */
  public Snack[] getSnacksFromOrder()
  {
    if (getSnacks() != null){
      Snack[] result = getSnacks().toArray(new Snack[0]);
      return result;
    }
    return null;
  }
  /**
   * Retrieves an array of tickets associated with the order.
   *
   * @return An array containing all tickets associated with the order, or null if there are no tickets
   */
  public Ticket[] getTicketsFromOrder()
  {
    if (getTickets() != null)
    {
      Ticket[] result = getTickets().toArray(new Ticket[0]);
      return result;
    }
    return null;
  }
  /**
   * Calculates the total price of the order by summing the prices of all tickets and snacks associated with it.
   * This total price is then set as the order's current price.
   */
  public void calculateOrderPrice()
  {
    double price = 0;
    for (Ticket ticket :tickets){
      price += ticket.getTicketPrice();
    }
    for (Snack snack:snacks){
      price += snack.getPrice();
    }
    this.orderPrice = price;
  }
  /**
   * Adds a snack to the order if it's in the pending state, recalculates the order price,
   * and updates it accordingly. Throws an IllegalStateException if the order is not in the pending state.
   *
   * @param snack The snack to be added to the order.
   * @throws IllegalStateException If the order is not in the pending state.
   */
  public void addSnack(Snack snack){
    if (orderState instanceof PendingOrder){
      snacks.add(snack);
      calculateOrderPrice();
    }else throw new IllegalStateException("Order has expired and cannot be cancelled or modified.");
  }
  /**
   * Removes a snack from the order if it's in the pending state, recalculates the order price,
   * and updates it accordingly. Throws an IllegalStateException if the order is not in the pending state.
   *
   * @param snack The snack to be removed from the order.
   * @throws IllegalStateException If the order is not in the pending state.
   */
  public void removeSnack(Snack snack){
if (orderState instanceof PendingOrder){
  snacks.remove(snack);
  calculateOrderPrice();
}else throw new IllegalStateException("Order cannot be modified because it is: " + orderState.status());
  }
  /**
   * Removes a ticket from the order if it's in the pending state, cancels the ticket, recalculates
   * the order price, and updates it accordingly. Throws an IllegalStateException if the order
   * is not in the pending state or if the order has only one ticket left.
   *
   * @param ticket The ticket to be removed from the order.
   * @throws IllegalStateException If the order is not in the pending state or if the order has only one ticket left.
   */
  public void removeTicket(Ticket ticket){
    if (orderState instanceof PendingOrder)
    {
      if (tickets.size() > 1)
      {
        tickets.remove(ticket);
        ticket.cancelTicket();
        calculateOrderPrice();
      }
      else
      {
        throw new IllegalStateException("Order cannot have 0 tickets");
      }
    }else throw new IllegalStateException("Order has expired and cannot be cancelled or modified.");
  }
  /**
   * Adds a ticket to the order if it's in the pending state, sets the order date based on the
   * screening date of the first ticket added, recalculates the order price, and updates it accordingly.
   * Throws an IllegalStateException if the order is not in the pending state.
   *
   * @param ticket The ticket to be added to the order.
   * @throws IllegalStateException If the order is not in the pending state.
   */
  public void addTicket(Ticket ticket){
    if (orderState instanceof PendingOrder){
      tickets.add(ticket);
      this.orderDate = tickets.get(0).getScreening().getDate();
      calculateOrderPrice();
    }else throw new IllegalStateException("Order has expired and cannot be cancelled or modified.");
  }
  /**
   * Cancels the order by invoking the cancel method of the current order state.
   *
   * @throws IllegalStateException If the order cannot be cancelled due to its current state.
   */
public void cancelOrder(){
    orderState.cancel(this);
}

//*******************************************************Getters and setters*********************************8
  /**
   * Retrieves the list of tickets associated with this order.
   *
   * @return The list of tickets.
   */
  public ArrayList<Ticket> getTickets()
  {
    return tickets;
  }

  /**
   * retrieves order id-getter
   *
   * @return order id
   */
  public int getOrderID()
  {
    return orderID;
  }
  /**
   * Retrieves the list of snacks associated with this order.
   *
   * @return The list of snacks.
   */
  public ArrayList<Snack> getSnacks()
  {
    return snacks;
  }
  /**
   * Retrieves price for order.
   *
   * @return price of an order.
   */
  public double getOrderPrice()
  {
    return orderPrice;
  }
  /**
   * Retrieves date for the order
   *
   * @return Order date
   */
  public SimpleDate getOrderDate()
  {
    return orderDate;
  }
  /**
   * Sets the date of the order.
   *
   * @param orderDate The date of the order to be set.
   */
  public void setOrderDate(SimpleDate orderDate)
  {
    this.orderDate = orderDate;
  }
  /**
   * Sets the list of snacks for the order.
   *
   * @param snacks The list of snacks to be set for the order.
   */
  public void setSnacks(ArrayList<Snack> snacks)
  {
    this.snacks = snacks;
  }
  /**
   * Sets id for the order.
   *
   * @param orderID  the id to be set for the order
   */
  public void setOrderID(int orderID)
  {
    this.orderID = orderID;
  }

  public void setOrderPrice(double orderPrice)
  {
    this.orderPrice = orderPrice;
  }
  /**
   * Sets the list of tickets for the order.
   *
   * @param tickets The list of snacks to be set for the order.
   */
  public void setTickets(ArrayList<Ticket> tickets)
  {
    this.tickets = tickets;
  }
}
