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
  private OrderState orderState;

  public Order(int orderID){
    this.orderID = orderID;
    this.orderDate = null;
    this.orderPrice = 0;
    this.tickets = new ArrayList<>();
    this.snacks = new ArrayList<>();
    this.orderState = new PendingOrder(this);
  }

  public void setOrderState(OrderState orderState)
  {
    this.orderState = orderState;
  }

  public OrderState getOrderState()
  {
    return orderState;
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
}else throw new IllegalStateException("Order has expired and cannot be cancelled or modified.");
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
