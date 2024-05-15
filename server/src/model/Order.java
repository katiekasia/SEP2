package model;

import java.io.Serializable;
import java.util.ArrayList;

public class Order implements Serializable
{
  private int orderID;
  private double orderPrice;
  private SimpleDate orderDate;
  private ArrayList<Ticket> tickets;
  private ArrayList<Snack> snacks;

  public Order(int orderID){
    this.orderID = orderID;
    this.orderDate = null;
    this.orderPrice = 0;
    this.tickets = new ArrayList<>();
    this.snacks = new ArrayList<>();
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
    snacks.add(snack);
    calculateOrderPrice();
  }
  public void removeSnack(Snack snack){
    snacks.remove(snack);
    calculateOrderPrice();
  }
  public void removeTicket(Ticket ticket){
    if (tickets.size() > 1)
    {
      tickets.remove(ticket);
      ticket.cancelTicket();
      calculateOrderPrice();
    }else {
      throw new IllegalStateException("client_model.Order cannot have 0 tickets");
    }
  }
  public void addTicket(Ticket ticket){
    tickets.add(ticket);
    this.orderDate = tickets.get(0).getScreening().getDate();
    calculateOrderPrice();
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
