package model;

import java.io.Serializable;

public class PendingOrder extends OrderState implements Serializable
{

  private boolean expired;
  public PendingOrder(Order order){
    expired = false;
     expire(order);
  }
  @Override public void expire(Order order)
  {
    if (!expired){
      if (order.isExpired()){
        expired = true;
        order.setOrderState(new ExpiredOrder(order));
      }
    }
  }

  @Override public void cancel(Order order)
  {
    order.setOrderState(new CancelledOrder(order));
    for (Ticket ticket : order.getTickets()){
      ticket.cancelTicket();
    }
  }

  @Override public String status()
  {
    return "Pending";
  }
}
