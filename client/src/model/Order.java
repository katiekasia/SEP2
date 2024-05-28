package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

public class Order implements Serializable
{
  private int orderID;
  private double orderPrice;
  private SimpleDate orderDate;
  private ArrayList<Ticket> tickets;
  private ArrayList<Snack> snacks;
  private OrderState orderState;

  public Order(int orderID){
    this.orderID = orderID;
    this.orderDate = null;
    this.orderPrice = 0;
    this.tickets = new ArrayList<>();
    this.snacks = new ArrayList<>();
    setOrderState(new PendingOrder(this));
  }

  public void setOrderState(OrderState orderState)
  {
    this.orderState = orderState;
    orderState.expire(this);

  }
  public void upgrade(Ticket ticket, double price){
    if (orderState instanceof PendingOrder)
    {
      if (ticket instanceof StandardTicket)
      {
        Ticket tick = getTicketForView(ticket.getTicketID());
        Seat seat = ticket.getSeat();
        Screening screening = ticket.getScreening();
        String id = ticket.getTicketID();
        tick.cancelTicket();
        Ticket temp = new VIPTicket(id, price, seat, screening);
        screening.getRoom().getSeatByID(seat.getID()).book(ticket);
        tickets.add(temp);
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
  public boolean isExpired(){
    return getOrderDate().isAfter(new SimpleDate(LocalDate.now()));
  }
  public String generateTicketID(){
    String id =String.valueOf(orderID + tickets.size() + Math.floor(Math.random() * 401));
    return id;
  }
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

  public OrderState getOrderState()
  {
    return orderState;
  }

  public Ticket getTicketForView(String ID){
    for (Ticket ticket : tickets){
      if (ticket.getTicketID().equals(ID)){
        return ticket;
      }
    }
    throw new RuntimeException("No such ticket found");
  }

  public Snack[] getSnacksFromOrder()
  {
    if (getSnacks() != null){
      Snack[] result = getSnacks().toArray(new Snack[0]);
      return result;
    }
    return null;
  }
  public Ticket[] getTicketsFromOrder()
  {
    if (getTickets() != null)
    {
      Ticket[] result = getTickets().toArray(new Ticket[0]);
      return result;
    }
    return null;
  }
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
  public void addSnack(Snack snack){
    if (orderState instanceof PendingOrder){
      snacks.add(snack);
      calculateOrderPrice();
    }else throw new IllegalStateException("Order has expired and cannot be cancelled or modified.");
  }
  public void removeSnack(Snack snack){
if (orderState instanceof PendingOrder){
  snacks.remove(snack);
  calculateOrderPrice();
}else throw new IllegalStateException("Order cannot be modified because it is: " + orderState.status());
  }
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
  public void addTicket(Ticket ticket){
    if (orderState instanceof PendingOrder){
      tickets.add(ticket);
      this.orderDate = tickets.get(0).getScreening().getDate();
      calculateOrderPrice();
    }else throw new IllegalStateException("Order has expired and cannot be cancelled or modified.");
  }
public void cancelOrder(){
    orderState.cancel(this);
}

//*******************************************************Getters and setters*********************************8
  public ArrayList<Ticket> getTickets()
  {
    return tickets;
  }


  public int getOrderID()
  {
    return orderID;
  }

  public ArrayList<Snack> getSnacks()
  {
    return snacks;
  }

  public double getOrderPrice()
  {
    return orderPrice;
  }

  public SimpleDate getOrderDate()
  {
    return orderDate;
  }

  public void setOrderDate(SimpleDate orderDate)
  {
    this.orderDate = orderDate;
  }

  public void setSnacks(ArrayList<Snack> snacks)
  {
    this.snacks = snacks;
  }

  public void setOrderID(int orderID)
  {
    this.orderID = orderID;
  }

  public void setOrderPrice(double orderPrice)
  {
    this.orderPrice = orderPrice;
  }
  public void setTickets(ArrayList<Ticket> tickets)
  {
    this.tickets = tickets;
  }
}
